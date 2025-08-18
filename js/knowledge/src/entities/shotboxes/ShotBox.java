package entities.shotboxes;

import java.awt.Rectangle;

import entities.Collider;
import entities.RenderObject;
import entities.enemies.Enemy;
import entities.player.Player;

/**
 * <h3>Abstract class for ShotBox objects (e.g. Question and Info boxes) </h3>
 * <p>Extends {@link entities.RenderObject} for rendering and implements {@link entities.Collider} for collision detection purposes. </p>
 * <p>Has {@link java.awt.Rectangle} instance for collision checking.</p>
 * <p>Has a reference to its shooter {@link entities.enemies.Enemy} </p>
 */
public abstract class ShotBox extends RenderObject implements Collider{
	private Rectangle collider;
	protected static Player player;
	protected Enemy shooter;
	
	/**
	 * Constructor for ShotBox object
	 * @param X starting X
	 * @param Y starting Y
	 * @param p image name in *.png format
	 * @param shooter reference for Enemy object
	 */
	public ShotBox(int X, int Y, String p,Enemy shooter) {
		super(X, Y, p);
		collider = new Rectangle(X,Y,40,40);
		this.shooter = shooter;
	}
	
	public static void setPlayer(Player player) {
		ShotBox.player = player;
	}
	
	@Override
	public void setY(int y) {
		if(Y > YBOUND) {
			renders.remove(this);
			return;
		}
		Y = y;
		collider.y = y;
	}
	
	@Override
	public void setX(int x) {
		if(x > XBOUND) {
			renders.remove(this);
			return;
		}
		X = x;
		collider.x = x;
	}
	
	@Override
	public Rectangle getCollider() {
		return collider;
	}
}
