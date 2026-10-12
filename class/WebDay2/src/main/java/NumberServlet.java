

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import datamodel.NumberLogic;

/**
 * Servlet implementation class NumberServlet
 */
@WebServlet("/prime")
public class NumberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NumberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		String numStr = request.getParameter("num");
		if(numStr == null || numStr.trim().isEmpty())
		{
			return;
		}
		else
		{
			int numInt = Integer.parseInt(numStr);
			boolean numbool =NumberLogic.isPrime(numInt);
			if(numbool)
			{
				response.getWriter().append("是質數:  "+numInt);
			}
			else
			{
				response.getWriter().append("不是質數:  "+numInt);
			}
		}
		
	}

}
