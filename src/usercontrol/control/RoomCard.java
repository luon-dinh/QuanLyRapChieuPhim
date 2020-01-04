package usercontrol.control;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Connector.Connector;
import Model.Phim;
import Model.Phim_LoaiPhim;
import Model.PhongChieuPhim;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import plugin.AlertBox;
import plugin.MyWindows;
import plugin.AlertBox.MyButtonType;

public class RoomCard extends AnchorPane implements Initializable {
	@FXML
	public ImageView image;
	@FXML
	public Text title, capacity, chairs, status, description;
	public PhongChieuPhim phong;
	public ContextMenu menu;

//	public RoomCard() {
//		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/RoomCard.fxml"));
//		fxmlLoader.setRoot(this);
//		fxmlLoader.setController(this);
//		try {
//			fxmlLoader.load();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		image.setOnContextMenuRequested(e -> {
//			menu.show(this, e.getScreenX(), e.getScreenY());
//		});
//	}

	public RoomCard(PhongChieuPhim p) {
		super();
		phong = p;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/RoomCard.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		image.setOnContextMenuRequested(e -> {
			menu.show(this, e.getScreenX(), e.getScreenY());
		});
		title.setText(" " + p.getTenPhong());
		Connector<PhongChieuPhim> c = new Connector<PhongChieuPhim>();
		
		Image img = c.convertToBufferImage(p.getHinhAnh());
		//
		Rectangle2D croppedPortion;
		if (img.getWidth() / 180.0 > img.getHeight() / 250.0) {

			croppedPortion = new Rectangle2D(img.getWidth() / 2.0 - img.getHeight() / 250.0 * 90.0, 0,
					img.getHeight()/250.0*180.0, img.getHeight());

		} else {
			croppedPortion = new Rectangle2D(0, img.getHeight() / 2.0 - img.getWidth() / 180.0 * 125.0,
					img.getWidth(), img.getWidth() / 180.0*250.0);
		}

		// target width and height:
		double scaledWidth = 180;
		double scaledHeight = 250;
		image.setImage(img);		
		image.setViewport(croppedPortion);
		image.setFitWidth(scaledWidth);
		image.setFitHeight(scaledHeight);
		image.setSmooth(false);
		//
		capacity.setText(phong.getSucChua() + "");
		chairs.setText(phong.getSoGhe() + "");
		description.setText(phong.getMoTa() + "");
		status.setText(phong.getTrangThai() + "");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		menu = new ContextMenu();
	}
}
