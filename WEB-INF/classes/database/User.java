package database;

public class User
{
	public String id, name, tele, pw;
	public User(String id, String pw)
	{
		this.id = id;
		this.pw = pw;
		this.name = null;
		this.tele = null;
	}
	public User(String id, String name, String tele, String pw)
	{
		this(id, pw);
		this.name = name;
		this.tele = tele;
	}
}


