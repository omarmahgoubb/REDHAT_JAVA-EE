package model;
import javax.ejb.Stateful;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.List;
import java.util.ArrayList;

@Stateful
@Entity
public class Board 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardid;

    private String boardname;

  
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="userxboard",
	joinColumns=@JoinColumn(name="boardid"),
	inverseJoinColumns=@JoinColumn(name="userid"))
    private List<User> boardUsers = new ArrayList<>();

    
   @OneToMany(mappedBy="board",fetch = FetchType.EAGER , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AymanTestModel> cardlist;


public int getBoardid() {
	return boardid;
}


public String getBoardname() {
	return boardname;
}


public List<User> getBoardUsers() {
	return boardUsers;
}


public List<AymanTestModel> getCardlist() {
	return cardlist;
}


public void setBoardid(int boardid) {
	this.boardid = boardid;
}


public void setBoardname(String boardname) {
	this.boardname = boardname;
}


public void setBoardUsers(List<User> boardUsers) {
	this.boardUsers = boardUsers;
}


public void setCardlist(List<AymanTestModel> cardlist) {
	this.cardlist = cardlist;
}


public Board(int boardid, String boardname, List<User> boardUsers, List<AymanTestModel> cardlist) {
	this.boardid = boardid;
	this.boardname = boardname;
	this.boardUsers = boardUsers;
	this.cardlist = cardlist;
}


public Board() {
} 
    
    
	
	
	
	
}