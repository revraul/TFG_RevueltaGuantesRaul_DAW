<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">  
    <link rel="stylesheet" type="text/css" href="/css/styles.css" media="screen"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
  </head>
  
  <body class="p-5 container text-center">
    
    <!-- Caja de formulario con tarjeta -->
    <div class="card p-4 shadow-lg rounded" style="max-width: 400px; margin: 0 auto; background-color: #f8f9fa;">
      <form action="/login" method="post">
        <img class="logo-img mb-4 mx-auto d-block" src="/images/petsHero.jpg" alt="Logo" style="max-height: 150px; max-width: 150px;">
        <h1 class="h3 mb-3 fw-normal">Bienvenido a animalControl()</h1>
        
        <div class="form-floating mb-3">
          <input type="text" class="form-control" id="username" name="username" placeholder="Username" required>
          <label for="username">Username</label>
        </div>
        
        <div class="form-floating mb-3">
          <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
          <label for="password">Password</label>
        </div>
        
        <button class="w-100 btn btn-lg btn-primary" type="submit">Entrar</button>
      </form>
    </div>
  </body>
</html>
