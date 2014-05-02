package servlet;

import database.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;

public class LoadIndex extends HttpServlet
{
	private String prefix;
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
		BookStore store = Common.getBookStore(prefix);
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
