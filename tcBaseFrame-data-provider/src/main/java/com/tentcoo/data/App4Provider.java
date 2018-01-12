package com.tentcoo.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by rover on 2018/1/12.
 */
@SpringBootApplication
@ImportResource({ "classpath:dubbo-spring-mybatis.xml" })
@EnableAutoConfiguration
public class App4Provider extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(App4Provider.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        application.listeners();
        return application.sources(applicationClass);
    }

    private static Class<App4Provider> applicationClass = App4Provider.class;

}
