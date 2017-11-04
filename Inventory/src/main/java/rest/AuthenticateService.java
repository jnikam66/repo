package rest;

import java.sql.SQLException;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import entities.UserEntity;

@Path("/userServices")
public class AuthenticateService {
        EntityManagerFactory emf = Persistence
                        .createEntityManagerFactory("jpa-inventory");
        EntityManager entityManager = emf.createEntityManager();

        @GET
        @Path("/authenticate/{username}/{password}")
        public Boolean authenticateUser(@PathParam("username") String username,
                        @PathParam("password") String password)
                        throws ClassNotFoundException, SQLException {
        	UserEntity user = entityManager.find(UserEntity.class, username);
                //TODO Null check on user.getPassword() before comparing
                if (password.equals(user.getPassword())) {
                        return true;
                } else {
                        return false;
                }

        }

        @Transactional
        @POST
        @Path("/register")
        @Consumes(MediaType.APPLICATION_JSON)
        public String register(UserEntity userEntityRecord) throws Exception,
                        SecurityException, SystemException {
                String result = "Registering User...";
                try {
                        
                        entityManager.persist(userEntityRecord);
                        entityManager.getTransaction().commit();
                        result = "Transaction Complete";

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
        public String editRegister(@PathParam("username") String username,
        		UserEntity userEntityRecord) throws Exception, SecurityException,
                        SystemException {
                String result = "Updating User...";
                UserEntity user = entityManager.find(UserEntity.class, username);
                entityManager.getTransaction().begin();

                //TODO set all the fields that come from ui to entity object
                user.setCompany(userEntityRecord.getAccessLevel());

                entityManager.getTransaction().commit();

                result = "Transaction Complete";
                return result;
        }
        @GET
        @Path("/getAllUsers/")
        public Collection<UserEntity> getAllUsers() throws ClassNotFoundException, SQLException {
            Query query = entityManager.createNativeQuery("SELECT * FROM User u");
            return (Collection<UserEntity>) query.getResultList();
        }
}
