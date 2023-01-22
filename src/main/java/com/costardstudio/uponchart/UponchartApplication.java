package com.costardstudio.uponchart;

import com.costardstudio.uponchart.configuration.PropertiesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class UponchartApplication {

    private final PropertiesConfiguration configuration;

    public UponchartApplication(PropertiesConfiguration configuration) {
        this.configuration = configuration;
    }

    public static void main(String[] args) {
        SpringApplication.run(UponchartApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins(configuration.getCorsAllowedOrigins());
            }
        };
    }

}
