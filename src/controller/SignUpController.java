package controller;

import plugin.*;
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
		
		SceneController.GetInstance().TryReplaceScene("Main");
	}

	public void Login_click(ActionEvent actionEvent) {
		SceneController.GetInstance().TryReplaceScene("Login");
	}
}
