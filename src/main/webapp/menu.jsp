<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Menu</title>
</head>
<body>
<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr/>
    <h2>Menu of ${restaurant.name}</h2>
    <hr/>
    <a href="menu?action=create">Add new dish</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Description</th>
            <th>Price</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${menu}" var="dish">
            <jsp:useBean id="dish" type="ru.veryprosto.restVote.model.Dish"/>
            <tr>
                <td>${dish.name}</td>
                <td>${dish.price}</td>
                <td><a href="menu?action=update&id=${dish.id}">Update</a></td>
                <td><a href="menu?action=delete&id=${dish.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>