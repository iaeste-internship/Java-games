package gameSessionMenager;

/**
 * Interface that follows:
 * Every cards are discardable. 
 * 
 * @author Aykut Bir
 * @since 09/05/2024
 * 
 */

public interface Discardable {

	public abstract boolean isDiscardable(Card card);
	
}
