

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import datamodel.ExponentLogic;
import datamodel.NumberLogic;

/**
 * Servlet implementation class ExponentLogicServlet
 */
@WebServlet("/explogicservlet")
public class ExponentLogicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExponentLogicServlet() {
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
		String baseStr = request.getParameter("base");
		String exponentStr = request.getParameter("exponent");
		
		int base = Integer.parseInt(baseStr);
		int exponent = Integer.parseInt(exponentStr);
		long numAns =ExponentLogic.powerByLoop(base,exponent);
		response.getWriter().append(base+"的"+exponent+"次方，").append("答案是:  "+numAns);			
		
	}

}
