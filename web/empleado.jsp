<%@page import="modelo.Puesto" %>
<%@page import="modelo.Empleados" %>
<%@page import="javax.swing.table.DefaultTableModel" %>
<%@page import="java.util.HashMap" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Empleados</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <h1>Formulario Empleados</h1>
      
        <button type="button" class="btn btn-primary">
            <a href='puesto.jsp' style="color: white;" text-decoration: none;>Puestos</a>
        </button>
        <br>
        <br>
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modal_empleado" onclick="limpiar()">Nuevo</button>
  
  <div class="modal" id="modal_empleado">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <!-- Modal body -->
      <div class="modal-body">
        <form action="sr_empleado" method="post" class="form-group">
                <label for="lbl_id"><b>ID:</b></label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>
                <label for="lbl_nombres"><b>Nombres:</b></label>
                <input type="text" name="txt_nombres" id="txt_nombres" class="form-control" placeholder="Juan Ovidio" required>
                <label for="lbl_apellidos"><b>Apellidos:</<b></label>
                <input type="text" name="txt_apellidos" id="txt_apellidos" class="form-control" placeholder="Ejemplo: Perez Mas" required>
                <label for="lbl_direccion"><b>Direccion:</<b></label>
                <input type="text" name="txt_direccion" id="txt_direccion" class="form-control" placeholder="Ejemplo: 4ta Calle 0-00" required>
                <label for="lbl_telefono"><b>Telefono:</<b></label>
                <input type="number" name="txt_telefono" id="txt_telefono" class="form-control" placeholder="Ejemplo: 11111111" required>
                <label for="lbl_DPI"><b>DPI:</<b></label>
                <input type="number" name="txt_DPI" id="txt_DPI" class="form-control" placeholder="Ejemplo: 1111 11111 1111" required>
                <label for="lbl_genero"><b>Genero:</<b></label>
                <input type="text" name="txt_genero" id="txt_genero" class="form-control" placeholder="Ejemplo: M/F" required>
                <label for="lbl_fn"><b>Fecha de Nacimiento</<b></label>
                <input type="date" name="txt_fecha_n" id="txt_fecha_n" class="form-control" required>
                <label for="lbl_fil"><b>Fecha Inicio Labores</<b></label>
                <input type="date" name="txt_fecha_il" id="txt_fecha_il" class="form-control" required>
                <label for="lbl_fi"><b>Fecha Ingreso</<b></label>
                <input type="datetime" name="txt_fecha_i" id="txt_fecha_i" class="form-control" required>
                <label for="lbl_puesto"><b>Puesto</<b></label>
                <select name="drop_puesto" id="drop_puesto" class="form-control">
                    <%
                        Puesto puesto = new Puesto();
                        HashMap<String,String> drop = puesto.drop_puesto();
                        for (String i: drop.keySet()){
                            out.println("<option value='"+ i + "'>" + drop.get(i) +"</option>");
                        }
                    %>
                </select>
                <br>
                <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-primary">Agregar</button>
                <button name="btn_modificar" id="btn_modificar" value="modificar" class="btn btn-success">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-danger" onclick="javascript:if(!confirm('¿Desea eliminar?'))return false">Eliminar</button>
            </form>  
      </div>

      <!-- Modal footer -->
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
        <th>Nombres</th>
        <th>Apellidos</th>
        <th>Direccion</th>
        <th>Telefono</th>
        <th>DPI</th>
        <th>Genero</th>
        <th>Fecha de Nacimiento</th>
        <th>Fecha Inicio Labores</th>
        <th>Fecha Ingreso</th>
        <th>Puesto</th>
      </tr>
    </thead>
<tbody id="tbl_empleados">
        <%
        Empleados empleado = new Empleados();
        DefaultTableModel tabla = new DefaultTableModel();
        tabla = empleado.leer();
        for (int t=0;t<tabla.getRowCount();t++){
            out.println("<tr data-id=" + tabla.getValueAt(t, 0) + " data-id_p=" + tabla.getValueAt(t, 10) + ">");
            out.println("<td>"+ tabla.getValueAt(t, 1) + "</td>");
            out.println("<td>"+ tabla.getValueAt(t, 2) + "</td>");
            out.println("<td>"+ tabla.getValueAt(t, 3) + "</td>");
            out.println("<td>"+ tabla.getValueAt(t, 4) + "</td>");
            out.println("<td>"+ tabla.getValueAt(t, 5) + "</td>");
            out.println("<td>"+ tabla.getValueAt(t, 6) + "</td>");
            out.println("<td>"+ tabla.getValueAt(t, 7) + "</td>");
            out.println("<td>"+ tabla.getValueAt(t, 8) + "</td>");
            out.println("<td>"+ tabla.getValueAt(t, 9) + "</td>");
            out.println("<td>"+ tabla.getValueAt(t, 10) + "</td>");
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
                $("#txt_direccion").val('');
                $("#txt_telefono").val('');
                $("#txt_DPI").val('');
                $("#txt_genero").val('');  
                $("#txt_fecha_n").val('');
                $("#txt_fecha_il").val('');
                $("#txt_fecha_i").val('');
                $("#drop_puesto").val(1);
            }
            
            $('#tbl_empleados').on('click','tr td',function(evt){
                var target,id,id_p,nombres,apellidos,direccion,telefono,DPI,genero,fecha_nacimiento,fecha_inicio_labores,fecha_ingreso;
                target = $(event.target);
                id = target.parent().data('id');
                id_p = target.parent().data('id_p');
                nombres = target.parent("tr").find("td").eq(0).html();
                apellidos = target.parent("tr").find("td").eq(1).html();
                direccion = target.parent("tr").find("td").eq(2).html();
                telefono = target.parent("tr").find("td").eq(3).html();
                DPI = target.parent("tr").find("td").eq(4).html();
                genero = target.parent("tr").find("td").eq(5).html();
                fecha_nacimiento = target.parent("tr").find("td").eq(6).html();
                fecha_inicio_labores = target.parent("tr").find("td").eq(7).html();
                fecha_ingreso = target.parent("tr").find("td").eq(8).html();
                $("#txt_id").val(id);
                $("#txt_nombres").val(nombres);
                $("#txt_apellidos").val(apellidos);
                $("#txt_direccion").val(direccion);
                $("#txt_telefono").val(telefono);
                $("#txt_DPI").val(DPI);
                $("#txt_genero").val(genero);  
                $("#txt_fecha_n").val(fecha_nacimiento);
                $("#txt_fecha_il").val(fecha_inicio_labores);
                $("#txt_fecha_i").val(fecha_ingreso);
                $("#drop_puesto").val(id_p);
                $("#modal_empleado").modal('show');
    });
        </script>
            
    </body>
</html>
