package model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
@Entity

public class AymanTestModel
{
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int listid;
	 @NotNull
	String type;
	 
	 
	 @ManyToOne
	 @JoinColumn(name= "boardid")
	 Board board;
	 
	 
	 @OneToMany(mappedBy="list")
	 ArrayList <Card> cards;


	public int getListid() {
		return listid;
	}


	public String getType() {
		return type;
	}


	public Board getBoard() {
		return board;
	}


	public ArrayList<Card> getCards() {
		return cards;
	}


	public void setListid(int listid) {
		this.listid = listid;
	}


	public void setType(String type) {
		this.type = type;
	}


	public void setBoard(Board board) {
		this.board = board;
	}


	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}


	public AymanTestModel(int listid, @NotNull String type, Board board, ArrayList<Card> cards) {
		this.listid = listid;
		this.type = type;
		this.board = board;
		this.cards = cards;
	}


	public AymanTestModel() {
	}
	
	
	
	

}