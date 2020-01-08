package usercontrol.control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.imageio.ImageIO;

import Model.LichChieuPhim;
import Model.Phim;
import controller.ScheduleController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.LocalTimeStringConverter;

import com.jfoenix.controls.JFXTimePicker;

import Connector.Connector;
import plugin.AlertBox;
import plugin.ImagesControler;
import plugin.MyWindows;
import plugin.SceneController;

public class AddEditInfo {
	private Stage stage;
	private Scene scene;
	private GridPane grid = new GridPane();
	private HashMap<String, TextField> map = new HashMap<>();
	private HashMap<String, TextArea> mapTextArea = new HashMap<>();
	private HashMap<String, ComboBox> mapComboBox = new HashMap<>();
	private HashMap<String, ImageView> mapImageView = new HashMap<>();
	private HashMap<String, DatePicker> mapDatePicker = new HashMap<>();
	private HashMap<String, PasswordField> mapPasswordField = new HashMap<>();
	private HashMap<String, com.jfoenix.controls.JFXTimePicker> mapTimePicker = new HashMap<>();
	public ButtonType boxReturn = null;
	private int sumSize = 0;
	public File f;// dùng cho các thao tác sửa có hình ảnh
	private boolean isShow = false;// dùng kiểm tra có đang show hay không

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	public AddEditInfo(String title) {
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(10, 10, 10, 10));
		grid.setHgap(10);
		grid.setVgap(10);
		ColumnConstraints c1 = new ColumnConstraints();
		c1.setHgrow(Priority.ALWAYS);
		ColumnConstraints c2 = new ColumnConstraints();
		c2.setHgrow(Priority.ALWAYS);
		c2.setMinWidth(300);
		grid.getColumnConstraints().add(c1);
		grid.getColumnConstraints().add(c2);
		pane.centerProperty().set(grid);

		scene = new Scene(pane);
		stage = new Stage();
		stage.setTitle(title);
		stage.setScene(scene);
		stage.getIcons().add(ImagesControler.getInstance().tryGetImage("ApplicationIcon"));

		AnchorPane anchor = new AnchorPane();
		pane.bottomProperty().set(anchor);
		HBox box = new HBox(10f);
		box.setPadding(new Insets(10, 0, 0, 0));
		AnchorPane.setRightAnchor(box, 0d);
		anchor.getChildren().add(box);
		Button ok = new Button("Đồng ý");
		ok.setStyle("-fx-pref-width: 80.0;");
		Button cancel = new Button("Hủy");
		cancel.setStyle("-fx-pref-width: 80.0;");
		box.getChildren().add(ok);
		box.getChildren().add(cancel);

		ok.setOnAction(e -> {
			boxReturn = ButtonType.OK;
			stage.close();
		});
		cancel.setOnAction(e -> {
			boxReturn = ButtonType.CANCEL;
			stage.close();
		});

		stage.initOwner(SceneController.GetInstance().getCurrentStage());
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setResizable(false);
	}

	public AddEditInfo(Phim phim) {
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(10, 10, 10, 10));
		grid.setHgap(10);
		grid.setVgap(10);
		ColumnConstraints c1 = new ColumnConstraints();
		c1.setHgrow(Priority.ALWAYS);
		ColumnConstraints c2 = new ColumnConstraints();
		c2.setHgrow(Priority.ALWAYS);
		c2.setMinWidth(300);
		grid.getColumnConstraints().add(c1);
		grid.getColumnConstraints().add(c2);
		pane.centerProperty().set(grid);

		scene = new Scene(pane);
		stage = new Stage();
		stage.setTitle("Thông tin lịch chiếu");
		stage.setScene(scene);
		stage.getIcons().add(ImagesControler.getInstance().tryGetImage("ApplicationIcon"));

		AnchorPane anchor = new AnchorPane();
		pane.bottomProperty().set(anchor);
		HBox box = new HBox(10f);
		box.setPadding(new Insets(10, 0, 0, 0));
		AnchorPane.setRightAnchor(box, 0d);
		anchor.getChildren().add(box);
		Button ok = new Button("Đồng ý");
		ok.setStyle("-fx-pref-width: 80.0;");
		Button cancel = new Button("Hủy");
		cancel.setStyle("-fx-pref-width: 80.0;");
		box.getChildren().add(ok);
		box.getChildren().add(cancel);

		ok.setOnAction(e -> {
			boxReturn = ButtonType.OK;
			stage.close();
		});
		cancel.setOnAction(e -> {
			boxReturn = ButtonType.CANCEL;
			stage.close();
		});

		stage.initOwner(SceneController.GetInstance().getCurrentStage());
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setResizable(false);
	}

	public void show() {
		isShow = true;
		stage.showAndWait();
		isShow = false;
	}

	public void addImageView(String content, Image image) {
		grid.add(new Label(content), 0, sumSize);
		ImageView imgv = new ImageView(image);
		imgv.setFitWidth(80);
		imgv.setFitHeight(80);
		imgv.setOnMouseClicked(e -> {
			FileChooser fileChooser = new FileChooser();
			// f=fileChooser.showOpenDialog(SceneController.GetInstance().getCurrentStage());
			f = fileChooser.showOpenDialog(MyWindows.lastStage);
			if (f != null) {
				try {
					BufferedImage bimg = ImageIO.read(f);
					Image img = SwingFXUtils.toFXImage(bimg, null);
					imgv.setImage(img);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		imgv.setVisible(true);
		grid.add(imgv, 1, sumSize);
		sumSize++;
		mapImageView.put(content, imgv);
	}

	public ImageView getImageView(String contentKey) {
		return mapImageView.get(contentKey);
	}

	public void AddComboBox(String content) {
		grid.add(new Label(content), 0, sumSize);
		ComboBox<String> comboBox = new ComboBox<String>();
		grid.add(comboBox, 1, sumSize);
		sumSize++;
		mapComboBox.put(content, comboBox);
	}

	public void AddTextArea(String content) {
		grid.add(new Label(content), 0, sumSize);
		TextArea textArea=new TextArea();
		grid.add(textArea, 1, sumSize);
		sumSize++;
		mapTextArea.put(content, textArea);
	}
	
	public TextArea getTextArea(String contentKey) {
		return mapTextArea.get(contentKey);
	}
	
	public void AddPasswordField(String content) {
		grid.add(new Label(content), 0, sumSize);
		PasswordField passwordField = new PasswordField();
		grid.add(passwordField, 1, sumSize);
		sumSize++;
		mapPasswordField.put(content, passwordField);
	}

	public PasswordField getPasswordField(String content) {
		return mapPasswordField.get(content);
	}

	public void AddDatePicker(String content) {
		grid.add(new Label(content), 0, sumSize);
		DatePicker datePicker = new DatePicker();

		StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

			@Override
			public String toString(LocalDate date) {
				if (date != null) {
					return dateFormatter.format(date);
				} else {
					return "";
				}
			}

			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalDate.parse(string, dateFormatter);
				} else {
					return null;
				}
			}
		};
		datePicker.setConverter(converter);
		datePicker.setPromptText("dd-MM-yyyy");
		grid.add(datePicker, 1, sumSize);
		sumSize++;
		mapDatePicker.put(content, datePicker);
	}

	public DatePicker getDatePicker(String content) {
		return mapDatePicker.get(content);
	}

	public void AddTimePicker(String content) {
		grid.add(new Label(content), 0, sumSize);
		com.jfoenix.controls.JFXTimePicker timePicker = new com.jfoenix.controls.JFXTimePicker();
		timePicker.valueProperty().addListener(new ChangeListener<LocalTime>() {
			@Override
			public void changed(ObservableValue<? extends LocalTime> observable, LocalTime oldValue,
					LocalTime newValue) {
				// TODO Auto-generated method stub
				if (newValue == null || ScheduleController.date == null)
					return;
				ArrayList<LichChieuPhim> dsLichChieu = new ArrayList<LichChieuPhim>();
				Connector<LichChieuPhim> c = new Connector<LichChieuPhim>();
				dsLichChieu.addAll(c.select(LichChieuPhim.class,
						"select * from LICHCHIEUPHIM where NgayChieu='" + ScheduleController.date.toString()
								+ "' and MaPhongChieu='" + ScheduleController.phong.getMaPhong() + "'"));
				for (LichChieuPhim lc : dsLichChieu) {
					try {
						if (LocalTime.parse(lc.getGioBatDau()).plusMinutes(lc.getThoiLuong()).isAfter(newValue)) {
							AlertBox.show(AlertType.INFORMATION,"Nhập sai","", "Trùng lịch chiếu");
							return;
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		});
		grid.add(timePicker, 1, sumSize);
		mapTimePicker.put(content, timePicker);
		sumSize++;
	}

	public com.jfoenix.controls.JFXTimePicker getTimePicker(String content) {
		return mapTimePicker.get(content);
	}

	public ComboBox getComboBox(String contenKey) {
		return mapComboBox.get(contenKey);
	}

	public void Add(String content) {
		grid.add(new Label(content), 0, sumSize);
		TextField textField = new TextField();
		textField.setPromptText(content);
		grid.add(textField, 1, sumSize);
		map.put(content, textField);
		sumSize++;
	}

	public void Add(String content, boolean b) {
		grid.add(new Label(content), 0, sumSize);
		TextField textField = new TextField();
		textField.setPromptText(content);
		if (b == true) {
			textField.setOnKeyTyped(e -> {
				char input = e.getCharacter().charAt(0);
				if (Character.isDigit(input) != true) {
					e.consume();
				}
			});
		}
		grid.add(textField, 1, sumSize);
		map.put(content, textField);
		sumSize++;
	}

	public void AddAll(String[] contents) {
		for (String item : contents)
			Add(item);
	}

	public void AddAll(Collection<String> contents) {
		for (String item : contents)
			Add(item);
	}

	public TextField Get(String contentKey) {
		return map.get(contentKey);
	}
}
