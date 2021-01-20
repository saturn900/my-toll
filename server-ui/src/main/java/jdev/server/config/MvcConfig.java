package jdev.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").    setViewName("home");

        registry.addViewController("/routes").  setViewName("routes");
        registry.addViewController("/payments").setViewName("payments");

        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/error").setViewName("error");

        registry.addViewController("/registerClient").  setViewName("registerClient");
        registry.addViewController("/registerManager"). setViewName("registerManager");
    }

}
