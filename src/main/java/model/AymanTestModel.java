package model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
@Entity

public class AymanTestModel
{
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 @NotNull
	String type;
//	 public User getUsercreate() {
//		return usercreate;
//	}
//	public void setUsercreate(User usercreate) {
//		this.usercreate = usercreate;
//	}
	//User usercreate;
	
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