package entities.enemies;

import java.util.ArrayList;

/**
 * <h3>Teaching Assistant class that extends Enemy</h3>
 * <p>Moves in moderate speed. Moderate chance of shooting infos. Moderate chance of shooting questions.</p>
 * <p>Has a moderate ShotBox velocity.</p>
 * <p>Has a composition relationship with {@link entities.enemies.PlayerFocuser} in order to focus to player in random times.</p>
 */
public class TA extends Enemy{
	private PlayerFocuser focuser;
	
	/**
	 * The constructor for TA.
	 * @param X starting X
	 * @param Y starting Y
	 * @param p image name in *.png format
	 * @param questions ArrayList of questions
	 * @param infos	ArrayList of informations
	 */
	public TA(int X, int Y, String p, ArrayList<String> questions, ArrayList<String> infos) {
		super(X, Y, p, questions, infos);
		// Initialize rates and velocities.
		questionRate = 0.1;
		infoRate = 0.05;
		boxVelocity = 8;
		velocityX = 6;
		focuser = new PlayerFocuser(this); // Modifies the random movement.
		focuser.setFocusRate(0.1);
		focuser.moveXRandomWithFocus(); // Starts the modified random movement.
	}
	
	public PlayerFocuser getFocuser() {
		return focuser;
	}
}
