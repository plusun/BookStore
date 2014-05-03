package servlet;

import database.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class AddBook extends HttpServlet
{
	public String prefix;
	public void init() throws ServletException
	{
		prefix = getServletContext().getRealPath("/");
	}
	public void doPost(HttpServletRequest request,
										 HttpServletResponse response)
		throws ServletException, IOException
	{
		BookStore store = Common.getBookStore(prefix);
		String isbn = request.getParameter("ISBN");
		String name = request.getParameter("name");
		String author = request.getParameter("author");
		double price = Double.parseDouble(request.getParameter("price"));
		String publisher = request.getParameter("publisher");
		int number = Integer.parseInt(request.getParameter("number"));
		bean.Status stat;
		try
			{
				if (store == null || isbn == null || name == null || author == null ||
						price <= 0 || publisher == null || number <= 0 ||
						!store.addBook(new Book(isbn, name, author, price, publisher, number)))
					{
						stat = new bean.Status(false, "Wrong information!", "LoadIndex");
					}
				else
					{
						stat = new bean.Status(true, "Success!", "LoadIndex");
					}
			}
		catch (SQLException ex)
			{
				throw new IOException();
			}
		request.setAttribute("status", stat);
		RequestDispatcher dispatcher =
			request.getRequestDispatcher("/redirect.jsp");
		dispatcher.forward(request, response);
	}
	public void doGet(HttpServletRequest request,
										HttpServletResponse response)
		throws ServletException, IOException
	{
		doPost(request, response);
	}
}
