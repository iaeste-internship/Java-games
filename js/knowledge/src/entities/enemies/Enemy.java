package entities.enemies;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.util.ArrayList;
import javax.swing.Timer;
import entities.RenderObject;
import entities.shotboxes.InfoBox;
import entities.shotboxes.QuestionBox;

/**
 * <h3>Abstract Enemy class for general Enemy properties that extends {@link entities.RenderObject} </h3>
 * <p>Has question and info ArrayList. </p>
 * <p>Implements general X axis random move method. </p>
 */
public abstract class Enemy extends RenderObject{
	protected ArrayList<String> questions;
	protected ArrayList<String> infos;
	protected double infoRate,questionRate;
	protected int boxVelocity;
	private SecureRandom rand = new SecureRandom();

	/**
	 * <p>In every 600 ms:</p>
	 * <p>Change the direction of movement (%50) </p>
	 * <p>Shoot a {@link entities.shotboxes.QuestionBox} (%questionRate) </p>
	 * <p>Shoot a {@link entities.shotboxes.InfoBox} (%infoRate) </p>
	 * <p>Moves for 600 ms with given velocity. </p>
	 * @see entities.RenderObject#moveX(int)
	 */
	private Timer moveXRandomTimer = new Timer(600, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(rand.nextBoolean()) {
				velocityX *= -1;
			}
			if(rand.nextDouble() <= infoRate) {
				InfoBox box = new InfoBox(X, Y, "info.png", infos.get(rand.nextInt(infos.size())),Enemy.this);
				box.setVelocityY(boxVelocity);
				box.moveY(5000);
			}
			if(rand.nextDouble() <= questionRate) {
				QuestionBox box = new QuestionBox(X, Y, "question.png",questions.get(rand.nextInt(questions.size())),Enemy.this);
				box.setVelocityY(boxVelocity);
				box.moveY(5000);
			}
			moveX(600);
		}
	});
	
	/**
	 * Constructor for Enemy
	 * @param X starting X
	 * @param Y starting Y
	 * @param p image name in *.jpg format
	 * @param questions ArrayList of questions
	 * @param infos ArrayList of informations.
	 */
	public Enemy(int X, int Y, String p, ArrayList<String> questions, ArrayList<String> infos) {
		super(X, Y, p);
		this.questions = questions;
		this.infos = infos;
	}
	
	/**
	 * Moves the Enemy Object randomly with the move function in the RenderObject
	 */
	public void moveXRandom() {
		moveXRandomTimer.start();
	}
	
	/**
	 * Stops the random movement.
	 */
	public void stopMoveXRandom() {
		moveXRandomTimer.stop();
	}
	
	/**
	 * Checks if the Enemy obect is moving randomly.
	 * @return True if moveXRandomTimer is active
	 */
	public boolean isMoveXActive() {
		return moveXRandomTimer.isRunning();
	}
}
