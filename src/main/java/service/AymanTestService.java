package service;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import model.AymanTestModel;
import model.Board;
import model.Card;
import model.User;

@Stateful 
public class AymanTestService {
	@PersistenceContext(unitName="hello")
    private EntityManager entityManager; // Inject EntityManager
	public String create_lists(AymanTestModel list) {
	    String type = list.getType();

	    try {
	        if (type == null || type.isEmpty()) {
	            return "List type cannot be empty or null";
	        }

	        // Validate list type against allowed values
	        if (!isValidListType(type)) {
	            return "List type must be one of [todo, in progress, testing, done]";
	        }

	        // Check if a list with the same type already exists
	        TypedQuery<Long> countQuery = entityManager.createQuery(
	            "SELECT COUNT(l) FROM AymanTestModel l WHERE l.type = :type", Long.class);
	        countQuery.setParameter("type", type);
	        Long count = countQuery.getSingleResult();

	        if (count > 0) {
	            return "A list with type '" + type + "' already exists";
	        }

	        // Set the validated type to the list and persist it
	        list.setType(type);
	        entityManager.persist(list);

	        return "List added successfully";
	    } catch (Exception e) {
	        e.printStackTrace(); // Log the exception properly
	        return "An error occurred during list creation: " + e.getMessage();
	    }
	}

	// Helper method to validate list type against allowed values
	private boolean isValidListType(String type) {
	    return "todo".equalsIgnoreCase(type) ||
	           "in progress".equalsIgnoreCase(type) ||
	           "testing".equalsIgnoreCase(type) ||
	           "done".equalsIgnoreCase(type);
	}
	
	
	@Transactional
    public String deleteList(int boardId, String type) {
		try {
            // Create a JPQL query to delete lists by board ID and type
            Query deleteQuery = entityManager.createQuery(
                "DELETE FROM AymanTestModel m WHERE m.board.boardid = :boardId AND m.type = :type");
            deleteQuery.setParameter("boardId", boardId);
            deleteQuery.setParameter("type", type);

            // Execute the delete operation
            int deletedCount = deleteQuery.executeUpdate();

            if (deletedCount > 0) {
                return "Deleted " + deletedCount + " list(s) of type '" + type + "' for board ID " + boardId;
            } else {
                return "No lists of type '" + type + "' found for board ID " + boardId;
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception properly
            return "An error occurred during list deletion: " + e.getMessage();
        }
    }
	  public String assignlistToboard(String type, String boardname)
	    {
	        try 
	        {
	            // Find the card by its title
	            TypedQuery<AymanTestModel> listsquery = entityManager.createQuery
	            		("SELECT c FROM AymanTestModel c WHERE c.type = :type", AymanTestModel.class);
	            listsquery.setParameter("type", type);
	            List<AymanTestModel> lists = listsquery.getResultList();

	            if (lists.isEmpty())
	            {
	                return "list not found with this name : " + type;
	            }

	            AymanTestModel list = lists.get(0);
	            TypedQuery<Board> boardQuery = entityManager.createQuery
	            		("SELECT u FROM Board u WHERE u.boardname = :boardname", Board.class);
	            boardQuery.setParameter("boardname", boardname);
	            List<Board> boards = boardQuery.getResultList();
	            if (boards.isEmpty()) 
	            {
	                return "board not found with this name: " + boardname;
	            }
	            Board board = boards.get(0);
	            
	            board.getCardlist().add(list);
	            entityManager.merge(board);

	            return "list assigned to board successfully";
	        } catch (Exception e) 
	        {
	            e.printStackTrace();
	            return "An error occurred while assigning list to board";
	        }
	    }
	
		
	}