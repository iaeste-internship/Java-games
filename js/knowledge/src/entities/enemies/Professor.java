package entities.enemies;

import java.util.ArrayList;

/**
 * <h3>Professor class that extends Enemy</h3>
 * <p>Moves in high speed. Low chance of shooting infos. High chance of shooting questions.</p>
 * <p>Has a high ShotBox velocity.</p>
 * <p>Has a composition relationship with {@link entities.enemies.PlayerFocuser} in order to focus to player in random times.</p>
 */
public class Professor extends Enemy{
	private PlayerFocuser focuser;
	
	/**
	 * The constructor for Professor.
	 * @param X starting X
	 * @param Y starting Y
	 * @param p image name in *.png format
	 * @param questions ArrayList of questions
	 * @param infos	ArrayList of informations
	 */
	public Professor(int X, int Y, String p, ArrayList<String> questions, ArrayList<String> infos) {
		super(X, Y, p, questions, infos);
		// Initialize rates and velocities.
		questionRate = 0.15;
		infoRate = 0.03;
		boxVelocity = 10;
		velocityX = 8;
		focuser = new PlayerFocuser(this);  // Modifies the random movement.
		focuser.setFocusRate(0.15);
		focuser.moveXRandomWithFocus(); // Starts the modified random movement.
	}
	
	public PlayerFocuser getFocuser() {
		return focuser;
	}
}
