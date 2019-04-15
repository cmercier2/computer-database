package com.excilys.app;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class ContextConfig implements WebApplicationInitializer {
    public static AnnotationConfigWebApplicationContext context;
    
    @Override
    public void onStartup(ServletContext servletContext) {
    	context = new AnnotationConfigWebApplicationContext();
    	context.register(AppConfig.class);
    	context.setServletContext(servletContext);
    	context.refresh();
        DispatcherServlet servlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }

}