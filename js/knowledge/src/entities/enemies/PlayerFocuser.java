package entities.enemies;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

import javax.swing.Timer;

import entities.player.Player;
import entities.shotboxes.QuestionBox;

/**
 * <h3>PlayerFcouser class that takes an Enemy object and modifies random movement</h3>
 * <p>Randomly focuses given enemy to the static player object by creating a new random movement method for given enemy. </p>
 * <p>Details of focusing process is in the {@link entities.enemies.PlayerFocuser#timer} </p>
 * @see entities.enemies.Enemy
 */
public class PlayerFocuser {
	private Enemy enemy;
	private static Player focusPlayer;
	private SecureRandom rand = new SecureRandom();
	private double focusRate;
	/**
	 * <p>In every 1200 ms randomly focuses player via {@link entities.enemies.PlayerFocuser#focusOnPlayer} timer with the chance of (%focusRate) </p>
	 * <p>In the end of focusing process Enemy shoots a {@link entities.shotboxes.QuestionBox} with higher velocity.</p>
	 */
	private Timer timer = new Timer(1200, new ActionListener() {
			
		@Override
		public void actionPerformed(ActionEvent e) {
			if(focusOnPlayer.isRunning()) {
				enemy.setVelocityX(enemy.getVelocityX()/(3/2));
				focusOnPlayer.stop();
				QuestionBox box = new QuestionBox(enemy.getX(), enemy.getY(), "question.png",enemy.questions.get(rand.nextInt(enemy.questions.size())),enemy);
				box.setVelocityY(enemy.boxVelocity+5);
				box.moveY(5000);
			}
			if(rand.nextDouble() < focusRate) {
				enemy.setVelocityX(enemy.getVelocityX()*(3/2));
				focusOnPlayer.start();
				enemy.stopMoveXRandom();
			}
			else if(!enemy.isMoveXActive()) {
				enemy.moveXRandom();
			}
		}
	});
	/**
	 * While active this timer changes given enemy's sign of the velocity according to static Player reference and moves with that changed velocity.
	 */
	private Timer focusOnPlayer = new Timer(50, new ActionListener() {
			
		@Override
		public void actionPerformed(ActionEvent e) {
			if(Math.abs(enemy.getX()-focusPlayer.getX()) < 20) {
				return;
			}
			if(enemy.getX() < focusPlayer.getX()) {
				enemy.setVelocityX(Math.abs(enemy.getVelocityX()));
			}
			else{
				enemy.setVelocityX(-1*Math.abs(enemy.getVelocityX()));
			}
			enemy.setX(enemy.getX()+enemy.getVelocityX());
		}
	});
	
	/**
	 * Constructor for PlayerFocuser
	 * @param enemy Enemy object that will gain focus property. 
	 */
	public PlayerFocuser(Enemy enemy) {
		this.enemy = enemy;
	}
	
	/**
	 * Starts modified random movement.
	 */
	public void moveXRandomWithFocus() {
		enemy.moveXRandom();
		timer.start();
	}
	
	/**
	 * Stops modified random movement.
	 */
	public void stopMoveXRandoWithFocus() {
		enemy.stopMoveXRandom();
		timer.stop();
	}
	
	public static void setFocusPlayer(Player focusPlayer) {
		PlayerFocuser.focusPlayer = focusPlayer;
	}
	
	public void setFocusRate(double focusRate) {
		this.focusRate = focusRate;
	}
}
