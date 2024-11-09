package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

public class Empleados {
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String DPI;
    private String genero;
    private String fecha_nacimiento;
    private String fecha_inicio_labores;
    private String fecha_ingreso;
    private int id_puesto;
    private int id;
    private Conexion cn;

    public Empleados() {
    }

    public Empleados(String nombres, String apellidos, String direccion, String telefono, String DPI, String genero, String fecha_nacimiento, String fecha_inicio_labores, String fecha_ingreso, int id_puesto, int id) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.DPI = DPI;
        this.genero = genero;
        this.fecha_nacimiento = fecha_nacimiento;
        this.fecha_inicio_labores = fecha_inicio_labores;
        this.fecha_ingreso = fecha_ingreso;
        this.id_puesto = id_puesto;
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDPI() {
        return DPI;
    }

    public void setDPI(String DPI) {
        this.DPI = DPI;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getFecha_inicio_labores() {
        return fecha_inicio_labores;
    }

    public void setFecha_inicio_labores(String fecha_inicio_labores) {
        this.fecha_inicio_labores = fecha_inicio_labores;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
         public HashMap drop_empleado(){
      HashMap<String,String> drop = new HashMap();
      try{
          cn = new Conexion();
          String query= "SELECT id_empleado as id,nombres,apellidos,direccion,telefono,DPI,genero,fecha_nacimiento,fecha_inicio_labores,fecha_ingreso,id_puesto FROM empleados;";
          cn.abrirConexion();
          ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
          while (consulta.next()){
             drop.put(consulta.getString("id"), consulta.getNString("nombres"));
          }
          cn.cerrarConexion();
          
      } catch(SQLException ex){
           System.out.println(ex.getMessage());
      }
      return drop;
    }
    
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrirConexion();
            String query = "SELECT e.id_empleado as id,e.nombres,e.apellidos,e.direccion,e.telefono,e.DPI,e.genero,e.fecha_nacimiento,e.fecha_inicio_labores,e.fecha_ingreso,e.id_puesto FROM empleados as e inner join puestos as p on p.id_puesto = e.id_puesto;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"id","nombres","apellidos","direccion","telefono","DPI","genero","fecha_nacimiento","fecha_inicio_labores","fecha_ingreso","id_puesto"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[11];
            while (consulta.next()) {
                //System.out.println("Id: " + consulta.getString("id"));
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("nombres");
                datos[2] = consulta.getString("apellidos");
                datos[3] = consulta.getString("direccion");
                datos[4] = consulta.getString("telefono");
                datos[5] = consulta.getString("DPI");
                datos[6] = consulta.getString("genero");
                datos[7] = consulta.getString("fecha_nacimiento");
                datos[8] = consulta.getString("fecha_inicio_labores");
                datos[9] = consulta.getString("fecha_ingreso");
                datos[10] = consulta.getString("id_puesto");

                tabla.addRow(datos);
            }
            cn.cerrarConexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return tabla;
    }
    
    public int agregar(){
        int retorno = 0;
        try {
            cn = new Conexion();
            PreparedStatement parametro;
            String query = "INSERT INTO empleados(nombres,apellidos,direccion,telefono,DPI,genero,fecha_nacimiento,fecha_inicio_labores,fecha_ingreso,id_puesto)VALUES(?,?,?,?,?,?,?,?,?,?);";
            cn.abrirConexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNombres());
            parametro.setString(2, getApellidos());
            parametro.setString(3, getDireccion());
            parametro.setString(4, getTelefono());
            parametro.setString(5, getDPI());
            parametro.setString(6, getGenero());
            parametro.setString(7, getFecha_nacimiento());
            parametro.setString(8, getFecha_inicio_labores());
            parametro.setString(9, getFecha_ingreso());
            parametro.setInt(10, getId_puesto());

            retorno = parametro.executeUpdate();
            
            cn.cerrarConexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }
    
    public int modificar(){
        int retorno = 0;
        try {
            cn = new Conexion();
            PreparedStatement parametro;
            String query = "UPDATE empleados SET nombres=?,apellidos=?,direccion=?,telefono=?,DPI=?,genero=?,fecha_nacimiento=?,fecha_inicio_labores=?,fecha_ingreso=?,id_puesto=? WHERE id_empleado=?;";
            cn.abrirConexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNombres());
            parametro.setString(2, getApellidos());
            parametro.setString(3, getDireccion());
            parametro.setString(4, getTelefono());
            parametro.setString(5, getDPI());
            parametro.setString(6, getGenero());
            parametro.setString(7, getFecha_nacimiento());
            parametro.setString(8, getFecha_inicio_labores());
            parametro.setString(9, getFecha_ingreso());
            parametro.setInt(10, getId_puesto());
            parametro.setInt(11, getId());

            retorno = parametro.executeUpdate();
            
            cn.cerrarConexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }
    
    public int eliminar(){
        int retorno = 0;
        try {
            cn = new Conexion();
            PreparedStatement parametro;
            String query = "DELETE FROM empleados WHERE id_empleado=?;";
            cn.abrirConexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId());

            retorno = parametro.executeUpdate();
            
            cn.cerrarConexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }
}
