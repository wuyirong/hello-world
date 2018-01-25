package com.tentcoo.consumer;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({ "classpath:applicationContext.xml", "dubbo-services.xml" })
@EnableAutoConfiguration
//@ComponentScan("com.tentcoo.controller")
@ServletComponentScan
public class App4Consumer extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(App4Consumer.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		application.listeners();
		return application.sources(applicationClass);
	}

	private static Class<App4Consumer> applicationClass = App4Consumer.class;

}
