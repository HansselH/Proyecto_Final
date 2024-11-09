package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Productos {
    private String producto;
    private String descripcion;
    private String imagen;
    private double precio_costo;
    private double precio_venta;
    private int existencia;
    private String fecha_ingreso;
    private int id_marca;
    private int id;
    private Conexion cn;

    public Productos() {
    }

    public Productos(String producto, String descripcion, String imagen, double precio_costo, double precio_venta, int existencia, String fecha_ingreso, int id_marca, int id) {
        this.producto = producto;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.precio_costo = precio_costo;
        this.precio_venta = precio_venta;
        this.existencia = existencia;
        this.fecha_ingreso = fecha_ingreso;
        this.id_marca = id_marca;
        this.id = id;
    }

    // Getters y Setters

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public double getPrecio_costo() {
        return precio_costo;
    }

    public void setPrecio_costo(double precio_costo) {
        this.precio_costo = precio_costo;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrirConexion();
            String query = "SELECT p.id_producto as id,p.producto,p.descripcion,p.imagen,p.precio_costo,p.precio_venta,p.existencia,p.fecha_ingreso,p.id_marca FROM productos as p inner join marcas as m on m.id_marca = p.id_marca;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"id", "producto", "descripcion", "imagen", "precio_costo", "precio_venta", "existencia", "fecha_ingreso", "id_marca"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[9];
            while (consulta.next()) {
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("producto");
                datos[2] = consulta.getString("descripcion");
                datos[3] = consulta.getString("imagen");
                datos[4] = consulta.getString("precio_costo");
                datos[5] = consulta.getString("precio_venta");
                datos[6] = consulta.getString("existencia");
                datos[7] = consulta.getString("fecha_ingreso");
                datos[8] = consulta.getString("id_marca");
                tabla.addRow(datos);
            }
            cn.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return tabla;
    }

    public int agregar() {
        int retorno = 0;
        try {
            cn = new Conexion();
            PreparedStatement parametro;
            String query = "INSERT INTO productos (producto, descripcion, imagen, precio_costo, precio_venta, existencia, fecha_ingreso, id_marca) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            cn.abrirConexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getProducto());
            parametro.setString(2, getDescripcion());
            parametro.setString(3, getImagen());
            parametro.setDouble(4, getPrecio_costo());
            parametro.setDouble(5, getPrecio_venta());
            parametro.setInt(6, getExistencia());
            parametro.setString(7, getFecha_ingreso());
            parametro.setInt(8, getId_marca());

            retorno = parametro.executeUpdate();
            cn.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }

    public int modificar() {
        int retorno = 0;
        try {
            cn = new Conexion();
            cn.abrirConexion();
            PreparedStatement parametro;
            String query = "UPDATE productos SET producto = ?, descripcion = ?, imagen = ?, precio_costo = ?, precio_venta = ?, existencia = ?, fecha_ingreso = ?, id_marca = ? WHERE id_producto = ?;";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getProducto());
            parametro.setString(2, getDescripcion());
            parametro.setString(3, getImagen());
            parametro.setDouble(4, getPrecio_costo());
            parametro.setDouble(5, getPrecio_venta());
            parametro.setInt(6, getExistencia());
            parametro.setString(7, getFecha_ingreso());
            parametro.setInt(8, getId_marca());
            parametro.setInt(9, getId());

            retorno = parametro.executeUpdate();
            cn.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }

    public int eliminar() {
        int retorno = 0;
        try {
            cn = new Conexion();
            PreparedStatement parametro;
            String query = "DELETE FROM productos WHERE id_producto = ?;";
            cn.abrirConexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId());

            retorno = parametro.executeUpdate();
            cn.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }

    // Nuevo método para actualizar la existencia del producto
    public boolean actualizarExistencia(int cantidadVendida) {
        try {
            cn = new Conexion();
            cn.abrirConexion();
            String query = "UPDATE productos SET existencia = existencia - ? WHERE id_producto = ?;";
            PreparedStatement parametro = cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, cantidadVendida);
            parametro.setInt(2, getId()); // Asumimos que has establecido el ID del producto antes de llamar a este método

            int rowsAffected = parametro.executeUpdate();
            cn.cerrarConexion();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}