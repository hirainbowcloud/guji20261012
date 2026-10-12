package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.*;
import java.util.*;

import dao.SuppliersDAO;
import dao.SuppliersDAOimpl;

/**
 * Servlet implementation class SupplierController
 */
@WebServlet("/suppliers")
public class SupplierController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupplierController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		SuppliersDAO dao = new SuppliersDAOimpl();
		String action = request.getParameter("action");
		if("add".equals(action))
		{
			response.sendRedirect("supplieradd.jsp");
			return;
		}
		else if("update".equals(action))
		{
			int id = Integer.parseInt(request.getParameter("sid"));
			Suppliers s1= dao.findBySupId(id);
			request.setAttribute("supplier", s1);
			request.getRequestDispatcher("supplierupdate.jsp").forward(request, response);
			return;
		}
		else if("delete".equals(action))
		{
			int id = Integer.parseInt(request.getParameter("sid"));
			Suppliers s1= dao.findBySupId(id);
			request.setAttribute("supplier", s1);
			request.getRequestDispatcher("supplierdelete.jsp").forward(request, response);
			return ;
		}

		List<Suppliers> data = dao.findAll();
		request.setAttribute("suppliers", data);
		request.getRequestDispatcher("viewsupplier.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String submit=request.getParameter("submit");
		if("新增".equals(submit)) {
			addSupplier(request,response);
		}else if("修改".equals(submit)) {
			updateSupplier(request,response);
		}else if("刪除".equals(submit)) {
			deleteSupplier(request,response);
		}
	}

	public void deleteSupplier(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		SuppliersDAO dao = new SuppliersDAOimpl();
		int id=Integer.parseInt(request.getParameter("supId"));
		try {
			dao.delSuppliers(id);
			response.sendRedirect("suppliers");
		}catch(Exception ex) {
			System.out.println("Servlet delete Supplier error "+ex.getMessage());
			request.setAttribute("error", ex.getMessage());
			request.getRequestDispatcher("supplierdelete.jsp").forward(request, response);;
			return ;
		}

	}

	public void updateSupplier(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		SuppliersDAO dao = new SuppliersDAOimpl();
		String supId = request.getParameter("supId");
		String supName = request.getParameter("supName");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		try {
			Suppliers s1=new Suppliers();
			s1.setSupId(Integer.parseInt(supId));
			s1.setSupName(supName);
			s1.setStreet(street);
			s1.setCity(city);
			s1.setState(state);
			s1.setZip(zip);
			dao.updateSuppliers(s1);
			response.sendRedirect("suppliers");

		}catch(Exception ex)
		{
			System.out.println("Servlet update Supplier error "+ex.getMessage());
			request.setAttribute("error", ex.getMessage());
			request.getRequestDispatcher("supplierupdate.jsp").forward(request, response);;
			return ;
		}
	}

	public void addSupplier(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		SuppliersDAO dao=new SuppliersDAOimpl();
		String supId=request.getParameter("supId");
		String supName=request.getParameter("supName");
		String street=request.getParameter("street");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String zip=request.getParameter("zip");
		try {
			Suppliers s1=new Suppliers();
			s1.setSupId(Integer.parseInt(supId));
			s1.setSupName(supName);
			s1.setStreet(street);
			s1.setCity(city);
			s1.setState(state);
			s1.setZip(zip);
			dao.insertSuppliers(s1);
			response.sendRedirect("suppliers");
		}catch(Exception ex) {
			System.out.println("Servlet Insert Supplier error "+ex.getMessage());
			request.setAttribute("error", ex.getMessage());
			request.getRequestDispatcher("supplieradd.jsp").forward(request, response);;
			return ;
		}

	}

}
