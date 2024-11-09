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
import modelo.Proveedor;
        
        
@WebServlet(name = "sr_proveedor", urlPatterns = {"/sr_proveedor"})
public class sr_proveedores extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Proveedor proveedores;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_proveedores</title>");
            out.println("</head>");
            out.println("<body>");
            if ("agregar".equals(request.getParameter("btn_agregar"))){
                proveedores = new Proveedor(request.getParameter("txt_proveedor"),request.getParameter("txt_nit"),request.getParameter("txt_direccion"),request.getParameter("txt_telefono"),Integer.valueOf(request.getParameter("txt_id")));
                if (proveedores.agregar()>0){
                    response.sendRedirect("proveedor.jsp");
             
                } else {
                    out.println("<h1>No se Ingreso</h1>");
                    out.println("<a href='proveedor.jsp'>Regresar...</a>");
                }
            }
            
            // Boton modificar 
            if ("modificar".equals(request.getParameter("btn_modificar"))){
              proveedores = new Proveedor(request.getParameter("txt_proveedor"),request.getParameter("txt_nit"),request.getParameter("txt_direccion"),request.getParameter("txt_telefono"),Integer.valueOf(request.getParameter("txt_id")));
                if (proveedores.modificar()>0){
                    response.sendRedirect("proveedor.jsp");
             
                } else {
                    out.println("<h1>No se Modifico</h1>");
                    out.println("<a href='proveedor.jsp'>Regresar...</a>");
                }
             }
            
            // Boton eliminar 
            if ("eliminar".equals(request.getParameter("btn_eliminar"))){
             proveedores = new Proveedor(request.getParameter("txt_proveedor"),request.getParameter("txt_nit"),request.getParameter("txt_direccion"),request.getParameter("txt_telefono"),Integer.valueOf(request.getParameter("txt_id")));
                if (proveedores.eliminar()>0){
                    response.sendRedirect("proveedor.jsp");
             
                } else {
                    out.println("<h1>No se Modifico</h1>");
                    out.println("<a href='proveedor.jsp'>Regresar...</a>");
                }
             }
            
            
          
            out.println("<h1>Servlet sr_proveedores at " + request.getContextPath() + "</h1>");
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
