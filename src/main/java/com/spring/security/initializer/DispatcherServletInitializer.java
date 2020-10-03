package com.spring.security.initializer;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.spring.security.config.ApplicationConfiguration;

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return ArrayUtils.toArray(ApplicationConfiguration.class);
	}

	@Override
	protected String[] getServletMappings() {
		return ArrayUtils.toArray("/");
	}
}
