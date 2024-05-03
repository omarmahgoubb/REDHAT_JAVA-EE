//package service;
//import model.Board;
//import model.cardlist;
//import controller.boardcontroller;
//import javax.ejb.Stateful;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//
//import java.util.ArrayList;
//import java.util.List;
//@Stateful
//
//public class Boardservice {
//   @PersistenceContext(unitName="hello")
//    private EntityManager entitymanger;
//    public String createBoard(Board board) {
//    	
//    	String name=board.getnameofBoard();
//    	int id=board.getBoard_id();
//    	ArrayList <cardlist> cardlist=board.getCardlist();
//    	
//    	
//    	board.setBoard_id(id);
//    	board.setnameofBoard(name);
//    	board.setCardlist(cardlist);
//    	
//    	try {
//    		if (name ==null || name.isEmpty()) {
//    			return "name cannot be empty";
//    		}
//    		TypedQuery<Long> query = entitymanger.createQuery
//    				("SELECT COUNT(u) FROM Board u WHERE u.name =" + ":name",Long.class);
//    		query.setParameter("name", name);
//    		long count=query.getSingleResult();
//    		if(count>0) {
//    			return "this name is already used";
//    		}
//    		entitymanger.persist(board);
//    		return "board created successfully";
//    			
//    	}
//    	catch (Exception e) {	
//
//            // Log the exception or handle it accordingly
//            e.printStackTrace(); // This is for demonstration, you may want to log it properly
//            return "An error occurred during board creation";
//    	}
//    	//logic
//    }
//}
