package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Venta {
    private int id;
    private int no_factura;
    private String serie;
    private String fecha_factura;
    private String fecha_ingreso;
    private int id_cliente;
    private int id_empleado;
    private Conexion cn;

    public Venta() {
    }

    public Venta(int id, int no_factura, String serie, String fecha_factura, String fecha_ingreso, int id_cliente, int id_empleado) {
        this.id = id;
        this.no_factura = no_factura;
        this.serie = serie;
        this.fecha_factura = fecha_factura;
        this.fecha_ingreso = fecha_ingreso;
        this.id_cliente = id_cliente;
        this.id_empleado = id_empleado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNo_factura() {
        return no_factura;
    }

    public void setNo_factura(int no_factura) {
        this.no_factura = no_factura;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(String fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }
    
public boolean agregar(){
    try {
        cn = new Conexion();
        cn.abrirConexion();
        PreparedStatement parametro;
        String query = "INSERT INTO ventas(no_factura,serie,fecha_factura,fecha_ingreso,id_cliente,id_empleado) VALUES (?,?,?,?,?,?);";
        parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        parametro.setInt(1, getNo_factura());
        parametro.setString(2, getSerie());
        parametro.setString(3, getFecha_factura());
        parametro.setString(4, getFecha_ingreso());
        parametro.setInt(5, getId_cliente());
        parametro.setInt(6, getId_empleado());

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
