<%@page import="javax.swing.table.DefaultTableModel"%>
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
        <title>Marcas</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <h1>Formulario Marcas</h1>
        
        <button type="button" class="btn btn-primary">
            <a href='producto.jsp' style="color: white;" text-decoration: none;>Productos</a>
        </button>
        
        <br><br>
        
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modal_empleado" onclick="limpiar()">Nuevo</button>
        
        <div class="modal" id="modal_empleado">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                            <form action="sr_marca" method="post" class="form-group">
                            <label for="lbl_id"><b>ID:</b></label>
                            <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>
                            <label for="lbl_codigo"><b>Marca:</b></label>
                            <input type="text" name="txt_marca" id="txt_marca" class="form-control" placeholder="Ejemplo: Marca" required>
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
                  <th>Codigo</th>
                  <th>Marca</th>
                </tr>
            </thead>
            <tbody id="tbl_marcas">
                <%
                 Marcas marca = new Marcas();
                 DefaultTableModel tabla = new DefaultTableModel();
                 tabla = marca.leer();
                 for (int t=0;t<tabla.getRowCount();t++){
                 out.println("<tr data-id" + tabla.getValueAt(t, 0) + ">");
                 out.println("<td>"+ tabla.getValueAt(t, 0) + "</td>");
                 out.println("<td>"+ tabla.getValueAt(t, 1) + "</td>");  
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
                $("#txt_marca").val('');
            }
            
            $('#tbl_marcas').on('click','tr td',function(evt){
               var target,id,marca;
               target = $(event.target);
               id = target.parent().data('id');
               id = target.parent("tr").find("td").eq(0).html();
               marca = target.parent("tr").find("td").eq(1).html();
               $("#txt_id").val(id);
               $("#txt_marca").val(marca);
               $("#modal_empleado").modal('show');
            });
        </script>
    </body>
</html>
