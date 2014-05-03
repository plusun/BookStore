package bean;

public class User
{
	private String id, name, tele, pw;
	public User()
	{
		id = null;
		name = null;
		tele = null;
		pw = null;
	}
	public User(String id, String name, String tele, String pw)
	{
		this.id = id;
		this.name = name;
		this.tele = tele;
		this.pw = pw;
	}
	public String getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	public String getTele()
	{
		return tele;
	}
	public String getPw()
	{
		return pw;
	}
}
