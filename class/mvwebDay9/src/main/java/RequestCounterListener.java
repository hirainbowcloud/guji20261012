

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Application Lifecycle Listener implementation class RequestCounterListener
 *
 */
@WebListener
public class RequestCounterListener implements ServletRequestListener {

    /**
     * Default constructor. 
     */
    public RequestCounterListener() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
    		ServletRequest sr = sre.getServletRequest();
    		HttpServletRequest hr = (HttpServletRequest)sr;
    		System.out.println("IP進入請求：" + hr.getRemoteAddr());
    		
        // 記錄請求開始時間
        sre.getServletRequest().setAttribute("startTime", System.currentTimeMillis());
        
        String uri = ((jakarta.servlet.http.HttpServletRequest) sre.getServletRequest()).getRequestURI();
        System.out.println("請求進入：" + uri);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        // 計算處理時間
        Long startTime = (Long) sre.getServletRequest().getAttribute("startTime");
        if (startTime != null) {
            long duration = System.currentTimeMillis() - startTime;
            String uri = ((jakarta.servlet.http.HttpServletRequest) sre.getServletRequest()).getRequestURI();
            System.out.println("請求離開：" + uri + " | 處理時間：" + duration + "ms");
        }
    }
	
}
