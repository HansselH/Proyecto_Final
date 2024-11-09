/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
/**
 *

 */
public class Cliente {
    private String nombres;
    private String apellidos;
    private String NIT;
    private String genero;
    private String telefono;
    private String correo_electronico;
    private String fecha_ingreso;
    private int id;
    private Conexion cn;

    public Cliente() {
    }

    public Cliente(String nombres, String apellidos, String NIT, String genero, String telefono, String correo_electronico, String fecha_ingreso, int id) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.NIT = NIT;
        this.genero = genero;
        this.telefono = telefono;
        this.correo_electronico = correo_electronico;
        this.fecha_ingreso = fecha_ingreso;
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

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
     public HashMap drop_cliente(){
      HashMap<String,String> drop = new HashMap();
      try{
          cn = new Conexion();
          String query= "select id_cliente as id,nombres,apellidos,NIT,genero,telefono,correo_electronico,fecha_ingreso from clientes;";
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
        try{
            cn = new Conexion();
            cn.abrirConexion();
                String query = "select id_cliente as id,nombres,apellidos,NIT,genero,telefono,correo_electronico,fecha_ingreso from clientes;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"id","Nombres","Apellidos","NIT","Genero","Telefono","Email","Fecha_ingreso"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[]=new String[8];
            while(consulta.next()){
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("nombres");
                datos[2] = consulta.getString("apellidos");
                datos[3] = consulta.getString("NIT");
                datos[4] = consulta.getString("genero");
                datos[5] = consulta.getString("telefono");
                datos[6] = consulta.getString("correo_electronico");
                datos[7] = consulta.getString("fecha_ingreso");

                tabla.addRow(datos);
            }
            cn.cerrarConexion();   
        }catch(SQLException ex){
            System.out.println("Error: " + ex.getMessage() );
        }
        return tabla;
  }

    public int agregar(){
     int retorno = 0;
     try{
         PreparedStatement parametro;
         cn = new Conexion();
         cn.abrirConexion();
         String query = "INSERT INTO clientes(nombres,apellidos,NIT,genero,telefono,correo_electronico,fecha_ingreso) VALUES (?,?,?,?,?,?,?);";
         parametro  = (PreparedStatement) cn.conexionBD.prepareStatement(query);
         parametro.setString(1, getNombres());
         parametro.setString(2, getApellidos());
         parametro.setString(3, getNIT());
         parametro.setString(4, getGenero());
         parametro.setString(5, getTelefono());
         parametro.setString(6, getCorreo_electronico());
         parametro.setString(7, getFecha_ingreso());
         
         retorno = parametro.executeUpdate();
         cn.cerrarConexion();
     }catch(HeadlessException | SQLException ex){
         System.out.println("Error"+ex.getMessage());
         retorno = 0;
     }
     return retorno;
}
    
 public int actualizar(){
        int retorno = 0;
        try{
         PreparedStatement parametro;
         cn = new Conexion();
         cn.abrirConexion();
         String query = "UPDATE clientes set nombres=?,apellidos=?,NIT=?,genero=?,telefono=?,correo_electronico=?,fecha_ingreso=? where id_cliente=?;";
         parametro  = (PreparedStatement) cn.conexionBD.prepareStatement(query);
         parametro.setString(1, getNombres());
         parametro.setString(2, getApellidos());
         parametro.setString(3, getNIT());
         parametro.setString(4, getGenero());
         parametro.setString(5, getTelefono());
         parametro.setString(6, getCorreo_electronico());
         parametro.setString(7, getFecha_ingreso());
         parametro.setInt(8, getId());
         
         retorno = parametro.executeUpdate();
         cn.cerrarConexion();
     }catch(HeadlessException | SQLException ex){
         System.out.println("Error"+ex.getMessage());
         retorno = 0;
     }
     return retorno;
   }
 
   
   public int eliminar(){
            int retorno = 0;
        try{
         PreparedStatement parametro;
         cn = new Conexion();
         cn.abrirConexion();
         String query = "DELETE FROM clientes WHERE id_cliente=?;";
         parametro  = (PreparedStatement) cn.conexionBD.prepareStatement(query);
         parametro.setInt(1, getId());
         
         retorno = parametro.executeUpdate();
         cn.cerrarConexion();
     }catch(HeadlessException | SQLException ex){
         System.out.println("Error"+ex.getMessage());
         retorno = 0;
     }
     return retorno;
   }


    
   
}