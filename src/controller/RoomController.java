package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import Connector.Connector;
import Model.Phim;
import Model.Phim_LoaiPhim;
import Model.PhongChieuPhim;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import plugin.AlertBox;
import plugin.MyWindows;
import plugin.AlertBox.MyButtonType;
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

	ArrayList<PhongChieuPhim> dsPhong = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initial(null);
		addEvents();
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		searchTextField.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				xuLiTimKiem(newValue);
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
			edit.setOnAction(e -> {
				// sua
			});
			MenuItem delete = new MenuItem("Xóa");
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

		paneRoom.prefWidthProperty().bind(root.widthProperty().subtract(20));
	}

	@FXML
	void AddNewRoomAction(ActionEvent event) {
		MyWindows w = new MyWindows("../view/AddNewRoom.fxml");
		w.Show();
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
}
