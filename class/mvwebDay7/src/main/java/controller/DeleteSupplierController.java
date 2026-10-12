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
import jakarta.servlet.http.HttpSession;

@WebServlet("/suppliers/delete")
public class DeleteSupplierController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final supplierDAO supplierDao = new SupplierDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("deleteSuccess");
        session.removeAttribute("deleteError");

        int supId;

        // 1. 驗證格式是否為數字
        try {
            supId = Integer.parseInt(request.getParameter("supId"));
        } catch (NumberFormatException e) {
            session.setAttribute("deleteError", "刪除失敗：供應商編號格式錯誤，必須是數字。");
            redirectToIndex(request, response);
            return;
        }

        // 2. 驗證是否為正整數
        if (supId <= 0) {
            session.setAttribute("deleteError", "刪除失敗：供應商編號必須是大於 0 的正整數。");
            redirectToIndex(request, response);
            return;
        }

        // 3. 執行資料庫查詢與刪除
        String supName = null; // 提出變數，讓 catch 也能讀取到名稱（若已查詢到）
        try {
            suppliers supplier = supplierDao.getSupplierById(supId);

            if (supplier == null) {
                // 安全處理：查無資料時，不存取 supplier.getSupName()
                session.setAttribute("deleteError", "刪除失敗：供應商編號 " + supId + " 不存在或已被刪除。");
            } else {
                supName = supplier.getSupName();
                boolean isDeleted = supplierDao.deleteSupplier(supId);

                if (isDeleted) {
                    session.setAttribute("deleteSuccess", 
                        "刪除成功：已刪除供應商編號 " + supId + "，名稱「" + supName + "」。");
                } else {
                    session.setAttribute("deleteError", 
                        "刪除失敗：無法刪除供應商編號 " + supId + "（" + supName + "）。");
                }
            }
        } catch (SQLException e) {
            // 安全輸出 Log：若查無名稱則顯示「未知」
            String displayName = (supName != null) ? supName : "未知";
            getServletContext().log("刪除供應商 [ID: " + supId + ", 名稱: " + displayName + "] 失敗", e);
            session.setAttribute("deleteError", describeDeleteError(e));
        }

        redirectToIndex(request, response);
    }

    /**
     * 輔助方法：統一重導向回供應商列表頁
     */
    private void redirectToIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/suppliers"));
    }

    private String describeDeleteError(SQLException e) {
        if (e.getErrorCode() == 1451) {
            return "刪除失敗：此供應商已被其他資料（例如商品）引用，請先清除關聯資料後再刪除。";
        }
        String sqlState = e.getSQLState();
        if (sqlState != null && sqlState.startsWith("23")) {
            return "刪除失敗：資料庫限制完整性約束，不允許刪除此供應商。";
        }
        if (sqlState != null && sqlState.startsWith("08")) {
            return "刪除失敗：無法連線至資料庫，請稍後再試。";
        }
        return "刪除失敗：資料庫發生錯誤（SQLState: " + sqlState
                + "，錯誤碼: " + e.getErrorCode() + "），請聯絡管理員。";
    }
}