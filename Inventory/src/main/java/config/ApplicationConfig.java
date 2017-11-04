package config;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import org.jboss.resteasy.plugins.interceptors.CorsFilter;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import rest.AuthenticateService;
import rest.InventoryService;

@ApplicationPath("")
public class ApplicationConfig extends Application {
	private Set<Object> singletons = new HashSet<Object>();

	public ApplicationConfig() {
		singletons.add(new AuthenticateService());
		singletons.add(new InventoryService());
	}

	
	@Override
	public Set<Object> getSingletons() {
		if (singletons == null) {
			CorsFilter corsFilter = new CorsFilter();
			corsFilter.getAllowedOrigins().add("*");

			singletons = new LinkedHashSet<Object>();
			singletons.add(corsFilter);
		}
		return singletons;
	}
}
