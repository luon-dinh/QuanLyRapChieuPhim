package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import Connector.Connector;
import Model.Phim;
import Model.Phim_LoaiPhim;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import plugin.AlertBox;
import plugin.MyWindows;
import plugin.AlertBox.MyButtonType;
import usercontrol.control.AddEditInfo;
import usercontrol.control.AdvanceMenuFilterContent;
import usercontrol.control.MovieCard;

public class MoviesController implements Initializable {
	@FXML
	private AnchorPane root;
	@FXML
	private MenuButton advance;
	@FXML
	private FlowPane paneMovie;
	@FXML
	private TextField condition;
	@FXML
	private Button btn_refresh, btn_timkiem;

	private AdvanceMenuFilterContent menuContent = new AdvanceMenuFilterContent();
	private ArrayList<Phim> dsPhim;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initial(null);
		addEvents();
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		btn_timkiem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String cond = condition.getText();
				xuLiTimKiem(cond);
			}
		});

		btn_refresh.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				condition.setText("");
			}
		});

		condition.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				xuLiTimKiem(newValue);
			}
		});

	}

	private void initial(ArrayList<Phim> ps) {
		// TODO Auto-generated method stub
		paneMovie.getChildren().removeAll(paneMovie.getChildren());
		dsPhim = new ArrayList<Phim>();
		dsPhim.addAll(new Connector().selectPhim("select * from PHIM"));
		ArrayList<Phim> temp = new ArrayList<Phim>();
		if (ps == null) {
			temp = dsPhim;
		} else {
			temp = ps;
		}

		for (Phim p : temp) {
			MovieCard card = new MovieCard(p);
			MenuItem edit = new MenuItem("Sửa");

			edit.setOnAction(e -> {


			edit.setOnAction(e->{
				editPhim(card);

			});
			MenuItem delete = new MenuItem("Xóa");
			delete.setOnAction(e -> {
				Optional<ButtonType> result = AlertBox.show(AlertType.CONFIRMATION, "Xác nhận",
						"Bạn có thực sự muốn xóa bộ phim này?", MyButtonType.YesNo);
				if (result.get() == ButtonType.YES) {
					new Connector<Phim>().delete("delete from PHIM where MaPhim='" + p.getMaPhim() + "'");
					new Connector<Phim_LoaiPhim>()
							.delete("delete from PHIM_LOAIPHIM where MaPhim='" + p.getMaPhim() + "'");
					paneMovie.getChildren().remove(card);
				}
			});
			card.menu.getItems().add(edit);
			card.menu.getItems().add(delete);
			paneMovie.getChildren().add(card);
		}
		advance.getItems().add(new CustomMenuItem(menuContent, false));

		menuContent.Add("Thể loại", "Hài hước");
		menuContent.Add("Thể loại", "Hành động");
		menuContent.Add("Năm", "2018");

		paneMovie.prefWidthProperty().bind(root.widthProperty().subtract(20));

	}



	@FXML
	private void FindMovies(KeyEvent event) {
		// xử lí lọc
		if (event.getCode() == KeyCode.ENTER) {
			String cond = condition.getText();


	private void editPhim(MovieCard card) {
		// TODO Auto-generated method stub
		Phim p = card.phim;
		AddEditInfo sua = new AddEditInfo("Sửa thông tin phim");
		String[] info = { "Tên phim", "Nước sản xuất", "Năm sản xuất", "Đạo diễn", "Thời lượng", "Mô tả" };
		sua.AddAll(info);
		sua.addImageView("Ảnh phim", card.image.getImage());
		sua.Get("Tên phim").setText(p.getTenPhim());
		sua.Get("Nước sản xuất").setText(p.getTenNuocSanXuat());
		sua.Get("Năm sản xuất").setText(p.getNamSanXuat() + "");
		sua.Get("Đạo diễn").setText(p.getTenDaoDien());
		sua.Get("Thời lượng").setText(p.getThoiLuong());
		sua.Get("Mô tả").setText(p.getMota());
		sua.show();
		if (sua.boxReturn == ButtonType.CANCEL)
			return;
		if (sua.boxReturn == ButtonType.OK) {
			try {
				String tenPhim = sua.Get("Tên phim").getText();
				String nuocSanXuat = sua.Get("Nước sản xuất").getText();
				String namSanXuat = sua.Get("Năm sản xuất").getText();
				String daoDien = sua.Get("Đạo diễn").getText();
				String thoiLuong = sua.Get("Thời lượng").getText();
				String moTa = sua.Get("Mô tả").getText();
				if (sua.f != null) {
					new Connector<Phim>().update("update PHIM set TenPhim='" + tenPhim + "', TenNuocSanXuat='"
							+ nuocSanXuat + "', NamSanXuat='" + namSanXuat + "', TenDaoDien='" + daoDien
							+ "', ThoiLuong='" + thoiLuong + "', MoTa='" + moTa + "', HinhAnh=? where MaPhim='"
							+ p.getMaPhim() + "'", Connector.convertFileToByte(sua.f));
				} else {
					new Connector<Phim>()
							.update("update PHIM set TenPhim='" + tenPhim + "', TenNuocSanXuat='" + nuocSanXuat
									+ "', NamSanXuat='" + namSanXuat + "', TenDaoDien='" + daoDien + "', ThoiLuong='"
									+ thoiLuong + "', MoTa='" + moTa + "' where MaPhim='" + p.getMaPhim() + "'");
				}
				initial(null);
			} catch (Exception e) {
				// TODO: handle exception
				AlertBox.show(AlertType.WARNING, "Nhập sai", "", "Vui lòng kiểm tra lại thông tin");
			}
		}
	}

	@FXML private void FindMovies(KeyEvent event) {
		//xử lí lọc
		if (event.getCode()==KeyCode.ENTER) {
			String cond=condition.getText();

			xuLiTimKiem(cond);
		}
	}

	private void xuLiTimKiem(String cond) {
		// TODO Auto-generated method stub
		ArrayList<Phim> ds = new ArrayList<Phim>();
		cond = cond.toLowerCase();
		for (Phim p : dsPhim) {
			if (p.getTenPhim().toLowerCase().contains(cond)) {
				ds.add(p);
			}
		}
		initial(ds);
	}

	@FXML
	void AddNewMovie(ActionEvent event) {
		MyWindows w = new MyWindows("../view/AddNewMovie.fxml");
		w.Show();
		initial(null);
	}
}