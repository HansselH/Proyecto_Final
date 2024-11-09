/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Compra;
import modelo.Compra_Detalle;
import modelo.Productos; // Asegúrate de importar la clase Productos
import modelo.Conexion;
/**
 *
 * @author Hanssel
 */
@WebServlet(name = "sr_compra", urlPatterns = {"/sr_compra"})
public class sr_compra extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_compra</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sr_compra at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            // Crear una nueva compra
            Compra compra = new Compra();
            compra.setNo_orden_compra(Integer.parseInt(request.getParameter("no_orden_compra")));
            compra.setFecha_orden(request.getParameter("fecha_orden"));
            compra.setFecha_ingreso(request.getParameter("fecha_ingreso"));
            compra.setId_proveedor(Integer.parseInt(request.getParameter("id_proveedor")));
            
          

            // Guardar la venta y verificar que el ID de venta esté disponible
            if (compra.agregar()) {  // Guarda la venta en la base de datos
                int idCompra = compra.getId();  // Verifica que este método retorne el ID generado de la venta

                // Leer la cantidad de detalles
                int cantidadDetalles = Integer.parseInt(request.getParameter("cantidad_detalles"));

                // Guardar cada detalle de la venta
                for (int i = 1; i <= cantidadDetalles; i++) {
                    Compra_Detalle detalle = new Compra_Detalle();
                    detalle.setCantidad(Integer.parseInt(request.getParameter("cantidad" + i)));
                    detalle.setPrecio_costo_unitario(request.getParameter("precio_costo_unitario" + i));
                    detalle.setId_compra(idCompra);
                    detalle.setId_producto(Integer.parseInt(request.getParameter("id_producto" + i)));
                  
                    // Guardar el detalle
                    if (detalle.agregar()) {
                        // Actualizar la existencia del producto
                        Productos producto = new Productos();
                        producto.setId(detalle.getId_producto()); // Establecer el ID del producto
                        if (!producto.actualizarExistencia(-detalle.getCantidad())) {
                            // Manejar el error si la actualización del saldo falla
                            response.getWriter().write("Error al actualizar la existencia del producto ID: " + detalle.getId_producto());
                            return;
                        }
                    } else {
                        // Manejar el error si la inserción del detalle falla
                        response.getWriter().write("Error al guardar el detalle de venta para el producto ID: " + request.getParameter("id_producto" + i));
                        return;
                    }
                }
            } else {
                response.getWriter().write("Error al guardar la venta principal");
            }

            response.sendRedirect("compra.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
