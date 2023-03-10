package ArgProg4.CV_Dynamic1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CvDynamic1Application {

	public static void main(String[] args) {
		SpringApplication.run(CvDynamic1Application.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(){
   	String[] allowDomains = new String[3];   	
   	// allowDomains[1] = "http://localhost:8081";
	allowDomains[0] = "https://cv-arg-prog.web.app";
	allowDomains[1] = "https://cv-argprog4-nippur7.koyeb.app";
	allowDomains[2] = "http://localhost:4200";

   	System.out.println("CORS configuration....");
   	return new WebMvcConfigurer() {
    	@Override
    	public void addCorsMappings(CorsRegistry registry) {
        	registry.addMapping("/**")
			// .allowedOrigins(allowDomains)
			.allowedOrigins("*")
			.allowedMethods("*")
			.allowedHeaders("*");
      }
   };
}

}
