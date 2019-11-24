package control;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class MovieScheduleCard extends AnchorPane {
	@FXML public ImageView image;
    @FXML public Text cost;
    @FXML public Text name;
    @FXML public Text numberSeats;
    @FXML public Label time;
	
	public MovieScheduleCard() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/MovieScheduleTopCard.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
