package io.github.lianjishu.config;

import io.github.lianjishu.interceptor.CartCountInterceptor;
import io.github.lianjishu.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Servlet 子容器：视图解析器、拦截器、静态资源、Controller 扫描
 */
@Configuration
@EnableWebMvc // 全项目只此一处
// handler 包必须在这里扫描：GlobalExceptionHandler 是 @ControllerAdvice，
// 只有存在于 DispatcherServlet 所在（或其父）容器里才会生效
@ComponentScan(basePackages = {"io.github.lianjishu.controller", "io.github.lianjishu.handler"})
public class WebMvcConfig implements WebMvcConfigurer {

    private final CartCountInterceptor cartCountInterceptor;
    private final LoginInterceptor loginInterceptor;

    @Autowired
    public WebMvcConfig(CartCountInterceptor cartCountInterceptor, LoginInterceptor loginInterceptor) {
        this.cartCountInterceptor = cartCountInterceptor;
        this.loginInterceptor = loginInterceptor;
    }

    /**
     * 文件上传解析器
     */
    @Bean
    MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    /**
     * 视图解析器
     */
    @Bean
    ViewResolver viewResolver() {
        return new InternalResourceViewResolver(
                "/WEB-INF/views/", //prefix
                ".jsp"     //suffix
        );
    }

    /**
     * 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cartCountInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/css/**", "/js/**", "/images/**",
                        "/picture/**", "/fonts/**", "/admin/css/**",
                        "/admin/js/**", "/admin/img/**"
                );
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/admin/**");
    }

    /**
     * 资源处理器
     * <p>
     * Spring MVC 里 location 以 / 开头且不带 `classpath:`前缀时,
     * 会被当作 ServletContext 相对路径解析 (即 webapp/css/)。
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**") //所有以 /css/ 开头的请求
                .addResourceLocations("/css/"); //都去webapp下的 /css/目录里找对应文件
        registry.addResourceHandler("/js/**") //所有以 /js/开头的请求
                .addResourceLocations("/js/"); //都去webapp下的 /js/目录里找对应文件
        registry.addResourceHandler("/fonts/**") //所有以 /fonts/开头的请求
                .addResourceLocations("/fonts/"); //都去webapp下的 /fonts/ 目录里找对应文件
        registry.addResourceHandler("/images/**") //所有以 /images/ 开头的请求
                .addResourceLocations("/images/"); //都去webapp下的 /images/目录里找对应文件
        registry.addResourceHandler("/layer/**") //所有以 /layer/开头的请求
                .addResourceLocations("/layer/"); //都去webapp下的 /layer/目录里找对应文件
        registry.addResourceHandler("/picture/**") //所有以 /picture/开头的请求
                .addResourceLocations("/picture/"); //都去webapp下的 /picture/目录里找对应文件
    }

    /**
     * 视图控制器
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index");
    }
}
