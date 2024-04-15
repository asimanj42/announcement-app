package az.ingress.announcementapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AnnouncementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnnouncementAppApplication.class, args);
	}

}
