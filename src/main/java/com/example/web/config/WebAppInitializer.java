package com.example.web.config;

import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/** 
 * AbstractAnnotationConfigDispatcherServletInitializer 的实现类会被spring-mvc环境注入到 SpringServletContainerInitializer 中，
 * SpringServletContainerInitializer 这个内部对象会被容器的扫描到用来配置容器的DispatcherServlet，这样容器就不需要通过扫描web.xml来
 * 配置DispatcherServlet了 
 * ps: DispatcherServlet是容器中处理请求的分发器，在原始的javaWeb中一般用一个web.xml来定义，将请求地址跟具体的servlet关联起来。
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	/**
     *Specifies Java Config classes for non-Spring MVC, such as business logic.
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {};
    }

    /**
     *Specifies the Java Config class for Spring MVC.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {MvcConfig.class};
    }

    /**
     *Specifies the URL pattern for the DispatcherServlet.
     * "/"By specifying, DispatcherServlet receives all requests.
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     *Specify the Servlet filter.
     *If there are multiple filters, they will be executed in the order specified in the array.
     */
    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{
                new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true)};
    }
}