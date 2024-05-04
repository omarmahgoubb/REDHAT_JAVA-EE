package model;
import javax.ejb.Stateful;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.ArrayList;

@Stateful
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int Board_id;
	String boardname;
	ArrayList <cardlist> cardlist;
	public Board( String boardname) {

		this.boardname = boardname;

		this.cardlist = cardlist;
	}
	
	public ArrayList<cardlist> getCardlist() {
		return cardlist;
	}
	public void setCardlist(ArrayList<cardlist> cardlist) {
		this.cardlist = cardlist;
	}
	public Board() {
		
	}
	public int getBoard_id() {
		return Board_id;
	}
	public void setBoard_id(int Board_id) {
		this.Board_id = Board_id;
	}
	public String getboardname() {
		return boardname;
	}
	public void setboardname(String boardname) {
		this.boardname = boardname;
	}
	
	
}