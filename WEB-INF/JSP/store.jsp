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
	    out.print("<tr>");
	    out.print("<td>" + book.name + "</td>");
	    out.print("<td>" + book.author + "</td>");
	    out.print("<td>" + book.publisher + "</td>");
	    out.print("<td>" + book.price + "</td>");
	    out.print("<td>" + book.isbn + "</td>");
	    out.print("<td>" + book.number + "</td>");	    
	    out.println("</tr>");
	  }
	  %>
      </table>
    </p>
  </body>
</html>
