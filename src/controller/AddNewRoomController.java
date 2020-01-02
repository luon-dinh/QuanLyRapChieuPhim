package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import Connector.Connector;
import Model.LoaiPhim;
import Model.Phim;
import Model.PhongChieuPhim;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import plugin.SceneController;

public class AddNewRoomController implements Initializable {
	@FXML
	private ImageView image;
	@FXML
	private TextField name;
	@FXML
	private TextField capacity;
	@FXML
	private TextField chairs;
	@FXML
	private ComboBox<String> status;
	@FXML
	private TextArea description;
	@FXML
	private BorderPane addNewRoom_borderPane;
	@FXML
	private Button btn_dongy;
	@FXML
	private Button btn_huy;

	private File file = null;
	private Image img = null;
	private ObservableList<String> list = FXCollections.observableArrayList("Active", "Inactive");

	public ObjectProperty<Image> imageProperty() {
		return image.imageProperty();
	}

	public StringProperty titleProperty() {
		return name.textProperty();
	}

	public StringProperty capacityProperty() {
		return capacity.textProperty();
	}

	public StringProperty chairsProperty() {
		return chairs.textProperty();
	}

	public StringProperty descriptionProperty() {
		return description.textProperty();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		initial();
		addEvents();

	}

	private void addEvents() {
		// TODO Auto-generated method stub
		image.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				Stage stage = (Stage) addNewRoom_borderPane.getScene().getWindow();
				FileChooser fileChooser = new FileChooser();
				FileChooser.ExtensionFilter filter = new ExtensionFilter("Image Files", "*.png", "*.jpg");
				fileChooser.getExtensionFilters().add(filter);
				file = fileChooser.showOpenDialog(stage);
				if (file != null) {
					try {
						BufferedImage bimg = ImageIO.read(file);
						img = SwingFXUtils.toFXImage(bimg, null);
						image.setImage(img);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		btn_dongy.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				XuLyThemPhongChieu();
				Stage stage=(Stage)btn_dongy.getScene().getWindow();
				stage.close();
			}
		});
		
		btn_huy.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Stage stage=(Stage)btn_huy.getScene().getWindow();
				stage.close();
			}
		});

	}

	private void initial() {
		// TODO Auto-generated method stub
		status.setItems(list);

	}

	private void XuLyThemPhongChieu() {
		Connector<PhongChieuPhim> c = new Connector<PhongChieuPhim>();
		List<PhongChieuPhim> dsPhongChieuPhim = c.selectPhongChieuPhim("select * from PHONGCHIEUPHIM");
		int index = 0;
		if (dsPhongChieuPhim.size() > 0) {
			index = Integer.parseInt(dsPhongChieuPhim.get(dsPhongChieuPhim.size() - 1).getMaPhong().substring(1,
					dsPhongChieuPhim.get(dsPhongChieuPhim.size() - 1).getMaPhong().length()));
		}
		String maPhongChieuPhim = "R" + (index + 1);
		// TODO Auto-generated method stub
		String tenPhongChieuPhim = name.getText();
		String trangThai = status.getValue();
		int sucChua = Integer.parseInt(capacity.getText());
		int soGhe = Integer.parseInt(chairs.getText());
		String moTa = description.getText();
		
		byte[] hinhAnh = null;
		if (file != null) {
			hinhAnh = Connector.convertFileToByte(file);
		}
		try {
			c.connect();
			PreparedStatement pst = c.connection
					.prepareStatement("insert into PHONGCHIEUPHIM values('" + maPhongChieuPhim + "','" + tenPhongChieuPhim + "','" + trangThai
							+ "','" + sucChua + "','" + soGhe + "','" + moTa + "',?)");
			pst.setBytes(1, hinhAnh);
			pst.execute();
			c.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
