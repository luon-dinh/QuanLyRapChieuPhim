package usercontrol.control;

import java.io.IOException;

import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class Chip extends HBox {
    @FXML private Label content;
    @FXML private ImageView DeleteButton;
    
    private void init() {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Chip.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		content.styleProperty().bind(Bindings.when(content.hoverProperty()).then("-fx-text-fill: black").otherwise("-fx-text-fill: black"));
    }
    
	public Chip() {
		init();
	}
	
	public Chip(String content) {
		init();
		getTextProperty().set(content);
	}
    
    public StringProperty getTextProperty() {
    	return content.textProperty();
    }
    
	public final void setOnMouseClickedDelete(EventHandler<? super MouseEvent> value) {
		DeleteButton.setOnMouseClicked(value);
	}
}
