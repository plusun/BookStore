package servlet;

import java.nio.file.*;
import database.*;
import java.io.*;

public class Common
{
	private static final String dbConfig = "/config/database.properties";
	private static final String storeConfig = "/config/bookStore.properties";
	private static final String initConfig = "/config/initialization.sql";
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
	public static BookStore getBookStore(String prefix) throws IOException
	{
		return new BookStore(DBCPath(prefix), BSCPath(prefix), initCPath(prefix));
	}		
}
