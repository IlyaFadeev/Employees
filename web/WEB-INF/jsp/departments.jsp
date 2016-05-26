<%--
  Created by IntelliJ IDEA.
  User: Ilia Komarov
  Date: 01.05.2016
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="pojo.DEPARTMENTS" %>
<%@ page import="java.util.List" %>
<%@ page import="services.EmployeesService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Departments</title>
    <style type="text/css">
        <%@ include file="styles.css" %>
    </style>

    <h1>
        <a href="mainPage">Main page</a>
    </h1>
</head>
<body>
<h1>Departments</h1>
<table class="tablestyle">
    <tr>
        <td>Name</td>
        <td>Location</td>
        <td>Manager</td>
        <td></td>
    </tr>
    <c:forEach var="listDepts" items="${depts}">
        <tr>
            <td>${listDepts.dname}</td>
            <td>${listDepts.location}</td>
            <td>${listDepts.manager}</td>

            <td><form action="employeesfull?deptno=${listDepts.deptno}" method="post"><button type="submit" class="btn-link">Employees of dept.</button></form></td>
            <td><form action="updatedept?deptno=${listDepts.deptno}" method="post"><button type="submit" class="btn-link">Edit</button></form></td>
        </tr>
    </c:forEach>
</table>
<form action="adddept" method="post">
    <input type="submit" value="Add dept.">
</form>
</body>
</html>
