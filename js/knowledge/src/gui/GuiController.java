package gui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;

import entities.player.User;

/**
 * Consist of static helper methods for easily manipulate data.
 */
public class GuiController {
	private static ArrayList<User> users = new ArrayList<User>();
	private static Set<String> usernames = new HashSet<String>();
	private static ArrayList<String> logLines = new ArrayList<String>();
	
	/**
	 * Starts game by showing the login frame.
	 * @param frame Must be the login frame
	 */
	public static void startGame(LoginFrame frame) {
		frame.setVisible(true);
	}
	
	/**
	 * Changes frame to the given frame.
	 * @param frame1 starting frame
	 * @param frame2 destination frame
	 */
	public static void changeFrame(JFrame frame1, JFrame frame2) {
		frame2.setVisible(true);
		frame1.dispose();
	}
	
	/**
	 * <p>Gets user data from users.txt</p>
	 * <p>First thing to call in the launching.</p>
	 * @throws CorruptedFileException
	 */
	public static void parseUserData() throws CorruptedFileException{
		try{
			File file = new File("res/text/users.txt");
			file.createNewFile();
			Scanner input = new Scanner(file);
			while(input.hasNextLine()) {
				String[] tmp = input.nextLine().split(",");
				if(tmp.length != 4) {
					users.clear();
					usernames.clear();
					input.close();
					throw new CorruptedFileException("File Corrupted");
				}
				users.add(new User(tmp[0], tmp[1], tmp[2],parseScoreData(tmp[3])));
				usernames.add(tmp[0]);
			}
			input.close();
		} catch (IOException e) {
			// Nearly impossible to happen because file is created if it is not present
			addLogLine("Fatal error happend: "+e.getMessage() + "Exiting the application");
			System.exit(1);
		}
	}
	
	/**
	 * Helper method for parsing the score data.
	 * @param s the part of the scores for a user in users.txt
	 * @return ArrayList of scores
	 * @throws CorruptedFileException
	 */
	private static ArrayList<Integer> parseScoreData(String s) throws CorruptedFileException {
		if(s.equals("empty")) {
			return new ArrayList<Integer>();
		}
		ArrayList<Integer> ans = new ArrayList<Integer>();
		String[] tmp = s.split(";");
		for(String x: tmp) {
			try {
				ans.add(Integer.valueOf(x));
			}
			catch (NumberFormatException e) {
				throw new CorruptedFileException("Incorrect type");
			}
		}
		return ans;
	}
	
	/**
	 * Takes arguments as ArrayList of different enemies with file type (questions.txt or infos.txt). Modifies given lists.
	 * @param sl
	 * @param ta
	 * @param prof
	 * @param file questions.txt or infos.txt
	 * @throws CorruptedFileException
	 * @throws IOException
	 */
	public static void parseData(ArrayList<String> sl, ArrayList<String> ta, ArrayList<String> prof,String file) throws CorruptedFileException,IOException{
		Scanner input = new Scanner(Paths.get("res/text/"+file));
		while(input.hasNextLine()) {
			String[] tmp = input.nextLine().split(":");
			if(tmp.length != 2) {
				throw new CorruptedFileException("File Corrupted");
			}
			if(tmp[0].equals("sl")) {
				sl.add(tmp[1]);
			}
			else if(tmp[0].equals("ta")) {
				ta.add(tmp[1]);
			}
			else if(tmp[0].equals("prof")) {
				prof.add(tmp[1]);
			}
			else {
				sl.clear();
				ta.clear();
				prof.clear();
				throw new CorruptedFileException("Invalid Enemy Type");
			}
		}
		input.close();
	}
	
	/**
	 * Checks username set for given username.
	 * @param username
	 * @return True is username is taken. Otherwise false
	 */
	public static boolean isUsernameTaken(String username) {
		return usernames.contains(username);
	}
	
	/**
	 * Checks if there exists such user with the given username and password.
	 * @param username
	 * @param password
	 * @return User with given username and password. Null if there is no such User.
	 */
	public static User findUser(String username, String password) {
		if(!usernames.contains(username)) {
			return null;
		}
		User res = null;
		for(User u: users) {
			if(User.isEquals(u, username, password)) {
				res = u;
				break;
			}
		}
		return res;
	}
	
	/**
	 * Register a user by adding it to the Arraylist and the Set.
	 * @param u User
	 */
	public static void registerUser(User u) {
		users.add(u);
		usernames.add(u.getUsername());
		updateUsers();
	}
	
	/**
	 * Updates the users.txt file according to ArrayList of users.
	 */
	public static void updateUsers() {
		try(Formatter output = new Formatter("res/text/users.txt")){
			for(User user: users) {
				output.format("%s,%s,%s,%s\n",user.getUsername(),user.getPassword(),user.getImgName(),user.gameSessionsString());
			}
			output.close();
		} catch (IOException e) {
			// TODO write to the log file
			System.err.println("Fatal error happend: "+e.getMessage());
			System.exit(1);
		}
	}
	
	/**
	 * Getting sorted user scores in the desired format for high score table.
	 * @return 2D String array. Each entry is for User with username, game session and score.
	 */
	public static String[][] getSortedUserScores(){
		ArrayList<String[]> res = new ArrayList<>();
		for(User user: users) {
			ArrayList<Integer> scores = user.getScores();
			for(int i = 0;i < scores.size();i++) {
				String[] tmp = {user.getUsername(),"Game "+(i+1),scores.get(i).toString()};
				res.add(tmp);
			}
		}
		String[][] ans = new String[res.size()][];
		for(int i = 0;i < res.size();i++) {
			ans[i] = res.get(i);
		}
		Arrays.sort(ans,new Comparator<String[]>() {
			@Override
			public int compare(String[] arg0, String[] arg1) {
				int val0 = Integer.valueOf(arg0[2]), val1 = Integer.valueOf(arg1[2]);
				if(val0 > val1) {
					return -1;
				}
				if(val0 < val1) {
					return 1;
				}
				return 0;
			}
		});
		return ans;
	}
	
	/**
	 * Adds line to the log file with time stamp.
	 * @param line
	 */
	public static void addLogLine(String line) {
		logLines.add(String.format("%s \t\t\t(%s)",line,LocalTime.now()));
		try(Formatter output = new Formatter("log/logs.txt")){
			for(String s: logLines) {
				output.format(s+"\n");
			}
			output.close();
		} catch (IOException e) {
			// Nearly impossible to happen because file is created if it is not present
			System.err.println("An error happend in log files.");
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
	
	/**
	 * Initialize log file. Creates if does not exists.
	 */
	public static void initializeLogFile() {
		try {
			File file = new File("log/logs.txt");
			file.createNewFile();
			Scanner input = new Scanner(file);
			while(input.hasNextLine()) {
				logLines.add(input.nextLine());
			}
			input.close();
		}
		catch (IOException e) {
			// Nearly impossible to happen because file is created if it is not present
			System.err.println("An error happend in log files.");
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}
