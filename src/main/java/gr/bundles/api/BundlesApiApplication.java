package gr.bundles.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class BundlesApiApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	      return application.sources(BundlesApiApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BundlesApiApplication.class, args);
	}

	@RequestMapping(value = "/")
	public String info() {
		return "This is a demo REST API for bundled offers";
	}
	
}
