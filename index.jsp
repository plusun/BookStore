<!DOCTYPE HTML>
<html>
  <head>
    <meta content = "text/html"; charset = "UTF-8"></meta>
    <title>Welcome to Book Store!</title>
    <%
    HttpSession session = request.getSession();
    String id = (String) session.getAttribute("id");
    String pw = (String) session.getAttribute("pw");
    if (id != null)
    {
      database.BookStore bs = new database.BookStore();
      database.User user = new databse.User(id, pw);
      if (bs.checkUser(user)) response.sendRedirect("store.jsp");
    }
    %>
  </head>
  <body>
    <h1>Welcome!</h1>h1>
    <p>
      <a href = "login.html">Sign in</a>a> or <a href = "register.html">sign up</a>.
    </p>
  </body>
</html>
