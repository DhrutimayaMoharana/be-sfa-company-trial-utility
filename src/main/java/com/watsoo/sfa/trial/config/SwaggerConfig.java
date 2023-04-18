package com.watsoo.sfa.trial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.watsoo.sfa.trial")).build();
    }
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("SFA Company Trail Utility Apis")
				.description("SFA Company Trail Utility apis for SFA")
				.version("1.0")
				.build();
	}
}
