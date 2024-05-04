package model;
import model.card;

import java.util.ArrayList;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Stateful
@Entity
public class ListModel
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	String type;
	ArrayList <card> cards;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<card> getCards() {
		return cards;
	}
	public void setCards(ArrayList<card> cards) {
		this.cards = cards;
	}
	public ListModel(String type, ArrayList<card> cards) {

		this.type = type;
		this.cards = cards;
	}
	public ListModel() {
	}
	
	

	
	

}
