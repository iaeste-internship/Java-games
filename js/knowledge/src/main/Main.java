/************** Pledge of Honor ******************************************
I hereby certify that I have completed this programming project on my own without
any help from anyone else. The effort in the project thus belongs completely to me.
I did not search for a solution, or I did not consult any program written by others
or did not copy any program from other sources. I read and followed the guidelines
provided in the project description.
READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
SIGNATURE: Sinan Efe Özler, 90179
*************************************************************************/
package main;

import javax.swing.JOptionPane;

import gui.*;

public class Main {
	/**
	 * Launches the application by using {@link gui.GuiController#changeFrame(javax.swing.JFrame, javax.swing.JFrame)}
	 * @see {@link gui.GuiController#changeFrame(javax.swing.JFrame, javax.swing.JFrame)}
	 * @param args
	 */
	public static void main(String[] args) {
		GuiController.initializeLogFile();
		GuiController.addLogLine("---------------------New App Session Starting---------------------");
		LoginFrame loginFrame = new LoginFrame();
		try { // checking the questions and infos files.
			GuiController.parseUserData();
		}
		catch (CorruptedFileException e) {
			GuiController.addLogLine("users.txt file is corrupted. User's response is waiting.");
			int option = JOptionPane.showConfirmDialog(loginFrame,"users.txt file is corrupted. Would you like to create an empty file (All previous user will be deleted)","Fatal Error",JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION) {
				GuiController.updateUsers();
				GuiController.addLogLine("Recreating users.txt file");
			}
			else if(option == JOptionPane.NO_OPTION) {
				GuiController.addLogLine("Exiting the application.");
				System.exit(1);
			}
		}
		GuiController.startGame(loginFrame);
	}

}
