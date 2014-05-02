/**
	 Access to database.
	 @version 2014.5.1
	 @author Yang Zheng @SJTU SE 12
 */

package database;

import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.*;

public class Database
{
  public static Connection getConnection(Path configFile) throws SQLException, IOException
  {
    Properties props = new Properties();
    InputStream in = Files.newInputStream(configFile);
    props.load(in);
      
    String drivers = props.getProperty("jdbc.drivers");
    if (drivers != null)
      System.setProperty("jdbc.drivers", drivers);
    String url = props.getProperty("jdbc.url");
    String username = props.getProperty("jdbc.username");
    String password = props.getProperty("jdbc.password");

    return DriverManager.getConnection(url, username, password);
  }
  
  public static void initialize(Path initialFile, Connection conn) throws IOException
  {
		execSQL(conn, initialFile);
  }
  public static void execSQL(Connection conn, Path file) throws IOException
  {
    try
      {
        Scanner in = new Scanner(file);

        Statement stat = conn.createStatement();

        while (in.hasNextLine())
          {
            String line = in.nextLine();
            if (line.equalsIgnoreCase("EXIT"))
              return;
            if (line.trim().endsWith(";"))
              {
                line = line.trim();
                line = line.substring(0, line.length() - 1);
              }
            boolean isResult = stat.execute(line);
            if (isResult)
              {
                ResultSet rs = stat.getResultSet();
                showResultSet(rs);
              }
            else
              {
                int updateCount = stat.getUpdateCount();
                System.out.println(updateCount + " rows updated");
              }
          }
      }
    catch (SQLException ex)
      {
        for (Throwable t : ex)
          t.printStackTrace();
      }
  }
  public static void showResultSet(ResultSet result) throws SQLException
  {
    ResultSetMetaData metaData = result.getMetaData();
    int columnCount = metaData.getColumnCount();

    for (int i = 1; i <= columnCount; i++)
      {
        if (i > 1)
          System.out.print(", ");
        System.out.print(metaData.getColumnLabel(i));
      }
    System.out.println();

    while (result.next())
      {
        for (int i = 1; i <= columnCount; i++)
          {
            if (i > 1)
              System.out.print(", ");
            System.out.print(result.getString(i));
          }
        System.out.println();
      }
  }
}
