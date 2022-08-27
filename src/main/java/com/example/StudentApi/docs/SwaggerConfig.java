package com.example.StudentApi.docs;

import com.example.StudentApi.model.resource.StudentRequestResource;
import com.example.StudentApi.model.resource.StudentResponseResource;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Import(SpringDataRestConfiguration.class)
public class SwaggerConfig {

    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket api() {
        return new Docket(
                DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.example.StudentApi.controller"))
                .paths(PathSelectors.any())
                .build()
                .additionalModels(typeResolver.resolve(StudentRequestResource.class))
                .additionalModels(typeResolver.resolve(StudentResponseResource.class))
                .useDefaultResponseMessages(true)
                .apiInfo(apiInfo());
    }


    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Student API")
                .description("student checker")
                .version("1.0.0")
                .build();

    }
}
