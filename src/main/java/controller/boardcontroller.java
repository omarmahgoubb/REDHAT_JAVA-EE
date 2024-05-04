package controller;

import model.Board;
import service.Boardservice;

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
	

}
