package usercontrol.control;

import java.io.IOException;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContextMenu;
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
    @FXML private ContextMenu contextMenu;
    
    public SimpleObjectProperty<MovieScheduleCard> deleteObject = new SimpleObjectProperty<>(null);
	
	public MovieScheduleCard() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/MovieScheduleCard.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		image.setOnContextMenuRequested(e -> {
			contextMenu.show(this, e.getScreenX(), e.getScreenY());
		});
	}
	
	@FXML
    void delete(ActionEvent event) {
		deleteObject.setValue(this);		
    }
}
