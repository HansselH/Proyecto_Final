<%@page import="modelo.Cliente" %>
<%@page import="javax.swing.table.DefaultTableModel" %>
<%@page import="java.util.HashMap" %>
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
        <title>Clientes</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <div style="display: flex; justify-content: center;">
        <h1>Formulario Clientes</h1>
        </div>
        
        <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#modal_cliente" onclick="limpiar()">Nuevo</button>
  
  <div class="modal" id="modal_cliente">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <!-- Modal body -->
      <div class="modal-body">
        <form action="sr_cliente" method="post" class="form-group">
                <label for="lbl_id"><b>ID:</b></label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>
                <label for="lbl_nombres"><b>Nombres:</b></label>
                <input type="text" name="txt_nombres" id="txt_nombres" class="form-control" placeholder="Ejemplo: Juan Ovidio" required>
                <label for="lbl_apellidos"><b>Apellidos:</b></label>
                <input type="text" name="txt_apellidos" id="txt_apellidos" class="form-control" placeholder="Ejemplo: Fernandez Mas" required>
                <label for="lbl_direccion"><b>NIT</b></label>
                <input type="text" name="txt_NIT" id="txt_NIT" class="form-control" placeholder="Ejemplo: 123456789" required>
                <label for="lbl_telefono"><b>Genero</b></label>
                <input type="text" name="txt_genero" id="txt_genero" class="form-control" placeholder="Ejemplo: M/F" required>
                <label for="lbl_DPI"><b>Telefono</b></label>
                <input type="number" name="txt_telefono" id="txt_telefono" class="form-control" placeholder="Ejemplo: 55555" required>
                <label for="lbl_genero"><b>Email</b></label>
                <input type="text" name="txt_email" id="txt_email" class="form-control" required>
                <label for="lbl_fn"><b>Fecha de Ingreso</b></label>
                <input type="datetime" name="txt_fecha_i" id="txt_fecha_i" class="form-control" required>
                
                <br>
                <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-primary">Agregar</button>
                <button name="btn_modificar" id="btn_modificar" value="modificar" class="btn btn-success">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-danger" onclick="javascript:if(!confirm('¿Desea eliminar?'))return false">Eliminar</button>
                <button type="button" class="btn btn-warning" data-bs-dismiss="modal">Cerrar</button>   
        </form>  
      </div>

      <!-- Modal footer -->

    </div>
  </div>
</div>
                <br>
   <table class="table table-striped">
    <thead>
      <tr>
        <th>Nombres</th>
        <th>Apellidos</th>
        <th>NIT</th>
        <th>Genero</th>
        <th>Telefono</th>
        <th>Email</th>
        <th>Fecha de Ingreso</th>
      </tr>
    </thead>
<tbody id="tbl_clientes">
        <%
        Cliente cliente = new Cliente();
        DefaultTableModel tabla = new DefaultTableModel();
        tabla = cliente.leer();
        for (int t=0;t<tabla.getRowCount();t++){
            out.println("<tr data-id=" + tabla.getValueAt(t, 0) + " data-id_p=" + tabla.getValueAt(t, 7) + ">");
            out.println("<td>"+ tabla.getValueAt(t, 1) + "</td>");
            out.println("<td>"+ tabla.getValueAt(t, 2) + "</td>");
            out.println("<td>"+ tabla.getValueAt(t, 3) + "</td>");
            out.println("<td>"+ tabla.getValueAt(t, 4) + "</td>");
            out.println("<td>"+ tabla.getValueAt(t, 5) + "</td>");
            out.println("<td>"+ tabla.getValueAt(t, 6) + "</td>");
            out.println("<td>"+ tabla.getValueAt(t, 7) + "</td>");
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
                $("#txt_nombres").val('');
                $("#txt_apellidos").val('');
                $("#txt_NIT").val('');
                $("#txt_genero").val('');
                $("#txt_telefono").val('');
                $("#txt_email").val('');  
                $("#txt_fecha_i").val('');
            }
            
            $('#tbl_clientes').on('click','tr td',function(evt){
                var target,id,nombres,apellidos,NIT,genero,telefono,correo_electronico,fecha_ingreso;
                target = $(event.target);
                id = target.parent().data('id');
                nombres = target.parent("tr").find("td").eq(0).html();
                apellidos = target.parent("tr").find("td").eq(1).html();
                NIT = target.parent("tr").find("td").eq(2).html();
                genero = target.parent("tr").find("td").eq(3).html();
                telefono = target.parent("tr").find("td").eq(4).html();
                correo_electronico = target.parent("tr").find("td").eq(5).html();
                fecha_ingreso = target.parent("tr").find("td").eq(6).html();
                $("#txt_id").val(id);
                $("#txt_nombres").val(nombres);
                $("#txt_apellidos").val(apellidos);
                $("#txt_NIT").val(NIT);
                $("#txt_genero").val(genero);
                $("#txt_telefono").val(telefono);
                $("#txt_email").val(correo_electronico);
                $("#txt_fecha_i").val(fecha_ingreso);
                $("#modal_cliente").modal('show');
    });
        </script>
            
    </body>
</html>
