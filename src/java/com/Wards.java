/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mohammed Maaz S
 */
public class Wards extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String connectionURL = "jdbc:derby://localhost:1527/hospital";// newData is the database
        Connection connection;
        String driver = "org.apache.derby.jdbc.ClientDriver";
        try {
            Class.forName(driver).newInstance();
            String type = request.getParameter("type");
            String des = request.getParameter("ward_des");
            String charges = request.getParameter("charges");

            connection = DriverManager.getConnection(connectionURL, "maaz", "root");
            PreparedStatement pst = connection.prepareStatement("insert into wards(ward_type,ward_description,daily_charges)values(?,?,?)");//try2 is the name of the table

            pst.setString(1, type);
            pst.setString(2, des);
            pst.setString(3, charges);
            int i = pst.executeUpdate();
           if (i != 0) {
              
                pw.println("<script>");
                pw.println("alert('Records inserted');");
                pw.println("</script>");
                response.sendRedirect("Wards.jsp");
            } else {
                pw.println("<script>");
                pw.println("alert('failed to insert the data');");
                pw.println("</script>");
            }
        } catch (Exception e) {
            pw.println(e);
        }
    }

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
