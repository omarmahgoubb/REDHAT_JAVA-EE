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
import java.util.Collections;
import java.util.List;


@Stateful
public class Boardservice {
   @PersistenceContext(unitName="hello")
    private EntityManager entitymanager;
   
  public String createBoard(Board board, String username) {
	    String boardname = board.getBoardname();
	    
	    try {
	        if (boardname == null || boardname.isEmpty()) {
	            return "Name cannot be null or empty";
	        }

	        TypedQuery<Long> countQuery = entitymanager.createQuery(
	                "SELECT COUNT(b) FROM Board b WHERE b.boardname = :boardname", Long.class);
	        countQuery.setParameter("boardname", boardname);
	        long count = countQuery.getSingleResult();

	        if (count > 0) {
	            return "This name is already used";
	        }

	        TypedQuery<User> userQuery = entitymanager.createQuery(
	                "SELECT u FROM User u WHERE u.username = :username", User.class);
	        userQuery.setParameter("username", username);
	        List<User> boardUsers = userQuery.getResultList();
	       // User user = boardUsers.get(0);

	       // board.getBoardUsers().add(user);
	        board.setBoardUsers(boardUsers);
	        board.setBoardname(boardname); // This line seems redundant
	        
	        entitymanager.persist(board);

	        return "Board created successfully";
	    } catch (Exception e) {
	        // Log the exception or handle it accordingly
	        e.printStackTrace(); // This is for demonstration, you may want to log it properly
	        return "An error occurred during board creation";
	    }
	}
   public List<Board> getBoards(int id) 
   {
	 
		   
	   TypedQuery<Board> query = entitymanager.createQuery(
			    "SELECT b FROM Board b JOIN b.boardUsers u WHERE u.userid = :id", Board.class);
			query.setParameter("id", id);
			return query.getResultList();

   }
   
 

     
    
    public String deleteboard(String boardname , String username) 
    {
        try 
        {
    	TypedQuery<Board> query = entitymanager.createQuery("SELECT b FROM Board b WHERE b.boardname = :boardname", Board.class);
        query.setParameter("boardname", boardname);
        Board existingBoard = query.getSingleResult();
        
        if (existingBoard == null) 
        {
            return "Board not found";
        }
        

        entitymanager.remove(existingBoard);            
        return "Board deleted successfully";
    } catch (Exception e) 
        {
        e.printStackTrace(); // This is for demonstration, you may want to log it properly
        return "An error occurred during board deletion";
    }

    
    
} 
   
  
}