package controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.KhachHang;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import usercontrol.control.AddEditInfo;

public class CustomerController implements Initializable {
	private AddEditInfo add = new AddEditInfo("Thêm khách hàng");
	private AddEditInfo edit = new AddEditInfo("Sửa khách hàng");
	
	
	@FXML TableView<KhachHang> table_khachhang;
	@FXML TableColumn<KhachHang, String> column_makhachhang;
	@FXML TableColumn<KhachHang, String> column_mataikhoan;
	@FXML TableColumn<KhachHang, String> column_hoten;
	@FXML TableColumn<KhachHang, String> column_email;
	@FXML TableColumn<KhachHang, String> column_sodienthoai;
	
    @FXML void AddAction(ActionEvent event) {
    	add.show();
    }

    @FXML void DeleteAction(ActionEvent event) {
    }

    @FXML void EditAction(ActionEvent event) {
    	edit.show();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String[] tmp = { "Mã tài khoản", "Họ tên", "Email", "Số điện thoại" };
		add.AddAll(tmp);
		edit.AddAll(tmp);
	}

}
