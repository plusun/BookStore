package bean;

import database.*;
import java.util.*;

public class Userinfo
{
	private List<Book> books;
	private List<Order> orders;
	public Userinfo()
	{
		books = new ArrayList<Book>();
		orders = new ArrayList<Order>();
	}
	public Userinfo(List<Book> books, List<Order> orders)
	{
		this.books = new ArrayList<Book>(books);
		this.orders = new ArrayList<Order>(orders);
	}
	public List<Book> getBooks()
	{
		return books;
	}
	public List<Order> getOrders()
	{
		return orders;
	}
}
