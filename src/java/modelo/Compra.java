/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
 *
 * @author Hanssel
 */
public class Compra {
    private int id;
    private int no_orden_compra;
    private String fecha_orden;
    private String fecha_ingreso;
    private int id_proveedor;
    private Conexion cn;

    public Compra() {}

    
    
    public Compra(int id, int no_orden_compra, String fecha_orden, String fecha_ingreso, int id_proveedor) {
        this.id = id;
        this.no_orden_compra = no_orden_compra;
        this.fecha_orden = fecha_orden;
        this.fecha_ingreso = fecha_ingreso;
        this.id_proveedor = id_proveedor;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNo_orden_compra() {
        return no_orden_compra;
    }

    public void setNo_orden_compra(int no_orden_compra) {
        this.no_orden_compra = no_orden_compra;
    }

    public String getFecha_orden() {
        return fecha_orden;
    }

    public void setFecha_orden(String fecha_orden) {
        this.fecha_orden = fecha_orden;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    
 public boolean agregar(){
    try {
        cn = new Conexion();
        cn.abrirConexion();
        PreparedStatement parametro;
        String query = "INSERT INTO compras(no_orden_compra,fecha_orden,fecha_ingreso,id_proveedor) VALUES (?,?,?,?);";
        parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        parametro.setInt(1, getNo_orden_compra());
        parametro.setString(2, getFecha_orden());
        parametro.setString(3, getFecha_ingreso());
        parametro.setInt(4, getId_proveedor());
    

        int rowsAffected = parametro.executeUpdate();

        if (rowsAffected > 0) {
            // Recupera el ID generado de la venta
            try (ResultSet generatedKeys = parametro.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    setId(generatedKeys.getInt(1));  // Establece el ID de la venta
                }
            }
            cn.cerrarConexion();
            return true;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return false;  // Retorna false si algo sale mal
}   
    
}

