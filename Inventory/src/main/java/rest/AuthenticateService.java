package rest;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/authenticate")
public class AuthenticateService {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-inventory"); 

	
	@GET
		@Path("/{username}/{password}")
	       public Boolean authenticateUser(@PathParam("username") String username,@PathParam("password") String password ) throws ClassNotFoundException, SQLException {
	             EntityManager entityManager = emf.createEntityManager();
	             Query query = entityManager.createNativeQuery("select l.password from login l where l.username = :username");
	             query.setParameter("username", username.toUpperCase());
	             if(query.getResultList().get(0) == password) {
	            	 return true;
	             }else {
	            	 return false;
	             }
	             
	       } 
	}
