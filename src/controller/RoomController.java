package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import plugin.MyWindows;
import usercontrol.control.RoomCard;

public class RoomController implements Initializable{

    @FXML private AnchorPane root;
    @FXML private FlowPane paneRoom;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		paneRoom.prefWrapLengthProperty().bind(root.widthProperty());
	}

	@FXML
	private void FindRooms(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER)
			paneRoom.getChildren().add(new RoomCard());
    }
	
	@FXML
    void AddNewRoomAction(ActionEvent event) {
		MyWindows w = new MyWindows("../view/AddNewRoom.fxml");
		w.Show();
    }
}
