package es.usc.rai.coego.martin.demiurgo.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import es.usc.rai.coego.martin.demiurgo.web.beans.LoggedUser;

@Configuration
@EnableAutoConfiguration
public class WebConfig extends WebMvcConfigurerAdapter {
	@Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public LoggedUser user() {
        return new LoggedUser();
    }
	
	@Bean
	public LoginInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login").excludePathPatterns("/register").excludePathPatterns("/error");
    }

}