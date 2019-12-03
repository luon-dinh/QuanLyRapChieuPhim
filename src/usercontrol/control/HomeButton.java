package usercontrol.control;

import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import plugin.Helper;

public class HomeButton extends AnchorPane {
	@FXML private ImageView image;
	@FXML private Label name;
	@FXML private Text description;
	@FXML private Button button;
	
	public SimpleStringProperty nameProperty = new SimpleStringProperty();
	public SimpleStringProperty descriptionProperty = new SimpleStringProperty();

	public HomeButton(String name) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/HomeButton.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.setUserData(name);
		image.setImage(plugin.ImagesControler.getInstance().tryGetImage(name));
		nameProperty.set(Helper.HomeMenuInfo.get(name));
		descriptionProperty.set(Helper.HomeMenuDescription.get(name));
		this.name.textProperty().bind(nameProperty);
		description.textProperty().bind(descriptionProperty);
		description.wrappingWidthProperty().bind(button.widthProperty().subtract(25));
		
		button.setOnAction(e->{
			application.MainController.SelectedButton.set(super.getUserData().toString());
		});
	}
}
