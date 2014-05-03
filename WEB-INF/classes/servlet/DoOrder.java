package servlet;

import database.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class DoOrder extends HttpServlet
{
	public String prefix;
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
		String isbn = request.getParameter("ISBN");
		int number = Integer.parseInt(request.getParameter("number"));
		bean.User user = (bean.User)session.getAttribute("user");
		bean.Status stat;
		try
			{
				if (store == null || isbn == null || user == null || number <= 0
						|| !store.addOrder(new Order(isbn, user.getId(), number)))
					{
						stat = new bean.Status(false, "Wrong ISBN or number!", "LoadIndex");
					}
				else
					{
						stat = new bean.Status(true, "Success!", "LoadIndex");
					}
			}
		catch (SQLException ex)
			{
				throw new IOException();
			}
		request.setAttribute("status", stat);
		RequestDispatcher dispatcher =
			request.getRequestDispatcher("/redirect.jsp");
		dispatcher.forward(request, response);
	}
	public void doGet(HttpServletRequest request,
										HttpServletResponse response)
		throws ServletException, IOException
	{
		doPost(request, response);
	}
}
