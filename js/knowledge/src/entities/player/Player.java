package entities.player;

import java.awt.Rectangle;

import entities.Collider;
import entities.RenderObject;

/**
 * <h3>This is a class for rendering the Player object</h3>
 * <p>Extends RenderObject for rendering and implements Collider for collision purposes.</p>
 * <p>Has a User variable for user that logged in for game. </p>
 * <p>Has dynamic health and score information. </p>
 * <p>Has Rectangle object for collision detection. </p>
 */
public class Player extends RenderObject implements Collider{
	private User user;
	private Rectangle collider;
	private int health = 100;
	private int score = 0;

	/**
	 * Constructor for Player Object
	 * @param X starting X
	 * @param Y starting Y
	 * @param user Logged in User
	 */
	public Player(int X, int Y, User user) {
		super(X,Y,user.getImgName());
		this.user = user;
		collider = new Rectangle(X,Y,60,80);
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * Sets the X coordinate of player and its collider with boundries as defined in {@link entities.RenderObject}
	 */
	@Override
	public void setX(int x) {
		if(x >= 0 && x <= XBOUND) {
			X = x;
			collider.x = x;
		}
	}
	
	/**
	 * Sets the Y coordinate of player and its collider with boundries as defined in {@link entities.RenderObject}
	 */
	@Override
	public void setY(int y) {
		if(y >= 0 && y <= YBOUND) {
			Y = y;
			collider.y = y;
		}
	}
	
	@Override
	public Rectangle getCollider() {
		return collider;
	}
	
	/**
	 * Necessary for implementing Collider.
	 */
	@Override public void invokeCollision() {}
	
}
