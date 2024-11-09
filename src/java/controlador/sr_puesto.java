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
import modelo.Puesto;

/**
 *
 * @author josej
 */
@WebServlet(name = "sr_puesto", urlPatterns = {"/sr_puesto"})
public class sr_puesto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Puesto puesto;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_puesto</title>");
            out.println("</head>");
            out.println("<body>");
            if ("agregar".equals(request.getParameter("btn_agregar"))){
                puesto = new Puesto (Integer.valueOf(request.getParameter("txt_id")),(request.getParameter("txt_puesto")));
                if (puesto.agregar()> 0) {
                    response.sendRedirect("puesto.jsp");
                } else {
                    out.println("<h1>Error al agregar</h1>");
                    out.println("<a href ='puesto.jsp'>Regresar</a>");
                }
            }
            
            if ("modificar".equals(request.getParameter("btn_modificar"))) {
                puesto = new Puesto (Integer.valueOf(request.getParameter("txt_id")),(request.getParameter("txt_puesto")));
                if (puesto.actualizar()> 0) {
                    response.sendRedirect("puesto.jsp");
                } else {
                    out.println("<h1>Error al editar</h1>");
                    out.println("<a href ='puesto.jsp'>Regresar</a>");
                }
            }
            
            if  ("eliminar".equals(request.getParameter("btn_eliminar"))){
                puesto = new Puesto (Integer.valueOf(request.getParameter("txt_id")),(request.getParameter("txt_puesto")));
                if (puesto.eliminar()> 0) {
                    response.sendRedirect("puesto.jsp");
                } else {
                    out.println("<h1>Error al eliminar</h1>");
                    out.println("<a href ='puesto.jsp'>Regresar</a>");
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
