package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

import dao.*;
import java.util.*;

import dao.CoffeesDAO;
import dao.CoffeesDAOimpl;

/**
 * Servlet implementation class CoffeeController
 */
@WebServlet("/coffees")
public class CoffeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CoffeeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CoffeesDAO dao = new CoffeesDAOimpl();
		String action = request.getParameter("action");
		if("add".equals(action))
		{
			response.sendRedirect("coffeeadd.jsp");
			return;
		}
		else if("update".equals(action))
		{
			String name = request.getParameter("cn");
			Coffees c1= dao.findByCofName(name);
			request.setAttribute("coffee", c1);
			request.getRequestDispatcher("coffeeupdate.jsp").forward(request, response);
			return;
		}
		else if("delete".equals(action))
		{
			String name = request.getParameter("cn");
			Coffees c1= dao.findByCofName(name);
			request.setAttribute("coffee", c1);
			request.getRequestDispatcher("coffeedelete.jsp").forward(request, response);
			return ;
		}
		
		List<Coffees> data = dao.findAll();
		request.setAttribute("coffees", data);
		request.getRequestDispatcher("viewcoffee.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String submit=request.getParameter("submit");		
		if("新增".equals(submit)) {
			addCoffee(request,response);
		}else if("修改".equals(submit)) {
			updateCoffee(request,response);
		}if("刪除".equals(submit)) {
			deleteCoffee(request,response);
		}
	}
	
	public void deleteCoffee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		CoffeesDAO dao = new CoffeesDAOimpl();
		String name=request.getParameter("cofName");
		try {
			dao.delCoffees(name);
			response.sendRedirect("coffees");
		}catch(Exception ex) {
			System.out.println("Servlet delete Coffee error "+ex.getMessage());
			request.setAttribute("error", ex.getMessage());
			request.getRequestDispatcher("coffeedelete.jsp").forward(request, response);;
			return ;
		}
		
	}
	
	public void updateCoffee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		CoffeesDAO dao = new CoffeesDAOimpl();
		String name = request.getParameter("cofName");
		String supId = request.getParameter("supId");
		String price = request.getParameter("price");
		String sales=request.getParameter("sales");
		String total=request.getParameter("total");
		try {
			Coffees c1=new Coffees();
			c1.setCofName(name);
			c1.setSupId(Integer.parseInt(supId));
			c1.setPrice(new BigDecimal(price));
			c1.setSales(Integer.parseInt(sales));
			c1.setTotal(Integer.parseInt(total));
			dao.updateCoffees(c1);
			response.sendRedirect("coffees");
			
		}catch(Exception ex)
		{
			System.out.println("Servlet update Coffee error "+ex.getMessage());
			request.setAttribute("error", ex.getMessage());
			request.getRequestDispatcher("coffeeupdate.jsp").forward(request, response);;
			return ;
		}
	}
	
	public void addCoffee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		CoffeesDAO dao=new CoffeesDAOimpl();
		String name=request.getParameter("cofName");
		String supId=request.getParameter("supId");
		String price=request.getParameter("price");
		String sales=request.getParameter("sales");
		String total=request.getParameter("total");
		try {
			Coffees c1=new Coffees();
			c1.setCofName(name);
			c1.setSupId(Integer.parseInt(supId));
			c1.setPrice(new BigDecimal(price));
			c1.setSales(Integer.parseInt(sales));
			c1.setTotal(Integer.parseInt(total));
			dao.insertCoffees(c1);
			response.sendRedirect("coffees");
		}catch(Exception ex) {
			System.out.println("Servlet Insert Coffee error "+ex.getMessage());
			request.setAttribute("error", ex.getMessage());
			request.getRequestDispatcher("coffeeadd.jsp").forward(request, response);;
			return ;
		}
		
	}

}
