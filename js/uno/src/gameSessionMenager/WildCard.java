package gameSessionMenager;

import javax.swing.ImageIcon;

/**
 * WildCard class that has special set of effects.
 * 
 * @author Aykut Bir
 * @since 09/05/2024
 * 
 */

public class WildCard extends Card{
	
	String selectedColor = null;
	
	/**
	 * Constructor.
	 * WildCards have fixed 50 points
	 * 
	 * @param value : String
	 * @param icon :ImageIcon
	 */
	public WildCard (String value, ImageIcon icon) {
		super(value, icon);
		this.name = value;
		this.point = 50;
	}

	/**
	 * Wildcards are always discardable
	 */
	@Override
	public boolean isDiscardable(Card card) {
		return true;
		
	}

	/**
	 * Wildcards have selectedColor field that can be changed throughout the game session.
	 */
	public String getSelectedColor() {
		return selectedColor;
	}

	/**
	 * Wildcards have selectedColor field that can be changed throughout the game session.
	 */
	public void setSelectedColor(String selectedColor) {
		this.selectedColor = selectedColor;
	}
}
