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

//Controller Servlet: 顯示新增畫面 / 接收表單呼叫 insert() 後轉送列表畫面
@WebServlet(name = "SupplierAddController", urlPatterns = { "/SupplierAdd" })
public class SupplierAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/addsupplier.jsp").forward(request, response);
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
            request.getRequestDispatcher("/addsupplier.jsp").forward(request, response);
            return;
        }
        String supName = request.getParameter("supName");
        if (supName == null || supName.trim().isEmpty()) {
            request.setAttribute("error", "SupName 不得為空");
            request.getRequestDispatcher("/addsupplier.jsp").forward(request, response);
            return;
        }

        Supplier supplier = new Supplier(supId, supName.trim(),
                request.getParameter("street"),
                request.getParameter("city"),
                request.getParameter("state"),
                request.getParameter("zip"));

        SupplierDAO dao = new SupplierDAOImpl();
        try {
            dao.insert(supplier);
        } catch (RuntimeException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/addsupplier.jsp").forward(request, response);
            return;
        }

        List<Supplier> supplierList = dao.findAll();
        request.setAttribute("supplierList", supplierList);
        request.getRequestDispatcher("/viewsupplier.jsp").forward(request, response);
    }
}