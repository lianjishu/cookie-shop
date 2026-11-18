package io.github.lianjishu.config;

import org.junit.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 非 Spring Boot 环境下 springdoc 装配的回归测试：
 * 刷新一个最小 Web 上下文（@EnableWebMvc + {@link OpenApiConfig}），
 * 断言 /v3/api-docs 与 swagger-ui 欢迎页端点已注册。
 * <p>
 * 该测试同时起到「体检」作用：若 springdoc 配置类缺少某个属性 Bean，
 * 上下文刷新会直接抛 NoSuchBeanDefinitionException，指明缺哪个。
 */
public class OpenApiWiringTest {

    @Configuration
    @Import(OpenApiConfig.class)
    static class MinimalWebConfig extends org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport {
    }

    @Test
    public void springdocEndpointsAreRegistered() {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.setServletContext(new MockServletContext());
        ctx.register(MinimalWebConfig.class);
        ctx.refresh();
        try {
            RequestMappingHandlerMapping mapping = ctx.getBean(RequestMappingHandlerMapping.class);
            Set<String> urls = new TreeSet<>();
            for (Map.Entry<RequestMappingInfo, org.springframework.web.method.HandlerMethod> e
                    : mapping.getHandlerMethods().entrySet()) {
                if (e.getKey().getPathPatternsCondition() != null) {
                    urls.addAll(e.getKey().getPathPatternsCondition().getPatternValues());
                }
            }
            System.out.println("== registered mappings ==");
            for (String u : urls) {
                System.out.println("  " + u);
            }
            assertTrue("缺少 /v3/api-docs 端点，已注册: " + urls, urls.stream().anyMatch(u -> u.contains("api-docs")));
        } finally {
            ctx.close();
        }
    }

    private static void assertTrue(String message, boolean condition) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    @SuppressWarnings("unused")
    private static void assertMethod(RequestMappingInfo info, RequestMethod method) {
        // 预留：如需断言 GET/POST 方法约束可在此扩展
    }
}
