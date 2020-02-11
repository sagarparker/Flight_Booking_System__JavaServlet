/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;


@WebServlet(urlPatterns = {"/ns2"})
public class ns2 extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
           String username=request.getParameter("uname");
           String password= request.getParameter("pssw");
           String name,pass;
           Connection con;
           PreparedStatement pre;
           try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fwu","root","root");
                String query ="SELECT u_name,u_pass FROM "+username+" where  u_name= '"+username+"' or u_pass='"+password+"'";
                pre=con.prepareStatement(query);
                pre.executeQuery();
               ResultSet res= pre.getResultSet();
                if(res.next())
                {
                    name= res.getString("u_name");
                    pass=res.getString("u_pass");
                    if(name.equals(username)&& pass.equals(password))
                    {
                        response.sendRedirect("load.html");
                    }
                    
                   
                    else
                    {
                        response.sendRedirect("index.html");
                    }
                    
                    
                    
                    
                }
                
                
                
				
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "invalid");
                
            }
            
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
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

