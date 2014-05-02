package database;

public class Book
{
	public String isbn, name, author, publisher;
	public double price;
	public int number;
	public Book(String isbn, String name, String author, double price,
							String publisher, int number)
	{
		this.isbn = isbn;
		this.name = name;
		this.author = author;
		this.price = price;
		this.publisher = publisher;
		this.number = number;
	}
}
