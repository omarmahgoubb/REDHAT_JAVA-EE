package controller;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import model.Card;
import service.CardService;

import java.util.List;

@Stateful
@Path("card")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CardController
{
	@EJB 
	private CardService cardService;
	
	@GET
    @Path("getcards")
    public List<Card> getCards() 
	{
        return cardService.getCards();
    }
	
	@POST
    @Path("create")
    public String createCard(Card card)
	{
        return cardService.createCard(card);
    }
	
	
	@PUT
	@Path("addcomment/{title}")
	@Consumes(MediaType.TEXT_PLAIN)
	public String addCommentToCardByTitle(@PathParam("title") String title, String comment)
	{
	        return cardService.addCommentToCardByTitle(title, comment);
	}
	    
	@PUT
	@Path("adddesc/{title}")
	@Consumes(MediaType.TEXT_PLAIN)

	    public String addDescriptionToCardByTitle(@PathParam("title") String title, String desc)
	{
	        return cardService.addDescriptionToCardByTitle(title, desc);
	}
	    
//	    @PUT
//	    @Path("assign/{cardTitle}/{username}")
//	    public String assignCardToUser(
//	            @PathParam("cardTitle") String cardTitle,
//	            @PathParam("username") String username) {
//	        return cardService.assignCardToUser(cardTitle, username);
//	    }
	    
	    @PUT
	    @Path("assign/{cardTitle}/{userName}")
	    public String assignCardToUser
	    (@PathParam("cardTitle") String cardTitle , @PathParam("userName") String userName )
	    {	
	    	return cardService.assignCardToUser(cardTitle, userName);
	    }
	    
	    
	    @PUT
	    @Path("assigncardtolist/{cardTitle}/{type}")
	    public String assigncardtolist
	    (@PathParam("cardTitle") String cardTitle , @PathParam("type") String type )
	    {	
	    	return cardService.assignCardTolist(cardTitle, type);
	    }
	   

	

}