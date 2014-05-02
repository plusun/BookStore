package servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class LogIn extends HttpServlet
{
	private String prefix;
	public void init() throws ServletException
	{
		prefix = getServletContext().getRealPath("/");
	}
	public void doPost(HttpServletRequest request,
										 HttpServletRequest response)
		throws ServletException, IOException
	{
		
	}
}
