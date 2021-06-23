package com.ykb.vacation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    //UI Link   : http://localhost:8080/swagger-ui.html
    //Doc Link  : http://localhost:8080/v2/api-docs

    @Value("${swagger.scan.pack}")
    private String scanPack;

    @Value("${swagger.scan.link}")
    private String scanLink;

    @Value("${swagger.title}")
    private String title;

    @Value("${swagger.description}")
    private String description;

    @Value("${swagger.version}")
    private String version;

    @Value("${swagger.contact.name}")
    private String contactName;

    @Value("${swagger.contact.email}")
    private String contactEmail;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage(scanPack))
                .paths(PathSelectors.regex(scanLink))
                .build();
    }

    @Bean
    public ApiInfo apiInfo() {
        Contact contact = new Contact("","","");
        if(contactName!=null){
            contact = new Contact(contactName,"",contactEmail);
        }

        return new ApiInfoBuilder()
                .title(title)
                .contact(contact)
                .description(description)
                .version(version)
                .build();
    }

}
