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

public class boardcontroller {
	@EJB
    private Boardservice boardservice;
	@POST
	@Path("create")
	public String createboard(Board board) {
		return boardservice.createBoard(board); 
	}
	@GET
	@Path("get_boards")
	public List<Board> getboards(){
		return boardservice.getboards();
	}
	  @DELETE
	    @Path("/{boardId}")
	    public Response deleteBoard(@PathParam("boardId") int boardId) {
	        Board boardToDelete = new Board();
	        boardToDelete.setBoard_id(boardId);

	        String result = boardservice.deleteboard(boardToDelete);

	        if (result.equals("Board deleted successfully")) {
	            return Response.status(Response.Status.OK).entity(result).build();
	        } else {
	            return Response.status(Response.Status.NOT_FOUND).entity(result).build();
	        }
	    }
	

}