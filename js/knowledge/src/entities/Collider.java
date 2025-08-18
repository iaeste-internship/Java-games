package entities;

import java.awt.Rectangle;

/**
 * Interface for implementing Collider Box for RenderObject.
 */
public interface Collider {
	public void setY(int y);
	public void setX(int x);
	public Rectangle getCollider();
	/**
	 * <p>Call when some collision invoked from some other Collider interface.</p>
	 * <p>Controlled by main loop</p>
	 * @see gui.GraphicsControl
	 */
	public void invokeCollision();
}
