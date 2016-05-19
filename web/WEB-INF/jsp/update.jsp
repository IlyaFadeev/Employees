<%--
  Created by IntelliJ IDEA.
  User: Fadeev
  Date: 4/18/2016
  Time: 4:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update</title>
    <style type="text/css">
        <%@ include file="styles.css" %>
    </style>
    <h1>
        <a href="mainPage">Main page</a>
    </h1>
</head>
<body>
<form action="save" method="post">
    <label>Emp no:</label>
    <input type="text" name="empno" value="${employee.empNo}" READONLY>
    <br>
    <br>
    <label>First name:</label>
    <input type="text" name="first_name" value="${employee.firstName}">
    <br>
    <br>
    <label>Last name:</label>
    <input type="text" name="last_name" value="${employee.secondName}">
    <br>
    <br>
    <label>Job:</label>
    <select name="job">
        <c:forEach var="listValue" items="${jobs}">
            <option <c:if test="${listValue.jname.equals(employee.job)}">selected</c:if>>
                    ${listValue.jname}
            </option>
        </c:forEach>

    </select>
    <br>
    <br>
    <label>MGR:</label>
    <select name="mgr">
        <c:forEach var="listValue" items="${empls}">
            <option <c:if test="${listValue.empNo.equals(employee.mgr)}">selected</c:if>>
                    ${listValue.firstName}
            </option>
        </c:forEach>

    </select>
    <br>
    <br>
    <label>Hire date:</label>
    <input type="date" name="hiredate" value="${employee.hireDate}">
    <br>
    <br>
    <label>Salary:</label>
    <input type="text" name="salary" value="${employee.sal}">
    <br>
    <br>
    <label>Dept no:</label>
    <select name="deptno">
        <c:forEach var="listValue" items="${depts}">
            <option <c:if test="${listValue.deptno.equals(employee.deptNo)}">selected</c:if>>
                    ${listValue.deptno}
            </option>
        </c:forEach>

    </select>
    <br>
    <br>
    <br>
    <label>Start date:</label>
    <input type="date" name="start" value="${time.startdate}">
    <br>
    <br>
    <label>End date:</label>
    <input type="date" name="end" value="${time.enddate}">
    <br>
    <br>
    <label>Type:</label>
    <select name="type">
        <c:forEach var="listValue" items="${types}">
            <option <c:if test="${listValue.type.equals(time.type)}">selected</c:if>>
                    ${listValue.type}
            </option>
        </c:forEach>

    </select>
    <br>
    <br>
    <br>
    <input type="submit" value="update">
</form>
</body>
</html>
