package controller;

import java.io.IOException;
import java.sql.SQLException;

import dao.SupplierDAOImpl;
import dao.supplierDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/suppliers")
public class SupplierController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final supplierDAO supplierDao = new SupplierDAOImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("supplierList", supplierDao.getAllSuppliers());
			HttpSession session = request.getSession(false);
			if (session != null) {
				request.setAttribute("deleteSuccess", session.getAttribute("deleteSuccess"));
				request.setAttribute("deleteError", session.getAttribute("deleteError"));
				session.removeAttribute("deleteSuccess");
				session.removeAttribute("deleteError");
			}
			request.getRequestDispatcher("/viewsupplier.jsp").forward(request, response);
		} catch (SQLException e) {
			throw new ServletException("無法讀取供應商資料", e);
		}
	}
}
