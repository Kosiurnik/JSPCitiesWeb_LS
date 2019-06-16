<%--
  Created by IntelliJ IDEA.
  User: LS
  Date: 16.06.2019
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Find a city</title>
</head>
<body>
    <form method="post" action="one">
        <select name="id">
            <c:forEach items="${requestScope.cities}" var="city">
                <option value="${city.id}">${city.name}</option>
            </c:forEach>
        </select>
        <button type="submit">Wyszukaj</button>
    </form>
</body>
</html>
