package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;

public class AccountController implements Initializable {
	@FXML private Line line;
	@FXML private BorderPane pane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		line.startXProperty().bind(pane.getCenter().layoutXProperty().subtract(14));
		line.endXProperty().bind(pane.getCenter().layoutXProperty().subtract(14));
		line.setStartY(40);
		line.endYProperty().bind(pane.heightProperty().add(4));
	}
}
