package Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class C01ServletContextListener implements ServletContextListener {

	// 사이트 시작 시 기동
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("[LISTENER] C01 ServletContextListener start");
	}

	// 사이트 종료 시 기동
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("[LISTENER] C01 ServletContextListener end");
	}
}