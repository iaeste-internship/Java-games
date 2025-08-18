package gui.levels;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.RenderObject;
import entities.enemies.Enemy;
import entities.enemies.PlayerFocuser;
import entities.enemies.SL;
import entities.enemies.TA;
import entities.player.Player;
import entities.player.User;
import gui.GraphicsControl;
import gui.GuiController;

/**
 * <h3>Level2Frame that inherited from abstract {@link gui.levels.LevelFrame} class. </h3>
 * <p>Consist of 4 {@link entities.enemies.SL} and 2 {@link entities.enemies.TA} </p>
 */
public class Level2Frame extends LevelFrame {
	
	/**
	 * Constructor for Level1Frame that sets up the RenderObjects and GraphicsController.
	 * @param user
	 */
	public Level2Frame(User user) {
		super(user);
		GuiController.addLogLine("Level 2 started");
		levellbl.setText("Level 2");
		
		// Setting up the RenderObjects
		player = new Player(0,380,this.user);
		PlayerFocuser.setFocusPlayer(player);
		ArrayList<SL> slList = new ArrayList<SL>();
		slList.add(new SL(300, 40, "sl5.png", slQuestions, slInfos));
		slList.add(new SL(200, 40, "sl6.png", slQuestions, slInfos));
		slList.add(new SL(100, 40, "sl7.png", slQuestions, slInfos));
		slList.add(new SL(400, 40, "sl8.png", slQuestions, slInfos));
		
		ArrayList<TA> taList = new ArrayList<TA>();
		taList.add(new TA(400, 40, "ta1.png", taQuestions, taInfos));
		taList.add(new TA(500, 40, "ta2.png", taQuestions, taInfos));
		
		
		// Initializing the GraphicsControl
		GraphicsControl graphicsControl = new GraphicsControl(RenderObject.getRenders(),player);
		graphicsControl.setBounds(0, 0, 700, 500);
		graphicsControl.setParentFrame(this);
		graphicsControl.setPassingScore(100);
		
		graphicsControl.mainLoop();
		contentPane.add(graphicsControl);
	}

	/**
	 * Changes frame to Level 3 properly via using inherited graphics method from LevelFrame.
	 * @see gui.levels.LevelFrame#stopAllMoves()
	 * @see gui.levels.LevelFrame#clearRenders()
	 * @see gui.GuiController#changeFrame(javax.swing.JFrame, javax.swing.JFrame)
	 */
	@Override
	public void levelPassed(int score) {
		GuiController.addLogLine("Level 2 passed");
		stopAllMoves();
		JOptionPane.showMessageDialog(null, "Level 2 Succesfully Passed", "Level Passed", JOptionPane.PLAIN_MESSAGE, null);
		clearRenders();
		Level3Frame level3 = new Level3Frame(user);
		level3.setInitScore(score);
		GuiController.changeFrame(this, level3);
	}
	
}
