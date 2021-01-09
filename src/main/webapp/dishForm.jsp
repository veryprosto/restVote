<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<!-- определяем констекст для абсолютных ссылок -->
<c:set var="baseURL" value="${pageContext.request.requestURL.substring(0, pageContext.request.requestURL.length() - pageContext.request.requestURI.length())}${pageContext.request.contextPath}/" />
<head>
    <base href="${baseURL}" />
    <title>Dish form</title>
</head>
<body>
<section>
    <h3><a href="menu.jsp">Home</a></h3>
    <hr>
    <h2>${action_create == true ? 'Adding a new dish' : 'Editing a dish'}</h2>
    <jsp:useBean id="dish" type="ru.veryprosto.restVote.model.Dish" scope="request"/>
    <form method="post" action="menu">
        <input type="hidden" name="id" value="${dish.id}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" value="${dish.name}" size=40 name="name" required></dd>
        </dl>
        <dl>
            <dt>price:</dt>
            <dd><input type="number" value="${dish.price}" name="price" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>