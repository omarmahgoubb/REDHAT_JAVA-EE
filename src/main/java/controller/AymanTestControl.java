package controller;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.AymanTestModel;
import model.Board;
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
    @DELETE
    @Path("lists/{boardId}/{type}")
    public String deleteListsByBoardIdAndType(@PathParam("boardId") int boardId,@PathParam("type") String type) {
        return listservice.deleteList(boardId, type);
    }
}