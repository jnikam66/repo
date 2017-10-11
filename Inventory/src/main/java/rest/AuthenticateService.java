package rest;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.transaction.Transactional; 

import Entities.UserEntity;

@Path("/userServices")
public class AuthenticateService {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-inventory");
	EntityManager entityManager = emf.createEntityManager();

	@GET
	@Path("/authenticate/{username}/{password}")
	public Boolean authenticateUser(@PathParam("username") String username, @PathParam("password") String password)
			throws ClassNotFoundException, SQLException {
		Query query = entityManager.createNativeQuery("select l.password from login l where l.username = :username");
		query.setParameter("username", username.toUpperCase());
		if (query.getResultList().get(0).toString().equals(password.toUpperCase())) {
			return true;
		} else {
			return false;
		}

	}
	@Transactional() 
	@POST	
	@Path("/register/")
	//@Path("/register/{username}/{password}")
	//public String register(@PathParam("username") String email, @PathParam("password") String password)
			//throws Exception, SecurityException, SystemException {
	public String register(UserEntity userEntityRecord) throws Exception, SecurityException, SystemException {
		String result = "Registering User...";
		try {
			entityManager.getTransaction().begin(); 
			
			/*UserEntity userRec = new UserEntity();
			userRec = entityManager.find(User.class,userEntityRecord.getUsername());
			if(userRec!=null){
				result = "Duplicate user!!";
			}else{
				*/
				entityManager.persist(userEntityRecord); 
				entityManager.getTransaction().commit();
				result = "Transaction Complete";
			//}
			entityManager.close();
			
			/*
			Query query = entityManager.createNativeQuery(	"insert into login(id,username,password) values(?,?,?)");
			query.setParameter(1, 10);
			query.setParameter(2, email);
			query.setParameter(3, password);
			if (query.executeUpdate() > 0) {
				result = "Transaction Complete";
			}
			entityManager.getTransaction().commit();
		    entityManager.close(); 
			*/
		     
		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}
}
