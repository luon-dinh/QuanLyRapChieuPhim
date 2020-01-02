package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Connector.Connector;
import Model.KhachHang;
import Model.LoaiTaiKhoan;
import Model.TaiKhoan;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import plugin.AutoCompleteComboBoxListener;
import plugin.MyWindows;
import usercontrol.control.AddEditInfo;

public class CustomerController implements Initializable {
	private AddEditInfo add = new AddEditInfo("Thêm khách hàng");
	private AddEditInfo edit = new AddEditInfo("Sửa khách hàng");
	private List<KhachHang> khs;
	private ArrayList<TaiKhoan> dsTaiKhoan;
	
	@FXML TableView<KhachHang> table_khachhang;
	@FXML TableColumn<KhachHang, Integer> column_sothutu;
	@FXML TableColumn<KhachHang, String> column_makhachhang;
	@FXML TableColumn<KhachHang, String> column_mataiKhoan;
	@FXML TableColumn<KhachHang, String> column_hoten;
	@FXML TableColumn<KhachHang, String> column_email;
	@FXML TableColumn<KhachHang, String> column_sodienthoai;
	
    @FXML void AddAction(ActionEvent event) {
    	add.show();
    	if(add.boxReturn==ButtonType.CANCEL)
    		return;
    	if(add.boxReturn==ButtonType.OK) {
    		String mataiKhoan=add.Get("Mã tài khoản").getText();
    	}
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
		String[] tmp = { "Họ tên", "Email", "Số điện thoại" };
		add.AddComboBox("Mã tài khoản");
		edit.AddComboBox("Mã tài khoản");
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
		inItTableKhachHang();
		loadTableKhachHang();
		loadDanhSachTaiKhoan();
		inItComboBoxMaTaiKhoan();
	}
	
	private void inItComboBoxMaTaiKhoan() {
		// TODO Auto-generated method stub
		ObservableList<String> mas=FXCollections.observableArrayList();
		for(TaiKhoan tk:dsTaiKhoan) {
			mas.add(tk.getMaTaiKhoan());
		}
		add.getComboBox("Mã tài khoản").setItems(mas);
		edit.getComboBox("Mã tài khoản").setItems(mas);
		new AutoCompleteComboBoxListener<String>(add.getComboBox("Mã tài khoản"));
		new AutoCompleteComboBoxListener<String>(edit.getComboBox("Mã tài khoản"));
	}

	private void loadDanhSachTaiKhoan() {
		// TODO Auto-generated method stub
		dsTaiKhoan=new ArrayList<TaiKhoan>();
		dsTaiKhoan.addAll(new Connector().select(TaiKhoan.class,"select * from TAIKHOAN"));
	}

	private void loadTableKhachHang() {
		Connector<KhachHang> connection=new Connector<KhachHang>();
		khs=connection.select(KhachHang.class, "select * from KHACHHANG");
		ObservableList<KhachHang> dsKhachHang=FXCollections.observableArrayList();
		dsKhachHang.addAll(khs);
		table_khachhang.setItems(dsKhachHang);
	}
	
}
