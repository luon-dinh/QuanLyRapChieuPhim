package controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import Connector.Connector;
import Model.LichChieuPhim;
import Model.PhongChieuPhim;
import application.MainController;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.StringConverter;
import plugin.AlertBox;
import plugin.AutoCompleteComboBoxListener;
import plugin.MyWindows;
import plugin.AlertBox.MyButtonType;
import usercontrol.control.MovieCard;
import usercontrol.control.MovieScheduleCard;

public class ScheduleController implements Initializable {
	@FXML
	private Line timeLine;
	@FXML
	private HBox schedulePane;
	@FXML
	public ComboBox<String> cb_phong;
	@FXML
	private Label lb_ngay, lb_thang, lb_nam, lb_thuhai, lb_thuba, lb_thutu, lb_thunam, lb_thusau, lb_thubay, lb_chunhat;
	@FXML
	private HBox hbox;
	@FXML
	private Button btn_them, btn_next, btn_previous, btn_thuhai, btn_thuba, btn_thutu, btn_thunam, btn_thusau,
			btn_thubay, btn_chunhat;
	// @FXML private GridPane grid;
	@FXML
	public DatePicker datePicker;

	public static LocalDate date = null;
	public static PhongChieuPhim phong;
	private ArrayList<LichChieuPhim> dsLichChieu;
	private ObservableList<String> dsPhong;
	private ArrayList<PhongChieuPhim> dsPhongChieu;

	ArrayList<Button> btns = new ArrayList<Button>();
	ArrayList<Label> labels = new ArrayList<Label>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btns.add(btn_thuhai);
		btns.add(btn_thuba);
		btns.add(btn_thutu);
		btns.add(btn_thunam);
		btns.add(btn_thusau);
		btns.add(btn_thubay);
		btns.add(btn_chunhat);
		labels.add(lb_thuhai);
		labels.add(lb_thuba);
		labels.add(lb_thutu);
		labels.add(lb_thunam);
		labels.add(lb_thusau);
		labels.add(lb_thubay);
		labels.add(lb_chunhat);
		datePicker.getEditor().setDisable(true);
		StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
		initValues();
		for (int i = 0; i < 7; i++) {
			if (labels.get(i).getText().equals(date.toString())) {
				btns.get(i).styleProperty().set("-fx-background-color: lightgray");
				break;
			}
		}

		if (LoginController.taikhoan.getLoaiTaiKhoan().equals("user")) {
			btn_them.setVisible(false);
			cb_phong.setVisible(false);
		}
		inItValue();// hàm để refresh toàn bộ data binding data khi data được cập nhật
		addEvents();// khởi tạo sự kiện cho các control
		timeLine.endXProperty()
				.bind(Bindings.max(schedulePane.widthProperty(), MainController.mainPage.widthProperty().add(-105)));
		timeLine.translateYProperty().bind(schedulePane.heightProperty().add(-40));
	}

	private void initValues() {
		// TODO Auto-generated method stub
		datePicker.setValue(LocalDate.now());
		date = datePicker.getValue();
		lb_ngay.setText(date.getDayOfMonth() + "");
		lb_thang.setText(date.getMonth() + "");
		lb_nam.setText(date.getYear() + "");
		setButton();
	}

	private void setButton() {
		System.out.println(date.getDayOfWeek());
		switch (date.getDayOfWeek()) {
		case MONDAY: {
			for (int i = 0; i < 7; i++) {
				labels.get(i).setText(date.plusDays(i).toString());
			}
			break;
		}
		case TUESDAY: {
			for (int i = -1; i < 6; i++) {
				labels.get(i + 1).setText(date.plusDays(i).toString());
			}
			break;
		}
		case WEDNESDAY: {
			for (int i = -2; i < 5; i++) {
				labels.get(i + 2).setText(date.plusDays(i).toString());
			}
			break;
		}
		case THURSDAY: {
			for (int i = -3; i < 4; i++) {
				labels.get(i + 3).setText(date.plusDays(i).toString());
			}
			break;
		}
		case FRIDAY: {
			for (int i = -4; i < 3; i++) {
				labels.get(i + 4).setText(date.plusDays(i).toString());
			}
			break;
		}
		case SATURDAY: {
			for (int i = -5; i < 2; i++) {
				labels.get(i + 5).setText(date.plusDays(i).toString());
			}
			break;
		}
		case SUNDAY: {
			for (int i = -6; i < 1; i++) {
				labels.get(i + 6).setText(date.plusDays(i).toString());
			}
			break;
		}

		default:
			break;
		}
	}

	private void addEvents() {
		// TODO Auto-generated method stub

		for (int i = 0; i < 7; i++) {
			final int j = i;
			Button btn = btns.get(i);
			btn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					try {
						datePicker.setValue(LocalDate.parse(labels.get(j).getText()));
						for (int i = 0; i < 7; i++) {
							btns.get(i).styleProperty().set("");
						}
						btn.styleProperty().set("-fx-background-color: lightgray");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			});
		}

		datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {

			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue,
					LocalDate newValue) {
				if (oldValue == newValue)
					return;
				// TODO Auto-generated method stub
				date = datePicker.getValue();
				lb_ngay.setText(date.getDayOfMonth() + "");
				lb_thang.setText(date.getMonth() + "");
				lb_nam.setText(date.getYear() + "");
				inItValue();
				setButton();
			}
		});

		cb_phong.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if (newValue == null || newValue.equals(oldValue))
					return;
				phong = new Connector<PhongChieuPhim>()
						.select(PhongChieuPhim.class, "select * from PHONGCHIEUPHIM where TenPhong='" + newValue + "'")
						.get(0);
			}
		});

		btn_them.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				themLichChieuPhim();
			}
		});

		btn_next.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				datePicker.setValue(date.plusDays(7));
			}
		});

		btn_previous.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				datePicker.setValue(date.plusDays(-7));
			}
		});

	}

	private void themLichChieuPhim() {
		if (cb_phong.getValue() == null || datePicker.getValue() == null) {
			AlertBox.show(AlertType.WARNING, "Vui lòng chọn ngày/phòng trước khi thêm lịch chiếu phim");
			return;
		}
		MyWindows w = new MyWindows("../view/AddMovieToSchedule.fxml");
		w.Resize(940, 600);
		w.Show();
		// w2.show();
		inItValue();

	}

	private void inItValue() {
		schedulePane.getChildren().removeAll(schedulePane.getChildren());
		// TODO Auto-generated method stub
		dsPhong = FXCollections.observableArrayList();
		dsPhongChieu = new ArrayList<PhongChieuPhim>();
		dsLichChieu = new ArrayList<LichChieuPhim>();
		dsPhongChieu
				.addAll(new Connector<PhongChieuPhim>().select(PhongChieuPhim.class, "select * from PHONGCHIEUPHIM"));
		System.out.println(date.toString());
		List<LichChieuPhim> lc = new Connector<LichChieuPhim>().select(LichChieuPhim.class,
				"select * from LICHCHIEUPHIM where NgayChieu='" + date.toString() + "'");
		dsLichChieu.addAll(lc);

		for (PhongChieuPhim pc : dsPhongChieu) {
			dsPhong.add(pc.getTenPhong());
		}
		cb_phong.setItems(dsPhong);
		cb_phong.setEditable(true);
		if (dsPhong.size() > 0)
			cb_phong.setValue(dsPhong.get(0));
		new AutoCompleteComboBoxListener<String>(cb_phong);

		for (LichChieuPhim lcp : dsLichChieu) {
			MovieScheduleCard card = new MovieScheduleCard(lcp);
			schedulePane.getChildren().add(card);
//    		MenuItem edit = new MenuItem("Sửa");
			MenuItem delete = new MenuItem("Xóa");
			delete.setOnAction(E -> {
				Optional<ButtonType> result = AlertBox.show(AlertType.CONFIRMATION, "Xác nhận",
						"Bạn có thực sự muốn xóa lịch chiếu phim này?", MyButtonType.YesNo);
				if (result.get() == ButtonType.YES) {
					// xử lí xóa
					xuLiXoa(lcp);
					schedulePane.getChildren().remove(card);
				}
			});
//    		card.contextMenu.getItems().add(edit);
			if (!LoginController.taikhoan.getLoaiTaiKhoan().equals("user")) {
				card.contextMenu.getItems().add(delete);
			} else {
				card.image.setOnMouseClicked(e -> {
					if (LocalDate.parse(lcp.getNgayChieu()).compareTo(LocalDate.now()) < 0) {
						return;
					}
					if (e.getButton() == MouseButton.PRIMARY) {
						MyWindows bookTicket = new MyWindows("../view/BookTicket.fxml", card);
						bookTicket.Show();
					}
				});
			}
		}
	}

	private void xuLiXoa(LichChieuPhim lcp) {
		// TODO Auto-generated method stub
		new Connector().delete("delete from LICHCHIEUPHIM where MaLichChieu='" + lcp.getMaLichChieu() + "'");
	}

}
