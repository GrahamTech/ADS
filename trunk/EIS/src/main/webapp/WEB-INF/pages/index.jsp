<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
<title>Enterprise Governance, Risk, and Compliance - Executive Information System</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="context" value="${pageContext.request.contextPath}"/>
<script src="${context}/resources/js/test.js"></script>
<link rel="stylesheet" type="text/css" href="${context}/resources/css/test_styles.css">
</head>

<body>
<img src="${context}/resources/images/GrahamTechLogo_png.png" width="70" height="70"/>
<h1>${message}</h1>
<input type="submit" name="Test JS" onClick="testFunction()">

</br></br>

<div align="center">
    <h1>User Profile List</h1>
    <table border="1">
        <th>No</th>
        <th>Email</th>
         
        <c:forEach var="user" items="${userProfileList}" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${user.email}</td>
        </tr>
        </c:forEach>             
    </table>
</div>

</body>
</html>
