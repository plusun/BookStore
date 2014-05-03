package bean;

public class Status
{
	private boolean status;
	private String details;
	private String address;
	public Status()
	{
		status = false;
		details = "Oops...";
		address = "/";
	}
	public Status(boolean status, String details, String address)
	{
		this.status = status;
		this.details = details;
		this.address = address;
	}
	public String getStatus()
	{
		if (status)
			return ":)";
		else
			return ":(";
	}
	public String getDetails()
	{
		return details;
	}
	public String getAddress()
	{
		return address;
	}
}
