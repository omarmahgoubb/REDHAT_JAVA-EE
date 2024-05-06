package model;

import javax.ejb.Stateful;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;

    @NotNull
    private String email;
    private String username;
    @NotNull
    private String password;
    private String name;
    private String role;

    
   @ManyToMany(mappedBy="boardUsers",fetch = FetchType.EAGER)
   @JsonIgnore
    private List<Board> Boards = new ArrayList<>();
    
   @ManyToMany( mappedBy = "assignedUsers",fetch = FetchType.EAGER)
   @JsonIgnore
   private List<Card> assignedCards = new ArrayList<>();

public int getUserid() {
	return userid;
}

public String getEmail() {
	return email;
}

public String getUsername() {
	return username;
}

public String getPassword() {
	return password;
}

public String getName() {
	return name;
}

public String getRole() {
	return role;
}

public List<Board> getBoards() {
	return Boards;
}

public List<Card> getAssignedCards() {
	return assignedCards;
}

public void setUserid(int userid) {
	this.userid = userid;
}

public void setEmail(String email) {
	this.email = email;
}

public void setUsername(String username) {
	this.username = username;
}

public void setPassword(String password) {
	this.password = password;
}

public void setName(String name) {
	this.name = name;
}

public void setRole(String role) {
	this.role = role;
}

public void setBoards(List<Board> boards) {
	Boards = boards;
}

public void setAssignedCards(List<Card> assignedCards) {
	this.assignedCards = assignedCards;
}

public User(int userid,String email, String username,
		String password, String name, String role,List<Board> boards, List<Card> assignedCards) {
	this.userid = userid;
	this.email = email;
	this.username = username;
	this.password = password;
	this.name = name;
	this.role = role;
	Boards = boards;
	this.assignedCards = assignedCards;
}

public User() {}
   

public User(int userid,String email, String username,
		String password, String name, String role)
{
	this.userid = userid;
	this.email = email;
	this.username = username;
	this.password = password;
	this.name = name;
	this.role = role;
}
   
   
   
   
}