package service;
import model.Board;
import model.User;
import model.card;
import controller.boardcontroller;
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
   
    public String createBoard(Board board) {
    	
    	String boardname=board.getboardname();
    	//ArrayList <cardlist> cardlist=board.getCardlist()	;
    	//toadd list of listsssssssss 
    	
    	board.setboardname(boardname);

    	//board.setCardlist(cardlist);
    	
    	try {
    		if (boardname ==null || boardname.isEmpty()) {
                return "name cannot be null or empty";
    		}
    		
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
    public List<Board> getboards()
    {
    	
    	TypedQuery<Board> query =entitymanger.createQuery("SELECT n from Board n", Board.class);
		List <Board> boards= query.getResultList();
		return boards;
        // Business logic for fetching users...
    }
    
    public String deleteboard(Board board) {
        try {
            Board existingBoard = entitymanger.find(Board.class, board.getBoard_id());
            
            if (existingBoard == null) {
                return "Board not found"; // Return an appropriate message if the board doesn't exist
            }

            entitymanger.remove(existingBoard);            
            return "Board deleted successfully";
            
        } catch (Exception e) {
            // Log the exception or handle it accordingly
            e.printStackTrace(); // This is for demonstration, you may want to log it properly
            return "An error occurred during board deletion";
        }
    }

    
    
}