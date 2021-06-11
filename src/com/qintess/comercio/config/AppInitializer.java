package com.qintess.comercio.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//Essa classe será inicializada automaticamente pelo spring e efetuará todas as configurações necessárias
//Nós temos que escrever as configurações manualmente
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() { //esse método configura componentes externos (ex: hibernate)
		return new Class[] {
				HibernateConfig.class
		};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() { //esse método configura o mvc do spring
		return new Class[] {
			WebMvcConfig.class	
		};
	}

	@Override
	protected String[] getServletMappings() { // esse método configura o mapeamento do servlet do spring
		return new String[] {
			"/"	
		};
	}
	
	@Override
  protected Filter[] getServletFilters() {

    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
    characterEncodingFilter.setEncoding("UTF-8");
    return new Filter[] { characterEncodingFilter};
  }
	
	

}
