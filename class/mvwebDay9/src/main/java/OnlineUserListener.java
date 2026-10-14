

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class OnlineUserListener
 *
 */
@WebListener
public class OnlineUserListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public OnlineUserListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */

    // Session 建立時執行
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 取得 ServletContext 來儲存全域變數
        var context = se.getSession().getServletContext();
        
        // 取得目前線上人數（預設為 0）
        Integer onlineCount = (Integer) context.getAttribute("onlineCount");
        if (onlineCount == null) {
            onlineCount = 0;
        }
        
        // 加 1
        onlineCount++;
        context.setAttribute("onlineCount", onlineCount);
        
        System.out.println("新 Session 建立，線上人數：" + onlineCount);
    }

    // Session 銷毀時執行
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        var context = se.getSession().getServletContext();
        
        Integer onlineCount = (Integer) context.getAttribute("onlineCount");
        if (onlineCount == null) {
            onlineCount = 0;
        }
        
        // 減 1
        onlineCount--;
        if (onlineCount < 0) onlineCount = 0;
        context.setAttribute("onlineCount", onlineCount);
        
        System.out.println("Session 銷毀，線上人數：" + onlineCount);
    }
	
}
