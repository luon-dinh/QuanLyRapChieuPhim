package Model;

import java.util.Date;

public class Account {

	@DBTable(columnName = "ID")
	private String ID;
	
	@DBTable(columnName = "Username")
	private String Username;
	
	@DBTable(columnName = "Password")
	private String Password;
	
	@DBTable(columnName = "Type")
	private String Type;
	
	@DBTable(columnName = "CreatedDate")
	private String Date;
	
	@DBTable(columnName = "Status")
	private String Status;
	public Account() {
		super();
	}
	public Account(String iD, String username, String password, String type, String date, String status) {
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
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		this.Date = date;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
}
