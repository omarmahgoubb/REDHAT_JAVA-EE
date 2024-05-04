package service;
import model.Board;
import model.cardlist;
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
    			    "SELECT COUNT(n) FROM Board n WHERE n.boardname = :boardname", Long.class);
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
}
