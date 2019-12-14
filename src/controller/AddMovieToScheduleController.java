package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.FlowPane;
import plugin.MyWindows;
import usercontrol.control.AdvanceMenuFilterContent;
import usercontrol.control.MovieCard;

public class AddMovieToScheduleController implements Initializable{
    @FXML private MenuButton advance;
    @FXML private FlowPane paneMovie;
    @FXML private ScrollPane root;
    
    @FXML void FindMovies(KeyEvent event) {
    	if (event.getCode() == KeyCode.ENTER)
    	{
    		MovieCard card = new MovieCard();
    		card.rattingBar.readOnly.set(true);
    		card.cursorProperty().set(Cursor.HAND);
    		card.setOnMouseClicked(e->{
				if (e.getButton() == MouseButton.PRIMARY)
				{
					MyWindows.lastStage.setUserData(card);
					MyWindows.lastStage.close();
				}
    		});
    		paneMovie.getChildren().add(card);
    	}
    }

    private AdvanceMenuFilterContent menuContent = new AdvanceMenuFilterContent();
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		advance.getItems().add(new CustomMenuItem(menuContent, false));

		menuContent.Add("Thể loại", "Hài hước");
		menuContent.Add("Thể loại", "Hành động");
		menuContent.Add("Năm", "2018");

		paneMovie.prefWidthProperty().bind(root.widthProperty().subtract(10));
	}
}
