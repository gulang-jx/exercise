package com.xbsafe.webservice.config;

import com.xbsafe.webservice.service.DemoService;
import com.xbsafe.webservice.service.DemoServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class CxfConfig {

    @Bean
    public ServletRegistrationBean dispatcherServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new CXFServlet(), "/demo/*");
        servletRegistrationBean.setName("webService");
        return servletRegistrationBean;
    }
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public DemoService demoJsonService(){
        return new DemoServiceImpl();
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), demoJsonService());
        endpoint.publish("/ws");
        endpoint.getInInterceptors().add(new WsInterceptor()); //add webservice inteceptor
        return endpoint;
    }
}
