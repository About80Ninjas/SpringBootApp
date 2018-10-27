package us.about80ninjas.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.SpringServletContainerInitializer;

@SpringBootApplication
public class AppApplication extends SpringServletContainerInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
}
