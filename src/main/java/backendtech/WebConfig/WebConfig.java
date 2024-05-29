package backendtech.WebConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins(
                                "http://localhost:5173", "http://localhost:5174", "http://localhost:5175",
                                "http://localhost:5176", "http://localhost:5177", "http://localhost:5178",
                                "http://localhost:5179", "http://localhost:5180", "http://localhost:5181",
                                "http://localhost:5182",
                                "https://frontend-webtech.onrender.com"
                        );
        }
}