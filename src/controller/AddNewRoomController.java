package controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AddNewRoomController {
    @FXML private ImageView image;
    @FXML private TextField title;
    @FXML private TextField capacity;
    @FXML private TextField chairs;
    @FXML private TextField status;
    @FXML private TextArea description;
    
    public ObjectProperty<Image> imageProperty() {
    	return image.imageProperty();
    }
    public StringProperty titleProperty() {
    	return title.textProperty();
    }
    public StringProperty capacityProperty() {
    	return capacity.textProperty();
    }
    public StringProperty chairsProperty() {
    	return chairs.textProperty();
    }
    public StringProperty statusProperty() {
    	return status.textProperty();
    }
    public StringProperty descriptionProperty() {
    	return description.textProperty();
    }
}
