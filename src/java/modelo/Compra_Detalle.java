/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author Hanssel
 */
public class Compra_Detalle {
    private int id;
    private int cantidad;
    private String precio_costo_unitario;
    private int id_compra;
    private int id_producto;
    private Conexion cn;

    public Compra_Detalle() {}

    
    
    public Compra_Detalle(int id, int cantidad, String precio_costo_unitario, int id_compra, int id_producto) {
        this.id = id;
        this.cantidad = cantidad;
        this.precio_costo_unitario = precio_costo_unitario;
        this.id_compra = id_compra;
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

    public String getPrecio_costo_unitario() {
        return precio_costo_unitario;
    }

    public void setPrecio_costo_unitario(String precio_costo_unitario) {
        this.precio_costo_unitario = precio_costo_unitario;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
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
            String query = "INSERT INTO compras_detalle(cantidad,precio_costo_unitario,id_compra,id_producto) VALUES (?,?,?,?);";
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getCantidad());
            parametro.setString(2, getPrecio_costo_unitario());
            parametro.setInt(3, getId_compra());
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
