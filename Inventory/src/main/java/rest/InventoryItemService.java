package rest;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
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
import entities.InventoryItemEntity;
import entities.UserEntity;


@Path("/InventoryItemServices")
public class InventoryItemService {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-inventory");
        EntityManager entityManager = emf.createEntityManager();

        @SuppressWarnings("unchecked")
		@GET
        @Path("/getInventoryItemDetails/{username}/{inventoryId}")
        public List<InventoryItemEntity> getInventoryItemDetails( @PathParam("username") String username, @PathParam("inventoryId") String inventoryId)
                        throws ClassNotFoundException, SQLException {    
        	
    		List<InventoryItemEntity> listOfAllEntities = null;  
    		
    		try {
    			InventoryEntity inventory = entityManager.find(InventoryEntity.class, inventoryId);
    			UserEntity user = entityManager.find(UserEntity.class, username);
    			String validation = validateInventory(user, inventory);
    			
    			if(validation == null) {
    				TypedQuery<InventoryItemEntity> query = null;
            		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
                    CriteriaQuery<InventoryItemEntity> q = cb.createQuery(InventoryItemEntity.class);
                    Root<InventoryItemEntity> c = q.from(InventoryItemEntity.class);
                    q.select(c).where(cb.equal(c.get("inventoryid"), inventoryId));
    				query = entityManager.createQuery(q);
    				
    				if(query.getResultList() != null && query.getResultList().size() > 0) {
            			listOfAllEntities = query.getResultList();
            		}
    			}
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
            return listOfAllEntities;
        }

        @Transactional
        @POST
        @Path("/addItem/{username}/{inventoryId}")
        @Consumes(MediaType.APPLICATION_JSON)
        public String addInventoryItem(InventoryItemEntity inventoryItemRecord,  @PathParam("username") String username,
        		 @PathParam("inventoryId") String inventoryId) throws Exception, SecurityException, SystemException {
        	String result = "Adding new Inventory Item";
        	try {
        		InventoryEntity inventory = entityManager.find(InventoryEntity.class, inventoryId);
        		UserEntity user = entityManager.find(UserEntity.class, username);
        		
        		result = validateInventory(user, inventory);
        		if(result == null) {
        			if(inventoryItemRecord == null) {
        				result = "No data present!!";
        			}else {
        				entityManager.getTransaction().begin();
        				inventoryItemRecord.setCompany(inventory.getCompany());
        				inventoryItemRecord.setCreatedby(user.getUsername());
        				inventoryItemRecord.setInventoryid(inventoryId);
        				inventoryItemRecord.setLocation(inventory.getLocation());
        				inventoryItemRecord.setCreatedDate(Calendar.getInstance().getTime());
        				entityManager.persist(inventoryItemRecord);
                        entityManager.getTransaction().commit();
                        result = "InventoryItem added successfully!!";
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
        @Path("/updateItem/{username}/{inventoryId}")
        @Consumes(MediaType.APPLICATION_JSON)
        public String updateInventoryItem(InventoryItemEntity record, @PathParam("username") String username,
       		 @PathParam("inventoryId") String inventoryId) throws Exception, SecurityException, SystemException {
        	String result = "Updating User...";
        	try {   
        		InventoryEntity inventory = entityManager.find(InventoryEntity.class, inventoryId);
        		UserEntity user = entityManager.find(UserEntity.class, username);
        		result = validateInventory(user, inventory);
        		if(result == null){
        			entityManager.getTransaction().begin();
        			InventoryItemEntity inventoryItem = entityManager.find(InventoryItemEntity.class, record.getItemid());
                    if(inventoryItem != null) {
           	           	 if(record.getCostPerUnit() != null) {
           	           		 inventoryItem.setCostPerUnit(record.getCostPerUnit());
           	           	 }
           	           	 if(record.getDescription() != null) {
           	           		 inventoryItem.setDescription(record.getDescription());
           	           	 }
           	           	 if(record.getExpirationDate() != null) {
           	           		 inventoryItem.setExpirationDate(record.getExpirationDate());
           	           	 }
           	           	 if(record.getItemGroup() != null) {
           	           		 inventoryItem.setItemGroup(record.getItemGroup());
           	           	 }
           	           	 if(record.getItemName() != null) {
           	           		 inventoryItem.setItemName(record.getItemName());
           	           	 }
           	           	 if(record.getQuantity() != null) {
           	           		 if(Integer.valueOf(inventoryItem.getInitial_quantity()) < Integer.valueOf(record.getQuantity())) {
           	           			 result = "Error: Cannot set quantity greater than initial quantity!!";
           	           		 }
           	           		 inventoryItem.setQuantity(record.getQuantity());
           	           	 }
           	           	 if(record.getStatus() != null) {
        	           		 inventoryItem.setStatus(record.getStatus());
        	           	 }
	           	         if(record.getUnitOfMeasure() != null) {
	     	           		 inventoryItem.setUnitOfMeasure(record.getUnitOfMeasure());
	     	           	 }
	           	         if(record.getInitial_quantity() != null) {
	     	           		 inventoryItem.setInitial_quantity(record.getInitial_quantity());
	     	           	 }

	           	         if(result == null) {
	           	        	 inventoryItem.setLastupdatedby(user.getUsername());
	              	          entityManager.merge(inventoryItem);
	              	          entityManager.getTransaction().commit();
	                         result = "Transaction Complete"; 
	           	         }
                    }else {
                    	result = "Inventory item not found!!";
                    }
        		}
        	}catch(Exception e){
        		e.printStackTrace();
    			result = e.getMessage();
        	}        	
           return result;
        }


        @Transactional
        @DELETE
        @Path("/deleteItem/{username}/{inventoryId}/{inventoryItemId}")
        @Consumes(MediaType.APPLICATION_JSON)
        public String deleteInventoryItem(@PathParam("username") String username,  @PathParam("inventoryId") String inventoryId,
        		@PathParam("inventoryItemId") String itemId) throws Exception, SecurityException, SystemException {
        	String result = "Deleting inventory";
        	try {
        		InventoryEntity inventory = entityManager.find(InventoryEntity.class, inventoryId);
        	    UserEntity user = entityManager.find(UserEntity.class, username);
        	    result = validateInventory(user, inventory);
        		if(result == null){
        			entityManager.getTransaction().begin();
        			InventoryItemEntity inventoryItem = entityManager.find(InventoryItemEntity.class, itemId);
        			
    				if(inventoryItem != null) {
    					 entityManager.remove(inventoryItem);
    	                 entityManager.getTransaction().commit();
    	                 result = "Inventory deleted successfully!!";
            		}else {
            			 result = "Inventory Item not found!!";
            		}
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