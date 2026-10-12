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

@WebServlet("/suppliers/insert")
public class InsertSupplierController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final supplierDAO supplierDao = new SupplierDAOImpl();

	/*
	 * response：Servlet 要回傳給瀏覽器的回應。	 *
	 *sendError(...)：送出錯誤回應。
	 *HttpServletResponse.SC_BAD_REQUEST：HTTP 狀態碼 400，代表請求資料不正確。
	 *"供應商編號必須是正整數"：附帶的錯誤訊息。	  
	 */
	
	/*
	 *  常數	   狀態碼	意思
		SC_OK	200	請求成功
		SC_CREATED	201	成功建立資料
		SC_NO_CONTENT	204	成功，但沒有回傳內容
		SC_BAD_REQUEST	400	送來的資料不正確
		SC_UNAUTHORIZED	401	尚未通過身分驗證
		SC_FORBIDDEN	403	沒有權限
		SC_NOT_FOUND	404	找不到網址或資源
		SC_METHOD_NOT_ALLOWED	405	不允許這種請求方式，例如只支援 POST 卻使用 GET
		SC_CONFLICT	409	資料衝突，例如供應商編號重複
		SC_INTERNAL_SERVER_ERROR	500	伺服器內部錯誤
	 * 
	 * */
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		int supId;
		try {
			supId = Integer.parseInt(request.getParameter("supId"));
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "供應商編號必須是正整數");
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
			if (!supplierDao.insertSupplier(supplier)) {
				throw new ServletException("新增供應商失敗");
			}
			response.sendRedirect(request.getContextPath() + "/suppliers"); //取消response.encodeRedirectURL(...)
		} catch (SQLException e) {
			throw new ServletException("無法新增供應商資料", e);
		}
	}

	private boolean tooLong(String value, int maxLength) {
		return value != null && value.length() > maxLength;
	}
	
	/*
	 * request.getContextPath()：取得專案的網址前綴。若專案部署為 mvwebDay7，就是 /mvwebDay7。
	 * + "/suppliers"：組成 /mvwebDay7/suppliers，也就是查詢所有供應商的 Servlet 路徑。
     1. request.getContextPath() + "/suppliers"（組合目標網址）
	request.getContextPath()：取得當前 Web 專案的根目錄名稱（Context Path）。

	例如你的專案部屬在 http://localhost:8080/my-shop，那 context path 就是 "/my-shop"。

	加上 "/suppliers" 後，組出來的相對路徑就是："/my-shop/suppliers"。

	為什麼不直接寫死 "/my-shop/suppliers"？
	因為專案日後如果改名或部屬到不同路徑，getContextPath() 會自動抓取正確名稱，不會讓路徑失效。

	2. response.encodeRedirectURL(...)（處理 Session ID）
	這是為了應對「使用者瀏覽器停用了 Cookie」的防護機制：
	正常情況（有 Cookie）：瀏覽器會自動在 HTTP Header 帶上 JSESSIONID，此方法不會對網址做任何改變，原樣回傳 "/my-shop/suppliers"。
	特殊情況（Cookie 被停用）：伺服器無法靠 Cookie 辨識使用者，這個方法會自動把 Session ID 附加在網址後面（URL 重寫 / URL Rewriting），變成：

	Plaintext
	/my-shop/suppliers;jsessionid=123456789ABCDEF
	這樣即使沒有 Cookie，伺服器也能辨識出當前的使用者 Session。

	3. response.sendRedirect(...)（命令瀏覽器轉址）
	伺服器會回傳一個 HTTP 302（或 307）重新導向狀態碼 給瀏覽器，並在回應標頭中附上剛才組好的目標網址。

	瀏覽器收到後，會立刻主動發出一個新的 GET 請求去抓取 "/my-shop/suppliers"。

	這時使用者的瀏覽器網址列會跟著改變。 
	*/
}
