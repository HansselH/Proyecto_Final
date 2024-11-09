/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Productos;

/**
 *
 * @author josej
 */
@WebServlet(name = "sr_productos", urlPatterns = {"/sr_productos"})
public class sr_productos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Productos productos;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_productos</title>");
            out.println("</head>");
            out.println("<body>");
            
            if ("agregar".equals(request.getParameter("btn_agregar"))){
                productos = new Productos (request.getParameter("txt_producto"),request.getParameter("txt_descripcion"),request.getParameter("txt_imagen"),Integer.valueOf(request.getParameter("txt_precio_c")),Integer.valueOf(request.getParameter("txt_precio_v")),Integer.valueOf(request.getParameter("txt_existencia")),request.getParameter("txt_fecha_i"),Integer.valueOf(request.getParameter("drop_marca")),Integer.valueOf(request.getParameter("txt_id")));
                if (productos.agregar() > 0) {
                    response.sendRedirect("producto.jsp");
                } else {
                    out.println("<h1>Error al agregar</h1>");
                    out.println("<a href ='producto.jsp'>Regresar</a>");
                }
            }
            
            if ("modificar".equals(request.getParameter("btn_modificar"))){
                productos = new Productos (request.getParameter("txt_producto"),request.getParameter("txt_descripcion"),request.getParameter("txt_imagen"),Integer.valueOf(request.getParameter("txt_precio_c")),Integer.valueOf(request.getParameter("txt_precio_v")),Integer.valueOf(request.getParameter("txt_existencia")),request.getParameter("txt_fecha_i"),Integer.valueOf(request.getParameter("drop_marca")),Integer.valueOf(request.getParameter("txt_id")));
                if (productos.modificar() > 0) {
                    response.sendRedirect("producto.jsp");
                } else {
                    out.println("<h1>Error al modificar</h1>");
                    out.println("<a href ='producto.jsp'>Regresar</a>");
                }
            }
            
            if ("eliminar".equals(request.getParameter("btn_eliminar"))){
                productos = new Productos (request.getParameter("txt_producto"),request.getParameter("txt_descripcion"),request.getParameter("txt_imagen"),Integer.valueOf(request.getParameter("txt_precio_c")),Integer.valueOf(request.getParameter("txt_precio_v")),Integer.valueOf(request.getParameter("txt_existencia")),request.getParameter("txt_fecha_i"),Integer.valueOf(request.getParameter("drop_marca")),Integer.valueOf(request.getParameter("txt_id")));
                if (productos.eliminar() > 0) {
                    response.sendRedirect("producto.jsp");
                } else {
                    out.println("<h1>Error al eliminar</h1>");
                    out.println("<a href ='producto.jsp'>Regresar</a>");
                }
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
