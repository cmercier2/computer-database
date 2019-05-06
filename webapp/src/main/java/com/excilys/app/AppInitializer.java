package com.excilys.app;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
 
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
 
   @Override
   protected Class<?>[] getRootConfigClasses() {
      return new Class[] { SecurityConfig.class };
   }
 
   @Override
   protected Class<?>[] getServletConfigClasses() {
      return new Class[] { ContextConfig.class };
   }
 
   @Override
   protected String[] getServletMappings() {
      return new String[] { "/" };
   }
}