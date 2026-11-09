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

//Controller Servlet: 顯示刪除畫面 / 接收表單呼叫 delete() 後轉送列表畫面
@WebServlet(name = "SupplierDeleteController", urlPatterns = { "/SupplierDelete" })
public class SupplierDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String sid=request.getParameter("sid");
        SupplierDAO dao = new SupplierDAOImpl();
        int id=Integer.parseInt(sid);
        Supplier sp1=dao.findById(id);
        request.setAttribute("sp1", sp1);
        request.getRequestDispatcher("/deletesupplier.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int supId;
        try {
            supId = Integer.parseInt(request.getParameter("supId").trim());
        } catch (NumberFormatException | NullPointerException e) {
            request.setAttribute("error", "SupId 必須為整數");
            request.getRequestDispatcher("/deletesupplier.jsp").forward(request, response);
            return;
        }

        SupplierDAO dao = new SupplierDAOImpl();
        try {
            dao.delete(supId);
        } catch (RuntimeException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/deletesupplier.jsp").forward(request, response);
            return;
        }

        List<Supplier> supplierList = dao.findAll();
        request.setAttribute("supplierList", supplierList);
        request.getRequestDispatcher("/viewsupplier.jsp").forward(request, response);
    }
}