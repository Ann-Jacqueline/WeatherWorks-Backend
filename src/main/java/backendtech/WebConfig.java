package backendtech;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings (CorsRegistry registry){
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins("http://localhost:5173",
                                "https://frontend-webtech.onrender.com/");
        }}
