package com.newyang.dormmanage.config;


import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket desertsApi () {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.newyang.dormmanage"))
                .paths(PathSelectors.any())
                .build()
                .groupName("DormManage API")
                .enable(true);
    }
    private ApiInfo apiInfo () {
        return new ApiInfoBuilder()
                .title("DormManage API")
                .description("RESTFUL 接口")
                .contact(new Contact("xsh", "localhost:8888", "1013836629@qq.com"))
                .version("1.0")
                .build();
    }
}