/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mohammed Maaz S
 */
public class CreateNewPatient extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String connectionURL = "jdbc:derby://localhost:1527/hospital";// newData is the database
        Connection connection;
        String driver = "org.apache.derby.jdbc.ClientDriver";
        try {
            Class.forName(driver).newInstance();
            
            String P_name = request.getParameter("p_name");
            String P_type = request.getParameter("p_type");
            String P_age = request.getParameter("p_age");
            String P_sex = request.getParameter("p_sex");
            String P_height = request.getParameter("p_height");
            String P_weight = request.getParameter("p_weight");
            String P_comments = request.getParameter("p_comments");

          
            connection = DriverManager.getConnection(connectionURL, "maaz", "root");
            PreparedStatement pst = connection.prepareStatement("insert into c_n_patient(p_name,p_type,p_age,p_sex,p_height,p_weight,p_comments)values(?,?,?,?,?,?,?)");//try2 is the name of the table
            
           
            pst.setString(1, P_name);
            pst.setString(2, P_type);
            pst.setString(3, P_age);
            pst.setString(4, P_sex);
            pst.setString(5, P_height);
            pst.setString(6, P_weight);
            pst.setString(7, P_comments);
            int i = pst.executeUpdate();
            if (i != 0) {
                pw.println("<br>Record has been inserted");
response.sendRedirect("CreateNewPatient.jsp");
            } else {
                pw.println("failed to insert the data");
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
