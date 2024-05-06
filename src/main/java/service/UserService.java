package service;

import model.Card;
import model.User;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Size;

import java.util.List;

@Stateful
public class UserService {
    @PersistenceContext(unitName="hello")
    private EntityManager entityManager; // Inject EntityManager

    public String registerUser(User user)
    {
    	    String email=user.getEmail();
    	    String username=user.getUsername();
	    	String password=user.getPassword();
	    	String name=user.getName();
	    	String role=user.getRole();
	    	
	    	user.setEmail(email);
	    	user.setPassword(password);
	    	user.setUsername(username);
	    	user.setRole(role);
	
	try {
		if (email == null || email.isEmpty())
		{
            return "Email cannot be null or empty";
        }
		if (password == null || password.isEmpty())
		{
            return "Password cannot be null or empty";
        }
		if(countDigits(password)<8)
		{
			return "password must be more than 8 characters";
		}
		if (username == null || username.isEmpty())
		{
            return "Username cannot be null or empty";
        }

		
		TypedQuery<Long> query = entityManager.createQuery
		("SELECT COUNT(u) FROM User u WHERE u.email = "
				+ ":email", Long.class);
		query.setParameter("email", email);
		Long count = query.getSingleResult();

		if (count > 0) 
		{
		    return "Email already found";
		}
		
		
		TypedQuery<Long> query2 = entityManager.createQuery
				("SELECT COUNT(u) FROM User u WHERE u.username = "
				+ ":username", Long.class);
				query2.setParameter("username", username); // Corrected line
				Long count2 = query2.getSingleResult(); // Corrected line

				if (count2 > 0)
				{
				    return "username already found";
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
   
    
    public static int countDigits(String str)
    {
    	 int count = 0;
         for (char c : str.toCharArray())
         {
             if (Character.isDigit(c)) 
             {
                 count++;
             }
         }
         return count;
     }
    
    public List<User> getUsers()
    {
    	TypedQuery<User> query =entityManager.createQuery
    			("SELECT c from User c", User.class);
		List <User> users= query.getResultList();
		
		return users;
    }
    public String updateUserProfile(User updatedUser)
    {
        try {
            TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username",
                User.class);
            query.setParameter("username", updatedUser.getUsername());
            
            List<User> resultList = query.getResultList();
            
            if (resultList.isEmpty())
            {
                return "User not found";
            }
            
            User existingUser = resultList.get(0);

            // Update profile information
            if (updatedUser.getEmail() == null ||
            		updatedUser.getEmail().isEmpty()) 
            {
                return "Email cannot be null or empty";
            }
            existingUser.setEmail(updatedUser.getEmail());
            
            if (updatedUser.getPassword() == null ||
            		updatedUser.getPassword().isEmpty()) {
                return "Password cannot be null or empty";
            }
            if (countDigits(updatedUser.getPassword()) < 8) 
            {
                return "Password must be more than 8 characters";
            }
            existingUser.setPassword(updatedUser.getPassword());
            
            existingUser.setName(updatedUser.getName());
            
            existingUser.setRole(updatedUser.getRole());
            
            entityManager.merge(existingUser);
            return "User profile updated successfully";

        } catch (Exception e)
        {
            // Log the exception or handle it accordingly
            e.printStackTrace(); // This is for demonstration, you may want to log it properly
            return "An error occurred while updating user profile";
        }
    }

    
    public String loginUser(User loggedUser)
    {
        String email = loggedUser.getEmail();
        String username = loggedUser.getUsername();
        String password = loggedUser.getPassword();
        
        try {
            // Query the database to find the user with the given email
            TypedQuery<User> query = 
            		entityManager.createQuery("SELECT u FROM User u "
            				+ "WHERE u.username = :username", User.class);
            query.setParameter("username", username);
            List<User> users = query.getResultList();
            
            if (users.isEmpty()) {
                return "username is wrong";
            }
            
            User user = users.get(0);
            
            // Here, we are checking the password directly from the database, which is not recommended
            if (!user.getPassword().equals(password))
            {
                return "Password is wrong";
            }
            
            return "User logged in successfully";
        	} catch (Exception e) 
        	{
            // Log the exception or handle it accordingly
            e.printStackTrace(); // This is for demonstration, you may want to log it properly
            return "An error occurred while logging in";
        }
    }

}
