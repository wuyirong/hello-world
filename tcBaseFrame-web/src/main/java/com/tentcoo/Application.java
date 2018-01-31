package com.tentcoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
@EnableAsync
@ImportResource({ "classpath:dubbo-services.xml","freemarker-shiro.xml"})
public class Application extends WebMvcConfigurerAdapter {
	
	//@Override
    /*protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }*/
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//将所有/static/** 访问都映射到classpath:/static/ 目录下
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}
//	@Bean
//	public EmbeddedServletContainerFactory servletContainer() {
//		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
//		return factory;
//	}

}