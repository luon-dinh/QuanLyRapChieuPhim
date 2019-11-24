package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class HomeController implements Initializable {
	@FXML private Button schedule;
	@FXML private Text scheduleTip;
	@FXML private GridPane pane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		scheduleTip.wrappingWidthProperty().bind(schedule.widthProperty().add(-40));
		pane.widthProperty().addListener((a, b, c)->{
			System.out.println(c);
		});
	}
	
	@FXML
    void JumpToWork(ActionEvent event) {
		MainController.SelectedButton.set(((Button)event.getSource()).getUserData().toString());
    }
}