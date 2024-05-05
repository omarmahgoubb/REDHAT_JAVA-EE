package service;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Card;

@Stateful
public class CardService {
	
    @PersistenceContext(unitName = "hello")
    private EntityManager entityManager;
    

    
    /////////////////////////
    public List<Card> getCards()
    {
    	TypedQuery<Card> query =entityManager.createQuery("SELECT c from Card c", Card.class);
		List <Card> cards= query.getResultList();
		
		return cards;
        // Business logic for fetching users...
    }
    
    ///////////////
    public String createCard(Card card) {
        String cardTitle = card.getTitle();

        try {
            if (cardTitle == null || cardTitle.isEmpty()) {
                return "Card title can't be null or empty";
            }

            // Check if a card with the same title already exists
            TypedQuery<Card> query = entityManager.createQuery(
                    "SELECT c FROM Card c WHERE c.title = :title", Card.class);
            query.setParameter("title", cardTitle);
            List<Card> existingCards = query.getResultList();

            if (!existingCards.isEmpty()) {
                return "A card with the title '" + cardTitle + "' already exists";
            }

            // If no existing card found with the same title, proceed to persist the new card
            entityManager.persist(card);
            System.out.println("After persist - Card ID: " + card.getId());
            System.out.println("After persist - Card title: " + card.getTitle());

            return "Card created successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while creating card";
        }
    }

/*
    public String moveCard(int cardId, int newListId) {
        try {
            Card card = entityManager.find(Card.class, cardId);
            if (card == null) {
                return "Card not found";
            }
            TaskList newList = entityManager.find(TaskList.class, newListId);
            if (newList == null) {
                return "Destination list not found";
            }
            card.setTaskList(newList);
            entityManager.merge(card);
            return "Card moved successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while moving card";
        }
    }
*/
    public String assignCard(int cardId, int userId) {
        try {
            // Implement logic to assign card to a user
            return "Card assigned successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while assigning card";
        }
    }

    
    ///////////////////////////////////////////////
   
/*    
    public String addCommentToCard(int cardId, String comment) {
    	

        try {
        	TypedQuery<Card> query =entityManager.createQuery("SELECT z from Card z where z.id =:zid", Card.class);
        	query.setParameter("zid", id );
        	Card card = query.getResultList();
    		return card
    		;
    		
        	Card card = entityManager.find(Card.class, cardId);
        	
        	if (card == null ) {
        		return "Card not found";
        	}
        	// add comment
        	
        	String existingComment = card.getComment();
            if (existingComment == null || existingComment.isEmpty()) {
                card.setComment(comment);
            } else {
                card.setComment(existingComment + "\n" + comment); // Append new comment to existing comment
            }
            
            entityManager.merge(card);
            return "Comment added successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while adding comment to card";
        }
    }
    */
    /////////////////////////////////////////////////////////////
    public String addDetailsToCard(Card cardToUpdate, String description) {
    	if (cardToUpdate == null ) {
    		return "Card can not be null";
    	}
    	else if (description == null || description.isEmpty()) {
    		return "description is null or Empty" ;
    	}

        try {
        	
        	Card existingCard = entityManager.find(Card.class, cardToUpdate.getId() ) ;
        	// add description
        	existingCard.setDescription(description); 
        	
            entityManager.merge(existingCard);
            return "description added successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while adding comment to card";
        }
    }
    /////////////////////////////////////////////////////////////////
    public String addDetailsAndCommentToCard(Card cardToUpdate, String description , String comment ) {
    	if (cardToUpdate == null ) {
    		return "Card can not be null";
    	}
    	else if (description == null || description.isEmpty()) {
    		return "description is null or Empty" ;
    	}
    	else if (comment == null || comment.isEmpty()) {
    		return "comment is null or Empty" ;
    	}

        try {
        	
        	Card existingCard = entityManager.find(Card.class, cardToUpdate.getId() ) ;
        	// add description & comment 
        	existingCard.setDescription(description); 
        	existingCard.setComment(comment); 

            entityManager.merge(existingCard);
            return "description and comment added successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while adding comment to card";
        }
    }
}