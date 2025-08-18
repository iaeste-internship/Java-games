package Launcher;

import guiAuthentication.LoginAndRegisterScreen;

/**
 * Launcher of application, contains main method.
 * 
 * @author Aykut Bir
 * @since 29/04/2024
 * 
 */

/************** Pledge of Honor ******************************************
I hereby certify that I have completed this programming project on my own
without any help from anyone else. The effort in the project thus belongs
completely to me. I did not search for a solution, or I did not consult any
program written by others or did not copy any program from other sources. I
read and followed the guidelines provided in the project description.
READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
SIGNATURE: <Aykut Bir, 83442>
*************************************************************************/

public class MainLauncher {

	public static void main(String[] args) {
		
		try {
			LoginAndRegisterScreen frame = new LoginAndRegisterScreen();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}

}
