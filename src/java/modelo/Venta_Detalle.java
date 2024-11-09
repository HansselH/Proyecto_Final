package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Venta_Detalle {
    private int id;
    private int cantidad;
    private String precio_unitario;
    private int id_venta;
    private int id_producto;
    private Conexion cn;

    public Venta_Detalle() {
    }

    public Venta_Detalle(int id, int cantidad, String precio_unitario, int id_venta, int id_producto) {
        this.id = id;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.id_venta = id_venta;
        this.id_producto = id_producto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(String precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
    
    public boolean agregar(){
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.abrirConexion();
            PreparedStatement parametro;
            String query = "INSERT INTO ventas_detalle(cantidad,precio_unitario,id_venta,id_producto) VALUES (?,?,?,?);";
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getCantidad());
            parametro.setString(2, getPrecio_unitario());
            parametro.setInt(3, getId_venta());
            parametro.setInt(4, getId_producto());
            int rowsAffected = parametro.executeUpdate();
            
            cn.cerrarConexion();
            
            return rowsAffected > 0;
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return false;
    }
    
}
