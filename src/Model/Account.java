package Model;

import java.util.Date;

public class Account {

	private String ID;
	private String Username;
	private String Password;
	private String Type;
	private Date Date;
	private String Status;
	public Account() {
		super();
	}
	public Account(String iD, String username, String password, String type, Date date, String status) {
		super();
		ID = iD;
		Username = username;
		Password = password;
		Type = type;
		Date = date;
		Status = status;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		this.Date = date;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
}
