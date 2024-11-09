<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="modelo.Productos"%>
<%@page import="java.util.HashMap"%>
<%@page import="modelo.Marcas"%>
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
        <title>Productos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    </head>
    <body>
        <h1>Formulario Productos</h1>
        
        <button type="button" class="btn btn-primary">
            <a href='marca.jsp' style="color: white;" text-decoration: none;>Marcas</a>
        </button>
        
        <br><br>
        
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modal_producto" onclick="limpiar()">Nuevo</button>

           <div class="modal" id="modal_producto">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                            <form action="sr_productos" method="post" class="form-group">
                            <label for="lbl_id"><b>ID:</b></label>
                            <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>
                            <label for="lbl_producto"><b>Producto</b></label>
                            <input type="text" name="txt_producto" id="txt_producto" class="form-control" placeholder="Ejemplo: Producto1" required>
                            <label for="lbl_descripcion"><b>Descripcion</b></label>
                            <input type="text" name="txt_descripcion" id="txt_descripcion" class="form-control" placeholder="Ejemplo: descripcion1" required>
                            <label for="lbl_imagen"><b>Imagen</b></label>
                            <input type="file" name="txt_imagen" id="txt_imagen" class="form-control" placeholder="Ejemplo: .jpg" required>
                            <label for="lbl_precio_c"><b>Precio Costo</b></label>
                            <input type="text" name="txt_precio_c" id="txt_precio_c" class="form-control" placeholder="Ejemplo: 10.01" required>
                            <label for="lbl_precio_v"><b>Precio Venta</b></label>
                            <input type="text" name="txt_precio_v" id="txt_precio_v" class="form-control" placeholder="Ejemplo: 10.01" required>
                            <label for="lbl_existencia"><b>Existencia</b></label>
                            <input type="text" name="txt_existencia" id="txt_existencia" class="form-control" placeholder="Ejemplo: 1" required>
                            <label for="lbl_fecha_i"><b>Fecha Ingreso</b></label>
                            <input type="datetime" name="txt_fecha_i" id="txt_fecha_i" class="form-control" placeholder="Ejemplo: 10.01" >
                            <label for="lbl_marca"><b>Marca</b></label>
                            <select name="drop_marca" id="drop_marca" class="form-control">
                                   <option value="" disabled selected>Seleccione una marca</option>
                                <%
                                Marcas marca = new Marcas();
                                HashMap<String,String> drop = marca.drop_marca();
                                for (String i: drop.keySet()){
                                    out.println("<option value='"+ i + "'>" + drop.get(i) + "</option>");
                                }
                                %>
                            </select>
                            
                                <%--<label for="imagen">Imagen del Producto:</label>
                            <input type="file" name="imagen" accept="image/*" required>--%>

                            
                            
                            <br>
                            <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-primary">Agregar</button>
                            <button name="btn_modificar" id="btn_modificar" value="modificar" class="btn btn-success">Modificar</button>
                            <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-danger" onclick="javascript:if(!confirm('¿Desea eliminar?'))return false">Eliminar</button>
                        </form>  
                    </div>
                    
                    <div class="modal-footer">
                     <button type="button" class="btn btn-warning" data-bs-dismiss="modal">Cerrar</button>
                    </div>
                    
                </div>
            </div>
        </div>
                            
        <br>
        <table class="table table-striped">
    <thead>
      <tr>
        <th>Producto</th>
        <th>Descripcion</th>
        <th>Imagen</th>
        <th>Precio Costo</th>
        <th>Precio Venta</th>
        <th>Existencia</th>
        <th>Fecha Ingreso</th>
        <th>Marca</th>
      </tr>
    </thead>
    <tbody id="tbl_productos">
        <%
            Productos productos = new Productos();
            DefaultTableModel tabla = new DefaultTableModel();
            tabla = productos.leer();
            for (int t=0;t<tabla.getRowCount();t++){
            out.println("<tr data-id" + tabla.getValueAt(t,0) + "data-id_m=" + tabla.getValueAt(t,8) + ">");
            out.println("<td>" + tabla.getValueAt(t, 1) + "</td>");
            out.println("<td>" + tabla.getValueAt(t, 2) + "</td>");
            out.println("<td>" + tabla.getValueAt(t, 3) + "</td>");
            out.println("<td>" + tabla.getValueAt(t, 4) + "</td>");
            out.println("<td>" + tabla.getValueAt(t, 5) + "</td>");
            out.println("<td>" + tabla.getValueAt(t, 6) + "</td>");
            out.println("<td>" + tabla.getValueAt(t, 7) + "</td>");
            out.println("<td>" + tabla.getValueAt(t, 8) + "</td>");

            }
        %>
        </tbody>
        </table>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script type="text/javascript">
            function limpiar(){
                $("#txt_id").val(0);
                $("#txt_producto").val('');
                $("#txt_descripcion").val('');
                $("#txt_imagen").val('');
                $("#txt_precio_c").val('');
                $("#txt_precio_v").val('');
                $("#txt_existencia").val('');
                $("#txt_fecha_i").val('');
                $("#drop_marca").val(1);
            }
            
            $('#tbl_productos').on('click','tr td',function(evt) {
                var target,id,id_m,producto,descripcion,imagen,precio_costo,precio_venta,existencia,fecha_ingreso;
                target = $(event.target);
                id = target.parent().data('id');
                id_m = target.parent().data('id_m');
                producto = target.parent("tr").find("td").eq(0).html();
                descripcion = target.parent("tr").find("td").eq(1).html();
                imagen = target.parent("tr").find("td").eq(2).html();
                precio_costo = target.parent("tr").find("td").eq(3).html();
                precio_venta = target.parent("tr").find("td").eq(4).html();
                existencia = target.parent("tr").find("td").eq(5).html();
                fecha_ingreso = target.parent("tr").find("td").eq(6).html();
                $("#txt_id").val(id);
                $("#txt_producto").val(producto);
                $("#txt_descripcion").val(descripcion);
                $("#txt_imagen").val(imagen);
                $("#txt_precio_c").val(precio_costo);
                $("#txt_precio_v").val(precio_venta);
                $("#txt_existencia").val(existencia);
                $("#txt_fecha_i").val(fecha_ingreso);
                $("#drop_marca").val(id_m);
                $("#modal_producto").modal('show');
    });
        </script>
    </body>
</html>
