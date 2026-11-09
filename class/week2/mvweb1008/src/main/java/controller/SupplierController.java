package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.*;
import java.util.*;

/**
 * Servlet implementation class SupplierController
 */
@WebServlet("/suppliers")
public class SupplierController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SupplierController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		SupplierDAO dao=new SupplierDAOImpl();
		if("add".equals(action)) {
			response.sendRedirect("supplieradd.jsp");
			return ;
		}else if("update".equals(action)) {
			String id=request.getParameter("sid");
			Supplier s1=dao.findById(Integer.parseInt(id));
			request.setAttribute("supplier", s1);
			System.out.println("update param:"+s1.getSupId());
			request.getRequestDispatcher("supplierupdate.jsp").forward(request, response);
			return ;
		}else if("delete".equals(action)) {
			String id=request.getParameter("sid");
			Supplier s1=dao.findById(Integer.parseInt(id));
			request.setAttribute("supplier", s1);
			request.getRequestDispatcher("supplierdelete.jsp").forward(request, response);
			return ;
		}
		
		List<Supplier> data=dao.findAll();
		request.setAttribute("suppliers", data);
		request.getRequestDispatcher("viewsupplier.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String submit=request.getParameter("submit");		
		if("新增".equals(submit)) {
			addSupplier(request,response);
		}else if("修改".equals(submit)) {
			updateSupplier(request,response);
		}if("刪除".equals(submit)) {
			deleteSupplier(request,response);
		}
		
	}
	public void deleteSupplier(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		SupplierDAO dao=new SupplierDAOImpl();
		String id=request.getParameter("supId");
		try {
			dao.delete(Integer.parseInt(id));
			response.sendRedirect("suppliers");
		}catch(Exception ex) {
			System.out.println("delete Supplier error "+ex.getMessage());
			request.setAttribute("error", ex.getMessage());
			request.getRequestDispatcher("supplierdelete.jsp").forward(request, response);
			return ;
		}
		
	}
	public void updateSupplier(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		SupplierDAO dao=new SupplierDAOImpl();
		try {
			Supplier s1=getSupplierFromRequest(request);
			dao.update(s1);
			response.sendRedirect("suppliers");
		}catch(Exception ex) {
			System.out.println("update Supplier error "+ex.getMessage());
			request.setAttribute("error", ex.getMessage());
			request.getRequestDispatcher("supplierupdate.jsp").forward(request, response);
			return ;
		}
		
	}
	public void addSupplier(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		SupplierDAO dao=new SupplierDAOImpl();
		try {
			Supplier s1=getSupplierFromRequest(request);
			dao.insert(s1);
			response.sendRedirect("suppliers");
		}catch(Exception ex) {
			System.out.println("Insert Supplier error "+ex.getMessage());
			request.setAttribute("error", ex.getMessage());
			request.getRequestDispatcher("supplieradd.jsp").forward(request, response);
			return ;
		}
		
	}
	private Supplier getSupplierFromRequest(HttpServletRequest request) {
		Supplier s1=new Supplier();
		s1.setSupId(Integer.parseInt(request.getParameter("supId")));
		s1.setSupName(request.getParameter("supName"));
		s1.setStreet(request.getParameter("street"));
		s1.setCity(request.getParameter("city"));
		s1.setState(request.getParameter("state"));
		String zip=request.getParameter("zip");
		s1.setZip(zip==null||zip.isEmpty()?null:zip);
		return s1;
	}

}
