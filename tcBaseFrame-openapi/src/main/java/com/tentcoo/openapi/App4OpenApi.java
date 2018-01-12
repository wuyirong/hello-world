package com.tentcoo.openapi;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({ "classpath:applicationContext.xml", "dubbo-services.xml" })
@EnableAutoConfiguration
//@ComponentScan("com.tentcoo.controller")
public class App4OpenApi extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(App4OpenApi.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		application.listeners();
		return application.sources(applicationClass);
	}

	private static Class<App4OpenApi> applicationClass = App4OpenApi.class;

}
