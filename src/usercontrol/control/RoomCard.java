package usercontrol.control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import plugin.AlertBox;
import plugin.MyWindows;
import plugin.AlertBox.MyButtonType;

public class RoomCard extends AnchorPane implements Initializable{ 
	@FXML private ImageView image;
    @FXML public Text title, capacity, chairs, status, description;
	private ContextMenu menu;
	public RoomCard() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/RoomCard.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		image.setOnContextMenuRequested(e -> {
			menu.show(this, e.getScreenX(), e.getScreenY());
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		menu = new ContextMenu();
		MenuItem edit = new MenuItem("Sửa");
		edit.setOnAction(e->{
			MyWindows w = new MyWindows("../view/AddNewRoom.fxml");
			w.Show();
		});
		MenuItem delete = new MenuItem("Xóa");
		delete.setOnAction(e -> {
			AlertBox.show(AlertType.CONFIRMATION, "Xác nhận", "Bạn có thực sự muốn xóa bộ phim này?", MyButtonType.YesNo);
		});
		menu.getItems().add(edit);
		menu.getItems().add(delete);
	}
}
