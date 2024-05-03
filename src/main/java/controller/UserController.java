package controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.User;

@Stateful
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class UserController {
    @PersistenceContext(unitName="hello")
    private EntityManager entityManager; // Inject EntityManager

    @EJB
    User user;
    
    
    
    @POST
    @Path("register")
    public String registerUser(User user)
    {
    	
 	    	String email=user.getEmail();
 	    	String password=user.getPassword();
 	    	
 	    	user.setEmail(email);
 	    	user.setPassword(password);
    	
    	try {
            entityManager.persist(user);

            return "User registered successfully";
            }
    	catch (Exception e) 
    	{
            // Log the exception or handle it accordingly
            e.printStackTrace(); // This is for demonstration, you may want to log it properly
            return "An error occurred during user registration";
        }
    }
    	
    @GET
	@Path("get_user")
	public List<User> getUsers()
	{
		TypedQuery<User> query =entityManager.createQuery("SELECT c from User c", User.class);
		List <User> users= query.getResultList();
		
		return users;
	}
    
    

    @POST
    @Path("update_profile")
    public String updateUserProfile(User updatedUser) 
    {
        try {
            User existingUser = entityManager.find(User.class, updatedUser.getId());
            
           
                // Update profile information
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setPassword(updatedUser.getPassword());

                entityManager.merge(existingUser);
                return "User profile updated successfully";
        
            
        } catch (Exception e) {
            // Log the exception or handle it accordingly
            e.printStackTrace(); // This is for demonstration, you may want to log it properly
            return "An error occurred while updating user profile";
        }
    }
    
   
    
    
    }