package usercontrol.control;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class RoomCard extends AnchorPane { 
	@FXML
    private ImageView image;

    @FXML public Text title, capacity, chairs, status, description;

	public RoomCard() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/RoomCard.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
