

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionAttributeListener
 *
 */
@WebListener
public class SessionAttributeListener implements HttpSessionListener, HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public SessionAttributeListener() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
        se.getSession().setAttribute("user", "usera");
    }
    public void sessionDestroyed(HttpSessionEvent se)  { 
        // TODO Auto-generated method stub
   }
   

	/**
    * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
    */
   @Override
   public void attributeAdded(HttpSessionBindingEvent sae) {
       System.out.println("Session 屬性新增：" + sae.getName() + " value= " + sae.getValue());
   }

   @Override
   public void attributeRemoved(HttpSessionBindingEvent sae) {
       System.out.println("Session 屬性移除：" + sae.getName());
   }

   @Override
   public void attributeReplaced(HttpSessionBindingEvent sae) {
       System.out.println("Session 屬性修改：" + sae.getName());
   }
	
}
