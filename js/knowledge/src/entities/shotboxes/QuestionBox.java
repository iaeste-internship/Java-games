package entities.shotboxes;

import javax.swing.JTextArea;

import entities.enemies.Enemy;
import entities.enemies.Professor;
import entities.enemies.SL;
import entities.enemies.TA;
import gui.GuiController;

/**
 * <h3>QuestionBox class for health reducer object that extends {@link entities.shotboxes.ShotBox} </h3>
 */
public class QuestionBox extends ShotBox{
	private String question;
	private static JTextArea area;
	
	public QuestionBox(int X, int Y, String p,String question,Enemy shooter) {
		super(X, Y, p,shooter);
		this.question = question;	
	}
	
	public String getQuestion() {
		return question;
	}
	
	public static void setArea(JTextArea area) {
		QuestionBox.area = area;
	}
	
	/**
	 * Health reducer for different shooter type.
	 */
	@Override
	public void invokeCollision() {
		area.setText(area.getText() + question + "\n");
		if(shooter instanceof SL) {
			player.setHealth(player.getHealth()-5);
			GuiController.addLogLine("Player hit by question box health reduced by 5");
		}
		else if(shooter instanceof TA) {
			player.setHealth(player.getHealth()-10);
			GuiController.addLogLine("Player hit by question box health reduced by 10");
		}
		else if(shooter instanceof Professor) {
			player.setHealth(player.getHealth()-20);
			GuiController.addLogLine("Player hit by question box health reduced by 20");
		}
	}
}
