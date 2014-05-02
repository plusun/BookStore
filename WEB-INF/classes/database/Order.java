package database;

public class Order
{
	public String isbn, id, name, author, publisher;
	public int count;
	public double price;
	public	Order(String isbn, String id, int count)
	{
		this.isbn = isbn;
		this.id = id;
		this.count = count;
		name = null;
		author = null;
		publisher = null;
		price = 0;
	}
	public Order(String isbn, String id, int count,
							 String name, String author, String publisher,
							 double price)
	{
		this(isbn, id, count);
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
	}
}
