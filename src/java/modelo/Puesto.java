/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
//import modelo.Conexion;

/**
 *
 * @author ccu33
 */
public class Puesto {
    private int id;
    private String puesto;
    private Conexion cn; 

    public Puesto(){}
    public Puesto(int id, String puesto) {
        this.id = id;
        this.puesto = puesto;
    }
    
    

    public int getId_puesto() {
        return id;
    }

    public void setId_puesto(int id) {
        this.id = id;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    
    
    
    
     public HashMap drop_puesto(){
      HashMap<String,String> drop = new HashMap();
      try{
          cn = new Conexion();
          String query= "SELECT id_puesto as id,puesto FROM puestos;";
          cn.abrirConexion();
          ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
          while (consulta.next()){
             drop.put(consulta.getString("id"), consulta.getNString("puesto"));
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
      String query = "SELECT id_puesto as id,puesto FROM puestos;";
      ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
      String encabezado[] = {"id_puesto","puesto"};
      tabla.setColumnIdentifiers(encabezado);
      String datos[] = new String[2];
      while (consulta.next()){
          datos[0] = consulta.getString("id");
          datos[1] = consulta.getString("puesto");
          tabla.addRow(datos);
      
      }
      
     cn.cerrarConexion();
 }catch(SQLException ex){
     System.out.println(ex.getMessage());
 }
 return tabla;
 }
    //CRUD
     
     public int agregar() {
         int retorno =0;
     
    try {
        PreparedStatement parametro;
        cn = new Conexion();
        String query = "INSERT INTO puestos (puesto) VALUES (?);";
        cn.abrirConexion();
        parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
        parametro.setString(1, getPuesto());
        
        retorno= parametro.executeUpdate();
        cn.cerrarConexion();
        
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
        retorno = 0;
    }
 
    
return retorno;
}
     public int actualizar() {
         int retorno=0;
    try {
        PreparedStatement parametro;
        cn = new Conexion();
        String query = "UPDATE puestos SET puesto = ? WHERE id_puesto = ?;";
        cn.abrirConexion();
         parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
        parametro.setString(1, getPuesto());
        parametro.setInt(2, getId_puesto());
        retorno= parametro.executeUpdate();
        cn.cerrarConexion();
        
        }catch(SQLException ex){
        System.out.println(ex.getMessage());
        retorno = 0;
    }
    
    
return retorno;    
}



     public int eliminar() {
     int retorno =0;
    try {
        PreparedStatement parametro;
        cn = new Conexion();
        String query = "DELETE FROM puestos WHERE id_puesto = ?";
        cn.abrirConexion();
        parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
        
        parametro.setInt(1, this.getId_puesto());
        
        retorno= parametro.executeUpdate();
        cn.cerrarConexion();
        
        }catch(SQLException ex){
        System.out.println(ex.getMessage());
        retorno = 0;
    }
    
    
return retorno;    
}   
     

}


