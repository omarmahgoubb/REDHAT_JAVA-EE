package service;

import model.User;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateful
public class UserService {
    @PersistenceContext(unitName="hello")
    private EntityManager entityManager; // Inject EntityManager

    public String registerUser(User user)
    {
    	String email=user.getEmail();
	    	String password=user.getPassword();
	    	String name=user.getName();
	    	
	    	user.setEmail(email);
	    	user.setPassword(password);
	
	try {
		if (email == null || email.isEmpty())
		{
            return "Email cannot be null or empty";
        }
		if (password == null || password.isEmpty())
		{
            return "Password cannot be null or empty";
        }
		
		TypedQuery<Long> query = entityManager.createQuery
		("SELECT COUNT(u) FROM User u WHERE u.email = "
				+ ":email", Long.class);
		query.setParameter("email", email);
		Long count = query.getSingleResult();

		if (count > 0) {
		    return "Email already found";
		}

		entityManager.persist(user);
        return "User registered successfully";
        }
	catch (Exception e) 
	{
        // Log the exception or handle it accordingly
        e.printStackTrace(); // This is for demonstration, you may want to log it properly
        return "An error occurred during user registration";
    }
        // Business logic for user registration...
    }

    public List<User> getUsers()
    {
    	TypedQuery<User> query =entityManager.createQuery("SELECT c from User c", User.class);
		List <User> users= query.getResultList();
		
		return users;
        // Business logic for fetching users...
    }

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
        // Business logic for updating user profile...
    }
}
