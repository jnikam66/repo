package rest;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/message")
public class MessageRestService {
	@PersistenceUnit(name = "jpa-inventory") 
	private EntityManagerFactory entityManagerFactory;

	public EntityManager getEntityManager(){
		return  entityManagerFactory.createEntityManager();
	}
	@GET
	@Path("/{param}")
	public Response printMessage(@PathParam("param") String msg) throws ClassNotFoundException, SQLException {

		Query query = this.getEntityManager().createQuery("select username from login");
        return (Response) query.getResultList(); 
	}

}