package model;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
	private String title;
    
    private String description;
    private String comment;
    
 
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> assignedUsers = new ArrayList<>();
    //ayman
//ayman
 /*  // Additional fields and relationships as needed
    @ManyToOne
    private TaskList taskList....;
*/
    // Getters and setters and constructor 
    
    public Card() {
    }
    public Card (String title) {
    	this.title=title;
    }
    
    public Card(String title, String description, String comment) {
        this.title = title;
        this.description = description;
        this.comment = comment;
    }
    
    public Card(String title, String description) {
        this.title = title;
        this.description = description;
        
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
//	public void addAssignedUser(User user) {
//        this.assignedUsers.add(user);
//        user.getAssignedCards().add(this);
//    }
//
//    public void removeAssignedUser(User user) {
//        this.assignedUsers.remove(user);
//        user.getAssignedCards().remove(this);
//    }

    public List<User> getAssignedUsers() {
        return assignedUsers;
    }
    public void setAssignedUsers(List<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }
    
}