package test;

import java.nio.file.*;
import java.sql.*;
import java.io.*;
import java.util.*;

/**
   Test for connction to MySQL.
   Copied from Core JAVA, Volume II, 9th Edition
   @version 1.0 2014.5.1
   @author Yang Zheng
*/

public class TestDB
{
  public static void main(String[] args) throws IOException
  {
    try
      {
        runTest();
      }
    catch (SQLException ex)
      {
        for (Throwable t : ex)
          t.printStackTrace();
      }
  }
  public static void runTest() throws SQLException, IOException
  {
    try (Connection conn = database.Database.getConnection())
          {
            Statement stat = conn.createStatement();
            stat.executeUpdate("CREATE TABLE Greetings (Message VARCHAR(20))");
            stat.executeUpdate("INSERT INTO Greetings VALUES ('Hello, world!')");
            try (ResultSet result = stat.executeQuery("SELECT * FROM Greetings"))
                  {					
                    if (result.next())
                      System.out.println(result.getString("Message"));
                  }
            stat.executeUpdate("DROP TABLE Greetings");
            database.Database.initialize(conn);
          }
  }
}
