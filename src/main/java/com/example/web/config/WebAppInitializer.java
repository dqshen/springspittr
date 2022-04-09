package com.example.web.config;

import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

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