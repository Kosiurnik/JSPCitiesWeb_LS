<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>City</title>
    <style>
        <%@include file="/style.css"%>
    </style>
</head>
<body>

<div class="content">
    <div class="data">
    <span class="row">
        <span class="label">ID miasta:</span>
        <span class="data">${requestScope.city.id}</span>
    </span>
        <span class="row">
        <span class="label">Nazwa miasta:</span>
        <span class="data">${requestScope.city.name}</span>
    </span>
        <span class="row">
        <span class="label">Nazwa miasta (ASCII):</span>
        <span class="data">${requestScope.city.asciiName}</span>
    </span>
        <span class="row">
        <span class="label">Nazwa miasta alternatywna:</span>
        <span class="data">${requestScope.city.alternateNames}</span>
    </span>
        <span class="row">
        <span class="label">Szerokość geograficzna:</span>
        <span class="data">${requestScope.city.latitude}</span>
    </span>
        <span class="row">
        <span class="label">Długość geograficzna:</span>
        <span class="data">${requestScope.city.longitude}</span>
    </span>
        <span class="row">
        <span class="label">Kod kraju:</span>
        <span class="data">${requestScope.city.code}</span>
    </span>
        <span class="row">
        <span class="label">Ludność:</span>
        <span class="data">${requestScope.city.population}</span>
    </span>
        <span class="row">
        <span class="label">Strefa czasowa:</span>
        <span class="data">${requestScope.city.timezone}</span>
    </span>
    </div>
    <div class="map">
        <iframe src="https://maps.google.com/maps?q=${requestScope.city.latitude},${requestScope.city.longitude}&amp;ie=UTF8&amp;t=h&amp;z=12&amp;iwloc=B&amp;output=embed"></iframe>
    </div>
</div>
</body>
</html>
