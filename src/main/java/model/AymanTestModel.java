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
	
	
	
	
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public ArrayList<Card> getCards() 
	{
		return cards;
	}
	public void setCards(ArrayList<Card> cards)
	{
		this.cards = cards;
	}
	public AymanTestModel(String type, ArrayList<Card> cards) 
	{
		//this.usercreate=user;
		this.type = type;
		this.cards = cards;
	}
	public AymanTestModel() 
	{}
}