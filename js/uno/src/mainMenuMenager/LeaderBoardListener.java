package mainMenuMenager;

/**
 * Interface to listen leaderboard button actions.
 * @see LeaderBoardUserPanel :to see concrete implementation
 * 
 * @author Aykut Bir
 * @since 04/05/2024
 * 
 */
public interface LeaderBoardListener extends Listens{
	
	public void listenLeaderboard(String userName);
}
