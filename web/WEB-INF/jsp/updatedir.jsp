<%--
  Created by IntelliJ IDEA.
  User: Ilia Komarov
  Date: 02.05.2016
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${dirtitle}</title>
    <style type="text/css">
        <%@ include file="styles.css" %>
    </style>
    <h1>
        <a href="mainPage">Main page</a>
    </h1>
</head>
<body>
<form action="savedir" method="post">
    <h1>${dirtitle}</h1>
    <input type="hidden" name="type" value="${type}">
    <input type="text" name="dir" value="${dirname}">
    <input type="submit" value="Confirm">
</form>

</body>
</html>
