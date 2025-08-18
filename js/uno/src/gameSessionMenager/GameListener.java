package gameSessionMenager;

import mainMenuMenager.Listens;

/**
 * Set of listen methods that will be initialized by GameScreen object to 
 * update game state and GUI components.
 * 
 */
public interface GameListener extends Listens{

	void listenDrawDeck();
	
	void listenDiscardPile(Card lastCard);
	
	void listenDirection();
	
	void listenPlayerMove(Card card);
	
	void listenPointOut();
	
	void listenCardCounts(Player player);
	
	public void listenColorSelection(String selection);
	
	public void listenGameEnded(boolean playerWon, String botName);
}
