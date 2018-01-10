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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "NewServlet", urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String connectionURL = "jdbc:derby://localhost:1527/hospital";// newData is the database
        Connection connection;
        String driver = "org.apache.derby.jdbc.ClientDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(connectionURL, "maaz", "root");
            String sub = request.getParameter("i_p_services");
           String lab = "Avail Lab Services";
           String pharma = "Pharmacy";
           String discharge_r = "Discharge Request";
           String discharge_p = "Discharge Patient";
            if (sub.equals(lab)) {
                RequestDispatcher rd=request.getRequestDispatcher("PatientLabService.jsp");
                rd.forward(request, response);
            }else if(sub.equals(pharma)){
                RequestDispatcher rd1=request.getRequestDispatcher("PatientPharmacy.jsp");
                rd1.forward(request, response);
           }else if(sub.equals(discharge_r)){
                RequestDispatcher rd1=request.getRequestDispatcher("Discharge.jsp");
                rd1.forward(request, response);
           }else
           {
                RequestDispatcher rd1=request.getRequestDispatcher("Discharge.jsp");
                rd1.forward(request, response);
           }
        } catch (Exception e) {
            e.getMessage();
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
