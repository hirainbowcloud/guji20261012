

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Servlet implementation class SupplierServlet
 */
@WebServlet("/suppliers")
public class SupplierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupplierServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 Connection conn=null;
	        response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        try {
	           Class.forName("com.mysql.cj.jdbc.Driver");
	            conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels?useUnicode=true&characterEncoding=utf8","root","1234");
	            out.println("<!DOCTYPE html>");
	            out.println("<html>");
	            out.println("<head>");
	            out.println("<title>Servlet SelectSupplier</title>");
	            out.println("</head>");
	            out.println("<body>");
	            viewTable(conn,out);
	            out.println("</body>");
	            out.println("</html>");         
	        }
	        catch(Exception ex)
	        {
	            System.out.println(ex.getMessage());
	        }
	        finally {
	            out.close();
	        }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	 public  void viewTable(Connection con,PrintWriter out) throws SQLException {
	        Statement stmt = null;
	        String query =
	                "select SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP from classicmodels.SUPPLIERS";
	        try {
	            stmt = con.createStatement();
	            ResultSet rs = stmt.executeQuery(query);
	            out.println("<table border='1'>");
	            while (rs.next()) {             
	                out.println("<tr><td>");
	                int supplierID = rs.getInt("SUP_ID");
	                out.println(""+supplierID);
	                out.println("</td><td>");
	                String supplierName = rs.getString("SUP_NAME");
	                out.println(""+supplierName);
	                out.println("</td><td>");
	                String street = rs.getString("STREET");
	                out.println(""+street);
	                out.println("</td><td>");
	                String city = rs.getString("CITY");
	                 out.println(""+city);
	                out.println("</td><td>");
	                String state = rs.getString("STATE");
	                 out.println(""+state);
	                out.println("</td><td>");
	                String zip = rs.getString("ZIP");                
	                 out.println(""+zip);
	                out.println("</td></tr>");
	            }
	            out.println("</table>");
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        } finally {
	            if (stmt != null) {
	                stmt.close();
	            }
	        }
	    }


}
