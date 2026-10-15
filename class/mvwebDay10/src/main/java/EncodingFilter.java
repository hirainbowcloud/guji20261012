

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter(
		 filterName = "encodingFilter",
		    urlPatterns = {"/*"},
		    initParams = {
		        @WebInitParam(name = "encoding", value = "UTF-8")
		    }
)
public class EncodingFilter  implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public EncodingFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	private String encoding = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 從 web.xml 讀取設定，預設為 UTF-8
        String enc = filterConfig.getInitParameter("encoding");
        if (enc != null) {
            this.encoding = enc;
        }
        System.out.println("EncodingFilter 啟用，編碼：" + encoding);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        
        // 設定請求編碼
        request.setCharacterEncoding(encoding);
        
        // 設定回應編碼
        response.setCharacterEncoding(encoding);
        response.setContentType("text/html;charset=" + encoding);
        System.out.println("Start EncodingFilter 編碼：" + encoding);
        // 繼續執行
        chain.doFilter(request, response);
    }


}
