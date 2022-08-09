package com.jtr.hilos.runnable.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig
{

	@Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error")))
                .build()
                .apiInfo(apiInfo());
    }
	
	
	/**
	 * apiInfo
	 * @return
	 */
    private ApiInfo apiInfo() 
    {
        return new ApiInfoBuilder()
                .title("runnable")
                .description("description")
                .termsOfServiceUrl("TERMS_SERVICE")
                .license("LICENSE")
                .licenseUrl("LICENSE_URL")
                .version("1.0")
                .build();
    }
}
