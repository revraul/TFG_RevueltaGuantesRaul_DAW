<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Nuevo Usuario</title>
	</head>
	
	<body>
	
		<jsp:include page="header.jsp"></jsp:include>
		
		<div class="page-header">
			<h1 class="container">Crear nuevo usuario</h1>
		</div>
	
		<main class="mt-5 container">
			<div class="container">${mensaje }</div>				
			<form action="/alta-usuario" method="post">
				<div class="form-floating mb-3">
					<input class="form-control" id="nombre" name="nombre" type="text" placeholder="Nombre" required>
					<label class="small mb-1" for="nombre">Nombre</label>
				</div>
				<div class="form-floating mb-3">
					<input class="form-control" id="apellido1" name="apellido1" type="text" placeholder="Apellido1" required>
					<label class="small mb-1" for="descripcion">Apellido 1</label>
				</div>
				<div class="form-floating mb-3">
					<input class="form-control" id="apellido2" name="apellido2" type="text" placeholder="Apellido2" required>
					<label class="small mb-1" for="descripcion">Apellido 2</label>
				</div>
				<div class="form-floating mb-3">
					<input class="form-control" id="fechaNacimiento" name="fechaNacimiento" type="date" placeholder="Fecha Nacimiento" required>
					<label class="small mb-1" for="fechaRegistro">Fecha Nacimiento</label>
				</div>
				<div class="form-floating mb-3">
					<input class="form-control" id="email" name="email" type="email" placeholder="email" required>
					<label class="small mb-1" for="email">Email</label>
				</div>
				<div class="form-floating mb-3">
    				<input class="form-control" id="username" name="username" type="text" placeholder="Username" required autocomplete="off">
    				<label class="small mb-1" for="username">User Name</label>
				</div>
				<div class="form-floating mb-3">
    				<input class="form-control" id="password" name="password" type="password" placeholder="Contraseña" required autocomplete="new-password">
    				<label class="small mb-1" for="password">Contraseña</label>
				</div>
				<div class="text-end mt-5">
					<a class="btn btn-outline-primary me-3" href="javascript:history.back()">Volver</a>
					<input class="btn btn-primary" type="submit" value="Guardar">
				</div>                       
			</form>		

		</main>
		
		<jsp:include page="footer.jsp"></jsp:include>
		
	</body>
	
</html>