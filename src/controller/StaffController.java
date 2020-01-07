package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import Connector.Connector;
import Model.KhachHang;
import Model.NhanVien;
import Model.NhanVien;
import Model.Phim;
import Model.TaiKhoan;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import plugin.AlertBox;
import plugin.AutoCompleteComboBoxListener;
import plugin.MyWindows;
import plugin.AlertBox.MyButtonType;
import usercontrol.control.AddEditInfo;

public class StaffController implements Initializable {
	private AddEditInfo add = new AddEditInfo("Thêm nhân viên");
	private AddEditInfo edit = new AddEditInfo("Sửa nhân viên");
	private List<NhanVien> nhanViens;
	private ArrayList<TaiKhoan> dsTaiKhoan;
	private ObservableList<NhanVien> dsNhanVien;
	
	@FXML TableView<NhanVien> table_nhanvien;
	@FXML TableColumn<NhanVien, Integer> column_sothutu;
	@FXML TableColumn<NhanVien, String> column_manhanvien;
	//@FXML TableColumn<NhanVien, String> column_mataiKhoan;
	@FXML TableColumn<NhanVien, String> column_hoten;
	@FXML TableColumn<NhanVien, String> column_ngaysinh;
	@FXML TableColumn<NhanVien, String> column_diachi;
	@FXML TableColumn<NhanVien, String> column_gioitinh;
	@FXML TableColumn<NhanVien, String> column_email;
	@FXML TableColumn<NhanVien, String> column_sodienthoai;
	@FXML TableColumn<NhanVien, String> column_ngayvaolam;
	@FXML Button btn_xoa, btn_refresh, btn_them, btn_timkiem;
	@FXML TextField txt_timkiem;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String[] tmp = { "Họ tên", "Địa chỉ", "Giới tính", "Email", "Số điện thoại" };
		//edit.AddComboBox("Mã tài khoản");
		add.AddComboBox("Mã tài khoản");
		add.AddAll(tmp);
		add.AddDatePicker("Ngày sinh");
		add.AddDatePicker( "Ngày vào làm");
		//edit.AddAll(tmp);
		addEvents();
		initialize();
	}

	private void addEvents() {
	// TODO Auto-generated method stub
		
		btn_timkiem.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				xuLiTimKiemTheoTen();
			}
		});
		
		txt_timkiem.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode()==KeyCode.ENTER) {
					xuLiTimKiemTheoTen();
				}
			}
		});
		
		btn_xoa.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				NhanVien nv=table_nhanvien.getSelectionModel().getSelectedItem();
				if(nv!=null) {
					Optional<ButtonType> result =AlertBox.show(AlertType.CONFIRMATION, "Xác nhận", "Bạn có thực sự muốn xóa bộ phim này?", MyButtonType.YesNo);
					if(result.get()==ButtonType.YES) {
						table_nhanvien.getItems().remove(nv);
						new Connector().delete("delete from NhanVien where MaNhanVien='"+nv.getMaNhanVien()+"'");
					}
				}
			}
		});
		
		btn_refresh.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				loadTableNhanVien(null);
			}
		});
		
		btn_them.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				xuLiThemNhanVien();
			}
		});
	}

	protected void xuLiTimKiemTheoTen() {
		// TODO Auto-generated method stub
		String cond=txt_timkiem.getText().toLowerCase();
		if(cond.equals("")) {
			loadTableNhanVien(null);
			return;
		}
		Connector<NhanVien> connection=new Connector<NhanVien>();
		ArrayList<NhanVien> nhanvien=new ArrayList<NhanVien>();
		nhanvien.addAll(connection.select(NhanVien.class, "select * from NHANVIEN"));
		ObservableList<NhanVien> _nvs=FXCollections.observableArrayList();
		ArrayList<NhanVien> temp=new ArrayList<NhanVien>();
		temp.addAll(nhanvien);
		int max_index=nhanvien.size();
		for(int i=0;i<max_index;i++) {
			NhanVien item=nhanvien.get(i);
			String tenKhachHang=item.getHoTen().toLowerCase();
			if(tenKhachHang.contains(cond)) {
				continue;
			}
			temp.remove(item);
		}
		_nvs.addAll(temp);
		table_nhanvien.setItems(_nvs);
	}

	protected void xuLiThemNhanVien() {
		// TODO Auto-generated method stub
		add.show();
		if(add.boxReturn==ButtonType.OK) {
			int index=0;
			if(nhanViens.size()>0) {
				String maNhanVien=nhanViens.get(nhanViens.size()-1).getMaNhanVien();
				index=Integer.parseInt(maNhanVien.substring(2,maNhanVien.length()));
			}
			try {
				String maNhanVien="NV"+(index+1);
				String maTaiKhoan=(String) add.getComboBox("Mã tài khoản").getValue();
				String hoTen=add.Get("Họ tên").getText();
				String ngaySinh=add.getDatePicker("Ngày sinh").getValue().toString();
				String diaChi=add.Get("Địa chỉ").getText();
				String gioiTinh=add.Get("Giới tính").getText();
				String email=add.Get("Email").getText();
				String soDienThoai=add.Get("Số điện thoại").getText();
				String ngayVaoLam=add.getDatePicker("Ngày vào làm").getValue().toString();
				new Connector().insert("insert into NHANVIEN values('"+maNhanVien+"','"+hoTen+"','"+ngaySinh+"','"+diaChi+"','"+gioiTinh+"','"+email+"','"+soDienThoai+"','"+ngayVaoLam+"','"+maTaiKhoan+"')");
				loadTableNhanVien(null);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	private void inItTableKhachHang() {
		column_sothutu.setCellValueFactory(
				column -> new ReadOnlyObjectWrapper<Integer>(table_nhanvien.getItems().indexOf(column.getValue()) + 1));
		column_manhanvien.setCellValueFactory(new PropertyValueFactory<>("MaNhanVien"));
		//column_mataiKhoan.setCellValueFactory(new PropertyValueFactory<>("MaTaiKhoan"));
		column_hoten.setCellValueFactory(new PropertyValueFactory<>("HoTen"));
		column_ngaysinh.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
		column_diachi.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
		column_gioitinh.setCellValueFactory(new PropertyValueFactory<>("GioiTinh"));
		column_email.setCellValueFactory(new PropertyValueFactory<>("Email"));
		column_sodienthoai.setCellValueFactory(new PropertyValueFactory<>("SoDienThoai"));
		column_ngayvaolam.setCellValueFactory(new PropertyValueFactory<>("NgayVaoLam"));
	}
	
	
	public void initialize() {
		dsNhanVien=FXCollections.observableArrayList();
		inItTableKhachHang();
		loadTableNhanVien(null);
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
		//edit.getComboBox("Mã tài khoản").setItems(mas);
		new AutoCompleteComboBoxListener<String>(add.getComboBox("Mã tài khoản"));
		//new AutoCompleteComboBoxListener<String>(edit.getComboBox("Mã tài khoản"));
	}

	private void loadDanhSachTaiKhoan() {
		// TODO Auto-generated method stub
		dsTaiKhoan=new ArrayList<TaiKhoan>();
		dsTaiKhoan.addAll(new Connector().select(TaiKhoan.class,"select * from TAIKHOAN"));
	}

	private void loadTableNhanVien(List<NhanVien> nvs) {
		ArrayList<NhanVien> temp=new ArrayList<NhanVien>();
		Connector<NhanVien> connection=new Connector<NhanVien>();
		nhanViens=connection.select(NhanVien.class, "select * from NhanVien");
		if(nvs!=null)
			temp.addAll(nvs);
		else
			temp.addAll(nhanViens);
		dsNhanVien.addAll(nhanViens);
		table_nhanvien.setItems(dsNhanVien);
	}
	
}
