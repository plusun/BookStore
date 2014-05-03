package servlet;

import database.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class LogIn extends HttpServlet
{
	private String prefix;
	public void init() throws ServletException
	{
		prefix = getServletContext().getRealPath("/");
	}
	public void doPost(HttpServletRequest request,
										 HttpServletResponse response)
		throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		BookStore store = Common.getBookStore(prefix);
		User user = new User(request.getParameter("ID"), request.getParameter("pw"));
		String address;
		try
			{
				if (store.checkUser(user))
					{
						user = store.getUser(user.id);
						bean.User u = new bean.User(user.id, user.name, user.tele, user.pw);
						session.setAttribute("user", u);
						address = "LoadIndex";
						response.sendRedirect(address);
						return;
					}
				else
					{
						bean.Status stat =
							new bean.Status(false,
															"Authentication error, maybe you input something wrong...",
															"login.html");
						session.invalidate();
						request.setAttribute("status", stat);
						address = "/redirect.jsp";
					}
			}
		catch (SQLException ex)
			{
				throw new IOException();
			}
		RequestDispatcher dispatcher =
			request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
	public void doGet(HttpServletRequest request,
										 HttpServletResponse response)
		throws ServletException, IOException
	{
		doPost(request, response);
	}
}
