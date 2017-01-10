package springTutorial.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BankServer {
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext(new String[] {
		"daos.xml", "hibernate.xml", "services.xml", "server.spring.xml" });
		System.out.println("Spring Server started");

	}
}
