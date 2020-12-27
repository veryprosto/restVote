<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Menu</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <h2>Menu of ${restaurant.name}</h2>
    <hr/>
    <a href="restaurants?action=create">Add restaurant</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Name</th>
            <th>Rating</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${restaurants}" var="restaurant">
            <jsp:useBean id="restaurant" type="ru.veryprosto.restVote.model.Restaurant"/>
            <tr>
                <td>${restaurant.name}</td>
                <td>${restaurant.rating}</td>
                <td><a href="restaurants?action=update&id=${restaurant.id}">Update</a></td>
                <td><a href="restaurants?action=delete&id=${restaurant.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>