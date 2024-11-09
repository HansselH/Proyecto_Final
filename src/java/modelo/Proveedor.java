package modelo;

/**
 *
 * 
 */
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;

public class Proveedor {
    private String proveedor;
    private String nit;
    private String direccion;
    private String telefono;
    private int id;
    private Conexion cn;

    public Proveedor() {
    }

    public Proveedor(String proveedor, String nit, String direccion, String telefono, int id) {
        this.proveedor = proveedor;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.id = id;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
     public HashMap drop_proveedor(){
      HashMap<String,String> drop = new HashMap();
      try{
          cn = new Conexion();
          String query= "select id_proveedor as id,proveedor,nit,direccion,telefono from proveedores;";
          cn.abrirConexion();
          ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
          while (consulta.next()){
             drop.put(consulta.getString("id"), consulta.getNString("proveedor"));
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
            String query = "SELECT id_proveedor as id,proveedor,nit,direccion,telefono FROM proveedores;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"id","proveedor","nit","direccion","telefono"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[5];
            while (consulta.next()) {
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("proveedor");
                datos[2] = consulta.getString("nit");
                datos[3] = consulta.getString("direccion");
                datos[4] = consulta.getString("telefono");
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
            try{
                cn = new Conexion();
                cn.abrirConexion();
                PreparedStatement parametro;
                String query = "INSERT INTO proveedores (proveedor,nit,direccion,telefono) VALUES (?,?,?,?);";
                parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
                parametro.setString(1, getProveedor());
                parametro.setString(2, getNit());
                parametro.setString(3, getDireccion());
                parametro.setString(4, getTelefono());
                
                retorno = parametro.executeUpdate();
                
                cn.cerrarConexion();
                
            }catch(HeadlessException | SQLException ex){
                System.out.println("Error"+ex.getMessage());
                retorno = 0;
            }
            return retorno;
        }
        
        public int modificar(){
            int retorno = 0;
            try{
                cn = new Conexion();
                cn.abrirConexion();
                PreparedStatement parametro;
                String query = "UPDATE proveedores SET proveedor=?,nit=?,direccion=?,telefono=? WHERE id_proveedor=?;";
                parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
                parametro.setString(1, getProveedor());
                parametro.setString(2, getNit());
                parametro.setString(3, getDireccion());
                parametro.setString(4, getTelefono());
                parametro.setInt(5, getId());

                
                retorno = parametro.executeUpdate();
                
                cn.cerrarConexion();
                
            }catch(SQLException ex){
                System.out.println("Error"+ex.getMessage());
                retorno = 0;
            }
            return retorno;
        }
        
        public int eliminar(){
            int retorno = 0;
            try{
                cn = new Conexion();
                cn.abrirConexion();
                PreparedStatement parametro;
                String query = "DELETE FROM proveedores WHERE id_proveedor=?;";
                parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
                parametro.setInt(1, getId());

                
                retorno = parametro.executeUpdate();
                
                cn.cerrarConexion();
                
            }catch(SQLException ex){
                System.out.println("Error"+ex.getMessage());
                retorno = 0;
            }
            return retorno;
        }
}
