package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.MainController;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import usercontrol.control.MovieScheduleCard;

public class ScheduleController implements Initializable {
    @FXML private Line timeLine;
    @FXML private HBox schedulePane;

    @FXML
    void AddScheduleCard(ActionEvent event) {
    	MovieScheduleCard card = new MovieScheduleCard();
		card.deleteObject.addListener((observable, oldValue, newValue) -> {
			schedulePane.getChildren().remove(newValue);
		});
		if (schedulePane.getChildren().size() % 2 == 0)
		card.name.setText("đây là một cái tên phim siêu siêu dài này, dài vê lờ luôn á");
		else {
			card.name.setText("Phim tên ngắn");
		}
		schedulePane.getChildren().add(card);
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		timeLine.endXProperty().bind(Bindings.max(schedulePane.widthProperty(),MainController.mainPage.widthProperty().add(-105)));
		timeLine.translateYProperty().bind(schedulePane.heightProperty().add(-40));
	}

}
