package de.zalando.platform.hellojan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;

@Configuration
@EnableSwagger
public class SwaggerConfig extends WebMvcConfigurerAdapter {
	private SpringSwaggerConfig springSwaggerConfig;

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger-ui/**").addResourceLocations("classpath:/META-INF/resources/swagger-ui/");
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Hello Jan", "API for monitoring resource usage of Amazon Webservice accounts", "", "", "", "");
	}

	@Bean
	public SwaggerSpringMvcPlugin customImplementation() {
		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo()).includePatterns("/.*");
	}

	@Autowired
	public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
		this.springSwaggerConfig = springSwaggerConfig;
	}
}
