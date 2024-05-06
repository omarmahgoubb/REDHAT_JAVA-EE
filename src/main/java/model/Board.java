package model;
import javax.ejb.Stateful;
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

  
    @ManyToMany()
    @JoinTable(name="userxboard",
	joinColumns=@JoinColumn(name="boardid"),
	inverseJoinColumns=@JoinColumn(name="userid"))
    private List<User> boardUsers = new ArrayList<>();

    
   @OneToMany(mappedBy="board")
    private List<AymanTestModel> cardlist; 
    
    
	public List<User> getboardYsers() {
		return boardUsers;
	}
	public void setboardUsers(List<User> collaborators) {
		this.boardUsers = collaborators;
	}
	
	public Board( String boardname) {

		this.boardname = boardname;
	}
	public Board() {
		
	}
	public int getBoard_id() {
		return boardid;
	}
	public void setBoard_id(int Board_id) {
		this.boardid = Board_id;
	}
	public String getboardname() {
		return boardname;
	}
	public void setboardname(String boardname) {
		this.boardname = boardname;
	}
	
	
}