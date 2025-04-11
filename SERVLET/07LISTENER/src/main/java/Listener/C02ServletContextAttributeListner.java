package Listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class C02ServletContextAttributeListner implements ServletContextAttributeListener{

	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		System.out.println("[LISTENER] C02ServletContextAttributeListener add()...");
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		System.out.println("[LISTENER] C02ServletContextAttributeListener remove()...");
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		System.out.println("[LISTENER] C02ServletContextAttributeListener replace()...");
	}
	
}
