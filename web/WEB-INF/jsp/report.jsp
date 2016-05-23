<%--
  Created by IntelliJ IDEA.
  User: Ilia Komarov
  Date: 01.05.2016
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="styles.css" type="text/css"/>
    <title>Employees</title>
    <style type="text/css">
        <%@ include file="styles.css" %>
    </style>
    <h1>
        <a href="mainPage">Report</a>
    </h1>
</head>
<body>
<h1>${pagetitle}</h1>

    <table class="tablestyle">
        <tr class="title">
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
            <c:forEach var="timeoff" items="${countOfDub}">
                <td>
                    Start time off date
                </td>
                <td>
                    End time off date
                </td>
                <td>
                    Type of time off
                </td>
            </c:forEach>
        </tr>
        <c:forEach var="listValue" items="${emps}">
            <tr>
                <td>
                        ${listValue.firstName}
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


                <c:forEach var="timeoff" items="${time}">
                    <c:if test="${timeoff.empno == listValue.empNo}">
                        <td>
                                ${timeoff.startdate}
                        </td>
                        <td>
                                ${timeoff.enddate}
                        </td>
                        <td>
                                ${timeoff.type}
                        </td>
                    </c:if>
                </c:forEach>

            </tr>
        </c:forEach>
    </table>
</body>
</html>
