package rest;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.mapping.Map;

import entities.InventoryEntity;
import entities.UserEntity;


@Path("/InventoryServices")
public class InventoryService {
        EntityManagerFactory emf = Persistence
                        .createEntityManagerFactory("jpa-inventory");
        EntityManager entityManager = emf.createEntityManager();

        @GET
        @Path("/getInventoryDetails/{username}")
        public List<InventoryEntity> getInventoryDetails(@PathParam("username") String username) throws ClassNotFoundException, SQLException {
        		UserEntity userEntityRecord = entityManager.find(UserEntity.class, username);
        		List<InventoryEntity> listOfAllEntities = null;
        		//HashMap<String, InventoryEntity> mapOfInventoryIdAndRecord = new HashMap<String, InventoryEntity>();
        		
        		TypedQuery<InventoryEntity> query = null;
        		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
                CriteriaQuery<InventoryEntity> q = cb.createQuery(InventoryEntity.class);
                Root<InventoryEntity> c = q.from(InventoryEntity.class);
        		
        		if(userEntityRecord != null) {
        			if(userEntityRecord.getAccessLevel().equalsIgnoreCase("Admin")) {
        				q.select(c);
        			}else if(userEntityRecord.getAccessLevel().equalsIgnoreCase("Manager")) {
        				q.select(c).where(cb.equal(c.get("company"), userEntityRecord.getCompany()));	
        			}else if(userEntityRecord.getAccessLevel().equalsIgnoreCase("Employee")) {
        				q.select(c).where(cb.equal(c.get("company"), userEntityRecord.getCompany()), cb.equal(c.get("location"), userEntityRecord.getLocation()));
        			}
        		}
        		entityManager.getTransaction().begin();
        		query = entityManager.createQuery(q);       
        		
        		if(query.getResultList() != null && query.getResultList().size() >0) {
        			listOfAllEntities = query.getResultList();
        			//for(InventoryEntity entity : listOfAllEntities) {
        				//mapOfInventoryIdAndRecord.put(entity.getInventoryid(), entity);
        			//}
        		}
        		 entityManager.getTransaction().commit();
                return listOfAllEntities;
        }

        @Transactional
        @POST
        @Path("/addInventory/{username}")
        @Consumes(MediaType.APPLICATION_JSON)
        public String addInventory(InventoryEntity inventoryRecord, @PathParam("username") String username)
        		throws Exception, SecurityException, SystemException {
        	String result = "Adding new Inventory";
        	try {
        		
        		UserEntity userEntityRecord = entityManager.find(UserEntity.class, username);
        		result = validateInventory(userEntityRecord, inventoryRecord);
        		if (result == null ) {
        			if(inventoryRecord.getLocation() == null && userEntityRecord.getLocation() == null) {
        				 result = "Insert failed: Location of Inventory must be specified!!";
        			}else if(inventoryRecord.getCompany() == null && userEntityRecord.getCompany() == null) {
       				 result = "Insert failed: Company of Inventory must be specified!!";
        			}else if(inventoryRecord.getCountry() == null && userEntityRecord.getCountry() == null) {
          				 result = "Insert failed: Country of Inventory must be specified!!";
           			}else {
        				if(inventoryRecord.getCompany() == null && userEntityRecord.getCompany() != null) {
        					inventoryRecord.setCompany(userEntityRecord.getCompany());
        				}
        				if(inventoryRecord.getCountry() == null && userEntityRecord.getCountry() != null) {
        					inventoryRecord.setCountry(userEntityRecord.getCountry());
        				}   
        				if(inventoryRecord.getLocation() == null) {
        					inventoryRecord.setLocation(userEntityRecord.getLocation());
        				}
        				
        				inventoryRecord.setCreatedby(username);
        				
        				entityManager.getTransaction().begin();
        				entityManager.persist(inventoryRecord);
                        entityManager.getTransaction().commit();
                        
                        result = "Inventory added successfully!!";
        			}
					
        		}
        	}catch(Exception e) {
        		e.printStackTrace();
    			result = e.getMessage();
        	}                
            return result;
        }

        @Transactional
        @PUT
        @Path("/updateInventory/{username}")
        @Consumes(MediaType.APPLICATION_JSON)
        public String updateInventory(InventoryEntity record, @PathParam("username") String username) 
        		throws Exception, SecurityException, SystemException {
        	String result = "Updating Inventory...";
        	try {  
        		entityManager.getTransaction().begin();
        		UserEntity userEntityRecord = entityManager.find(UserEntity.class, username);
        		InventoryEntity inventory = entityManager.find(InventoryEntity.class, record.getInventoryid());
        		result = validateInventory(userEntityRecord, record);
        		if(result == null){   
       	           	 if(record.getInventoryname() != null) {
       	           		 inventory.setInventoryname(record.getInventoryname());
       	           	 }
       	           	 if(record.getNumberofitems() != null) {
       	           		 inventory.setNumberofitems(record.getNumberofitems());
       	           	 }
       	           	 inventory.setLastupdatedby(username);
       	           	        	          
       	          entityManager.merge(inventory);
       	          entityManager.getTransaction().commit();
                  result = "Transaction Complete"; 
        		}
        	}catch(Exception e){
        		e.printStackTrace();
    			result = e.getMessage();
        	}        	
           return result;
        }


        @Transactional
        @DELETE
        @Path("/deleteInventory/{username}/{inventoryId}")
        @Consumes(MediaType.APPLICATION_JSON)
        public String deleteInventory( @PathParam("username") String username, @PathParam("inventoryId") String inventoryId) 
        		throws Exception, SecurityException, SystemException {
        	String result = "Deleting inventory";
        	try {
        		InventoryEntity inventory = entityManager.find(InventoryEntity.class, inventoryId);
        	    UserEntity userEntityRecord = entityManager.find(UserEntity.class, username);
        	    result = validateInventory(userEntityRecord, inventory);
        	    if(result == null) {
             		 entityManager.getTransaction().begin();
                     entityManager.remove(inventory);
                     entityManager.getTransaction().commit();
                     result = "Inventory deleted successfully!!";  
        	    }
        	}catch(Exception e) {
        		e.printStackTrace();
    			result = e.getMessage();
        	}
        	return result;
        }
        
        /* inventory validations */        
            public String validateInventory(UserEntity user, InventoryEntity inventory) throws Exception, SecurityException, SystemException {
            	String result = null;
            	if(user != null && inventory != null) {
        			if(!user.getAccessLevel().equalsIgnoreCase("Admin")) {
        				if(user.getAccessLevel().equalsIgnoreCase("Manager")
        					&& !inventory.getCompany().equalsIgnoreCase(user.getCompany())) {
        					result = "Insufficient access previledges!!";
        				}else if(user.getAccessLevel().equalsIgnoreCase("Employee") && !inventory.getLocation().equalsIgnoreCase(user.getLocation())
        					&& !inventory.getCompany().equalsIgnoreCase(user.getCompany())) {
        					result = "Insufficient access previledges!!";
            			}
        			}
            	}else{
            		result = "Invalid user/inventory data!!";
            	}
            	return result;
            }

}