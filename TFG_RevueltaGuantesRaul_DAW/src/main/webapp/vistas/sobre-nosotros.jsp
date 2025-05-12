<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sobre nosotros</title>
		
	</head>
	
	<body>
		<jsp:include page="header.jsp"></jsp:include>
		
		<div class="page-header">
			<h1 class="container">Sobre nosotros</h1>
		</div>
		
		<main>
			<section class="container">
                <div class="row">
                    <div class="col-md-4 mb-4">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Productos para Perros</h5>
                                <p class="card-text">En nuestra tienda encontrarás una amplia gama de productos para perros: juguetes, camas, collares, arneses, y mucho más. Todo lo necesario para que tu mascota esté feliz y saludable.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-4">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Productos para Gatos</h5>
                                <p class="card-text">Descubre los mejores productos para tu gato: desde comida hasta juguetes interactivos, pasando por cómodas camas y areneros. Todo lo que necesita tu felino en un solo lugar.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4 mb-4">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Accesorios y Cuidados</h5>
                                <p class="card-text">No solo vendemos productos básicos, sino también accesorios y artículos para el cuidado de tu mascota: champús, productos de higiene, y mucho más. Cuida a tu amigo peludo como se merece.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
	    </main>
	
		<jsp:include page="footer.jsp"></jsp:include>
			
	</body>
	
</html>
	