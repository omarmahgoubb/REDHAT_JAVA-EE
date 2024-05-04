package service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.ListModel;

public class ListService {
	@PersistenceContext(unitName="hello")
    private EntityManager entityManager; // Inject EntityManager
	public String create_lists(ListModel list) {
		String type;
		type=list.getType();
		try {
			if (type==null || type.isEmpty()) {
				return "list type cannot be empty or null";
			}
			if ("todo".equalsIgnoreCase(type) ||
	                "in progress".equalsIgnoreCase(type) ||
	                "testing".equalsIgnoreCase(type) ||
	                "done".equalsIgnoreCase(type)) {
	                list.setType(type);
	            }
			else {
				return "list type must be one of 4 types [todo ,in progress,testing,done]";	
			}
			
		}
		catch (Exception e) {
			 e.printStackTrace(); // This is for demonstration, you may want to log it properly
		        return "An error occurred during set type of list";
		}
		try {
			TypedQuery<Long> query = entityManager.createQuery
					("SELECT COUNT(l) FROM ListModel l WHERE l.type = "
							+ ":type", Long.class);
					query.setParameter("type", type);
					Long count = query.getSingleResult();
					if (count > 0) {
		                return "A list with type " + type + " already exists";
		            }
					entityManager.persist(type);
			        return "list added successfully";
			        }
				catch (Exception e) 
				{
			        // Log the exception or handle it accordingly
			        e.printStackTrace(); // This is for demonstration, you may want to log it properly
			        return "An error occurred during list creation";
			    }
			        // Business logic for user registration...
			    }
			   
			   
		
		
	}



