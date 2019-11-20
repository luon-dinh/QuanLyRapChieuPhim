package plugin;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertBox {
	public static void show(AlertType type, String title) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.show();
	}

	public static void show(AlertType type, String title, String header) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.show();
	}

	public static void show(AlertType type, String title, String header, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
	}
}
