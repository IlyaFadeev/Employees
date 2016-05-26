<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <h1>
        <a href="mainPage">Main page</a>
    </h1>
</head>
<body>
<form action="search" method="post">
    <label>
        Name:
    </label>
    <input type="text" name="name" title="Enter employee name">
    <br>
    <br>
    <label>
        Surname:
    </label>
    <input type="text" name="surname" title="Enter surname">
    <br>
    <br>
    <label>
        Hire date:
    </label>
    <input type="date" name="hiredate" title="Enter hire date">
    <br>
    <br>
    <label>
        Job:
    </label>
    <input type="text" name="job" title="Enter job">
    <br>
    <br>
    <label>
        Manager:
    </label>
    <input type="text" name="mgr" title="Enter manager">
    <br>
    <br>
    <label>
        Salary:
    </label>
    <input type="text" name="sal" title="Enter salary">
    <br>
    <br>
    <input type="submit" value="search" title="Click for search">
</form>
</body>
</html>
