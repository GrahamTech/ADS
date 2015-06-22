<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<body>
<h1>EIS Internationalization</h1>
 
Language : <a href="?language=en">English</a>&nbsp;|&nbsp;<a href="?language=es">Spanish</a>
 
<h3>
welcome.springmvc : <spring:message code="welcome.springmvc" text="default text" />
</h3>
 
Current Locale : ${pageContext.response.locale}
 
</body>
</html>
