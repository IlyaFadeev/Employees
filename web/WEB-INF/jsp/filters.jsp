<%--
  Created by IntelliJ IDEA.
  User: Fadeev
  Date: 4/22/2016
  Time: 11:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
  <style type="text/css">
    <%@ include file="styles.css" %>
  </style>
</head>
<body>
<form action="search">
  <h3>
    Name:
  </h3>
  <input type="text" name="name">
  <br>
  <h3>
    Surname:
  </h3>
  <input type="text" name="surname">
  <br>
  <h3>
    Hire date:
  </h3>
  <input type="date" name="hiredate">
  <br>
  <h3>
    Job:
  </h3>
  <input type="text" name="job">
  <br>
  <h3>
    Manager:
  </h3>
  <input type="text" name="mgr">
  <br>
  <h3>
    Salary:
  </h3>
  <input type="text" name="sal">
  <br>
  <br>
  <input type="submit" value="search">
</form>
</body>
</html>
