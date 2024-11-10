<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="modelo.Proveedor"%>
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
        <title>Proveedores</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <div style="display: flex; justify-content: center;">
        <h1>Formulario Proveedores</h1>
        </div>
        <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#modal_proveedor" onclick="limpiar()">Nuevo</button>
        
        <div class="modal" id="modal_proveedor">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                            <form action="sr_proveedor" method="post" class="form-group">
                            <label for="lbl_id"><b>ID:</b></label>
                            <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>
                            <label for="lbl_proveedor"><b>Proveedor</b></label>
                            <input type="text" name="txt_proveedor" id="txt_proveedor" class="form-control" placeholder="Ejemplo: Patito" required>
                            <label for="lbl_nit"><b>NIT</b></label>
                            <input type="text" name="txt_nit" id="txt_nit" class="form-control" placeholder="Ejemplo: 12345678" required>
                            <label for="lbl_direccion"><b>Direccion</b></label>
                            <input type="text" name="txt_direccion" id="txt_direccion" class="form-control" placeholder="Ejemplo: 6ta Calle 0-00" required>
                            <label for="lbl_direccion"><b>Telefono</b></label>
                            <input type="text" name="txt_telefono" id="txt_telefono" class="form-control" placeholder="Ejemplo: 55555555" required>
                           
                            <br>
                            <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-primary">Agregar</button>
                            <button name="btn_modificar" id="btn_modificar" value="modificar" class="btn btn-success">Modificar</button>
                            <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-danger" onclick="javascript:if(!confirm('¿Desea eliminar?'))return false">Eliminar</button>
                            <button type="button" class="btn btn-warning" data-bs-dismiss="modal">Cerrar</button>
                            </form>  
                    </div>
                    

                </div>
            </div>
        </div>
        
        <br>
        
        <table class="table table-striped">
            <thead>
                <tr>
                  <th>Codigo</th>
                  <th>Proveedor</th>
                  <th>NIT</th>
                  <th>Direccion</th>
                  <th>Telefono</th>
                </tr>
            </thead>
            <tbody id="tbl_proveedores">
                <%
                 Proveedor proveedor = new Proveedor();
                 DefaultTableModel tabla = new DefaultTableModel();
                 tabla = proveedor.leer();
                 for (int t=0;t<tabla.getRowCount();t++){
                 out.println("<tr data-id" + tabla.getValueAt(t, 0) + ">");
                 out.println("<td>"+ tabla.getValueAt(t, 0) + "</td>");
                 out.println("<td>"+ tabla.getValueAt(t, 1) + "</td>");  
                 out.println("<td>"+ tabla.getValueAt(t, 2) + "</td>");
                 out.println("<td>"+ tabla.getValueAt(t, 3) + "</td>");
                 out.println("<td>"+ tabla.getValueAt(t, 4) + "</td>");
                 out.println("</tr>");
                }
                %>
            </tbody>
        </table>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script type="text/javascript">
            function limpiar(){
                $("#txt_id").val(0);
                $("#txt_proveedor").val('');
                $("#txt_nit").val('');
                $("#txt_direccion").val('');
                $("#txt_telefono").val('');

            }
            
            $('#tbl_proveedores').on('click','tr td',function(evt){
               var target,id,proveedor,nit,direccion,telefono;
               target = $(event.target);
               id = target.parent().data('id');
               id = target.parent("tr").find("td").eq(0).html();
               proveedor = target.parent("tr").find("td").eq(1).html();
               nit = target.parent("tr").find("td").eq(2).html();
               direccion = target.parent("tr").find("td").eq(3).html();
               telefono = target.parent("tr").find("td").eq(4).html();

               $("#txt_id").val(id);
               $("#txt_proveedor").val(proveedor);
               $("#txt_nit").val(nit);
               $("#txt_direccion").val(direccion);
               $("#txt_telefono").val(telefono);
               $("#modal_proveedor").modal('show');
            });
        </script>
    </body>
</html>
