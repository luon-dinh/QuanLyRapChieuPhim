package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import plugin.SceneController;
import usercontrol.control.Chip;

public class AddNewMovieController implements Initializable{
    @FXML private ImageView image;
    @FXML private TextField name;
    @FXML private TextField year;
    @FXML private FlowPane genre;
    @FXML private ComboBox<String> newGenre;
    @FXML private TextField director;
    @FXML private TextField during;
    @FXML private TextArea summary;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		image.styleProperty().set("-fx-cursor: hand");
		newGenre.getItems().clear();
		ObservableList<String> list = FXCollections.observableArrayList();
		list.add("Ví dụ");
		newGenre.setItems(list);
		image.setOnMouseClicked(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.showOpenDialog(SceneController.GetInstance().getCurrentStage());
		});
	}    
	
	@FXML
    void genreKeyPress(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {			
			Chip chip = new Chip(newGenre.getValue());
			chip.setOnMouseClickedDelete(e->{
				genre.getChildren().remove(chip); 
			});
			genre.getChildren().add(chip);
			newGenre.setValue("");
		}
	}
}