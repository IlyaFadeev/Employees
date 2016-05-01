<%--
  Created by IntelliJ IDEA.
  User: Ilia Komarov
  Date: 01.05.2016
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><%@ page import="pojo.DEPARTMENTS" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Departments</title>
</head>
<body>
    <h1>Departments</h1>
    <table>
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
                <td><a href="employeesfull?deptno=${listDepts.deptno}">Employees of dept.</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
