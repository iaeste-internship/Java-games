package gui.levels;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import entities.RenderObject;
import entities.enemies.PlayerFocuser;
import entities.enemies.Professor;
import entities.enemies.TA;
import entities.player.Player;
import entities.player.User;
import gui.GraphicsControl;
import gui.GuiController;
import gui.LoginFrame;

/**
 * <h3>Level3Frame that inherited from abstract {@link gui.levels.LevelFrame} class. </h3>
 * <p>Consist of 3 {@link entities.enemies.TA} and 2 {@link entities.enemies.Professor} </p>
 */
public class Level3Frame extends LevelFrame{

	/**
	 * Constructor for Level3Frame that sets up the RenderObjects and GraphicsController.
	 * @param user
	 */
	public Level3Frame(User user) {
		super(user);
		GuiController.addLogLine("Level 3 started");
		levellbl.setText("Level 3");
		
		// Setting up the RenderObjects
		player = new Player(0,380,this.user);
		PlayerFocuser.setFocusPlayer(player);
		
		ArrayList<TA> taList = new ArrayList<TA>();
		taList.add(new TA(400, 40, "ta3.png", taQuestions, taInfos));
		taList.add(new TA(500, 40, "ta4.png", taQuestions, taInfos));
		taList.add(new TA(300, 40, "ta5.png", taQuestions, taInfos));
		
		ArrayList<Professor> profList = new ArrayList<Professor>();
		profList.add(new Professor(400, 40, "prof1.png", profQuestions, profInfos));
		profList.add(new Professor(300, 40, "prof2.png", profQuestions, profInfos));
		
		// Initializing the GraphicsControl
		GraphicsControl graphicsControl = new GraphicsControl(RenderObject.getRenders(),player);
		graphicsControl.setBounds(0, 0, 700, 500);
		graphicsControl.setParentFrame(this);
		graphicsControl.setPassingScore(150);
		
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
		GuiController.addLogLine("All levels passed with the score: "+score);
		stopAllMoves();
		JOptionPane.showMessageDialog(null, "Level 3 Succesfully Passed", "Level Passed", JOptionPane.PLAIN_MESSAGE, null);
		clearRenders();
		user.addScore(score);
		GuiController.updateUsers();
		JOptionPane.showMessageDialog(null, "You Completed All Levels. Returning to Login Page", "Victory", JOptionPane.PLAIN_MESSAGE, null);
		GuiController.changeFrame(this, new LoginFrame());
	}
	
}
