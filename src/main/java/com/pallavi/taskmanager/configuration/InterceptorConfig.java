package com.pallavi.taskmanager.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * InterceptorConfig class for intercepting and treating path patterns which needs to be excluded.
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    RequestProcessingTimeInterceptor requestProcessingTimeInterceptor;

    /**
     * Method to configure exclusion of path patters.
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestProcessingTimeInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                    "/**/swagger-ui.html/**", 
                    "/**/swagger-ui/**", 
                    "/swagger-ui/**",
                    "/swagger-resources/**",
                    "/webjars/**"
                );
    }
	
}
