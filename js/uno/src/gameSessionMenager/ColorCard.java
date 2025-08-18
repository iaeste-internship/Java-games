package gameSessionMenager;

import javax.swing.ImageIcon;

/**
 * ColorCard class that has color which affects is discardibility status.
 * 
 * @author Aykut Bir
 * @since 09/05/2024
 * 
 */
public class ColorCard extends Card{
	
	private String color;
	
	/**
	 * Constructor 
	 * 
	 * Sets the name field as: Color + Value
	 * 
	 * @param value :String, numeric or special value it holds
	 * @param color :String
	 * @param icon :ImageIcon
	 * @param point :int, Varies depending on value
	 */
	public ColorCard (String value, String color, ImageIcon icon, int point) {
		
		super(value, icon);
		this.point = point;
		this.name = color + value;
		this.color = color;
	}

	/**
	 * Checks cards if card is discardable onto parameter card, following UNO rules.
	 * Returns true is card is discardable onto parameter card.
	 */
	@Override
	public boolean isDiscardable(Card card) {
		boolean isDiscardable = false;
		if (card.getClass().getSimpleName().equals("WildCard")){
			if (!(((WildCard) card).getSelectedColor() == null)) {
				isDiscardable = (((WildCard) card).getSelectedColor()).equals(this.getColor()) ? true : false;
			} else {
				isDiscardable = true;
			}
		} else {
			if (((ColorCard) card).getColor().equals(this.getColor())) {
				isDiscardable = true;
			} else {
				isDiscardable = ((ColorCard) card).getValue().equals(this.getValue()) ? true : false;
			}
		}
		
		return isDiscardable;
	}
	
	/**
	 * 
	 * @return color :String, getter for color field
	 */
	public String getColor() {
		return color;
	}

}
