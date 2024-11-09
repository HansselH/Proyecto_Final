<%@page import="java.util.HashMap"%>
<%@page import="modelo.Cliente"%>
<%@page import="modelo.Empleados"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registrar Nueva Venta</title>
    
    <!-- Agregar el CDN de Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEJ1QJzNkTJR2NNJ9zW18dbQsZLtV8NTl9+q+Cv6fiEd4kVfMJ9ZnLtkD13jv" crossorigin="anonymous">

    <style>
        body {
            background-color: #f7f7f7;
            font-family: 'Arial', sans-serif;
        }

        .container {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 30px;
            margin-top: 30px;
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }

        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #004085;
        }

        .btn-danger {
            background-color: #dc3545;
            border-color: #dc3545;
        }

        .btn-danger:hover {
            background-color: #c82333;
            border-color: #bd2130;
        }

        .form-label {
            font-weight: bold;
        }

        .table th, .table td {
            vertical-align: middle;
        }

        .table-bordered th, .table-bordered td {
            border-color: #dee2e6;
        }

        .table-bordered td {
            padding: 10px;
        }

        .input-group-text {
            background-color: #f1f1f1;
        }

        .input-group input {
            border-radius: 5px;
        }

        .input-group-text {
            border-radius: 5px;
        }

        .my-4 {
            color: #007bff;
            font-weight: bold;
        }

        h2, h3 {
            text-align: center;
            margin-bottom: 30px;
        }

        .btn-add {
            width: 100%;
        }

        .form-control {
            border-radius: 8px;
        }

        .tooltip-inner {
            background-color: #007bff;
        }

        /* Centrar la tabla de detalles de venta */
        .detalles-container {
            display: flex;
            justify-content: center;
            margin-bottom: 30px;
        }
    </style>
</head>
<body class="container">

    <h2 class="my-4">Registrar Nueva Venta</h2>
    <div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
    <form action="sr_venta" method="POST">
        <!-- Información de la venta -->
        <input type="hidden" name="action" value="create">

        <h3 class="mb-4">Información de Venta</h3>
        <div class="mb-3">
            <label for="no_factura" class="form-label">No. Factura:</label>
            <input type="text" class="form-control" name="no_factura" id="no_factura" required>
        </div><br>

        <div class="mb-3">
            <label for="serie" class="form-label">Serie:</label>
            <input type="text" class="form-control" name="serie" id="serie" maxlength="1" required>
        </div><br>

        <div class="mb-3">
            <label for="fecha_factura" class="form-label">Fecha de Factura:</label>
            <input type="date" class="form-control" name="fecha_factura" id="fecha_factura" required>
        </div><br>

        <div class="mb-3">
            <label for="fecha_ingreso" class="form-label">Fecha de Ingreso:</label>
            <input type="datetime-local" class="form-control" name="fecha_ingreso" id="fecha_ingreso" required>
        </div><br>

        <div class="mb-3">
            <label for="id_cliente" class="form-label">Cliente (ID):</label>
            <select name="id_cliente" id="id_cliente" class="form-select">
                <%
                    Cliente cliente = new Cliente();
                    HashMap<String,String> drop1 = cliente.drop_cliente();
                    for (String i: drop1.keySet()){
                        out.println("<option value='"+ i + "'>" + drop1.get(i) +"</option>");
                    }
                %>
                </select>
        </div><br>

        <div class="mb-3">
            <label for="id_empleado" class="form-label">Empleado (ID):</label>
            <td class="text-center"><select name="id_empleado" id="id_empleado" class="form-select">
                <%
                    Empleados empleado = new Empleados();
                    HashMap<String,String> drop = empleado.drop_empleado();
                    for (String i: drop.keySet()){
                        out.println("<option value='"+ i + "'>" + drop.get(i) +"</option>");
                    }
                %>
                </select></td>
        </div><br>

        <!-- Detalles de la venta -->
        <h3 class="mb-4">Detalles de Venta</h3>

        <input type="hidden" id="cantidad_detalles" name="cantidad_detalles" value="1">

        <div class="detalles-container d-flex justify-content-center">
            <table id="detallesTabla" class="table table-bordered table-hover w-auto">
                <thead class="table-info">
                    <tr>
                        <th class="text-center">Producto (ID)</th>
                        <th class="text-center">Cantidad</th>
                        <th class="text-center">Precio Unitario</th>
                        <th class="text-center">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="text-center"><input type="number" name="id_producto1" class="form-control" required></td>
                        <td class="text-center"><input type="number" name="cantidad1" class="form-control" required></td>
                        <td class="text-center"><input type="text" name="precio_unitario1" class="form-control" required></td>
                        <td class="text-center"><button type="button" class="btn btn-danger" onclick="eliminarFila(this)">Eliminar</button></td>
                    </tr>
                </tbody>
            </table>
        </div>

        <br>
        <button type="button" class="btn btn-outline-primary btn-add" onclick="agregarFila()">Agregar Detalle</button>
        <br><br>

        <button type="submit" class="btn btn-primary btn-lg">Guardar Venta</button>
    </form>
    </div>

    <!-- Scripts para manejar dinámicamente los detalles -->
    <script>
        let detalleIndex = 1;

        function agregarFila() {
            detalleIndex++;
            document.getElementById("cantidad_detalles").value = detalleIndex;

            const tabla = document.getElementById("detallesTabla").getElementsByTagName("tbody")[0];
            const fila = tabla.insertRow();

            fila.innerHTML = `
                <td><input type="number" class="form-control" name="id_producto${detalleIndex}" required></td>
                <td><input type="number" class="form-control" name="cantidad${detalleIndex}" required></td>
                <td><input type="text" class="form-control" name="precio_unitario${detalleIndex}" required></td>
                <td><button type="button" class="btn btn-danger" onclick="eliminarFila(this)">Eliminar</button></td>
            `;
        }

        function eliminarFila(button) {
            const fila = button.parentNode.parentNode;
            fila.parentNode.removeChild(fila);
            detalleIndex--;
            document.getElementById("cantidad_detalles").value = detalleIndex;
        }
    </script>

    <!-- Agregar el CDN de Bootstrap JS y Popper.js -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz4fnFO9gybRnvF0vLvjITFfU6zHf2XgJh8m2p3yBb6jMcDkk4fYykd1t7" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq0v8Fq1aJXJOkt4JtSt8sHtDtjcU6l1f5cPQErh5ggf" crossorigin="anonymous"></script>
</body>
</html>
