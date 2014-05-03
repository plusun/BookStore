package servlet;

import database.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.*;

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
		bean.User u = session != null ? (bean.User)session.getAttribute("user") : null;
		User user = u == null ? null : new User(u.getId(), u.getName(), u.getTele(), u.getPw());
		String address;
		BookStore store = Common.getBookStore(prefix);
		try
			{
				if (user == null || !store.checkUser(user))
					{
						address = "/WEB-INF/JSP/welcome.jsp";
					}
				else
					{
						List<Book> books = store.getBooks();
						List<Order> orders = store.getOrders(user.id);
						bean.Userinfo info = new bean.Userinfo(books, orders);
						request.setAttribute("info", info);
						address = "/WEB-INF/JSP/store.jsp";
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
	public void doPost(HttpServletRequest request,
										HttpServletResponse response)
		throws ServletException, IOException
	{
		doGet(request, response);
	}
}
