package rest;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

import entities.Inventory;


@Path("inventory")
public class InventoryService {
        EntityManagerFactory emf = Persistence
                        .createEntityManagerFactory("jpa-inventory");
        EntityManager entityManager = emf.createEntityManager();

        @GET
        @Path("/{username}")
        public List<Inventory> getInventoryDetails(
                        @PathParam("username") String username)
                        throws ClassNotFoundException, SQLException {

                CriteriaBuilder cb = entityManager.getCriteriaBuilder();

                CriteriaQuery<Inventory> q = cb.createQuery(Inventory.class);
                Root<Inventory> c = q.from(Inventory.class);
                ParameterExpression<String> p = cb.parameter(String.class);
                q.select(c).where(cb.equal(c.get("createdby"), username));

                TypedQuery<Inventory> query = entityManager.createQuery(q);
                List<Inventory> allitems = query.getResultList();

                return allitems;
        }

        @Transactional
        @POST
        @Path("/add")
        @Consumes(MediaType.APPLICATION_JSON)
        public void addInventory(Inventory inventory) throws Exception,
                        SecurityException, SystemException {
                entityManager.getTransaction().begin();
                entityManager.persist(inventory);
                entityManager.getTransaction().commit();
        }

        @Transactional
        @PUT
        @Path("/update/{inventoryId}")
        @Consumes(MediaType.APPLICATION_JSON)
        public String updateInventory(@PathParam("inventoryId") String inventoryId,
                        Inventory record) throws Exception, SecurityException,
                        SystemException {
                String result = "Updating User...";
                entityManager.getTransaction().begin();
                Inventory inventory = entityManager.find(Inventory.class, inventoryId);

                //TODO set all the fields that come from ui to entity object
                inventory.setInventoryName(record.getInventoryName());

                entityManager.getTransaction().commit();

                result = "Transaction Complete";
                return result;
        }


        @Transactional
        @DELETE
        @Path("/delete/{inventoryId}")
        @Consumes(MediaType.APPLICATION_JSON)
        public void deleteInventory(@PathParam("inventoryId") String inventoryId)
                        throws Exception, SecurityException,
                        SystemException {
                Inventory inventory = entityManager.find(Inventory.class, inventoryId);
                entityManager.getTransaction().begin();
                entityManager.remove(inventory);
                entityManager.getTransaction().commit();
        }

}