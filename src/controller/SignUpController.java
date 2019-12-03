package controller;

import plugin.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Connector.Connector;
import Model.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class SignUpController {
	@FXML private TextField username;
	@FXML private PasswordField password;
	@FXML private PasswordField rePassword;

	public void SignUpButton_Press(ActionEvent event) {
		if (username.getLength() == 0) {
			AlertBox.show(AlertType.ERROR, "Lỗi", "Tài khoản không được để trống");
			return;
		}
		if (password.getLength() == 0) {
			AlertBox.show(AlertType.ERROR, "Lỗi", "Mật khẩu không được để trống");
			return;
		}
		if (!password.getText().equals(rePassword.getText())) {
			AlertBox.show(AlertType.ERROR, "Lỗi", "Xác nhận mật khẩu không chính xác");
			System.out.println(password.getText()+" "+rePassword.getText());
			return;
		}
		// signup & login
		
		Connector<Account> connector=new Connector<Account>();
		Date date=new Date();
		String d=new SimpleDateFormat("dd/MM/yyyy").format(date);
		String user=username.getText().toString();
		String pass=password.getText().toString();
		List<Account> accounts=connector.select(Account.class,"select * from account");
		int lenght=accounts.size();
		String ID="ID"+lenght;
		if(connector.insert(ID, user,pass,"user",d,"Active")>0)
			SceneController.GetInstance().TryReplaceScene("Main");
	}

	public void Login_click(ActionEvent actionEvent) {
		SceneController.GetInstance().TryReplaceScene("Login");
	}
}
