package controller;

import model.User;
import service.ListService;
import service.UserService;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;



import model.ListModel;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Stateful
@Path("lists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ListController {
	@EJB
	private ListService listservice;
	@POST
	@Path("create_list")
	public String create_list(ListModel list) {
		return listservice.create_lists(list);
		
		
	}
	
	

}
