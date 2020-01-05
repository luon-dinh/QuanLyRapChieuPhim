package controller;

import plugin.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Connector.Connector;
import Model.KhachHang;
import Model.TaiKhoan;
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
		Date date=new Date();
		String d=new SimpleDateFormat("dd/MM/yyyy").format(date);
		String user=username.getText().toString();
		String pass=password.getText().toString();
		Connector<TaiKhoan> connector=new Connector<TaiKhoan>();
		List<TaiKhoan> accounts=connector.select(TaiKhoan.class,"select * from TaiKhoan");
		for(TaiKhoan ac:accounts) {
			if(ac.getMatKhau().equals(pass)&&ac.getTenDangNhap().equals(user)) {
				AlertBox.show(AlertType.ERROR, "Lỗi", "Tài khoản và mật khẩu đã được sử dụng");
				return;
			}
		}
		List<KhachHang> dsKhachHang=new Connector().select(KhachHang.class, "select * from KHACHHANG");
		int indexKH=0;
		if(dsKhachHang.size()>0) {
			String maKhachHang=dsKhachHang.get(dsKhachHang.size()-1).getMaKhachHang();
			indexKH=Integer.parseInt(maKhachHang.substring(2, maKhachHang.length()));
		}
		int lenght=accounts.size();
		int index=0;
		if(lenght>0) {
			index=Integer.parseInt(accounts.get(lenght-1).getMaTaiKhoan().substring(2, accounts.get(lenght-1).getMaTaiKhoan().length()));
		}
		String ID="TK"+(index+1);
		if(connector.insert("insert into TAIKHOAN values('"+ID+"','"+user+"','"+pass+"','user','"+d+"','Active','user','')")>0) {
			SceneController.GetInstance().TryReplaceScene("Main");
			new Connector<KhachHang>().insert("insert into KHACHHANG(MaKhachHang, MaTaiKhoan) values('"+"KH"+(indexKH+1)+"','"+ID+"')");
		}
	}

	public void Login_click(ActionEvent actionEvent) {
		SceneController.GetInstance().TryReplaceScene("Login");
	}
}
