package service;
import model.Board;
import model.User;
import model.Card;
import controller.BoardController;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    
    
        
        public String assignboardtouser(String boardname ,String username ) {
        	try {
        		TypedQuery<Board> boardquery =entitymanager.createQuery("select b from Board b WHERE b.boardname = :boardname",Board.class);
        			boardquery.setParameter("boardname", boardname);
        			List<Board> boards=boardquery.getResultList();
        			if (boards.isEmpty()) {
                        return "board not found with this name: " + boardname;
        			
        		}
        			Board board = boards.get(0);
        			  TypedQuery<User> userQuery = entitymanager.createQuery
        	            		("SELECT u FROM User u WHERE u.username = :username", User.class);
        	            userQuery.setParameter("username", username);
        	            List<User> users = userQuery.getResultList();
        	            if (users.isEmpty()) 
        	            {
        	                return "User not found with username: " + username;
        	            }
        	            User user = users.get(0);
        	           board.getBoardUsers().add(user);
        	           entitymanager.merge(board);
        	           return "board assigned to user successfully";
            } catch (Exception e) 
            {
                e.printStackTrace();
                return "An error occurred while assigning board to user";
            }
        }
    
    
        
        
        public String inviteothers(String username ,String boardname) {
        	try {
        		TypedQuery<User> query = entitymanager.createQuery(
                    "SELECT u FROM User u WHERE u.username = :username",User.class);
     	  query.setParameter("username", username);
    	   List<User> users=query.getResultList();
    	   if (users.isEmpty())
           {
               return "User not found with this name "+ username ;
           }
           
           User usertoadd = users.get(0);
           TypedQuery<Board> boardquery =entitymanager.createQuery("select b from Board b WHERE b.boardname = :boardname",Board.class);
			boardquery.setParameter("boardname", boardname);
			List<Board> boards=boardquery.getResultList();
			if (boards.isEmpty())
	           {
	               return "board not found with this name "+ boardname ;
	           }
			Board board = boards.get(0);
			if (board.getBoardUsers().contains(usertoadd)) {
	            return "User " + username + " is already at the board " + boardname;
	        }
			 board.getBoardUsers().add(usertoadd);
	           entitymanager.merge(board);
	           return "user has invited to the board successfully";
        	}
        	
        	catch (Exception e) 
            {
                e.printStackTrace();
                return "An error occurred while assigning invite user";
            }
        	
        }
}