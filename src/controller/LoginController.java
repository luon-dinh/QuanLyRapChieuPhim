package controller;

import plugin.*;

import java.util.ArrayList;
import java.util.List;

import Connector.Connector;
import Model.TaiKhoan;
import application.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LoginController {
	@FXML private TextField username;
	@FXML private PasswordField password;
	public static TaiKhoan taikhoan=null;

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
			if(MainController.menu==null)
				MainController.menu=new ArrayList<String>();
			else{
				if(taikhoan.getLoaiTaiKhoan().equals("user")) {
					MainController.menu.clear();
					MainController.menu.add("Home");
					MainController.menu.add("Schedule");
					MainController.menu.add("Movies");
					//MainController.menu.add("Service");\
					MainController.menu.add("History");
					MainController.menu.add("Account");
				}
				else {
					MainController.menu.clear();
					MainController.menu.add("Home");
					MainController.menu.add("Schedule");
					MainController.menu.add("Movies");
					MainController.menu.add("Rooms");
					MainController.menu.add("Customer");
					MainController.menu.add("Service");
					MainController.menu.add("Account");
					MainController.menu.add("Statistic");
					MainController.menu.add("Staff");
				}
			}
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
			if(account.getTenDangNhap().equalsIgnoreCase(username)&&account.getMatKhau().equalsIgnoreCase(password)) {
				taikhoan=account;
				return true;
			}
		}
		return false;
	}
}
