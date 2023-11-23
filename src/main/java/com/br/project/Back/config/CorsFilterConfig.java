package com.br.project.Back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsFilterConfig {

    public static final List<String> allowedOrigins = Arrays.asList("http://localhost:4200");

    @Bean
    public FilterRegistrationBean<CorsFilter> initCorsFilter() {
        // @formatter:off
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        config.addAllowedMethod("*");
        config.setAllowedOrigins(allowedOrigins);

        // Configuração específica para a API /auth/login
        UrlBasedCorsConfigurationSource authConfigSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration authConfig = new CorsConfiguration();
        authConfig.setAllowCredentials(true);
        authConfig.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        authConfig.addAllowedMethod("*");
        authConfig.setAllowedOrigins(allowedOrigins);
        authConfigSource.registerCorsConfiguration("/auth/login", authConfig);

        // Registrar as configurações para /** e /auth/login
        source.registerCorsConfiguration("/**", config);
        source.registerCorsConfiguration("/auth/login", authConfig);

        // @formatter:on
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}