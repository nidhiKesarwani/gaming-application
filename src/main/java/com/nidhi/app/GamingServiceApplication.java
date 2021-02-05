/*
 * Copyright (c) 2021
 */

package com.nidhi.app;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.invoke.MethodHandles;

@ComponentScan(basePackages = {"com.nidhi"})
@SpringBootApplication
@EnableSwagger2
public class GamingServiceApplication {

    private static final Logger log = LogManager.getLogger(String.valueOf(MethodHandles.lookup().lookupClass()));

    public static void main(String[] args) {
        SpringApplication.run(GamingServiceApplication.class, args);
        log.info("*****************************************************************************");
        log.info("*************************                             ***********************");
        log.info("***********************                                **********************");
        log.info("*********************                                   *********************");
        log.info("*******************    Gaming App Started Successfully    *******************");
        log.info("*********************                                   *********************");
        log.info("**********************                                 **********************");
        log.info("***********************                               ***********************");
        log.info("*****************************************************************************");
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
