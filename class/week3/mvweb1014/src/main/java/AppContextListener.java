

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class AppContextListener
 *
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public AppContextListener() {
        // TODO Auto-generated constructor stub
    }

 // 應用程式啟動時執行
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("=== Web 應用程式啟動 ===");
        
        // 取得 ServletContext
        sce.getServletContext().setAttribute("appStart", new java.util.Date());
        sce.getServletContext().setAttribute("address", "公園路30號3樓");
        
        // 載入設定檔
        String appName = sce.getServletContext().getInitParameter("appName");
        System.out.println("應用程式名稱：" + appName);
    }

    // 應用程式關閉時執行
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("=== Web 應用程式關閉 ===");
        
        // 釋放資源
        // 例如：關閉資料庫連線池
    }
	
}
