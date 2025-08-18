package entities.shotboxes;

import javax.swing.JTextArea;

import entities.enemies.Enemy;
import entities.enemies.Professor;
import entities.enemies.SL;
import entities.enemies.TA;
import gui.GuiController;

/**
 * <h3>InfoBox class for score booster object that extends {@link entities.shotboxes.ShotBox} </h3>
 */
public class InfoBox extends ShotBox{
	private String info;
	private static JTextArea area;
	
	public InfoBox(int X, int Y, String p,String info,Enemy shooter) {
		super(X, Y, p,shooter);
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public static void setArea(JTextArea area) {
		InfoBox.area = area;
	}
	
	/**
	 * Score booster for different shooter type.
	 */
	@Override
	public void invokeCollision() {
		area.setText(area.getText() + info + "\n");
		if(shooter instanceof SL) {
			player.setScore(player.getScore()+10);
			GuiController.addLogLine("Player hit by info box score increased by 10");
		}
		else if(shooter instanceof TA) {
			player.setScore(player.getScore()+20);
			GuiController.addLogLine("Player hit by info box score increased by 20");
		}
		else if(shooter instanceof Professor) {
			player.setScore(player.getScore()+30);
			GuiController.addLogLine("Player hit by info box score increased by 30");
		}
	}
	
}
