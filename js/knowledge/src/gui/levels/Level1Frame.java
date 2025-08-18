package gui.levels;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import entities.RenderObject;
import entities.enemies.SL;
import entities.player.Player;
import entities.player.User;
import gui.GraphicsControl;
import gui.GuiController;

/**
 * <h3>Level1Frame that inherited from abstract {@link gui.levels.LevelFrame} class. </h3>
 * <p>Consist of 4 {@link entities.enemies.SL} </p>
 */
public class Level1Frame extends LevelFrame{
	
	/**
	 * Constructor for Level1Frame that sets up the RenderObjects and GraphicsController.
	 * @param user
	 */
	public Level1Frame(User user) {
		super(user);
		GuiController.addLogLine("Level 1 started");
		levellbl.setText("Level 1");
		
		// Setting up the RenderObjects
		player = new Player(0,380,this.user);
		ArrayList<SL> slList = new ArrayList<SL>();
		slList.add(new SL(300, 40, "sl1.png",slQuestions , slInfos));
		slList.add(new SL(200, 40, "sl2.png",slQuestions , slInfos));
		slList.add(new SL(400, 40, "sl3.png",slQuestions , slInfos));
		slList.add(new SL(500, 40, "sl4.png",slQuestions , slInfos));
		
		// Initializing the GraphicsControl
		GraphicsControl graphicsControl = new GraphicsControl(RenderObject.getRenders(),player);
		graphicsControl.setBounds(0, 0, 700, 500);
		graphicsControl.setParentFrame(this);
		graphicsControl.setPassingScore(50);
		
		graphicsControl.mainLoop();
		contentPane.add(graphicsControl);
	}
	
	/**
	 * Changes frame to Level 2 properly via using inherited graphics method from LevelFrame.
	 * @see gui.levels.LevelFrame#stopAllMoves()
	 * @see gui.levels.LevelFrame#clearRenders()
	 * @see gui.GuiController#changeFrame(javax.swing.JFrame, javax.swing.JFrame)
	 */
	@Override
	public void levelPassed(int score) {
		GuiController.addLogLine("Level 1 passed");
		stopAllMoves();
		JOptionPane.showMessageDialog(null, "Level 1 Succesfully Passed", "Level Passed", JOptionPane.PLAIN_MESSAGE, null);
		clearRenders();
		Level2Frame level2 = new Level2Frame(user);
		level2.setInitScore(score);
		GuiController.changeFrame(this, level2);
	}
}
