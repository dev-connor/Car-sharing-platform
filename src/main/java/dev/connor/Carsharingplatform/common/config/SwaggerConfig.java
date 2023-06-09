package dev.connor.Carsharingplatform.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    private String version;
    private String title;

    @Bean
    public Docket apiV1() {
        version = "V1";
        title = "Swagger Example API " + version;

        Parameter parameterBuilder = new ParameterBuilder()
                .name(HttpHeaders.AUTHORIZATION)
                .description("Access Token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();

        List<Parameter> globalParamters = new ArrayList<>();
        globalParamters.add(parameterBuilder);

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(globalParamters)
                .useDefaultResponseMessages(false)
                .groupName(version)
                .securityContexts(List.of(getSecurityContexts()))
                .securitySchemes(List.of(getApiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("dev.connor.Carsharingplatform"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .apiInfo(apiInfo(title, version));
    }

    private SecurityContext getSecurityContexts() {
        return SecurityContext.builder()
                .securityReferences(getDefaultAuth())
                .build();
    }

    private List<SecurityReference> getDefaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("Authorization", authorizationScopes));
    }

    private ApiKey getApiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    @Bean
    public Docket apiV2() {
        version = "V2";
        title = "Swagger Example API " + version;

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName(version)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.swagger.v2"))
                .paths(PathSelectors.ant("/v2/customer/**"))
                .build()
                .apiInfo(apiInfo(title, version));

    }

    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfo(
                title,
                "Swagger로 생성한 API Docs",
                version,
                "www.example.com",
                new Contact("Contact us", "www.example.com", "sharplee7@gmail.com"),
                "Licenses",
                "www.example.com",
                new ArrayList<>());
    }
}
