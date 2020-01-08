package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import Connector.Connector;
import Model.KhachHang;
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

public class CustomerController implements Initializable {
	private AddEditInfo add = new AddEditInfo("Thêm khách hàng");
	private AddEditInfo edit = new AddEditInfo("Sửa khách hàng");
	private List<KhachHang> khs;
	private ArrayList<TaiKhoan> dsTaiKhoan;
	private ObservableList<KhachHang> dsKhachHang;
	
	@FXML TableView<KhachHang> table_khachhang;
	@FXML TableColumn<KhachHang, Integer> column_sothutu;
	@FXML TableColumn<KhachHang, String> column_makhachhang;
	//@FXML TableColumn<KhachHang, String> column_mataiKhoan;
	@FXML TableColumn<KhachHang, String> column_hoten;
	@FXML TableColumn<KhachHang, String> column_email;
	@FXML TableColumn<KhachHang, String> column_sodienthoai;
	@FXML private Button btn_timkiem, btn_xoa, btn_refresh;
	@FXML private TextField txt_timkiem;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dsKhachHang=FXCollections.observableArrayList();
		initControls();
		initialize();
		addEvents();
	}

	private void initControls() {
		// TODO Auto-generated method stub
		String[] tmp = { "Họ tên", "Email", "Số điện thoại" };
		add.AddComboBox("Mã tài khoản");
		edit.AddComboBox("Mã tài khoản");
		add.AddAll(tmp);
		edit.AddAll(tmp);
		txt_timkiem.setPromptText("Nhập tên bạn muốn tìm");
	}

	private void addEvents() {
	// TODO Auto-generated method stub
		txt_timkiem.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode()==KeyCode.ENTER) {
					xuLiTimKiemTheoTen();
				}
			}
		});
		
		btn_timkiem.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				xuLiTimKiemTheoTen();
			}
		});
		
		btn_xoa.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				KhachHang kh=table_khachhang.getSelectionModel().getSelectedItem();
				if(kh!=null) {
					Optional<ButtonType> result =AlertBox.show(AlertType.CONFIRMATION, "Xác nhận", "Bạn có thực sự muốn xóa bộ phim này?", MyButtonType.YesNo);
					if(result.get()==ButtonType.YES) {
						table_khachhang.getItems().remove(kh);
						new Connector().delete("delete from KHACHHANG where MaTaiKhoan='"+kh.getMaTaiKhoan()+"'");
					}
				}
			}
		});
		
		btn_refresh.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				loadTableKhachHang(null);
			}
		});
	}

	protected void xuLiTimKiemTheoTen() {
		String cond=txt_timkiem.getText().toLowerCase();
		if(cond.equals("")) {
			loadTableKhachHang(null);
			return;
		}
		Connector<KhachHang> connection=new Connector<KhachHang>();
		ArrayList<KhachHang> danhsach=new ArrayList<KhachHang>();
		danhsach.addAll(connection.select(KhachHang.class, "select * from KHACHHANG"));
		ObservableList<KhachHang> _khs=FXCollections.observableArrayList();
		int max_index=danhsach.size();
		ArrayList<KhachHang> temp=new ArrayList<KhachHang>();
		temp.addAll(danhsach);
		for(int i=0;i<max_index;i++) {
			KhachHang item=danhsach.get(i);
			String tenKhachHang=item.getHoTen().toLowerCase();
			if(tenKhachHang.contains(cond)) {
				continue;
			}
			temp.remove(item);
		}
		_khs.addAll(temp);
		table_khachhang.setItems(_khs);
	}

	private void inItTableKhachHang() {
		column_sothutu.setCellValueFactory(
				column -> new ReadOnlyObjectWrapper<Integer>(table_khachhang.getItems().indexOf(column.getValue()) + 1));
		column_makhachhang.setCellValueFactory(new PropertyValueFactory<>("MaKhachHang"));
		//column_mataiKhoan.setCellValueFactory(new PropertyValueFactory<>("MaTaiKhoan"));
		column_hoten.setCellValueFactory(new PropertyValueFactory<>("HoTen"));
		column_email.setCellValueFactory(new PropertyValueFactory<>("Email"));
		column_sodienthoai.setCellValueFactory(new PropertyValueFactory<>("SoDienThoai"));
	}
	
	
	public void initialize() {
		inItTableKhachHang();
		loadTableKhachHang(null);
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

	private void loadTableKhachHang(List<KhachHang> _khs) {
		
		Connector<KhachHang> connection=new Connector<KhachHang>();
		khs=connection.select(KhachHang.class, "select * from KHACHHANG");
		ArrayList<KhachHang> temp=new ArrayList<KhachHang>();
		if(_khs==null) {
			temp.addAll(khs);
		}
		else {
			temp.addAll(_khs);
		}
		dsKhachHang.clear();
		dsKhachHang.addAll(temp);
		table_khachhang.setItems(dsKhachHang);
	}
	
}
