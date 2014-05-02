package servlet;

import database.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;

public class LoadIndex extends HttpServlet
{
	private String prefix;
	private static final String dbconfig = "/config/database.properties";
	private static final String storeconfig = "/config/bookStore.properties";
	private static final String initconfig = "/config/initialization.sql";
	public void init() throws ServletException
	{
		prefix = getServletContext().getRealPath("/");
	}
	public void doGet(HttpServletRequest request,
										HttpServletResponse response)
		throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		User user = session != null ? (User)session.getAttribute("user") : null;
		String address;
		BookStore store = new BookStore(Paths.get(prefix + dbconfig),
																		Paths.get(prefix + storeconfig),
																		Paths.get(prefix + initconfig));
		if (user == null || !store.checkUser(user))
			{
				address = "/WEB-INF/JSP/welcome.jsp";
			}
		else
			{
				address = "/WEB-INF/JSP/store.jsp";
			}
		RequestDispatcher dispatcher =
			request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}
