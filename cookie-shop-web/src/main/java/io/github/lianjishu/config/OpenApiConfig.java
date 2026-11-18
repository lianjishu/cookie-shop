package io.github.lianjishu.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.configuration.SpringDocConfiguration;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springdoc.core.properties.SwaggerUiOAuthProperties;
import org.springdoc.webmvc.core.configuration.SpringDocWebMvcConfiguration;
import org.springdoc.webmvc.ui.SwaggerConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 接口文档配置：springdoc-openapi + 官方 Swagger UI
 * <p>
 * 本项目不是 Spring Boot（没有自动装配），因此需要手工 @Import springdoc 的三个配置类：
 * <ul>
 *   <li>{@link SpringDocConfiguration}：OpenAPI 文档生成核心（解析 @RequestMapping 等）</li>
 *   <li>{@link SpringDocWebMvcConfiguration}：/v3/api-docs 端点（WebMvc 版）</li>
 *   <li>{@link SwaggerConfig}：官方 Swagger UI（swagger-ui webjar）的欢迎页与静态资源</li>
 * </ul>
 * springdoc 的 @ConfigurationProperties 属性 Bean 在 Boot 下由自动装配注册，
 * 非 Boot 环境必须用 {@link EnableConfigurationProperties} 手工补上，
 * 否则 SpringDocConfigProperties 等 Bean 缺失、上下文启动失败。
 * <p>
 * 装配完成后：
 * UI 入口 /swagger-ui.html，OpenAPI JSON /v3/api-docs。
 */
@Configuration
@Import({SpringDocConfiguration.class, SpringDocWebMvcConfiguration.class, SwaggerConfig.class})
@EnableConfigurationProperties({SpringDocConfigProperties.class, SwaggerUiConfigProperties.class, SwaggerUiOAuthProperties.class})
public class OpenApiConfig {

    /**
     * 文档元信息（标题、版本、描述）
     */
    @Bean
    public OpenAPI shopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cookie Shop 接口文档")
                        .version("1.0")
                        .description("cookie-shop-ssm 项目接口文档（springdoc-openapi + Swagger 官方 UI）"));
    }
}
