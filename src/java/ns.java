import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author student
 */
@WebServlet(urlPatterns = {"/ns"})
public class ns extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
         
            
            String name = request.getParameter("nam");
            String psk = request.getParameter("pass");
            String phone = request.getParameter("pn");
           
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Welcome");
            
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fwu","root","root");
            System.out.println("Connection created");
            
           Statement st=con.createStatement();
           String tab="create table "+name+"(u_name varchar(20),u_pass varchar(20),u_phone varchar(20),dep varchar(20),arr varchar(20),air varchar(20),cls varchar(20),date varchar(20),name varchar(20))";
           st.executeUpdate(tab);
           
           String s = "insert into "+name+"(u_name,u_pass,u_phone,dep,arr,air,cls,date,name)values(?,?,?,?,?,?,?,?,?)";
           PreparedStatement ps=con.prepareStatement(s);
            
            ps.setString(1,name);
            ps.setString(2,psk);
            ps.setString(3,phone);
            ps.setString(4,"NULL");
            ps.setString(5,"NULL");
            ps.setString(6,"NULL");
            ps.setString(7,"NULL");
            ps.setString(8,"NULL");
            ps.setString(9,"NULL");
          
            ps.execute(); 
            
            
            System.out.println("records inserted");  
            response.sendRedirect("index.html");
            con.close();
          
        } 
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ns.class.getName()).log(Level.SEVERE, null, ex);
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