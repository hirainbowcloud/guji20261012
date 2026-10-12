package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.*;
import java.util.*;

import dao.OfficesDAO;
import dao.OfficesDAOimpl;

/**
 * Servlet implementation class OfficeController
 */
@WebServlet("/offices")
public class OfficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OfficeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		OfficesDAO dao = new OfficesDAOimpl();
		String action = request.getParameter("action");
		if("add".equals(action))
		{
			response.sendRedirect("officeadd.jsp");
			return;
		}
		else if("update".equals(action))
		{
			String code = request.getParameter("oc");
			Offices o1= dao.findByOfficeCode(code);
			request.setAttribute("office", o1);
			request.getRequestDispatcher("officeupdate.jsp").forward(request, response);
			return;
		}
		else if("delete".equals(action))
		{
			String code = request.getParameter("oc");
			Offices o1= dao.findByOfficeCode(code);
			request.setAttribute("office", o1);
			request.getRequestDispatcher("officedelete.jsp").forward(request, response);
			return ;
		}

		List<Offices> data = dao.findAll();
		request.setAttribute("offices", data);
		request.getRequestDispatcher("viewoffice.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String submit=request.getParameter("submit");
		if("新增".equals(submit)) {
			addOffice(request,response);
		}else if("修改".equals(submit)) {
			updateOffice(request,response);
		}else if("刪除".equals(submit)) {
			deleteOffice(request,response);
		}
	}

	public void deleteOffice(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		OfficesDAO dao = new OfficesDAOimpl();
		String code=request.getParameter("officeCode");
		try {
			dao.delOffices(code);
			response.sendRedirect("offices");
		}catch(Exception ex) {
			System.out.println("Servlet delete Office error "+ex.getMessage());
			request.setAttribute("error", ex.getMessage());
			request.getRequestDispatcher("officedelete.jsp").forward(request, response);;
			return ;
		}

	}

	public void updateOffice(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		OfficesDAO dao = new OfficesDAOimpl();
		String officeCode = request.getParameter("officeCode");
		String city = request.getParameter("city");
		String phone = request.getParameter("phone");
		String addressLine1 = request.getParameter("addressLine1");
		String addressLine2 = request.getParameter("addressLine2");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		String postalCode = request.getParameter("postalCode");
		String territory = request.getParameter("territory");
		try {
			Offices o1=new Offices();
			o1.setOfficeCode(officeCode);
			o1.setCity(city);
			o1.setPhone(phone);
			o1.setAddressLine1(addressLine1);
			o1.setAddressLine2(addressLine2);
			o1.setState(state);
			o1.setCountry(country);
			o1.setPostalCode(postalCode);
			o1.setTerritory(territory);
			dao.updateOffices(o1);
			response.sendRedirect("offices");

		}catch(Exception ex)
		{
			System.out.println("Servlet update Office error "+ex.getMessage());
			request.setAttribute("error", ex.getMessage());
			request.getRequestDispatcher("officeupdate.jsp").forward(request, response);;
			return ;
		}
	}

	public void addOffice(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		OfficesDAO dao=new OfficesDAOimpl();
		String officeCode=request.getParameter("officeCode");
		String city=request.getParameter("city");
		String phone=request.getParameter("phone");
		String addressLine1=request.getParameter("addressLine1");
		String addressLine2=request.getParameter("addressLine2");
		String state=request.getParameter("state");
		String country=request.getParameter("country");
		String postalCode=request.getParameter("postalCode");
		String territory=request.getParameter("territory");
		try {
			Offices o1=new Offices();
			o1.setOfficeCode(officeCode);
			o1.setCity(city);
			o1.setPhone(phone);
			o1.setAddressLine1(addressLine1);
			o1.setAddressLine2(addressLine2);
			o1.setState(state);
			o1.setCountry(country);
			o1.setPostalCode(postalCode);
			o1.setTerritory(territory);
			dao.insertOffices(o1);
			response.sendRedirect("offices");
		}catch(Exception ex) {
			System.out.println("Servlet Insert Office error "+ex.getMessage());
			request.setAttribute("error", ex.getMessage());
			request.getRequestDispatcher("officeadd.jsp").forward(request, response);;
			return ;
		}

	}

}
