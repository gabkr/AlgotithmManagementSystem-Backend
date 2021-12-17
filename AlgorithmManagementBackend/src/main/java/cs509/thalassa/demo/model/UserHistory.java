package cs509.thalassa.demo.model;

public class UserHistory {
	public final String Username;
	public final String UserID;
	public final String Activity;
	public final String ActivityTime;
	
	public UserHistory (String UserID, String Username, String Activity, String ActivityTime) {
		this.UserID = UserID;
		this.Username = Username;
		this.Activity = Activity;
		this.ActivityTime = ActivityTime;
	}

	/**
	 * Equality of Constants determined by name alone.
	 */
	/**
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof UserHistory) {
			UserHistory other = (UserHistory) o;
			return Username.equals(other.Username);
		}
		
		return false;  // not a userhistory
	}

	public String toString() {
		return "[" + UserID + " " + Username + " " + Activity + ActivityTime + "]";
	}
	**/
}
