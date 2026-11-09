import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import datamodel.NumberLogic;

/**
 * Servlet implementation class PowerServlet
 */
@WebServlet("/power")
public class PowerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String b = request.getParameter("base");
		String p = request.getParameter("power");
		int v1;
		int v2;
		try {
			v1 = Integer.parseInt(b);
			v2 = Integer.parseInt(p);
		} catch (NumberFormatException e) {
			response.getWriter().append("請輸入整數: 底數=").append(b).append(" 指數=").append(p);
			return;
		}

		int value = NumberLogic.power(v1, v2);
		response.getWriter().append(b + "^" + p).append("=" + value);
	}

}
