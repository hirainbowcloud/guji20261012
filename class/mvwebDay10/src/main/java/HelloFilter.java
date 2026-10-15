

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;

/**
 * Servlet Filter implementation class HelloFilter
 */
@WebFilter("/demo/*")
public class HelloFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public HelloFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		 System.out.println("=== HelloFilter 關閉 ===");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("=== 請求進入 HelloFilter ===");
		 // 預處理：在 Servlet 之前執行
        long startTime = System.currentTimeMillis();
        
        // ★ 重要：呼叫 chain.doFilter() 繼續執行下一個 Filter 或 Servlet
        chain.doFilter(request, response);
        
        // 後處理：在 Servlet 之後執行
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("=== 請求離開 HelloFilter | 處理時間：" + duration + "ms ===");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		 System.out.println("=== HelloFilter 初始化 ===");
	}

}
