package ecom;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FetchProduct
 */
@WebServlet("/FetchProduct")
public class FetchProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        String sql = "SELECT * FROM products WHERE price=200.00";
        
        DbUtil db = new DbUtil();
        try {
            
            PreparedStatement pst = db.dbConnection(sql);

            // Execute the query
            ResultSet st = pst.executeQuery();

            // Check if there is data in the ResultSet
            if (st.next()) {
                
                String name = st.getString("name");  
                //String description = st.getString("description");
                String price = st.getString("price");
                String stock = st.getString("stock_quantity");
                String categoryID = st.getString("category_id");
                String imageURL = st.getString("image_url");

             
                request.setAttribute("category", categoryID);
                request.setAttribute("stock", stock);
                request.setAttribute("price", price);
                request.setAttribute("title", name);
               // request.setAttribute("description", description);
                request.setAttribute("image", imageURL);

             
                request.getRequestDispatcher("product.jsp").forward(request, response);
            } else {
                // No product found, handle this scenario (e.g., redirect or show a message)
                request.setAttribute("error", "Product not found.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle database errors (log, show a generic error page, etc.)
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
