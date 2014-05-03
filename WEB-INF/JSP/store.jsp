<!DOCTYPE HTML>
<html>
  <head>
    <meta content = "text/html"; charset = "UTF-8"></meta>
    <title>Book Store</title>
  </head>
  <body>
    <jsp:useBean id = "info" type = "bean.Userinfo" scope = "request" />
    <jsp:useBean id = "user" type = "bean.User" scope = "session" />
    <h1>Welcome!</h1>
    <p>
      Hello, <jsp:getProperty name = "user" property = "name" />.<br />
      You can click here to <a href = "logout.jsp">logout</a>.
    </p>
    <p>
      Books on sale:
      <table border = '1'>
	<tr>
	  <th>Name</th>
	  <th>Author</th>
	  <th>Publisher</th>
	  <th>Price</th>
	  <th>ISBN</th>
	  <th>Number</th>
	</tr>
	  <%
	  for (database.Book book : info.getBooks())
	  {
	    if (book.number > 0)
	    {
	      out.print("<tr>");
	      out.print("<td>" + book.name + "</td>");
	      out.print("<td>" + book.author + "</td>");
	      out.print("<td>" + book.publisher + "</td>");
	      out.print("<td>" + String.format("%.2f", book.price) + "</td>");
	      out.print("<td>" + book.isbn + "</td>");
	      out.print("<td>" + book.number + "</td>");	    
	      out.println("</tr>");
	    }
	  }
	  %>
      </table>
    </p>
    <p>
      Buy some books:<br />
      <form name = "order" action = "DoOrder" method = "post">
	ISBN: <input name = "ISBN" type = "text" />
	Number: <input name = "number" type = "text" />
	<input type = "submit" value = "Submit">
      </form>
      <br />
    </p>
    <p>
      Your orders:
      <table border = '1'>
	<tr>
	  <th>Name</th>
	  <th>Author</th>
	  <th>Publisher</th>
	  <th>Price</th>
	  <th>Number</th>
	  <th>Total</th>
	</tr>
	  <%

	  for (database.Order order : info.getOrders())
	  {
	    out.print("<tr>");
	    out.print("<td>" + order.name + "</td>");
	    out.print("<td>" + order.author + "</td>");
	    out.print("<td>" + order.publisher + "</td>");
	    out.print("<td>" + String.format("%.2f", order.price) + "</td>");
	    out.print("<td>" + order.count + "</td>");
	    out.print("<td>" + String.format("%.2f", order.count * order.price) + "</td>");
	    out.println("</tr>");
	  }
	  %>
      </table>
    </p>
    <p>
      <%
      if (user.getId().equals(servlet.Common.getAdministrator(getServletContext().getRealPath("/"))))
      {
	out.print("Add some books: <br />");
	out.print("<form name = 'addBooks' action = 'AddBook' method = 'post'>");
	out.print("ISBN: <input name = 'ISBN' type = 'text' /> <br />");
	out.print("Name: <input name = 'name' type = 'text' /> <br />");
	out.print("Author: <input name = 'author' type = 'text' /> <br />");
	out.print("Price: <input name = 'price' type = 'text' /> <br />");
	out.print("Publisher: <input name = 'publisher' type = 'text' /> <br />");
	out.print("Number: <input name = 'number' type = 'text' /> <br />");
	out.print("<input type = 'submit' value = 'Submit' /> <br />");
	out.print("</form>");
      }
      %>
    </p>
  </body>
</html>
