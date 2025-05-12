<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Carrito</title>
	</head>
	
	<body>
	
		<jsp:include page="header.jsp"></jsp:include>
		
		<div class="page-header">
			<h1 class="container">Carrito</h1>
		</div>
	
		<main class="my-5 container">
		<div class="container">${mensaje }</div>
			<form action="/cesta/comprar" method="post">

				<table class="table table-striped table-sm">
    <thead>
        <tr>
            <th class="text-center text-uppercase">ID Producto</th>
            <th class="text-center text-uppercase">Cantidad</th>
            <th class="text-center text-uppercase">Precio unidad</th>
            <th class="text-center text-uppercase">Subtotal</th>
        </tr>
    </thead>
    <tbody>
        <c:set var="total" value="0" />
        <c:forEach var="ele" items="${cesta}">
            <c:set var="subtotal" value="${ele.cantidad * ele.precioVenta}" />
            <c:set var="total" value="${total + subtotal}" />
            <tr>
                <td class="text-center align-center">${ele.idProducto}</td>
                <td class="text-center align-center">${ele.cantidad}</td>
                <td class="text-center align-center">
                    <fmt:formatNumber value="${ele.precioVenta}" type="number" minFractionDigits="2" maxFractionDigits="2" /> €
                </td>
                <td class="text-center align-center">
                    <fmt:formatNumber value="${subtotal}" type="number" minFractionDigits="2" maxFractionDigits="2" /> €
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="3" class="text-end fw-bold">Total:</td>
            <td class="text-center fw-bold">
                <fmt:formatNumber value="${total}" type="number" minFractionDigits="2" maxFractionDigits="2" /> €
            </td>
        </tr>
    </tbody>
</table>
				<div class="mb-5">
					<label class="my-3" for="direccion">Dirección de envío:</label><br>
		            <c:forEach items="${DireccionesUsuario}" var="ele">
		              <div class="form-check">
		                <input class="form-check-input" type="radio" name="direccion" id="opcion${ele.idDireccion}" value="${ele.idDireccion}" required>
		                <label class="form-check-label" for="opcion${ele.idDireccion}">${ele.descripcion}, Calle ${ele.calle}, Nº ${ele.numero}, Localidad ${ele.localidad}, CP: ${ele.codigoPostal}</label>
		
		              </div>
		            </c:forEach>
		
		            <label class="my-3" for="tarjeta">Tarjeta de pago:</label><br>
		            <c:forEach items="${TarjetasUsuario}" var="ele">
		              <div class="form-check">
		                <input class="form-check-input" type="radio" name="tarjeta" id="opcion${ele.idTarjeta}" value="${ele.idTarjeta}" required>
		                <label class="form-check-label" for="opcion${ele.idTarjeta}">${ele.nombreTitular}: Número ${ele.numero}, caducidad ${ele.fechaCaducidad}</label>
		
		              </div>
		            </c:forEach>
				</div>
				<div class="text-end">
					<button type="submit" class="btn btn-primary">Comprar</button>
				</div>
			
			</form>
			
		</main>
		
		<jsp:include page="footer.jsp"></jsp:include>
		
	</body>
	
</html>