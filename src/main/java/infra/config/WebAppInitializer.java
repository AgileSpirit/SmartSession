package infra.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 
 * <p>
 * Java based configuration equivalent to traditional 'web.xml' configuration file.
 * </p>
 * <p>
 * <!DOCTYPE web-app PUBLIC "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN" "http://java.sun.com/dtd/web-app_2_3.dtd" >
 * 
 * <web-app> <display-name>Archetype Created Web Application</display-name>
 * 
 * <!-- Spring Web MVC application configuration --> <servlet> <servlet-name>app</servlet-name>
 * <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class> <init-param>
 * <param-name>contextConfigLocation</param-name> <param-value></param-value> </init-param> <init-param>
 * <param-name>spring.profiles.default</param-name> <param-value>localhost</param-value> </init-param> <load-on-startup>1</load-on-startup>
 * </servlet> <servlet-mapping> <servlet-name>app</servlet-name> <url-pattern>/</url-pattern> </servlet-mapping>
 * 
 * </web-app>
 * </p>
 */
public class WebAppInitializer implements WebApplicationInitializer {

    private final static Logger LOGGER = LoggerFactory.getLogger(WebAppInitializer.class);

    @Override
    public void onStartup(ServletContext container) {
        addSpringWebSupport(container);
    }

    private void addSpringWebSupport(ServletContext container) {
        // Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(ApplicationConfig.class);
        dispatcherContext.getEnvironment().setDefaultProfiles("localhost");

        // Register and map the dispatcher servlet
        LOGGER.info("Add servlet : DispatcherServlet");
        ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/*");
    }

}