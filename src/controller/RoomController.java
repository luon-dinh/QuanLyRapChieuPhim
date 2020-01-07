package controller;

import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import Connector.Connector;
import Model.Ghe;
import Model.Phim;
import Model.Phim_LoaiPhim;
import Model.PhongChieuPhim;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import plugin.AlertBox;
import plugin.MyWindows;
import plugin.AlertBox.MyButtonType;
import usercontrol.control.AddEditInfo;
import usercontrol.control.AddEditRoomInfo;
import usercontrol.control.AdvanceMenuFilterContent;
import usercontrol.control.MovieCard;
import usercontrol.control.RoomCard;

public class RoomController implements Initializable {

	@FXML
	private AnchorPane root;
	@FXML
	private FlowPane paneRoom;
	@FXML
	private TextField searchTextField;

	@FXML
	private Button btn_timkiem, btn_refresh;
	@FXML
	private Label lb_soluongphong;

	private ArrayList<PhongChieuPhim> dsPhong = null;
	private ObservableList<String> list = FXCollections.observableArrayList("Active", "Inactive");

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initial(null);
		addEvents();
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		searchTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode() == KeyCode.ENTER) {
					String newValue = searchTextField.getText();
					xuLiTimKiem(newValue);
				}
			}
		});

		btn_timkiem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String newValue = searchTextField.getText();
				xuLiTimKiem(newValue);
			}
		});

		btn_refresh.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				initial(null);
				searchTextField.setText("");
			}
		});
	}

	private void initial(ArrayList<PhongChieuPhim> ps) {
		// TODO Auto-generated method stub
		paneRoom.getChildren().removeAll(paneRoom.getChildren());
		dsPhong = new ArrayList<PhongChieuPhim>();
		dsPhong.addAll(new Connector<PhongChieuPhim>().selectPhongChieuPhim("select * from PHONGCHIEUPHIM"));

		ArrayList<PhongChieuPhim> temp = new ArrayList<PhongChieuPhim>();
		if (ps == null) {
			temp = dsPhong;
		} else {
			temp = ps;
		}

		for (PhongChieuPhim p : temp) {
			RoomCard card = new RoomCard(p);
			MenuItem edit = new MenuItem("Sửa");
			MenuItem delete = new MenuItem("Xóa");

			edit.setOnAction(e -> {
				editPhong(card);
			});

			delete.setOnAction(e -> {
				Optional<ButtonType> result = AlertBox.show(AlertType.CONFIRMATION, "Xác nhận",
						"Bạn có thực sự muốn xóa phòng chiếu này?", MyButtonType.YesNo);
				if (result.get() == ButtonType.YES) {
					new Connector<PhongChieuPhim>()
							.delete("delete from PHONGCHIEUPHIM where MaPhong='" + p.getMaPhong() + "'");
					paneRoom.getChildren().remove(card);
				}
			});

			card.menu.getItems().add(edit);
			card.menu.getItems().add(delete);
			paneRoom.getChildren().add(card);
		}

		lb_soluongphong.setText(temp.size() + "");
		paneRoom.prefWidthProperty().bind(root.widthProperty().subtract(20));
	}

	@SuppressWarnings("unchecked")
	private void editPhong(RoomCard card) {
		// TODO Auto-generated method stub
		PhongChieuPhim p = card.phong;
		AddEditRoomInfo sua = new AddEditRoomInfo("Sửa thông tin phòng");
		String[] info_1 = { "Tên phòng", "Sức chứa" };
		String[] info_2 = { "Số ghế", "Mô tả" }; // , "Trạng thái"
		String status = "Trạng thái";

		sua.AddAll(info_1);
		sua.AddComboBox(status);
		sua.AddAll(info_2);
		sua.addImageView("Ảnh phim", card.image.getImage());
		sua.Get("Tên phòng").setText(p.getTenPhong());
		sua.getComboBox(status).setValue(p.getTrangThai());
		sua.getComboBox(status).setItems(list);
		sua.Get("Sức chứa").setText(p.getSucChua() + "");
		sua.Get("Số ghế").setText(p.getSoGhe() + "");
		sua.Get("Mô tả").setText(p.getMoTa());

		sua.cur_SucChua = Integer.parseInt(sua.Get("Sức chứa").getText());
		sua.cur_SoGhe = Integer.parseInt(sua.Get("Số ghế").getText());
		sua.Get("Sức chứa").textProperty().addListener((observable, oldValue, newValue) -> {
			sua.cur_SucChua = Integer.parseInt(newValue);
		});

		sua.Get("Số ghế").textProperty().addListener((observable, oldValue, newValue) -> {
			sua.cur_SoGhe = Integer.parseInt(newValue);
		});
		sua.show();

		if (sua.boxReturn == ButtonType.CANCEL)
			return;
		if (sua.boxReturn == ButtonType.OK) {

			if (sua.cur_SoGhe > sua.cur_SucChua) {
				return;
			}

			try {
				String tenPhong = sua.Get("Tên phòng").getText();
				String trangThai = sua.getComboBox("Trạng thái").getValue().toString();
				String sucChua = sua.Get("Sức chứa").getText();
				String soGhe = sua.Get("Số ghế").getText();
				String moTa = sua.Get("Mô tả").getText();

				if (sua.f != null) {
					new Connector<Phim>().update(
							"update PHONGCHIEUPHIM set TenPhong='" + tenPhong + "', TrangThai='" + trangThai
									+ "', SucChua='" + sucChua + "', SoGhe='" + soGhe + "', MoTa='" + moTa
									+ "', HinhAnh=? where MaPhong='" + p.getMaPhong() + "'",
							Connector.convertFileToByte(sua.f));
				} else {
					new Connector<Phim>().update("update PHONGCHIEUPHIM set TenPhong='" + tenPhong + "', TrangThai='"
							+ trangThai + "', SucChua='" + sucChua + "',  SoGhe='" + soGhe + "', MoTa='" + moTa
							+ "', MoTa='" + moTa + "' where MaPhong='" + p.getMaPhong() + "'");
				}
				xuLiCapNhatGhe(p.getMaPhong(), Integer.parseInt(soGhe));
				
				Stage stage = (Stage) sua.getComboBox("Trạng thái").getScene().getWindow();
				stage.close();
				AlertBox.show(AlertType.INFORMATION, "Thành công", "", "Cập nhật thông tin thành công!");
				initial(null);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				AlertBox.show(AlertType.WARNING, "Nhập sai", "", "Vui lòng kiểm tra lại thông tin");
			}
		}
	}

	@FXML
	void AddNewRoomAction(ActionEvent event) {
		MyWindows w = new MyWindows("../view/AddNewRoom.fxml");
		w.Show();
		initial(null);
	}

	private void xuLiTimKiem(String cond) {
		// TODO Auto-generated method stub
		ArrayList<PhongChieuPhim> ds = new ArrayList<PhongChieuPhim>();
		cond = cond.toLowerCase();
		for (PhongChieuPhim p : dsPhong) {
			if (p.getTenPhong().toLowerCase().contains(cond)) {
				ds.add(p);
			}
		}
		initial(ds);
	}

	private void xuLiCapNhatGhe(String maPhongChieuPhim, int soGhe) {
		// TODO Auto-generated method stub
		Connector<Ghe> c = new Connector<Ghe>();
		c.delete("delete from GHE where MaPhong='" + maPhongChieuPhim + "'");
		List<Ghe> dsGhe = c.select(Ghe.class, "select * from GHE");
		int index = 0;
		if (dsGhe.size() > 0) {
			index = dsGhe.size();
		}
		for (int i = 0; i < soGhe; i++) {
			c.insert("insert into GHE values('" + (index + i) + "', '" + maPhongChieuPhim + "')");
		}
	}

}
