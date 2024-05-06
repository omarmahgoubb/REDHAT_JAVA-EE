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
    private EntityManager entitymanger;
   
    public String createBoard(Board board,String username) {
    	String boardname=board.getboardname();
    	board.setboardname(boardname);

    	
    	try {
    		if (boardname ==null || boardname.isEmpty()) {
                return "name cannot be null or empty";
    		}
    		 TypedQuery<User> userQuery = entitymanger.createQuery(
    		            "SELECT u FROM User u WHERE u.username = :username", User.class);
    		        userQuery.setParameter("username", username);
    		        User owner = userQuery.getSingleResult();
    		       // board.setOwner(owner);
    		        board.setboardname(boardname);
    		TypedQuery<Long> query = entitymanger.createQuery(
    			    "SELECT COUNT(n) FROM Board n WHERE n.boardname = "+ 
    		":boardname", Long.class);
    			query.setParameter("boardname", boardname);

    		
    		query.setParameter("boardname", boardname);
    		
    		long count=query.getSingleResult();
    		
    		if(count>0) {
    			return "this name is already used";
    		}
    		
    		entitymanger.persist(board);
    		return "board created successfully";
    	}
    	catch (Exception e) {	

            // Log the exception or handle it accordingly
            e.printStackTrace(); // This is for demonstration, you may want to log it properly
            e.getCause();
            return "An error occurred during board creation";
    	}
    	//logic
    }
    public List<Board> getboards(String username)
    {
    	
    	TypedQuery<Board> query = entitymanger.createQuery(
    	        "SELECT b FROM Board b WHERE b.owner.username = :username", Board.class);
    	    query.setParameter("username", username);
    	    List<Board> boards = query.getResultList();
    	    return boards;
    }
    
    public String deleteboard(String boardname , String username) {
        try {
    	TypedQuery<Board> query = entitymanger.createQuery("SELECT b FROM Board b WHERE b.boardname = :boardname", Board.class);
        query.setParameter("boardname", boardname);
        Board existingBoard = query.getSingleResult();
        
        if (existingBoard == null) {
            return "Board not found"; // Return an appropriate message if the board doesn't exist
        }
        
//        if (!existingBoard.getOwner().getUsername().equals(username)) {
//            return "You are not authorized to delete this board";
//        }

        entitymanger.remove(existingBoard);            
        return "Board deleted successfully";
    } catch (Exception e) {
        e.printStackTrace(); // This is for demonstration, you may want to log it properly
        return "An error occurred during board deletion";
    }

    
    
}
}