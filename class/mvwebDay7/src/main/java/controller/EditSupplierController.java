package controller;

import java.io.IOException;
import java.sql.SQLException;

import dao.SupplierDAOImpl;
import dao.supplierDAO;
import dao.suppliers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/suppliers/edit")
public class EditSupplierController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final supplierDAO supplierDao = new SupplierDAOImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int supId;
		try {
			supId = Integer.parseInt(request.getParameter("supId"));
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "供應商編號必須是整數");
			return;
		}
		if (supId <= 0) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "供應商編號必須是正整數");
			return;
		}

		try {
			suppliers supplier = supplierDao.getSupplierById(supId);
			if (supplier == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "找不到此供應商");
				return;
			}
			request.setAttribute("supplier", supplier);
			request.getRequestDispatcher("/editsupplier.jsp").forward(request, response);
		} catch (SQLException e) {
			throw new ServletException("無法讀取供應商資料", e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		int supId;
		try {
			supId = Integer.parseInt(request.getParameter("supId"));
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "供應商編號必須是整數");
			return;
		}

		String supName = request.getParameter("supName");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		if (supId <= 0
				|| supName == null || supName.isBlank() || supName.trim().length() > 40
				|| street == null || street.isBlank() || tooLong(street, 40)
				|| city == null || city.isBlank() || tooLong(city, 20)
				|| state == null || state.isBlank() || tooLong(state, 2)
				|| tooLong(zip, 5)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "請確認供應商資料及欄位長度");
			return;
		}

		suppliers supplier = new suppliers(
			supId,
			supName.trim(),
			street,
			city,
			state,
			zip == null || zip.isBlank() ? null : zip
		);
		try {
			if (!supplierDao.updateSupplier(supplier)) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "找不到此供應商");
				return;
			}
			response.sendRedirect(request.getContextPath() + "/suppliers");
		} catch (SQLException e) {
			throw new ServletException("無法修改供應商資料", e);
		}
	}

	private boolean tooLong(String value, int maxLength) {
		return value != null && value.length() > maxLength;
	}
}
