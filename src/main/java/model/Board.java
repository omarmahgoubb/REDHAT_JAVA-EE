package model;
import javax.ejb.Stateful;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import java.util.List;
import java.util.ArrayList;

@Stateful
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String boardname;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;	
    
    public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public List<User> getCollaborators() {
		return collaborators;
	}
	public void setCollaborators(List<User> collaborators) {
		this.collaborators = collaborators;
	}
	@ManyToMany(mappedBy = "collaboratorBoards")
    private List<User> collaborators = new ArrayList<>();
	public Board( String boardname) {

		this.boardname = boardname;
	}
	public Board() {
		
	}
	public int getBoard_id() {
		return id;
	}
	public void setBoard_id(int Board_id) {
		this.id = Board_id;
	}
	public String getboardname() {
		return boardname;
	}
	public void setboardname(String boardname) {
		this.boardname = boardname;
	}
	
	
}