<%--
  Created by IntelliJ IDEA.
  User: Fadeev
  Date: 4/17/2016
  Time: 2:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="pojo.EMPLOYEES" %>
<%@ page import="services.TimeOffService" %>
<%@ page import="pojo.TIMEOFF" %>
<%@ page import="org.joda.time.DateTime" %>
<%@ page import="org.joda.time.Period" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Employee info</title>
    <style type="text/css">
        <%@ include file="styles.css" %>
    </style>

    <h1>
        <a href="mainPage">Main page</a>
    </h1>
    <h3>
        <a href="getallemps">Back to employees list</a>

    </h3>
</head>

<h1>
    Employee:
</h1>

    <table class="tablestyle">
        <tr>
            <td>
                Name
            </td>
            <td>
                Surname
            </td>
            <td>
                Hiredate
            </td>
            <td>
                Job
            </td>
            <td>
                Manager
            </td>
            <td>
                Salary
            </td>
            <td>
                Time off
            </td>
            <td>

            </td>
            <td>

            </td>
        </tr>
        <tr>
            <td>
                <a href="employees?empno= ${employee.empNo}">${employee.firstName}</a>
            </td>
            <td>
                ${employee.secondName}
            </td>
            <td>
                ${employee.hireDate}
            </td>
            <td>
                ${employee.job}
            </td>
            <td>
                ${employee.mgr}
            </td>
            <td>
                ${employee.sal}
            </td>
            <td>

                ${timeoff}

            </td>
            <td>
                <a href="remove?empno=${employee.empNo}">remove</a>
            </td>
            <td>
                <form action="update?empno=${employee.empNo}" method="post"><button type="submit" class="btn-link">edit</button></form>
            </td>
        </tr>

    </table>


<h1>
    Managers:
</h1>

    <table class="tablestyle">
        <tr>
            <td>
                Name
            </td>
            <td>
                Surname
            </td>
            <td>
                Hiredate
            </td>
            <td>
                Job
            </td>
            <td>
                Manager
            </td>
            <td>
                Salary
            </td>
            <td>

            </td>
            <td>

            </td>
        </tr>
        <c:forEach var="listValue" items="${mgrs}">
            <tr>
                <td>
                    <a href="employees?empno= ${listValue.empNo}">${listValue.firstName}</a>
                </td>
                <td>
                        ${listValue.secondName}
                </td>
                <td>
                        ${listValue.hireDate}
                </td>
                <td>
                        ${listValue.job}
                </td>
                <td>
                        ${listValue.mgr}
                </td>
                <td>
                        ${listValue.sal}
                </td>
                <td>
                    <a href="remove?empno=${listValue.empNo}">remove</a>
                </td>
                <td>
                    <form action="update?empno=${employee.empNo}" method="post"><button type="submit" class="btn-link">edit</button></form>
                </td>
            </tr>
        </c:forEach>

    </table>

<h1>
    Sub employees:
</h1>

    <table class="tablestyle">

        <tr>
            <td>
                Name
            </td>
            <td>
                Surname
            </td>
            <td>
                Hiredate
            </td>
            <td>
                Job
            </td>
            <td>
                Manager
            </td>
            <td>
                Salary
            </td>
            <td>

            </td>
            <td>

            </td>
        </tr>
        <c:forEach var="listValue" items="${subEmp}">
            <tr>
                <td>
                    <a href="employees?empno= ${listValue.empNo}">${listValue.firstName}</a>
                </td>
                <td>
                        ${listValue.secondName}
                </td>
                <td>
                        ${listValue.hireDate}
                </td>
                <td>
                        ${listValue.job}
                </td>
                <td>
                        ${listValue.mgr}
                </td>
                <td>
                        ${listValue.sal}
                </td>
                <td>
                    <a href="remove?empno=${listValue.empNo}">remove</a>
                </td>
                <td>
                    <form action="update?empno=${employee.empNo}" method="post"><button type="submit" class="btn-link">edit</button></form>
                </td>
            </tr>
        </c:forEach>

    </table>
<br>
<br>
<br>



<form action="filter" method="POST">
    <input type="submit" value="filters">
</form>
<br>
<br>

<form action="addemp" method="POST">
    <input type="submit" value="add">
    <br>
    <br>
</form>
</body>
</html>
