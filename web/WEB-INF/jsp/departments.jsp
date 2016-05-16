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
<div class="tablestyle">
<table class="tablestyle">
    <tr class="title">
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
            <td><a href="employeesfull?deptno=${listDepts.deptno}">Employees of dept.</a></td>
            <td><a href="updatedept?deptno=${listDepts.deptno}">Update</a></td>
        </tr>
    </c:forEach>
</table>
</div>
<form action="adddept">
    <input type="submit" value="Add dept.">
</form>
</body>
</html>
