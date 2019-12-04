package controller;

import plugin.*;

import java.util.List;

import Connector.Connector;
import Model.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class LoginController {
	@FXML private TextField username;
	@FXML private PasswordField password;

	public void LoginButton_Press(ActionEvent event) {
		if (username.getLength() == 0) {
			AlertBox.show(AlertType.ERROR, "Lỗi", "Tài khoản không được để trống");
			return;
		}
		if (password.getLength() == 0) {
			AlertBox.show(AlertType.ERROR, "Lỗi", "Mật khẩu không được để trống");
			return;
		}

		String user=username.getText().toString();
		String pass=password.getText().toString();
		
		// check password
		if(checkAccount(user,pass)) {
			SceneController.GetInstance().TryReplaceScene("Main");
		}
		else {
			AlertBox.show(AlertType.ERROR, "Lỗi", "Tài khoản hoặc mặt khẩu không đúng!");
			return;
		}
		
	}

	public void SignUp_click(ActionEvent actionEvent) {
		SceneController.GetInstance().TryReplaceScene("SignUp");
	}
	
	
	public boolean checkAccount(String username, String password) {
		Connector<Account> connector=new Connector<Account>();
		List<Account> accounts=connector.select(Account.class,"select * from account");
		for(Account account:accounts) {
			if(account.getUsername().equalsIgnoreCase(username)&&account.getPassword().equalsIgnoreCase(password))
				return true;
		}
		return false;
	}
}
