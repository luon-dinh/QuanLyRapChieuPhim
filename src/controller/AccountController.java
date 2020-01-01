package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Connector.Connector;
import Model.KhachHang;
import Model.NhanVien;
import Model.TaiKhoan;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import plugin.AlertBox;
import plugin.MyWindows;
import plugin.SceneController;
import usercontrol.control.AddEditInfo;

public class AccountController implements Initializable {
	@FXML private Line line;
	@FXML private BorderPane pane;
	@FXML private GridPane gp;
	private AddEditInfo EditInfo = new AddEditInfo("Cập nhật thông tin cá nhân");
	private AddEditInfo EditNickname = new AddEditInfo("Thay đổi tên đăng nhập");
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initial();
		line.startXProperty().bind(pane.getCenter().layoutXProperty().subtract(14));
		line.endXProperty().bind(pane.getCenter().layoutXProperty().subtract(14));
		line.setStartY(40);
		line.endYProperty().bind(pane.heightProperty().add(4));
		String[] tmp = {"Họ và tên","Giới tính","Ngày sinh","Email","Số điện thoại","Địa chỉ","Ngày vào làm"};
		EditInfo.AddAll(tmp);
		EditNickname.Add("Tên nickname mới");
	}

	private void initial() {
		// TODO Auto-generated method stub
		if(LoginController.taikhoan==null)
			return;
		TaiKhoan t=LoginController.taikhoan;	
		String loai=t.getLoaiTaiKhoan();
		ArrayList<NhanVien> nhanViens=new ArrayList<NhanVien>();
		nhanViens.addAll(new Connector().select(NhanVien.class,"select * from NHANVIEN where MaTaiKhoan='"+t.getMaTaiKhoan()+"'"));
		NhanVien nv=null;
		if(nhanViens.size()>0)
			nv=nhanViens.get(0);
		ObservableList<Node> childrens = gp.getChildren();
		
		
		//BINDING TEXT 
//	    for (Node node : childrens) {
//	        if(gp.getColumnIndex(node) == 1) {
//	           switch (gp.getRowIndex(node)) {
//			case 1:{
//				((Label)node).setText(t.getTenDangNhap());
//				break;	
//			}
//			case 2:{
//				((Label)node).setText(t.getMatKhau());
//				break;	
//			}
//			case 3:{
//				((Label)node).setText(t.getTenHienThi());
//				break;	
//			}
//			case 4:{
//				
//				break;	
//			}
//			case 5:{
//				if(nv==null)
//					break;
//				((Label)node).setText(nv.getHoTen());				
//				break;	
//			}
//			case 6:{
//				if(nv==null)
//					break;
//				((Label)node).setText(nv.getGioTinh());
//				break;	
//			}
//			case 7:{
//				if(nv==null)
//					break;
//				((Label)node).setText(nv.getNgaySinh());
//				break;	
//			}
//			case 8:{
//				if(nv==null)
//					break;
//				((Hyperlink)node).setText(nv.getEmail());
//				break;	
//			}
//			case 9:{
//				if(nv==null)
//					break;
//				((Label)node).setText(nv.getSoDienThoai());
//				break;	
//			}
//			case 10:{
//				if(nv==null)
//					break;
//				((Label)node).setText(nv.getDiaChi());
//				break;	
//			}
//			case 11:{
//				if(nv==null)
//					break;
//				((Label)node).setText(nv.getNgayVaoLam());
//				break;	
//			}
//			default:
//				break;
//			}
//	        }
//	    }
	}

	@FXML void UpdateInfo(ActionEvent event) {
		EditInfo.show();
	}

	@FXML void Logout(ActionEvent event) {
		application.MainController.SelectedButton.setValue("Home");
		plugin.SceneController.GetInstance().TryReplaceScene("Login");
	}

	@FXML void ChangePass(ActionEvent event) {
		MyWindows w = new MyWindows("../view/ChangePassword.fxml");
		w.Show();
	}

	@FXML void ChangeNickname(ActionEvent event) {
		EditNickname.show();
	}

	@FXML void ChangeAvatar(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.showOpenDialog(SceneController.GetInstance().getCurrentStage());
	}

	@FXML void CopyEmail(ActionEvent event) {
		AlertBox.show(AlertType.INFORMATION, "Thông tin", "Đã chép vào bộ nhớ tạm");
	}
}

