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

public class MediStaff extends HttpServlet{

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String connectionURL = "jdbc:derby://localhost:1527/hospital";// newData is the database
        Connection connection;
        String driver = "org.apache.derby.jdbc.ClientDriver";
        try {
            Class.forName(driver);

            String name = request.getParameter("e_name");
            String adrss = request.getParameter("adrs");
            String c_num = request.getParameter("cnt_num");
            String time = request.getParameter("w_time");
            String type = request.getParameter("e_type");
            String o_charges = request.getParameter("op_charge");
            String i_charges = request.getParameter("ip_charge");

            connection = DriverManager.getConnection(connectionURL, "maaz", "root");
            PreparedStatement pst = connection.prepareStatement("insert into medi_staff(mem_name,mem_address,mem_contact_no,mem_timings,mem_type,outpatient_visit,inpatient_visit)values(?,?,?,?,?,?,?)");//try2 is the name of the table

            pst.setString(1, name);
            pst.setString(2, adrss);
            pst.setString(3, c_num);
            pst.setString(4, time);
            pst.setString(5, type);
            pst.setString(6, o_charges);
            pst.setString(7, i_charges);
            int i = pst.executeUpdate();
            if (i != 0) {
              
                pw.println("<script>");
                pw.println("alert('Records inserted');");
                pw.println("</script>");
                response.sendRedirect("MediStaff.jsp");
            } else {
                pw.println("<script>");
                pw.println("alert('failed to insert the data');");
                pw.println("</script>");
            }
             
        } catch (Exception e) {
            pw.println(e.getMessage());
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
