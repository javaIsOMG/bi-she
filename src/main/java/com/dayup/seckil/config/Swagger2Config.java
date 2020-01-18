package com.dayup.seckil.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Classname Swagger2Config
 * @Description TODO
 * @Date 2019/12/21 15:44
 * @Created by Yinghao.He
 */
@Configuration
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()//选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("com.dayup.seckil.controller"))// 对所有api进行监控
                .paths(PathSelectors.any()) // 对所有路径进行监控
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("milk秒杀系统api文档")
                .termsOfServiceUrl("http://localhost:8080/swagger-ui.html")
                .version("2.0")
                .build();
    }
}
