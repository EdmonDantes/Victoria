package ru.teamname.projectname.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;


@EnableWebMvc
@Configuration
@ComponentScan({"ru.teamname.projectname.config", "ru.teamname.projectname.controller"})
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/admin/css/**").addResourceLocations("classpath:/static/adminPanel/css/")
                .setCacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS));

        registry.addResourceHandler("/admin/js/**").addResourceLocations("classpath:/static/adminPanel/js/")
                .setCacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS));

        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/root/")
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS));
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin").setViewName("/adminPanel/index.html");
        registry.addViewController("/**").setViewName("/root/index");
    }
}
