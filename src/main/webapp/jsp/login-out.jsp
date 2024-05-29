<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン成功</title>
</head>
<body>

    <p>こんにちは、<c:out value="${customer.getLogin()}"/>さん。</p>
    <form action="${pageContext.request.contextPath}/LogoutAction" method="post">
        <input type="submit" value="ログアウト">
    </form>

</body>
</html>
