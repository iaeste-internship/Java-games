package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

import entities.Collider;
import entities.RenderObject;
import entities.player.Player;
import entities.shotboxes.QuestionBox;
import entities.shotboxes.ShotBox;
import gui.levels.LevelFrame;

/**
 * <h3>This is the panel for RenderObjects </h3>
 * <p>Consist of one main game loop. Check detailed explanation from {@link gui.GraphicsControl#loop} </p>
 * <p>Implements KeyListener for player movement </p>
 */
public class GraphicsControl extends JPanel implements KeyListener{
	private ArrayList<RenderObject> renders;
	private Player player;
    private final int STEP = 10;
    private static JLabel healthlb,scorelbl;
    private LevelFrame parentFrame;
    private int passingScore;
    /**
	 * <h3>Main loop for game</h3>
	 * <p>repaints the frame.</p>
	 * <p>Checks and invokes collision via {@link entities.Collider#invokeCollision()} </p>
	 * <p>Updates the frame label components (Score, Health).</p> 
	 * <p>Checks player score and health situation.</p>
	 */
    private Timer loop = new Timer(1, new ActionListener() {
    	@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
			ArrayList<RenderObject> col = new ArrayList<RenderObject>();
			for(RenderObject r: renders) {
				if(r instanceof Collider && !(r instanceof Player)) {
					if(player.getCollider().intersects(((Collider) r).getCollider())) {
						col.add(r);
						((Collider)r).invokeCollision();
					}
				}
			}
			healthlb.setText("Health: "+Math.max(player.getHealth(),0));
			scorelbl.setText("Score: "+player.getScore());
			renders.removeAll(col);
			if(player.getScore() >= passingScore) {
				parentFrame.levelPassed(player.getScore());
				stopMainLoop();
			} else if(player.getHealth() <= 0) {
				parentFrame.gameOver();
				stopMainLoop();
			}
			
		}
	});
    
    /**
     * <p>Constructor for GraphicsControl</p>
     * @param render ArrayList of RenderObjects for the frame
     * @param player Player Object of the frame
     */
	public GraphicsControl(ArrayList<RenderObject> render, Player player) {
		this.renders = render;
		this.player = player;
		ShotBox.setPlayer(player);
		setFocusable(true);
		addKeyListener(this);
	}
	
	public void setPassingScore(int passingScore) {
		this.passingScore = passingScore;
	}
	
	public void setParentFrame(LevelFrame parentFrame) {
		this.parentFrame = parentFrame;
	}

	public static void setScorelbl(JLabel scorelbl) {
		GraphicsControl.scorelbl = scorelbl;
	}
	
	public static void setHealthlb(JLabel healthlb) {
		GraphicsControl.healthlb = healthlb;
	}

	/**
	 * <p>Draws background and each RenderObject via iterate through {@link entities.RenderObject#getRenders()} </p>
	 */
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("res/img/bg.png").getImage(), 0, 0, null);
        for(RenderObject r: renders) {
        	r.draw(g);
        }
	}
	
	/**
	 * Start the main loop. Checks loop object ActionLisener for details.
	 */
	public void mainLoop() {
		loop.start();
	}
	
	/**
	 * Stops the main loop timer.
	 */
	public void stopMainLoop() {
		loop.stop();
	}
	
	/**
	 * Control player movements.
	 */
	@Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
        	player.setX(player.getX()-STEP);
        } else if (key == KeyEvent.VK_RIGHT) {
            player.setX(player.getX()+STEP);
        }
        repaint();
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
    
}
