package entities.player;

import java.util.ArrayList;

/**
 * <h3>This is a User class to store user data collected from users.txt in a well defended format.</h3>
 * <p>It contains username, password informations and an ArrayList of Integers for scores.</p>
 */
public class User{
	private String username;
	private String password;
	private String imgName;
	private ArrayList<Integer> scores;
	
	/**
	 * Constructor for User with empty score list. Delegate to complete constructor with empty list.
	 * @param username
	 * @param password
	 * @param imgName
	 */
	public User(String username,String password,String imgName) {
		this(username, password, imgName, new ArrayList<Integer>());
	}
	
	/**
	 * Complete constructor for User class.
	 * @param username
	 * @param password
	 * @param imgName in a *.jpg format
	 * @param scores
	 */
	public User(String username,String password,String imgName,ArrayList<Integer> scores) {
		this.username = username;
		this.password = password;
		this.imgName = imgName;
		this.scores = scores;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}

	public String getImgName() {
		return imgName;
	}

	public ArrayList<Integer> getScores() {
		return scores;
	}

	/**
	 * In users.txt file scores for user separated by semicolon. This method generate such String from score list.
	 * @return Semicolon separated scores String.
	 */
	public String gameSessionsString() {
		if(scores.size() == 0) {
			return "empty";
		}
		String ans = "";
		for(Integer num: scores) {
			ans += num.toString()+";";
		}
		return ans;
	}
	
	/**
	 * Adds score to the scores list.
	 * @param score
	 */
	public void addScore(int score) {
		scores.add(score);
	}
	
	/**
	 * Static method for comparing two User by their username and password information.
	 * @param u1 User 1
	 * @param u2 User 2
	 * @return True if the two user have same username and password.
	 */
	public static boolean isEquals(User u1,User u2) {
		return u1.username == u2.username && u1.password == u2.password;
	}
	
	/**
	 * Static method for checking if the user has the given username and password.
	 * @param u1 User to be checked
	 * @param usr username String
	 * @param pw password String
	 * @return True if the user's username and password information is same as the given Strings.
	 */
	public static boolean isEquals(User u1, String usr, String pw) {
		return u1.username.equals(usr) && u1.password.equals(pw);
	}
}