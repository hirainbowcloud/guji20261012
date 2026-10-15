

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/admin/*")
public class LoginFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoginFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	 @Override
	    public void doFilter(ServletRequest req, ServletResponse res,
	                         FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;
	        
	        // 取得 Session
	        HttpSession session = request.getSession(false);
	        
	        // 檢查是否已登入
	        boolean isLoggedIn = (session != null && 
	                              session.getAttribute("user") != null);
	        
	        String loginURI = request.getContextPath() + "/admin/login";
	        boolean isLoginRequest = request.getRequestURI().equals(loginURI);
	        System.out.print(" isLoginRequest:"+" "+isLoginRequest); // isLoginRequest true isLoginRequest false
	        
	        if (isLoggedIn || isLoginRequest) {
	            // 已登入或正在登入，繼續執行	        		
	            chain.doFilter(request, response);//進入LoginServlet的doPost
	        } else {
	            // 未登入，導向登入頁面
	            response.sendRedirect(request.getContextPath() + "/login.jsp");
	        }
	    }


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
