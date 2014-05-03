<!DOCTYPE HTML>
<html>
  <head>
    <meta content = "text/html"; charset = "UTF-8"></meta>
    <title>Logout</title>
    <meta http-equiv="refresh" content = "3; url=LoadIndex"/>
  </head>
  <body>
    <p>
      <% session.invalidate(); %>
      Success!<br />
      You will be redirected in 3 seconds. <br />
    </p>
  </body>
</html>
