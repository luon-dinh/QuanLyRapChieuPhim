package plugin;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class AlertBox {
	public static void show(AlertType type, String title) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.showAndWait();
	}

	public static void show(AlertType type, String title, String header) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.showAndWait();
	}

	public static void show(AlertType type, String title, String header, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public enum MyButtonType { YesNo, ApplyCancel }
	public static Optional<ButtonType> show(AlertType type, String title, String header, MyButtonType buttonType) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.getButtonTypes().clear();
		switch (buttonType) {
		case YesNo:
			alert.getButtonTypes().add(ButtonType.YES);
			alert.getButtonTypes().add(ButtonType.NO);
			break;
		case ApplyCancel:
			alert.getButtonTypes().add(ButtonType.APPLY);
			alert.getButtonTypes().add(ButtonType.CANCEL);
			break;
		default:
			break;
		}
		return alert.showAndWait();
	}
	
	public static void setOnTop(boolean isOnTop)
	{
		
	}
}