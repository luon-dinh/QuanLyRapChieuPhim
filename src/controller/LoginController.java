package controller;

import plugin.*;

import java.util.List;

import Connector.Connector;
import Model.TaiKhoan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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

	@FXML
    void Pasword_KeyPress(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER)
			LoginButton_Press(null);
    }
	
	public boolean checkAccount(String username, String password) {
		Connector<TaiKhoan> connector=new Connector<TaiKhoan>();
		List<TaiKhoan> accounts=connector.select(TaiKhoan.class,"select * from TaiKhoan");
		for(TaiKhoan account:accounts) {
			if(account.getTenDangNhap().equalsIgnoreCase(username)&&account.getMatKhau().equalsIgnoreCase(password))
				return true;
		}
		return false;
	}
}
