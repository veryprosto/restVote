<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Restaurant voting</title>
</head>
<body>
<h3>Проект голосование за ресторан</h3>
<hr>
<form method="get" action="restaurants">
    <b>Restaurants of&nbsp;</b>
    <select name="userId">
        <option value="100000">User</option>
        <option value="100001">Ivanoff</option>
        <option value="100002">Petroff</option>
    </select>
    <button type="submit">Select</button>
</form>
<li><a href="users">Users</a></li>

</body>
</html>