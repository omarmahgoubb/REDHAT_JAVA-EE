package model;

import java.util.ArrayList;

public class Listt
{

	String type;
	
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
	public Listt(String type, ArrayList<Card> cards) 
	{
		this.type = type;
		this.cards = cards;
	}
	public Listt() 
	{}
}
