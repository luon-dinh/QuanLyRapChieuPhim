package login;

import plugin.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class LoginControler {
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
		SceneControler.GetInstance().TryReplaceScene("Main");
	}

	public void SignUp_click(ActionEvent actionEvent) {
		SceneControler.GetInstance().TryReplaceScene("SignUp");
	}
}
