package com.pallavi.taskmanager.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

    
    @Configuration
    public class SwaggerConfiguration {
    	 /**
         * APIs on the basis of pathsToMatch
         * @return Docket
         */
    	
    	 /**
         * Grouping APIs on the basis of pathsToMatch
         *
         * @return GroupedOpenApi
         */
        @Bean
        public GroupedOpenApi publicApi() {
            return GroupedOpenApi.builder()
                    .group("documentation")
                    .pathsToMatch("/api/**")
                    .packagesToScan("com.pallavi.taskmanager")
                    .build();
        }

        /**
         * Details about the project
         *
         * @return OpenAPI
         */
        @Bean
        public OpenAPI springShopOpenAPI() {
            return new OpenAPI()
                    .info(new Info().title("Task Manager API service")
                            .description("The Task Manager project is a simple task management application designed to help users organize and track their tasks efficiently.")
                            .version("Version 1.0")
                            .contact(new Contact().name("Test User").url("www.example.com").email("test@company.com")));
        }
    
}