<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Ilia Komarov
  Date: 03.05.2016
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Management</title>
    <style type="text/css">
        <%@ include file="styles.css" %>
    </style>
    <h1>
        <a href="mainPage">Main page</a>
    </h1>
</head>
<body>
<h1>Menu</h1>
<ul>
    <li><a href="getallemps">Employees</a></li>
    <li><a href="departments">Departments</a></li>
    <li><a href="directories">Directories</a></li>
    <li><form action="filter" method="post"><button type="submit" class="btn-link">Find an employee</button></form></li>
    <li><form action="addemp" method="post"><button type="submit" class="btn-link">Recruit an employee</button></form></li>
    <li><a href="report">Report</a></li>
</ul>
</body>
</html>
