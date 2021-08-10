package Certification.app.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {
	 
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
	        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
	        resolver.setOneIndexedParameters(true); //最初に表示されるページ数
	        resolver.setMaxPageSize(8); //1ページあたりの表示する件数
	        argumentResolvers.add(resolver);
	    }
}
