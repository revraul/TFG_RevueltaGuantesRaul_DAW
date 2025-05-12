<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>P�gina de error</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div class="error-container">
		<img src="/images/logo_pets_bg.png" alt="Logo animalControl">
        <h1> �Guau! Algo sali� mal...</h1>
        <p class="error-code">Error ${pageContext.errorData.statusCode}</p>
        <p class="error-message">${pageContext.errorData.throwable.message}</p>
        <p>No te preocupes, �lo estamos buscando y pronto podr�s seguir comprando para tu mascota! </p>
        <a href="/" class="btn btn-outline-primary me-3">Volver a la p�gina principal</a>
    </div>
    
    <jsp:include page="footer.jsp"></jsp:include>
    
</body>
</html>