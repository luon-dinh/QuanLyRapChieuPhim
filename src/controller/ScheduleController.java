package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.MainController;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import plugin.MyWindows;
import usercontrol.control.MovieCard;
import usercontrol.control.MovieScheduleCard;

public class ScheduleController implements Initializable {
    @FXML private Line timeLine;
    @FXML private HBox schedulePane;

    @FXML
    void AddScheduleCard(ActionEvent event) {
    	MyWindows w = new MyWindows("../view/AddMovieToSchedule.fxml");
    	w.Resize(940, 600);
    	w.Show();
    	MovieScheduleCard card = new MovieScheduleCard();
    	try {
    		card.setInfo((MovieCard) w.getUserData());
    		schedulePane.getChildren().add(card);
    		card.image.setOnMouseClicked(e->{
				if (e.getButton() == MouseButton.PRIMARY) {
					MyWindows bookTicket = new MyWindows("../view/BookTicket.fxml", 50);
    				bookTicket.Show();
    			}
    		});
		} catch (NullPointerException e) {}    		
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		timeLine.endXProperty().bind(Bindings.max(schedulePane.widthProperty(),MainController.mainPage.widthProperty().add(-105)));
		timeLine.translateYProperty().bind(schedulePane.heightProperty().add(-40));
	}
}
