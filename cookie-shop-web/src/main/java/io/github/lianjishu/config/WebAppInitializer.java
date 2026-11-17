package io.github.lianjishu.config;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 启动入口：注册 DispatcherServlet、指定两个配置类、设映射路径
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * SpringMVC 容器配置
     */
    @Nullable
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvcConfig.class};  //子容器 = ServletConfig
    }

    /**
     * 将 DispatcherServlet 映射到 "/"，即拦截所有请求（JSP 除外），
     * 交由 Spring MVC 的 HandlerMapping 分发到对应的 Controller。
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 根容器配置（Service、DataSource 等）
     */
    @Nullable
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class}; //根容器 = RootConfig
    }

    /**
     * 应用启动回调：在容器初始化 Servlet 之前设置全局会话超时时间，
     * 再调用父类逻辑完成 DispatcherServlet 的注册。
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setSessionTimeout(10080); //会话超时，单位分钟，10080 = 7天
        super.onStartup(servletContext);
    }

    /**
     * 在 Servlet 容器层面为 DispatcherServlet 开启 multipart 解析，
     * 使容器能够解析 multipart/form-data 请求。缺少此配置时，容器会拒绝/忽略上传请求，
     * 即使配置了 Spring 的 MultipartResolver 也无法工作。
     *
     * @param registration Servlet注册对象
     */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        //参数依次为：临时目录、单文件最大字节数、整个请求最大字节数、内存阈值。
        registration.setMultipartConfig(
                new MultipartConfigElement(
                        "/tmp", //临时目录
                        10485760,      //单文件最大字节数
                        31457280,      //整个请求最大字节数
                        0              //内存阈值
                )
        );
    }
}
