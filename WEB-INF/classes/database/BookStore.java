/**
	 Access to bookStore database.
	 @version 2014.5.2
	 @author Yang Zheng @SJTU SE 12
*/

package database;

import java.sql.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;

public class BookStore 
{
	private Connection conn;

	public BookStore(Path storeConfig, Path dbConfig, Path initConfig) throws IOException
	{
		try
			{
				conn = Database.getConnection(dbConfig);
				Database.initialize(initConfig, conn);
				Properties props = new Properties();
				InputStream in = Files.newInputStream(storeConfig);
				props.load(in);
				
				String admin = props.getProperty("bookStore.admin");
				String password = props.getProperty("bookStore.password");
				String tele = props.getProperty("bookStore.telephone");
				String name = props.getProperty("bookStore.name");
				addUser(new User(admin, name, tele, password));
			}
		catch (SQLException ex)
			{
				throw new IOException();
			}
	}
	
	public boolean addBook(Book book) throws SQLException
	{
		try
			{
				final String checkQuery =
					"SELECT ISBN FROM book WHERE ISBN = ?";
				PreparedStatement checkStat = conn.prepareStatement(checkQuery);
				checkStat.setString(1, book.isbn);
				if (checkStat.executeQuery().next())
					{
						final String addUpdate =
							"UPDATE book SET number = number + ? " +
							"WHERE ISBN = ?";
						PreparedStatement addStat = conn.prepareStatement(addUpdate);
						addStat.setInt(1, book.number);
						addStat.setString(2, book.isbn);
						if (addStat.executeUpdate() > 0)
							return true;
						else
							return false;
					}
				else
					{
						final String insertUpdate =
							"INSERT INTO book VALUES(?, ?, ?, ?, ?, ?)";
						PreparedStatement insertStat = conn.prepareStatement(insertUpdate);
						insertStat.setString(1, book.isbn);
						insertStat.setString(2, book.name);
						insertStat.setString(3, book.author);
						insertStat.setDouble(4, book.price);
						insertStat.setString(5, book.publisher);
						insertStat.setInt(6, book.number);
						if (insertStat.executeUpdate() > 0)
							return true;
						else
							return false;
					}
			}
		catch (SQLException ex)
			{
				throw ex;
			}
	}
		
	public boolean checkUser(User user) throws SQLException
	{
		try
			{
				final String query =
					"SELECT ID, password FROM user WHERE ID = ? " +
					"AND password = ?";
				PreparedStatement stat = conn.prepareStatement(query);
				stat.setString(1, user.id);
				stat.setString(2, user.pw);
				if (stat.executeQuery().next())
					return true;
				else
					return false;
			}
		catch (SQLException ex)
			{
				throw ex;
			}
	}
	
	public boolean addUser(User user) throws SQLException
	{
		try
			{
				final String query =
					"SELECT ID FROM user WHERE ID = ?";
				PreparedStatement stat = conn.prepareStatement(query);
				stat.setString(1, user.id);
				if (stat.executeQuery().next())
					return false;
				
				final String update =
					"INSERT INTO user VALUES(?, ?, ?, ?)";
				stat = conn.prepareStatement(update);
				stat.setString(1, user.id);
				stat.setString(2, user.name);
				stat.setString(3, user.tele);
				stat.setString(4, user.pw);
				if (stat.executeUpdate() > 0)
					return true;
				else
					return false;
			}
		catch (SQLException ex)
			{
				throw ex;
			}
	}
	
	public boolean addOrder(Order order) throws SQLException
	{
		try
			{
				final String existUserQuery =
					"SELECT ID FROM user WHERE ID = ?";
				PreparedStatement stat = conn.prepareStatement(existUserQuery);
				stat.setString(1, order.id);
				if (!stat.executeQuery().next())
					return false;
				final String existBookQuery =
					"SELECT number FROM book WHERE ISBN = ?";
				stat = conn.prepareStatement(existBookQuery);
				stat.setString(1, order.isbn);
				ResultSet rs = stat.executeQuery();
				if (rs.next())
					{
						int count = rs.getInt(1);
						if (count < order.count)
							order.count = count;
						if (order.count <= 0)
							return false;
					}
				else
					return false;

				rs = conn.createStatement().executeQuery("SELECT MAX(OID) AS max FROM orders");
				int oid = 0;
				if (rs.next())
					oid = rs.getInt(1) + 1;
				final String insertUpdate =
					"INSERT INTO orders VALUES(?, ?, ?, ?)";
				stat = conn.prepareStatement(insertUpdate);
				stat.setInt(1, oid);
				stat.setString(2, order.isbn);
				stat.setString(3, order.id);
				stat.setInt(4, order.count);
				if (stat.executeUpdate() <= 0)
					return false;
				final String update =
					"UPDATE book SET number = number - ? " +
					"WHERE ISBN = ?";
				stat = conn.prepareStatement(update);
				stat.setInt(1, order.count);
				stat.setString(2, order.isbn);
				if (stat.executeUpdate() > 0)
					return true;
				else
					return false;
			}
		catch (SQLException ex)
			{
				throw ex;
			}
	}
	
	public List<Book> getBooks() throws SQLException
	{
		try
			{
				ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM book");
				List<Book> books = new ArrayList<Book>();
				while (rs.next())
					{
						Book tmp = new Book(rs.getString("ISBN"), rs.getString("name"),
																rs.getString("author"), rs.getDouble("price"),
																rs.getString("publisher"), rs.getInt("number"));
						books.add(tmp);
					}
				return books;
			}
		catch (SQLException ex)
			{
				throw ex;
			}
	}
	public User getUser(String id) throws SQLException
	{
		try
			{
				final String query =
					"SELECT * FROM user " +
					"WHERE ID = ?";
				PreparedStatement stat = conn.prepareStatement(query);
				stat.setString(1, id);
				ResultSet rs = stat.executeQuery();
				User user = null;
				if (rs.next())
					{
						user = new User(rs.getString("ID"), rs.getString("name"),
														rs.getString("telephone"), rs.getString("password"));
					}
				return user;
			}
		catch (SQLException ex)
			{
				throw ex;
			}

	}
	public List<Order> getOrders(String id) throws SQLException
	{
		try
			{
				final String query =
					"SELECT * FROM book NATURAL JOIN orders " +
					"WHERE ID = ?";
				PreparedStatement stat = conn.prepareStatement(query);
				stat.setString(1, id);
				ResultSet rs = stat.executeQuery();
				List<Order> orders = new ArrayList<Order>();
				while (rs.next())
					{
						Order tmp = new Order(rs.getString("ISBN"), rs.getString("ID"),
																	rs.getInt("count"), rs.getString("name"),
																	rs.getString("author"), rs.getString("publisher"),
																	rs.getDouble("price"));
						orders.add(tmp);
					}
				return orders;
			}
		catch (SQLException ex)
			{
				throw ex;
			}

	}
}

