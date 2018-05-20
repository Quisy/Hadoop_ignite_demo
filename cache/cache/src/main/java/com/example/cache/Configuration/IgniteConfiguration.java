package com.example.cache.Configuration;

import org.apache.ignite.cache.websession.WebSessionFilter;
import org.apache.ignite.startup.servlet.ServletContextListenerStartup;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextListener;

@Configuration
public class IgniteConfiguration {

    @Bean
    public ServletContextListener servletContextListener() {
        return new ServletContextListenerStartup();
    }

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            servletContext.setInitParameter("IgniteConfigurationFilePath", "igniteConfig.xml");
            servletContext.setInitParameter("IgniteWebSessionsCacheName", "session");
        };
    }


    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setName("IgniteWebSessionsFilter");
        registration.setFilter(new WebSessionFilter());
        registration.addUrlPatterns("/*");
//        registration.addInitParameter("IgniteConfigurationFilePath", "igniteConfig.xml");
//        registration.addInitParameter("IgniteWebSessionsCacheName", "session");
        return registration;
    }

}
