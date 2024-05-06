package service;
import model.Board;
import model.User;
import model.Card;
import controller.BoardController;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;


@Stateful
public class Boardservice {
   @PersistenceContext(unitName="hello")
    private EntityManager entitymanager;
   
   public String createBoard(Board board, String username) {
	    String boardname = board.getboardname();
	    
	    try {
	        if (boardname == null || boardname.isEmpty()) {
	            return "Name cannot be null or empty";
	        }

	        // Query to check if the board name already exists
	        TypedQuery<Long> countQuery = entitymanager.createQuery(
	                "SELECT COUNT(b) FROM Board b WHERE b.boardname = :boardname", Long.class);
	        countQuery.setParameter("boardname", boardname);
	        long count = countQuery.getSingleResult();

	        if (count > 0) {
	            return "This name is already used";
	        }

	        // Query to find the user by username
	        TypedQuery<User> userQuery = entitymanager.createQuery(
	                "SELECT u FROM User u WHERE u.username = :username", User.class);
	        userQuery.setParameter("username", username);
	        List<User> boardUsers = userQuery.getResultList();

	        // Set the board users and board name
	        board.setboardUsers(boardUsers);
	        board.setboardname(boardname); // This line seems redundant
	        
	        // Persist the board entity
	        entitymanager.persist(board);

	        return "Board created successfully";
	    } catch (Exception e) {
	        // Log the exception or handle it accordingly
	        e.printStackTrace(); // This is for demonstration, you may want to log it properly
	        return "An error occurred during board creation";
	    }
	}
/*
    public List<Board> getboards(String username) {
    	 
             TypedQuery<Board> query = entitymanager.createQuery(
                 "SELECT b FROM Board b JOIN b.users u WHERE u.username = :username", Board.class);
             query.setParameter
             ("username", username);
             
             System.out.println("Generated JPQL: " + query.toString());

             
             return query.getResultList();
         
    }*/
   public List<Board> getBoards(String username) {
	    TypedQuery<Board> query = entitymanager.createQuery(
	        "SELECT b FROM Board b JOIN b.boardUsers u WHERE u.username = :username", Board.class);
	    query.setParameter("username", username);
	    return query.getResultList();
	}

   public List<Board> getallBoards() {
       // Create and execute the query to retrieve all cards
       TypedQuery<Board> query = entitymanager.createQuery("SELECT c FROM Board c", Board.class);
       List<Board> boards = query.getResultList();
       return boards;
   }    
    
    public String deleteboard(String boardname , String username) {
        try {
    	TypedQuery<Board> query = entitymanager.createQuery("SELECT b FROM Board b WHERE b.boardname = :boardname", Board.class);
        query.setParameter("boardname", boardname);
        Board existingBoard = query.getSingleResult();
        
        if (existingBoard == null) {
            return "Board not found"; // Return an appropriate message if the board doesn't exist
        }
        
//      if (!existingBoard.().getUsername().equals(username)) {
//            return "You are not authorized to delete this board";
//        }

        entitymanager.remove(existingBoard);            
        return "Board deleted successfully";
    } catch (Exception e) {
        e.printStackTrace(); // This is for demonstration, you may want to log it properly
        return "An error occurred during board deletion";
    }

    
    
}
}