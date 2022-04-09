package com.example.web.config;

import java.nio.charset.StandardCharsets;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@EnableWebMvc //Enable Spring MVC
@Configuration
@ComponentScan(basePackages = {"com.example.web.controller"})
public class MvcConfig implements WebMvcConfigurer {
	@Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver =
                new SpringResourceTemplateResolver();
        //Specify the name of the folder where you want to save the view
        templateResolver.setPrefix("/WEB-INF/templates/");
        //Specify the view extension
        templateResolver.setSuffix(".html");
        //Specify template mode in HTML
        templateResolver.setTemplateMode(TemplateMode.HTML);
        //Specify the character code when loading the template
        templateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());

        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(SpringResourceTemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        //Set SpringResourceTemplateResolver
        templateEngine.setTemplateResolver(templateResolver());
        //Enable SpEL's compiler to improve performance
        templateEngine.setEnableSpringELCompiler(true);
        //Added Dialect to use Date and Time APi
        templateEngine.addDialect(new Java8TimeDialect());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        //Specify the character code when exporting the view
        viewResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        return viewResolver;
    }
    
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
        registry.addResourceHandler("/webjars/**")
        		.addResourceLocations("classpath:/META-INF/resources/webjars/")
        		.resourceChain(false)
        		.addResolver(new VersionResourceResolver()
        				.addContentVersionStrategy("/**"));
    }

    //Message source settings
    //Property files can be used on web pages
    //Japanese messages: messages_ja.properties
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");

        //If set to true, the key will be displayed if the message key does not exist.
        //If false, throw NoSuchMessageException
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        // # -1 :Do not reload, 0:Always reload
        messageSource.setCacheSeconds(0);
        return messageSource;
    }
}