package controller;

import model.Board;
import model.User;
import service.Boardservice;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Stateful
@Path("boards")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class BoardController {
	@EJB
    private Boardservice boardservice;
	
	
	@POST
	@Path("create")
	public String createboard(Board board, @QueryParam("username") String username) {
		return boardservice.createBoard(board,username); 
	}
	@GET
	@Path("get_boards")
	public List<Board> getboards(@QueryParam("id") int id) {
	    return boardservice.getBoards( id);
	}	

	@PUT
    @Path("assign/{boardname}/{userName}")
    public String assignCardToUser
    (@PathParam("boardname") String cardTitle , @PathParam("userName") String userName )
    {	
    	return boardservice.assignboardtouser(cardTitle, userName);
    }
	
}