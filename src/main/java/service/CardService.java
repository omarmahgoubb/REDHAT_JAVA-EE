package service;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.AymanTestModel;
import model.Board;
import model.Card;
import model.User;

@Stateful
public class CardService {
	
    @PersistenceContext(unitName = "hello")
    private EntityManager entityManager;
 
    public List<Card> getCards() 
    {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c", Card.class);
        List<Card> cards = query.getResultList();
        return cards;
    }    
   
    
    
    public String createCard(Card card) 
    {
        String cardTitle = card.getTitle();

        try 
        {
            if (cardTitle == null || cardTitle.isEmpty()) 
            {
                return "Card title can't be null or empty";
            }

            TypedQuery<Card> query = entityManager.createQuery(
                    "SELECT c FROM Card c WHERE c.title = :title", Card.class);
            query.setParameter("title", cardTitle);
            List<Card> existingCards = query.getResultList();

            if (!existingCards.isEmpty())
            {
                return "A card with the title '" + cardTitle + "' already exists";
            }
            
            entityManager.persist(card);
            System.out.println("After persist - Card ID: " + card.getCardid());
            System.out.println("After persist - Card title: " + card.getTitle());

            return "Card created successfully";
        } catch (Exception e)
        {
            e.printStackTrace();
            return "An error occurred while creating card";
        }
    }


   
    public String addCommentToCardByTitle(String title, String comment) 
    {
        try
        {
            TypedQuery<Card> query = entityManager.createQuery
            		("SELECT c FROM Card c WHERE c.title = :title", Card.class);
            query.setParameter("title", title);
            List<Card> results = query.getResultList();

            if (results.isEmpty()) 
            {
                return "Card not found with title: " + title;
            }

            Card card = results.get(0); // Assuming title is unique, get the first result
            String existingComment = card.getComment();

            if (existingComment == null || existingComment.isEmpty()) 
            {
                card.setComment(comment);
            } else
            {
                card.setComment(existingComment +" "+ comment); // Append new comment to existing comment
            }
            entityManager.merge(card);
            return "Comment added successfully";
        } catch (Exception e) 
        {
            e.printStackTrace();
            return "An error occurred while adding comment to card";
        }
    }
    /////////////////////////////////////////////////////////////
    public String addDescriptionToCardByTitle(String title, String description) 
    {
        try 
        {
            TypedQuery<Card> query = entityManager.createQuery
            		("SELECT c FROM Card c WHERE c.title = :title", Card.class);
            query.setParameter("title", title);
            List<Card> results = query.getResultList();

            if (results.isEmpty()) 
            {
                return "Card not found with title: " + title;
            }
            Card card = results.get(0); // Assuming title is unique, get the first result
            String existingDescription = card.getDescription();

            if (existingDescription == null || existingDescription.isEmpty())
            {
                card.setDescription(description);
            } else 
            {
                card.setDescription(existingDescription + " "+ description); // Append new description to existing comment
            }

            entityManager.merge(card);
            return "description added successfully";
        } catch (Exception e)
        {
            e.printStackTrace();
            return "An error occurred while adding comment to card";
        }
    }
    
    ///////////
    public String assignCardToUser(String cardTitle, String username)
    {
        try 
        {
            // Find the card by its title
            TypedQuery<Card> cardQuery = entityManager.createQuery
            		("SELECT c FROM Card c WHERE c.title = :title", Card.class);
            cardQuery.setParameter("title", cardTitle);
            List<Card> cards = cardQuery.getResultList();

            if (cards.isEmpty())
            {
                return "Card not found with title: " + cardTitle;
            }

            Card card = cards.get(0);
            TypedQuery<User> userQuery = entityManager.createQuery
            		("SELECT u FROM User u WHERE u.username = :username", User.class);
            userQuery.setParameter("username", username);
            List<User> users = userQuery.getResultList();
            if (users.isEmpty()) 
            {
                return "User not found with username: " + username;
            }
            User user = users.get(0);
            
            card.getAssignedUsers().add(user);
            entityManager.merge(card);

            return "Card assigned to user successfully";
        } catch (Exception e) 
        {
            e.printStackTrace();
            return "An error occurred while assigning card to user";
        }
    }
    
    public String assignCardTolist(String cardTitle, String type)
    {
        try 
        {
            // Find the card by its title
            TypedQuery<Card> cardQuery = entityManager.createQuery
            		("SELECT c FROM Card c WHERE c.title = :title", Card.class);
            cardQuery.setParameter("title", cardTitle);
            List<Card> cards = cardQuery.getResultList();

            if (cards.isEmpty())
            {
                return "Card not found with title: " + cardTitle;
            }

            Card card = cards.get(0);
            TypedQuery<AymanTestModel> userQuery = entityManager.createQuery
            		("SELECT u FROM AymanTestModel u WHERE u.type = :type", AymanTestModel.class);
            userQuery.setParameter("type", type);
            List<AymanTestModel> lists = userQuery.getResultList();
            if (lists.isEmpty()) 
            {
                return "list not found with username: " + type;
            }
            AymanTestModel list = lists.get(0);
            
            list.getCards().add(card);
            entityManager.merge(card);

            return "card assigned to list successfully";
        } catch (Exception e) 
        {
            e.printStackTrace();
            return "An error occurred while assigning list to card";
        }
    }
    
    }


    
    /////////////////////////////////////////////////////////////////
  
//    
//    public String assignCardToUser(String title, String username) {
//        try {
//            TypedQuery<Card> cardQuery = entityManager.createQuery(
//                    "SELECT c FROM Card c WHERE c.title = :title", Card.class);
//            cardQuery.setParameter("title", title);
//            List<Card> cards = cardQuery.getResultList();
//
//            if (cards.isEmpty()) {
//                return "Card not found with title: " + title;
//            }
//
//            Card card = cards.get(0);
//
//            TypedQuery<User> userQuery = entityManager.createQuery(
//                    "SELECT u FROM User u WHERE u.username = :username", User.class);
//            userQuery.setParameter("username", username);
//            List<User> users = userQuery.getResultList();
//
//            if (users.isEmpty()) {
//                return "User not found with username: " + username;
//            }
//
//            User user = users.get(0);
//
//            // Assign card to user
//            user.addAssignedCard(card);
//            entityManager.merge(user);
//
//            return "Card assigned to user successfully";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "An error occurred while assigning card to user";
//        }
//    }
//
//    
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
    
    
    
