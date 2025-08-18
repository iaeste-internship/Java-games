package mainMenuMenager;

/**
 * @see LeaderBoardInitializer 
 * 
 * Class that utilized by LeaderBoardInitializer to compare leaderboard entries by their points.
 * 
 * @author Aykut Bir
 * @since 04/05/2024
 * 
 */
public class LeaderBoardPlayer implements Comparable<Object>{
	
	private String name;
	private int points;
	
	/**
	 * Constructor.
	 * Each instances of this class holds player name and total points.
	 * 
	 * @param name :Name of player
	 * @param points :Total points of player
	 */
	public LeaderBoardPlayer(String name, int points) {
		this.name = name;
		this.points = points;
	}
	
	/**
	 * @Override :Overrides compareTo of Comparable<> interface
	 * 
	 * Orders players by descending order. This ordering is preserved by Map object created by 
	 * LeaderBoardInitializer class.
	 * 
	 */
	
	@Override
	public int compareTo(Object o) {
		if (this.points < ((LeaderBoardPlayer) o).getPoints()) {
			return 1;
		} else if (this.points > ((LeaderBoardPlayer) o).getPoints()) {
			return -1;
		} else {
			return 0;
		}
	}
	
	/**
	 * Getter of playername.
	 * 
	 * @return playername :String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter of total points
	 * 
	 * @return points :int
	 */
	public int getPoints() {
		return points;
	}

}
