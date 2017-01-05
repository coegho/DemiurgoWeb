package es.usc.rai.coego.martin.demiurgo.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class DemiurgoWebApp extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(DemiurgoWebApp.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DemiurgoWebApp.class);
    }
}
