package entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 * <h3>Abstract RenderObject class for objects to be rendered easily </h3>
 * <p>Consist of moving methods for both axis (X and Y).</p>
 * <p>Has changeable velocity for both axis (X and Y). </p>
 * <p>Has draw method for drawing image to the frame. </p>
 * <p>Has static ArrayList of RenderObjects that consist of all RenderObjects for iterations. (e.g {@link gui.GraphicsControl#paintComponent(Graphics)}) </p>
 * @see entities.RenderObject#moveX(int)
 * @see entities.RenderObject#moveY(int)
 * @see entities.RenderObject#draw(Graphics)
 * @see entities.RenderObject#renders
 */
public abstract class RenderObject {
	protected final int XBOUND = 645;
	protected final int YBOUND = 500;
	private Image img;
	protected int X;
	protected int Y;
	private Timer moveXTimer;
	private Timer moveYTimer;
	private int moveCountX = 0;
	private int moveCountY = 0;
	protected int velocityX = 0;
	protected int velocityY = 0;
	protected static ArrayList<RenderObject> renders = new ArrayList<RenderObject>();
	
	/**
	 * Constructor for RenderObject
	 * @param X starting X coordinate
	 * @param Y starting Y coordinate
	 * @param p image name in *.jpg format
	 */
	public RenderObject(int X, int Y, String p) {
		this.X = X;
		this.Y = Y;
		img = new ImageIcon("res/img/"+p).getImage();
		renders.add(this);
	}
	
	/**
	 * Draw method for panel. Draws the RenderObject image to the X, Y coordinates.
	 * @param g
	 */
	public void draw(Graphics g) {
		g.drawImage(img, X, Y, null);
	}
	
	/**
	 * Moves the RenderObject smoothly in the X-axis with the given time parameter according to velocityX variable.
	 * @param time
	 */
	public void moveX(int time) {
		moveXTimer = new Timer(50,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveCountX += 50;
				setX(getX()+velocityX);
				if(moveCountX == time) {
					moveCountX = 0;
					moveXTimer.stop();
				}
			}
		});
		moveXTimer.start();
	}
	
	/**
	 * Moves the RenderObject smoothly in the Y-axis with the given time parameter according to velocityY variable.
	 * @param time
	 */
	public void moveY(int time) {
		moveYTimer = new Timer(50,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveCountY += 50;
				setY(getY()+velocityY);
				if(moveCountY == time) {
					moveCountY = 0;
					moveYTimer.stop();
				}
			}
		});
		moveYTimer.start();
	}
	
	public static ArrayList<RenderObject> getRenders(){
		return renders;
	}
	
	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}
	
	public void setX(int x) {
		if(x >= 0 && x <= XBOUND) {
			X = x;
		}
	}
	
	public void setY(int y) {
		if(y >= 0 && y <= YBOUND) {
			Y = y;
		}
	}
	
	public void SetImg(Image img) {
		this.img = img;
	}
	
	public int getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}
	
	public int getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}
}
