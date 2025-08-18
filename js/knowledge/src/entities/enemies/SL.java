package entities.enemies;

import java.util.ArrayList;

/**
 * <h3>Section Leader class that extends Enemy</h3>
 * <p>Moves slowly. High chance of shooting infos. Low chance of shooting questions.</p>
 * <p>Has a slow ShotBox velocity.</p>
 */
public class SL extends Enemy{
	/**
	 * The constructor for SL.
	 * @param X starting X
	 * @param Y starting Y
	 * @param p image name in *.png format
	 * @param questions ArrayList of questions
	 * @param infos	ArrayList of informations
	 */
	public SL(int X, int Y, String p, ArrayList<String> questions, ArrayList<String> infos) {
		super(X, Y, p,questions,infos);
		// Initialize rates and velocities.
		questionRate = 0.03;
		infoRate = 0.05;
		boxVelocity = 5;
		velocityX = 3;
		moveXRandom(); // start random movement
	}
	
}
