package usercontrol.control;

import java.io.IOException;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ChatMessage extends AnchorPane {
    @FXML public ImageView image;
    @FXML public Label name;
    @FXML public Text text;

	public enum MessageType { Send, Receive }
    
    public ChatMessage(MessageType type) {
		FXMLLoader fxmlLoader;
		if (type == MessageType.Send)
			fxmlLoader = new FXMLLoader(getClass().getResource("../view/ChatMessageSend.fxml"));
		else
			fxmlLoader = new FXMLLoader(getClass().getResource("../view/ChatMessageReceive.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public void Bindings(ReadOnlyDoubleProperty root) {
    	text.setWrappingWidth(root.get() - 160);
		root.addListener((Observable, OldValue, NewValue) -> {
			text.setWrappingWidth((double) NewValue - 160);
		});
    }
}
