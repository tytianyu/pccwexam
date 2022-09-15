package com.pccwexam.user.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Bean(value = "dockerBean")
    public Docket dockerBean() {
        //指定使用Swagger2规范
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .description("# Backend user restful apis")
                        .termsOfServiceUrl("https://doc.pccwexam.com/")
                        .contact(new Contact("tianyu", "https://com.pccw.exam.user", "tianyu@pccwexam"))
                        .version("1.0")
                        .build())
                .groupName("backendApi")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pccwexam.user"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

}
