<%--
  Created by IntelliJ IDEA.
  User: Ilia Komarov
  Date: 02.05.2016
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" import="java.io.*" contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Error</title>
  <style type="text/css">
    <%@ include file="styles.css" %>
  </style>
  <h1>
    <a href="mainPage">Main page</a>
  </h1>
</head>

<body>
<h1>Something goes wrong!..</h1>
<br><%=exception.getMessage()%>
</body>

</html>


