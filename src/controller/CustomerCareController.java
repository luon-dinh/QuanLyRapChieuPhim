package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import usercontrol.control.ChatMessage;
import usercontrol.control.ChatMessage.MessageType;

public class CustomerCareController implements Initializable {
    @FXML private TextArea text;
    @FXML private ScrollPane pane;
    @FXML private VBox box;

	@FXML void Send(MouseEvent event) {
		ChatMessage chat;
		if ((box.getChildren().size()&1)==0)
			chat = new ChatMessage(MessageType.Send);
		else
			chat = new ChatMessage(MessageType.Receive);
		chat.text.setText("Ví dụ về đoạn chat");
		box.getChildren().add(chat);
		chat.Bindings(pane.widthProperty());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		box.prefWidthProperty().bind(Bindings.when(box.heightProperty().greaterThan(pane.heightProperty()))
				.then(pane.widthProperty())
				.otherwise(pane.widthProperty().subtract(20)));
	}

}
