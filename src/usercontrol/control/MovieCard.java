package usercontrol.control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

public class MovieCard extends AnchorPane implements Initializable {	
	@FXML private ImageView image;
	@FXML private Text title;
	@FXML private FlowPane category;
	@FXML private FlowPane director;
	@FXML private Text length;
	@FXML private Text sumary;
	@FXML private BorderPane ratting;

	public MovieCard() {
		super();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/MovieCard.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ratting.setCenter(new RattingBar());
	}
}
