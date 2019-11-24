package controller;

import java.net.URL;
import java.util.ResourceBundle;

import control.MovieScheduleCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;

public class ScheduleController implements Initializable {

    @FXML
    private Line timeLine;

    @FXML
    private HBox schedulePane;

    @FXML
    void AddScheduleCard(ActionEvent event) {
    	schedulePane.getChildren().add(new MovieScheduleCard());
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		timeLine.endXProperty().bind(schedulePane.widthProperty());
		timeLine.translateYProperty().bind(schedulePane.heightProperty().add(-40));
	}

}
