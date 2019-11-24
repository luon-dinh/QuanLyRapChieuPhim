package controller;

import plugin.*;
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
		// check password
		SceneController.GetInstance().TryReplaceScene("Main");
	}

	public void SignUp_click(ActionEvent actionEvent) {
		SceneController.GetInstance().TryReplaceScene("SignUp");
	}
}
