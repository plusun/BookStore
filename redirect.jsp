<!DOCTYPE HTML>
<html>
  <head>
    <meta content = "text/html"; charset = "UTF-8"></meta>
    <title>Redirecting</title>
    <jsp:useBean id = "status" type = "bean.Status" scope = "request"/>
    <%
    out.println("<meta http-equiv='refresh' content = '3; url=" + status.getAddress() + "'/>");
    %>

  </head>
  <body>
    <p>
      <jsp:getProperty name = "status" property = "status" /><br />
      <jsp:getProperty name = "status" property = "details" /><br />
      You will be redirected in 3 seconds. <br />
    </p>
  </body>
</html>
