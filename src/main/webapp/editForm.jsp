<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Поля файла</title>
</head>
<body>
<section>
    <h2>Поля файла</h2>
    <hr>
    <form method="post" action="download">
        <table border="1" cellpadding="4" cellspacing="0">
            <tr>
                <td>№</td>
                <td>Имя</td>
                <td>Значение</td>
            </tr>
        <c:forEach items="${items}" var="item">
            <jsp:useBean id="item" type="ru.sinnord.testtask.model.RowItem"/>
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>
                    <c:if test="${item.value != null}" >
                        <input type="text" value="${item.value}" name="${item.id}">
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </table>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
