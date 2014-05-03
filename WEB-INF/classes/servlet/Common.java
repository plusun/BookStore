package servlet;

import java.nio.file.*;
import database.*;
import java.io.*;
import java.util.*;

public class Common
{
	private static final String dbConfig = "config/database.properties";
	private static final String storeConfig = "config/bookStore.properties";
	private static final String initConfig = "config/initialization.sql";
	public static Path DBCPath(String prefix)
	{
		return Paths.get(prefix + dbConfig);
	}
	public static Path BSCPath(String prefix)
	{
		return Paths.get(prefix + storeConfig);
	}
	public static Path initCPath(String prefix)
	{
		return Paths.get(prefix + initConfig);
	}
	public static String getAdministrator(String prefix)
	{
		try
			{
				Properties props = new Properties();
				InputStream in = Files.newInputStream(BSCPath(prefix));
				props.load(in);
				return props.getProperty("bookStore.admin");
			}
		catch (IOException ex)
			{
				return null;
			}
	}
	public static BookStore getBookStore(String prefix) throws IOException
	{
		return new BookStore(BSCPath(prefix), DBCPath(prefix), initCPath(prefix));
	}		
}
