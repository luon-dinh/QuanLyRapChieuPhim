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
	@FXML private Button schedule, movies, rooms, customer;
	@FXML private Text Tip1, Tip2, Tip3, Tip4;
	@FXML private GridPane pane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Tip1.wrappingWidthProperty().bind(schedule.widthProperty().add(-40));
		Tip2.wrappingWidthProperty().bind(movies.widthProperty().add(-40));
		Tip3.wrappingWidthProperty().bind(rooms.widthProperty().add(-40));
		Tip4.wrappingWidthProperty().bind(customer.widthProperty().add(-40));
	}
	
	@FXML
    void JumpToWork(ActionEvent event) {
		MainController.SelectedButton.set(((Button)event.getSource()).getUserData().toString());
    }
}