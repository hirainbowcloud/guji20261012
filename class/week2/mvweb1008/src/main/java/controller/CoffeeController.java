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
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String action=request.getParameter("action");
		CoffeeDAO dao=new CoffeeDAOImpl();
		if("add".equals(action)) {
			response.sendRedirect("coffeeadd.jsp");
			return ;
		}else if("update".equals(action)) {
			String name=request.getParameter("cn");
			Coffee c1=dao.findByName(name);
			request.setAttribute("coffee", c1);
			System.out.println("update param:"+c1.getCofName());
			request.getRequestDispatcher("coffeeupdate.jsp").forward(request, response);
			return ;
		}else if("delete".equals(action)) {
			String name=request.getParameter("cn");
			Coffee c1=dao.findByName(name);
			request.setAttribute("coffee", c1);
			request.getRequestDispatcher("coffeedelete.jsp").forward(request, response);
			return ;
		}
		
		List<Coffee> data=dao.findAll();
		request.setAttribute("coffees", data);
		request.getRequestDispatcher("viewcoffee.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
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
		CoffeeDAO dao=new CoffeeDAOImpl();
		String name=request.getParameter("cofName");
		try {
			dao.delete(name);
			response.sendRedirect("coffees");
		}catch(Exception ex) {
			System.out.println("delete Coffee error "+ex.getMessage());
			request.setAttribute("error", ex.getMessage());
			request.getRequestDispatcher("coffeedelete.jsp").forward(request, response);;
			return ;
		}
		
	}
	public void updateCoffee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		CoffeeDAO dao=new CoffeeDAOImpl();
		String name=request.getParameter("cofName");
		String supId=request.getParameter("supId");
		String price=request.getParameter("price");
		String sales=request.getParameter("sales");
		String total=request.getParameter("total");
		try {
			Coffee c1=new Coffee();
			c1.setCofName(name);
			c1.setSupId(Integer.parseInt(supId));
			c1.setPrice(new BigDecimal(price));
			c1.setSales(Integer.parseInt(sales));
			c1.setTotal(Integer.parseInt(total));
			dao.update(c1);
			response.sendRedirect("coffees");
		}catch(Exception ex) {
			System.out.println("update Coffee error "+ex.getMessage());
			request.setAttribute("error", ex.getMessage());
			request.getRequestDispatcher("coffeeupdate.jsp").forward(request, response);;
			return ;
		}
		
	}
	public void addCoffee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		CoffeeDAO dao=new CoffeeDAOImpl();
		String name=request.getParameter("cofName");
		String supId=request.getParameter("supId");
		String price=request.getParameter("price");
		String sales=request.getParameter("sales");
		String total=request.getParameter("total");
		try {
			Coffee c1=new Coffee();
			c1.setCofName(name);
			c1.setSupId(Integer.parseInt(supId));
			c1.setPrice(new BigDecimal(price));
			c1.setSales(Integer.parseInt(sales));
			c1.setTotal(Integer.parseInt(total));
			dao.insert(c1);
			response.sendRedirect("coffees");
		}catch(Exception ex) {
			System.out.println("Insert Coffee error "+ex.getMessage());
			request.setAttribute("error", ex.getMessage());
			request.getRequestDispatcher("coffeeadd.jsp").forward(request, response);;
			return ;
		}
		
	}

}
