package controller;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import model.Card;
import model.User;
import service.CardService;

import java.util.List;

@Stateful
@Path("card")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CardController {
	@EJB 
	private CardService cardService;
	
	@POST
    @Path("create")
    public String createCard(Card card) {
        return cardService.createCard(card);
    }
	
	@GET
    @Path("getcards")
    public List<Card> getCards() {
        return cardService.getCards();
    }
	
	/*
	 @PUT 
	 @Path("addcomment/{cardId}")
	 @Consumes(MediaType.TEXT_PLAIN)
	    public String addCommentToCard(@PathParam("carId") int cardId , String comment)  {
		 
	        return cardService.addCommentToCard(cardId , comment );
	    }
	    */

}