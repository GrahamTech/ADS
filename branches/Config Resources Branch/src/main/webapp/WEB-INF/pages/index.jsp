<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
<title>Executive Information System</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="context" value="${pageContext.request.contextPath}"/>
<script src="${context}/resources/js/test.js"></script>
<link rel="stylesheet" type="text/css" href="${context}/resources/css/test.css">
</head>

<body>
<img src="${context}/resources/images/GrahamTechLogo_png.png" width="70" height="70"/>
<h1>${message}.</h1>
<h3>${message2}!</h3>
<input type="submit" name="Test JS" onClick="testFunction()">
</body>
</html>
