package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Connector.Connector;
import Model.KhachHang;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import plugin.MyWindows;
import usercontrol.control.AddEditInfo;

public class CustomerController implements Initializable {
	private AddEditInfo add = new AddEditInfo("Thêm khách hàng");
	private AddEditInfo edit = new AddEditInfo("Sửa khách hàng");
	
	
	@FXML TableView<KhachHang> table_khachhang;
	@FXML TableColumn<KhachHang, Integer> column_sothutu;
	@FXML TableColumn<KhachHang, String> column_makhachhang;
	@FXML TableColumn<KhachHang, String> column_mataiKhoan;
	@FXML TableColumn<KhachHang, String> column_hoten;
	@FXML TableColumn<KhachHang, String> column_email;
	@FXML TableColumn<KhachHang, String> column_sodienthoai;
	
    @FXML void AddAction(ActionEvent event) {
    	add.show();
    }

    @FXML void DeleteAction(ActionEvent event) {
    	MyWindows w = new MyWindows("../view/Login.fxml");
    	w.Show();
    }

    @FXML void EditAction(ActionEvent event) {
    	edit.show();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String[] tmp = { "Mã tài khoản", "Họ tên", "Email", "Số điện thoại" };
		add.AddAll(tmp);
		edit.AddAll(tmp);
		initialize();
	}

	private void inItTableKhachHang() {
		column_sothutu.setCellValueFactory(
				column -> new ReadOnlyObjectWrapper<Integer>(table_khachhang.getItems().indexOf(column.getValue()) + 1));
		column_makhachhang.setCellValueFactory(new PropertyValueFactory<>("MaKhachHang"));
		column_mataiKhoan.setCellValueFactory(new PropertyValueFactory<>("MaTaiKhoan"));
		column_hoten.setCellValueFactory(new PropertyValueFactory<>("HoTen"));
		column_email.setCellValueFactory(new PropertyValueFactory<>("Email"));
		column_sodienthoai.setCellValueFactory(new PropertyValueFactory<>("SoDienThoai"));
	}
	
	
	public void initialize() {
		loadTableKhachHang();
		inItTableKhachHang();
	}
	
	private void loadTableKhachHang() {
		Connector<KhachHang> connection=new Connector<KhachHang>();
		List<KhachHang> khs=connection.select(KhachHang.class, "select * from KHACHHANG");
		ObservableList<KhachHang> dsKhachHang=FXCollections.observableArrayList();
		dsKhachHang.addAll(khs);
		table_khachhang.setItems(dsKhachHang);
	}
	
}
