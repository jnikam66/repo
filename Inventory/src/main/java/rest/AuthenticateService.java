package rest;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.HibernateException;

import entities.InventoryEntity;
import entities.UserEntity;



@Path("/userServices")
public class AuthenticateService {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-inventory");
	EntityManager entityManager = emf.createEntityManager();

	@GET
	@Path("/authenticate/{username}/{password}")
	public Boolean authenticateUser(@PathParam("username") String username, @PathParam("password") String password)
			throws ClassNotFoundException, SQLException {
		Query query = entityManager.createNativeQuery("select l.password from user l where upper(l.username) = :username");
		query.setParameter("username", username.toUpperCase());
		if (query.getResultList()!= null && query.getResultList().size()>0 && query.getResultList().get(0).toString().equalsIgnoreCase(password)) {
			return true;
		} else {
			return false;
		}
		 
	}
	
	@GET
	@Path("/getUserDetails/{username}/")
	public UserEntity getUserDetails(@PathParam("username") String username) throws ClassNotFoundException, SQLException {
		UserEntity userEntityRecord = entityManager.find(UserEntity.class, username);
		if (userEntityRecord!= null ) {
			return userEntityRecord;
		} else {
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Path("/getAllUsers/{username}")
	public HashMap<String, UserEntity> getAllUsers(@PathParam("username") String username) throws ClassNotFoundException, SQLException {
		
		HashMap<String, UserEntity> mapOfUsernameAndRecord = new HashMap<String, UserEntity>();
		UserEntity userEntityRecord = entityManager.find(UserEntity.class, username);
		
		TypedQuery<UserEntity> query = null;
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> q = cb.createQuery(UserEntity.class);
        Root<UserEntity> c = q.from(UserEntity.class);
		
		if(userEntityRecord.getAccessLevel().equalsIgnoreCase("Admin")) {
			q.select(c);			
		}else if(userEntityRecord.getAccessLevel().equalsIgnoreCase("Manager")) {
			q.select(c).where(cb.equal(c.get("company"), userEntityRecord.getCompany()));		
		}
		query = entityManager.createQuery(q);
		if(query.getResultList() != null && query.getResultList().size() >0) {
			List<UserEntity> listOfAllEntities = query.getResultList();
			for(UserEntity entity : listOfAllEntities) {
				mapOfUsernameAndRecord.put(entity.getUsername(), entity);
			}
		}
		return mapOfUsernameAndRecord;
	}
	
	@Transactional() 
	@POST	
	@Path("/register/")
	@Consumes(MediaType.APPLICATION_JSON)
	public String register(UserEntity userEntityRecord) throws Exception, SecurityException, SystemException, HibernateException {
		String result = "Registering User...";
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(userEntityRecord); 
			entityManager.getTransaction().commit();
			result = "Transaction Complete";
			//entityManager.close();
		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}
	
	 @Transactional
     @PUT
     @Path("/update/{username}")
     @Consumes(MediaType.APPLICATION_JSON)
     public String modifyUserRecord(UserEntity userEntityRecord, @PathParam("username") String username) 
    		 throws Exception, SecurityException, SystemException {
             String result = "Updating User...";
             Boolean allowUpdate = true;
             try {
            	 UserEntity loggedinUser = entityManager.find(UserEntity.class, username);
            	 UserEntity updateUser = entityManager.find(UserEntity.class, userEntityRecord.getUsername());
            	 
            	 
            	 if(!loggedinUser.getAccessLevel().equalsIgnoreCase("Admin")) {
     				if(loggedinUser.getAccessLevel().equalsIgnoreCase("Manager")
     					&& !loggedinUser.getCompany().equalsIgnoreCase(updateUser.getCompany())) {
     					result = "Insufficient access previledges!!";
     					allowUpdate = false;
     				}else if(loggedinUser.getAccessLevel().equalsIgnoreCase("Employee") 
     						&& !loggedinUser.getUsername().equalsIgnoreCase(updateUser.getUsername())) {
     					result = "Insufficient access previledges!!";
     					allowUpdate = false;
         			}
     			}
            	 
            	 if(allowUpdate && updateUser.getIsActive()) {
                	 entityManager.getTransaction().begin();
                	 
                	 if(userEntityRecord.getCompany() != null) {
                		 updateUser.setCompany(userEntityRecord.getCompany());
                	 }
                	 if(userEntityRecord.getPassword() != null) {
                		 updateUser.setPassword(userEntityRecord.getPassword());
                	 }
                	 if(userEntityRecord.getCountry() != null) {
                		 updateUser.setCountry(userEntityRecord.getCountry());
                	 }
                	 if(userEntityRecord.getEmail() != null) {
                		 updateUser.setEmail(userEntityRecord.getEmail());
                	 }
                	 if(userEntityRecord.getcontactno() != null) {
                		 updateUser.setcontactno(userEntityRecord.getcontactno());
                	 }
                	 if(userEntityRecord.getHashKey() != null) {
                		 updateUser.setHashKey(userEntityRecord.getHashKey());
                	 }
                	 if(userEntityRecord.getIsActive() != null) {
                		 updateUser.setIsActive(userEntityRecord.getIsActive());
                	 }
                     
                    // user.setProfilePicture(userEntityRecord.getIsActive());
                	 entityManager.persist(updateUser);
                     entityManager.getTransaction().commit();

                     result = "User Updated!!";
                 }
                 else if(!updateUser.getIsActive()){
                	result = "Inactive user cannot be updated!!";
                 } else{
					result = "User does not exist!!";
				 }
             }catch(Exception e) {
            	 e.printStackTrace();
     			 result = e.getMessage();
             }
             return result;
     }
}
