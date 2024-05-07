package controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.AymanTestModel;
import model.Board;
import model.Card;
import model.User;
import service.AymanTestService;
import service.UserService;

@Stateful
@Path("test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AymanTestControl {
    @EJB
    private AymanTestService listservice;
    @POST
    @Path("add_list")
    public String registerUser(AymanTestModel list) {
        return listservice.create_lists( list);
    }
   
    @PUT
    @Path("assignlisttoboard/{boardname}/{listname}")
    public String assignCardToUser
    (@PathParam("listname") String listname  ,@PathParam("boardname") String boardname  )
    {	
    	return listservice.assignlistToboard(listname, boardname);
    }
   
    @DELETE
    @Path("deletelist/{listtype}/{boardid}")
    public String deleteList(@PathParam("listtype") String listType, @PathParam("boardid") int boardId) {
        return listservice.deleteList(listType, boardId);
    }
    @GET
    @Path("getlists")
    public List<AymanTestModel> getlists() 
	{
        return listservice.getlist();
    }

}