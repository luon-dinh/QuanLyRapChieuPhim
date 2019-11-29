package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import usercontrol.control.AdvanceMenuFilterContent;
import usercontrol.control.MovieCard;

public class MoviesController implements Initializable {
	@FXML private AnchorPane root;
    @FXML private MenuButton advance;
    @FXML private FlowPane paneMovie;
    
    private AdvanceMenuFilterContent menuContent = new AdvanceMenuFilterContent();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		advance.getItems().add(new CustomMenuItem(menuContent, false));
		
		menuContent.Add("Thể loại", "Hài hước");
		menuContent.Add("Thể loại", "Hành động");
		menuContent.Add("Năm", "2018");
		
		paneMovie.prefWidthProperty().bind(root.widthProperty().subtract(20));
	}
	
	@FXML
	private void FindMovies(KeyEvent event) {
		if (event.getCode()==KeyCode.ENTER)
			paneMovie.getChildren().add(new MovieCard());
    }
}