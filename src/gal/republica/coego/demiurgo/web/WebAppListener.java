package gal.republica.coego.demiurgo.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class Listener
 *
 */
@WebListener
public class WebAppListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public WebAppListener() {
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		DemiurgoConnector.closeConnection();
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		DemiurgoConnector.connectWithDemiurgo();
	}

}
