

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.*;
import model.Fruit;
/**
 * Servlet implementation class Fruit2Controller
 */
@WebServlet("/Fruit2Controller")
public class Fruit2Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Fruit2Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		Fruit[] fruits= { new Fruit("Apple",50,"Canana"),
				          new Fruit("Banana",20,"Taiwan"),
				          new Fruit("Cherry",350,"USA"),
				          new Fruit("Grape",150,"Japan"),
				          new Fruit("Mongo",70,"Tailand")};
		request.setAttribute("fruit2s",fruits );
	    request.getRequestDispatcher("fruitsview.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
