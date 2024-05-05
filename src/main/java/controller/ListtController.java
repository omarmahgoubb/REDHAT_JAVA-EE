package controller;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Listt;
import service.ListtService;

@Stateful
@Path("lists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ListtController 
{
	@EJB
	private ListtService listservice;
	
	@POST
	@Path("create_list")
	public String create_list(Listt list) 
	{
		return listservice.create_lists(list);
		
		
	}
	
	

}