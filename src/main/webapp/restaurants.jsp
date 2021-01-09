<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<c:set var="baseURL" value="${pageContext.request.requestURL.substring(0, pageContext.request.requestURL.length() - pageContext.request.requestURI.length())}${pageContext.request.contextPath}/" />
<head>
    <base href="${baseURL}" />
    <title>Restaurants</title>
</head>
<body>
<h2>${message}</h2>
<form action="/logout" method="post">
    <input value="Logout" type="submit">
</form>
<section>
    <h2>Restaurants</h2>
    <hr/>
    <form action="restaurants/create" method="get">
        <input type="submit" value="Add restaurant" />
    </form>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Name</th>
            <th>Rating</th>
            <th>Menu</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${restaurantList}" var="restaurant">
            <jsp:useBean id="restaurant" type="ru.veryprosto.restVote.model.Restaurant"/>
            <tr>
                <td>${restaurant.name}</td>
                <td>${restaurant.rating}</td>
                <td>
                    <form action="restaurants/${restaurant.id}/menu" method="get">
                        <input type="submit" value="Menu" />
                    </form>
                </td>
                <td>
                    <form action="restaurants/${restaurant.id}" method="get">
                        <input type="submit" value="Update" />
                    </form>
                </td>
                <td>
                    <form action="restaurants/${restaurant.id}/delete" method="post">
                        <input type="submit"  value="Delete" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>