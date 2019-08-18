package cn.sevenyuan.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author JingQ at 2019-08-18
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.sevenyuan.demo.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(
                        new ApiInfoBuilder()
                                .description("Spring Boot learn project")
                                .contact(new Contact("JingQ", "https://github.com/vip-augus", "=-=@qq.com"))
                                .version("v1.0")
                                .title("API 测试文档")
                                .license("Apache2.0")
                                .licenseUrl("http://www.apache.org/licenese/LICENSE-2.0")
                                .build());

    }
}
