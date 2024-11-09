<%-- 
    Document   : index
    Created on : 21/10/2024, 12:25:44 p. m.
    Author     : josej
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    </head>
    <body>
        
        <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
  <div class="container-fluid">
    <div class="collapse navbar-collapse" id="mynavbar">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link" href="marca.jsp">Marcas</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="producto.jsp">Productos</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="puesto.jsp">Puestos</a>
        </li>
         <li class="nav-item">
          <a class="nav-link" href="empleado.jsp">Empleados</a>
        </li>
          <li class="nav-item">
          <a class="nav-link" href="cliente.jsp">Clientes</a>
        </li>
         <li class="nav-item">
          <a class="nav-link" href="proveedor.jsp">Proveedores</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="venta.jsp">Ventas</a>
        </li> 
        <li class="nav-item">
          <a class="nav-link" href="compra.jsp">Compras</a>
        </li> 
      </ul>
    </div>
  </div>
</nav>
        
        
      <%--  <a href="marca.jsp">Marcas</a>
        <br>
        <a href="producto.jsp">producto</a>
        <br>
        <a href="puesto.jsp">Puestos</a>
        <br>
        <a href="empleado.jsp">Empleados</a>
        <br>
        <a href="cliente.jsp">Clientes</a>
        <br>
        <a href="proveedor.jsp">Proveedores</a>--%>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    </body>
</html>
