package ecom;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Loginservlet")
public class Loginservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Loginservlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sql ="SELECT * FROM login WHERE username = ? AND password = ?";
        String userName = request.getParameter("username");
        String pass = request.getParameter("password");
        PrintWriter out = response.getWriter();
       
        
        if (userName == null || pass == null || userName.trim().isEmpty() || pass.trim().isEmpty()) {
            response.setContentType("text/html");
           out = response.getWriter();
            out.println("<html><body><h3>Please enter both username and password.</h3></body></html>");
            return;
        }

        try {
        	DbUtil db = new DbUtil();
        	PreparedStatement st =db.dbConnection(sql);
        	 st.setString(1, userName);
             st.setString(2, pass);
        	ResultSet rs = st.executeQuery();
        	

            if (rs.next()) {
              
                HttpSession session = request.getSession();
                session.setAttribute("user", userName); 
                out.println("welcome to yur login page");
                //response.sendRedirect("welcome.jsp");
            } else {
               
                response.setContentType("text/html");
          
                out.println("<html><body><h3>Invalid username or password.</h3></body></html>");
            }

        
            rs.close();
            st.close();
       

        } catch ( SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.setContentType("text/html");
            out = response.getWriter();
            out.println("<html><body><h3>Error occurred. Please try again later.</h3></body></html>");
        }
    }
}
