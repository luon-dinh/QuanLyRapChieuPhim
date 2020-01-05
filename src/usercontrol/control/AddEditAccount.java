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
import Model.PhongChieuPhim;
import Model.TaiKhoan;
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

public class AddEditAccount {
	private Stage stage;
	private Scene scene;
	private GridPane grid = new GridPane();
	private HashMap<String, TextField> map = new HashMap<>();
	private HashMap<String, ComboBox> mapComboBox = new HashMap<>();
	private HashMap<String, ImageView> mapImageView = new HashMap<>();
	public ButtonType boxReturn = null;
	private int sumSize=0;
	public File f;
	private boolean isShow=false;
	
	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	public AddEditAccount(String title) {
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
		
		ok.setOnAction(e->{
			boxReturn = ButtonType.OK;
			stage.close();
		});
		cancel.setOnAction(e->{
			boxReturn = ButtonType.CANCEL;
			stage.close();
		});
		
		stage.initOwner(SceneController.GetInstance().getCurrentStage());
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setResizable(false);
	}
	
	public AddEditAccount(TaiKhoan tk) {
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
		
		ok.setOnAction(e->{
			boxReturn = ButtonType.OK;
			stage.close();
		});
		cancel.setOnAction(e->{
			boxReturn = ButtonType.CANCEL;
			stage.close();
		});
		
		stage.initOwner(SceneController.GetInstance().getCurrentStage());
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setResizable(false);
	}
	
	public void show() {
		isShow=true;
		stage.showAndWait();
		isShow=false;
	}
	
	
	public void addImageView(String content, Image image) {
		grid.add(new Label(content), 0, sumSize);
		ImageView imgv=new ImageView(image);
		imgv.setFitWidth(80);
		imgv.setFitHeight(80);
		imgv.setOnMouseClicked(e->{
			FileChooser fileChooser = new FileChooser();
			//f=fileChooser.showOpenDialog(SceneController.GetInstance().getCurrentStage());
			f=fileChooser.showOpenDialog(MyWindows.lastStage);
			if(f!=null) {
				try {
					BufferedImage bimg=ImageIO.read(f);
					Image img=SwingFXUtils.toFXImage(bimg, null);
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
	
	public ComboBox getComboBox(String contenKey) {
		return mapComboBox.get(contenKey);
	}
	
	public void Add(String content) {
		grid.add(new Label(content), 0, sumSize);
		TextField textField = new TextField();
		textField.setPromptText(content);
		grid.add(textField, 1,sumSize);
		map.put(content, textField);
		sumSize++;
	}
	
	public void Add(String content,boolean b) {
		grid.add(new Label(content), 0, sumSize);
		TextField textField = new TextField();
		textField.setPromptText(content);
		if(b==true) {
			textField.setOnKeyTyped(e -> {
	            char input = e.getCharacter().charAt(0);
	            if (Character.isDigit(input) != true) {
	                e.consume();
	            }
	        });
		}
		grid.add(textField, 1,sumSize);
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
