package usercontrol.control;

import java.io.IOException;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ImageButton extends AnchorPane implements Comparable<ImageButton> {
	@FXML private ImageView imageView;
	@FXML public Button button;
	public SimpleBooleanProperty isSelected = new SimpleBooleanProperty();
	private Image nonSelected, selected;

	private static int counter = 0;
	public int index;

	public ImageButton(String name, boolean isSelected) {
		index = counter;
		counter += 1;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ImageButton.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		selected = plugin.ImagesControler.getInstance().tryGetImage(name);
		nonSelected = plugin.ImagesControler.getInstance().tryGetImage(name + "Invert");

		this.isSelected.addListener((observable, oldValue, newValue) -> {
			if (oldValue == newValue)
				return;
			if (newValue) {
				imageView.setImage(selected);
				button.styleProperty().unbind();
				button.setStyle("-fx-background-color: -fx-background");
			} else {
				imageView.setImage(nonSelected);
				button.styleProperty().bind(Bindings.when(button.hoverProperty()).then("-fx-background-color: #c7c7c7")
						.otherwise("-fx-background-color: transparent"));
			}
		});
		button.setUserData(name);
		this.isSelected.setValue(!isSelected);
		this.isSelected.setValue(isSelected);
	}

	@Override
	public int compareTo(ImageButton o) {
		return index - o.index;
	}
}
