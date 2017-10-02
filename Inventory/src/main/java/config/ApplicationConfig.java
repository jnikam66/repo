package config;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import rest.AuthenticateService;

@ApplicationPath ("")
public class ApplicationConfig extends Application {
		private Set<Object> singletons = new HashSet<Object>();

		public ApplicationConfig() {
			singletons.add(new AuthenticateService());
		}

		@Override
		public Set<Object> getSingletons() {
			return singletons;
		}
}
