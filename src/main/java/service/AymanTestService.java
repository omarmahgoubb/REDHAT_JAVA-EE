package service;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.AymanTestModel;
import model.Board;
import model.User;

@Stateful 
public class AymanTestService {
	@PersistenceContext(unitName="hello")
    private EntityManager entityManager; // Inject EntityManager
	public String create_lists(AymanTestModel list) {
        String type = list.getType();
        //User user=list.getUsercreate();

        try {
            if (type == null || type.isEmpty()) {
                return "list type cannot be empty or null";
            }

            if (!isValidType(type)) {
                return "list type must be one of [todo, in progress, testing, done]";
            }

            // Check if a list with the same type already exists
            TypedQuery<Long> countQuery = entityManager.createQuery(
                    "SELECT COUNT(l) FROM AymanTestModel l WHERE l.type = :type", Long.class);
            countQuery.setParameter("type", type);
            Long count = countQuery.getSingleResult();

            if (count > 0) {
                return "A list with type " + type + " already exists";
            }

            // Create a new instance of AymanTestModel and set its type
         //   AymanTestModel newlist = new AymanTestModel();
            list.setType(type);
          //  list.setUsercreate(user);
            
            // Persist the newlist entity
            entityManager.persist(list);

            return "List added successfully";
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception properly
            return "An error occurred during list creation the creator is ";//+user.getName();
        }
    }

    private boolean isValidType(String type) {
        return "todo".equalsIgnoreCase(type) ||
                "in progress".equalsIgnoreCase(type) ||
                "testing".equalsIgnoreCase(type) ||
                "done".equalsIgnoreCase(type);
    }
    public String deletelist(String type) {
        try {
            TypedQuery<AymanTestModel> query = entityManager.createQuery("SELECT b FROM AymanTestModel b WHERE b.type = :type", AymanTestModel.class);
            query.setParameter("type", type);
            
            List<AymanTestModel> existingLists = query.getResultList();
            
            if (existingLists.isEmpty()) {
                return "List not found"; // Return an appropriate message if the list doesn't exist
            }
            
            AymanTestModel existingList = existingLists.get(0); // Assuming you want to delete the first matching list
            
            // Perform authorization check if needed
            // For example, check if the logged-in user is authorized to delete this list
            
            entityManager.remove(existingList);
            
            return "List deleted successfully";
        } catch (NoResultException e) {
            return "List not found"; // Handle case where no result is found
        } catch (Exception e) {
            e.printStackTrace(); // Log the error properly
            return "An error occurred during list deletion";
        }
    }
}