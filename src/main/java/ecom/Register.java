package ecom;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Register() {
        super();
    }


    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
            response.getWriter().println("<html><body><h3>Please enter both username and password.</h3></body></html>");
            return;
        }

     
       
        PrintWriter pw = response.getWriter();
        String sql = "INSERT INTO login (username, password) VALUES (?, ?)";

        
        try {
        	DbUtil db = new DbUtil();
        	PreparedStatement ps =db.dbConnection(sql);
        	
            ps.setString(1, username);
            ps.setString(2, password);

           
            int result = ps.executeUpdate();

            
            if (result > 0) {
                pw.println("registerSuccessp");  
            } else {
                pw.println("<html><body><h3>Registration failed. Please try again.</h3></body></html>");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            pw.println("<html><body><h3>Error occurred while processing your registration.</h3></body></html>");
        }
    }
}
