package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.Supplier;
import dao.SupplierDAO;
import dao.SupplierDAOImpl;

//Controller Servlet: 讀取所有 Supplier 資料轉送 JSTL 畫面
@WebServlet(name = "SupplierController", urlPatterns = { "/SupplierList" })
public class SupplierController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SupplierDAO dao = new SupplierDAOImpl();
        List<Supplier> supplierList = dao.findAll();
        request.setAttribute("supplierList", supplierList);
        request.getRequestDispatcher("/viewsupplier.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}