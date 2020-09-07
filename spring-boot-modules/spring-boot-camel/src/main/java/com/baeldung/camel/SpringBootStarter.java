package com.baeldung.camel;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.baeldung.camel")
public class SpringBootStarter {
    Logger logger = LoggerFactory.getLogger(SpringBootStarter.class);

    @Value("${server.port:8481}")
    String serverPort;
    
    @Value("${baeldung.api.path:/camel}")
    String contextPath;
    
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarter.class, args);
    }

    @Bean
    ServletRegistrationBean servletRegistrationBean() {
		logger.info("ServletRegistrationBean::CAMEL[context-path]: " + contextPath);
		logger.info("ServletRegistrationBean::CAMEL[server-port]:  " + serverPort);
        ServletRegistrationBean servlet = new ServletRegistrationBean(new CamelHttpTransportServlet(), contextPath+"/*");
        servlet.setName("CamelServlet");
        return servlet;
    }

}
