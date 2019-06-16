<%--
  Created by IntelliJ IDEA.
  User: LS
  Date: 16.06.2019
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Cities</title>
    <style>
        <%@include file="/style.css"%>
    </style>
</head>
<body>
<span class="header">
    <span class="label">Nazwa miasta</span>
    <span class="data">Szczegóły</span>
</span>
<c:forEach items="${requestScope.cities}" var="city" varStatus="count">
    <span class="row ${count.index%2!=0 ? "even" : ""}">
        <span class="label"><a href="../city/one?id=${city.id}">${city.name}</a></span>
        <span class="data">${city.code} ${city.timezone} - ${city.longitude},${city.latitude}</span>
    </span>
</c:forEach>
<div class="paginator">
    <a href="all?page=first">Początek</a>
    <a href="all?page=prev">Poprzednia strona</a>
    <a href="all?page=next">Następna strona</a>
    <a href="all?page=last">Koniec</a>
</div>
</body>
</html>
