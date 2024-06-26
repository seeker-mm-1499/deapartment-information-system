package mm.edu.ec.mtu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan("mm.edu.ec.mtu")
public class WebMvcConfig implements WebMvcConfigurer{

	@Bean
	SpringResourceTemplateResolver templateResolver() {
		var resolver = new SpringResourceTemplateResolver();
		resolver.setPrefix("/views/");
		resolver.setSuffix(".html");
		resolver.setCacheable(false);
		return resolver;
	}
	
	@Bean
	SpringTemplateEngine engine(SpringResourceTemplateResolver resolver) {
		var engine = new SpringTemplateEngine();
		engine.setTemplateResolver(resolver);
		return engine;
	}
	
	@Bean
	ThymeleafViewResolver viewResolver(SpringTemplateEngine engine) {
		var viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(engine);
		return viewResolver;
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}
