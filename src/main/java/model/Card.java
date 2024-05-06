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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardid;

    @Column(nullable = false)
	private String title;
    
    private String description;
    private String comment;
    
 
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="userxcard",
	joinColumns=@JoinColumn(name="cardID"),
	inverseJoinColumns=@JoinColumn(name="userID"))
    private List<User> assignedUsers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name ="listid")
    @JsonBackReference
    private AymanTestModel list;
    
    public Card(int cardid, String title, String description, String comment, List<User> assignedUsers,
			AymanTestModel list) 
    {
		this.cardid = cardid;
		this.title = title;
		this.description = description;
		this.comment = comment;
		this.assignedUsers = assignedUsers;
		this.list = list;
	}
    
    
    public Card( String title) 
    {
		this.title = title;
	}

	public Card() {}
	public int getCardid() {
		return cardid;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getComment() {
		return comment;
	}

	public List<User> getAssignedUsers() {
		return assignedUsers;
	}
	
	public void setAssignedUsers(List<User> assignedUsers) {
		this.assignedUsers = assignedUsers;
	}


	public AymanTestModel getList() {
		return list;
	}

	public void setCardid(int cardid) {
		this.cardid = cardid;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setList(AymanTestModel list) {
		this.list = list;
	}

	 
    
    
    
    
}