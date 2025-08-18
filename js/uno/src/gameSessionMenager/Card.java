package gameSessionMenager;

import javax.swing.ImageIcon;

/**
 * Objects that is constantly drawn or discarded throughout the uno game. 
 * 
 * @author Aykut Bir
 * @since 09/05/2024
 * 
 */

public abstract class Card implements Discardable{
	
	protected String name;
	protected String value; 
	protected ImageIcon icon;
	protected int point;
	
	/**
	 * Constructor
	 * 
	 * @param value :String, can be numeric or special value depending on the card.
	 * @param icon :ImageIcon photo that will be displayed by GameScreen
	 * 
	 */
	public Card(String value, ImageIcon icon) {
		
		this.value = value;
		this.icon = icon;
		
	}
	
	public abstract boolean isDiscardable(Card card);
	
	
	// Getters for corresponding fields utilized by various methods.

	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
	}
	
	public ImageIcon getIcon() {
		return icon;
	}
	
	public int getPoint() {
		return point;
	}
	
	
}
