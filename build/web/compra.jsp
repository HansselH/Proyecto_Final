<%@page import="java.util.HashMap"%>
<%@page import="modelo.Proveedor"%>

<%-- 
    Document   : compra
    Created on : 8/11/2024, 11:13:43 p. m.
    Author     : Hanssel
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
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registrar Nueva Compra</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEJ1QJzNkTJR2NNJ9zW18dbQsZLtV8NTl9+q+Cv6fiEd4kVfMJ9ZnLtkD13jv" crossorigin="anonymous">
    
    <style>
        body {
            background-color: #f7f7f7;
            font-family: 'Georgia', serif;
        }
        .container {
            max-width: 900px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 30px;
            margin-top: 50px;
        }
        .form-text-color {
            color: #4b6584;
        }
        .form-section-title {
            color: #2c7a7b;
            font-weight: bold;
            text-align: center;
            margin-bottom: 20px;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        /* Espaciado adicional entre botones */
        .btn-spacing {
            margin-top: 10px;
            margin-bottom: 10px;
        }
        /* Espaciado ajustado entre campos del formulario */
        .form-group-row {
            display: flex;
            gap: 15px;
        }
        .form-group-row > div {
            flex: 1;
        }
    </style>
</head>
<body>

<div class="container">
    <h2 class="form-section-title">Registrar Compra</h2>
    <form action="sr_compra" method="POST">
        <!-- Información de la compra -->
        <input type="hidden" name="action" value="create">

        <h3 class="form-section-title">Información de Compra</h3>
        <div class="form-group-row form-text-color">
            <div>
                <label for="no_orden_compra" class="form-label">No. Orden de Compra:</label>
                <input type="text" class="form-control" name="no_orden_compra" id="no_orden_compra" required>
            </div>
            <div>
                <label for="fecha_orden" class="form-label">Fecha de Orden:</label>
                <input type="date" class="form-control" name="fecha_orden" id="fecha_orden" required>
            </div>
        </div>
        
        <div class="form-group-row form-text-color mt-3">
            <div>
                <label for="fecha_ingreso" class="form-label">Fecha de Ingreso:</label>
                <input type="datetime-local" class="form-control" name="fecha_ingreso" id="fecha_ingreso" required>
            </div>
            <div>
                <label for="id_proveedor" class="form-label">Proveedor (ID):</label>
                <select name="id_proveedor" id="id_proveedor" class="form-select">
                    <%
                        Proveedor proveedor = new Proveedor();
                        HashMap<String, String> drop1 = proveedor.drop_proveedor();
                        for (String i : drop1.keySet()) {
                            out.println("<option value='" + i + "'>" + drop1.get(i) + "</option>");
                        }
                    %>
                </select>
            </div>
        </div>

        <!-- Detalles de la compra -->
        <h3 class="form-section-title">Detalles de Compra</h3>
        <input type="hidden" id="cantidad_detalles" name="cantidad_detalles" value="1">
        
        <div class="table-container mt-3">
            <table id="detallesTabla" class="table table-bordered table-hover text-center">
                <thead class="table-info">
                    <tr>
                        <th>Producto (ID)</th>
                        <th>Cantidad</th>
                        <th>Precio Costo Unitario</th>

                      
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input type="number" name="id_producto1" class="form-control" required></td>
                        <td><input type="number" name="cantidad1" class="form-control" required></td>
                        <td><input type="text" name="precio_costo_unitario1" class="form-control" required></td>
                        <td><button type="button" class="btn btn-danger btn-spacing" onclick="eliminarFila(this)">Eliminar</button></td>
                    </tr>
                </tbody>
            </table>
            <button type="button" class="w-100 btn-spacing btn btn-dark" onclick="agregarFila()">Agregar Producto</button>
        </div>

        <button type="submit" class="btn btn-dark btn-lg mt-4 w-100 btn-spacing">Guardar Compra</button>
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
            <td><input type="text" class="form-control" name="precio_costo_unitario${detalleIndex}" required></td>
            <td><button type="button" class="btn btn-danger btn-spacing" onclick="eliminarFila(this)">Eliminar</button></td>
        `;
    }
    function eliminarFila(button) {
        const fila = button.parentNode.parentNode;
        fila.parentNode.removeChild(fila);
        detalleIndex--;
        document.getElementById("cantidad_detalles").value = detalleIndex;
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz4fnFO9gybRnvF0vLvjITFfU6zHf2XgJh8m2p3yBb6jMcDkk4fYykd1t7" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq0v8Fq1aJXJOkt4JtSt8sHtDtjcU6l1f5cPQErh5ggf" crossorigin="anonymous"></script>
</body>
</html>

