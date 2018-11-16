package mouseportation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	private Logger logger = LoggerFactory.getLogger(WebConfig.class);
	
	@Autowired
	private Environment env;

	@Value("${resources.projectroot:}")
	private String projectRoot;

	@Value("${app.version:}")
	private String appVersion;

	private String getProjectRootRequired() {
		Assert.state(this.projectRoot != null, "Please set \"resources.projectRoot\" in application.yml");
		return this.projectRoot;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index.html");
	}

	// This is needed so that the "forward:" prefix works when adding the preceeding view controller.
	@Bean
	public InternalResourceViewResolver defaultViewResolver() {
		return new InternalResourceViewResolver();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		if (this.env.acceptsProfiles("development")) {
			String location = "file:///" + getProjectRootRequired() + "/client/dist/";
			logger.info("In DEVELOPMENT mode. Setting content location to " + location);

			registry.addResourceHandler("/**")
					.addResourceLocations(location)
					.setCachePeriod(0)
					.enableDevMode()
					.addVersion("dev", "/**/*.js")
					.addVersionHash("/**");
		}
		else {
			String location = "classpath:static/";
			logger.info("In PRODUCTION mode. Setting content location to " + location);

			registry.addResourceHandler("/**")
					.addResourceLocations(location)
					.addVersion(this.appVersion, "/**/*.js")
					.addVersionHash("/**");
		}
	}

}