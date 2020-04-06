package com.wangwei.cloud.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @ClassName SwaggerConfig
 * @Description TODO
 * @Author lcy
 * @Date 17:04 2019/7/27
 * @Version 1.0
 **/
@Profile({"dev"})
@Configuration
@EnableSwagger2
public class Swagger2Configuration {
    public Swagger2Configuration() {
    }

    @Bean
    public Docket api() {
        return (new Docket(DocumentationType.SWAGGER_2)).
                apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.moorgen.hotel.admin.controller"))
                .paths(PathSelectors.regex("^(?!auth).*$"))
                .build().securitySchemes(securitySchemes()).securityContexts(securityContexts());
    }

    private List<ApiKey> securitySchemes() {
        return Lists.newArrayList(new ApiKey[]{new ApiKey("Authorization", "authorization", "header")});
    }

    private List<SecurityContext> securityContexts() {
        return Lists.newArrayList(new SecurityContext[]{SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$")).build()});
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
        return Lists.newArrayList(new SecurityReference[]{new SecurityReference("Authorization", authorizationScopes)});
    }

    private ApiInfo apiInfo() {
        return (new ApiInfoBuilder()).
                title("账户管理中心API").
                description("账户管理中心API接口").
                termsOfServiceUrl("http://192.168.0.129:9999").
                version("1.0").
                build();
    }
}