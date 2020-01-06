package application;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import usercontrol.control.ImageButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import plugin.*;

public class MainController implements Initializable {
	public static List<String> menu=new ArrayList<String>();// = { "Home", "Schedule", "Movies",  "Rooms", "Customer", "Service", "Account", "Statistic", "Staff" };
	public static Pane mainPage;
	private HashMap<String, ImageButton> buttons = new HashMap<>();	
	private static boolean updatOneTime=true;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		for (String item : menu) {
			ImageButton button = new ImageButton(item, false);
			buttons.put(item, button);
			MainMenu.getChildren().add(button);
		}
		
		for (ImageButton button : buttons.values()) {
			button.button.setOnAction(e->{
				String test = ((Button)e.getSource()).getUserData().toString();
				SelectedButton.set(test);
			});
		}
	
//		if(updatOneTime) {
			SelectedButton = new SimpleStringProperty("");
			SelectedButton.addListener((property, oldValue, newValue) -> {
//				if (oldValue.equals(newValue))
//					return;
				ImageButton button = buttons.get(oldValue);
				if (button != null)
					button.isSelected.set(false);
				button = buttons.get(newValue);
				if (button != null)
					button.isSelected.set(true);
				TryLoadNode(newValue);
			});
			updatOneTime=false;
//		}
		SelectedButton.set("Home");
		mainPage = MainPane;
	}
	
	@FXML private VBox MainMenu;
	@FXML private BorderPane MainPane;

	public static SimpleStringProperty SelectedButton;

	private HashMap<String, Node> map = new HashMap<>();
	private void LoadNode(String name) throws NullNodeException {
		Node replaceNode = map.get(name);
		if (replaceNode == null) {
			String location = Helper.FXMLlocation(name);
			try {
				replaceNode = FXMLLoader.load(getClass().getResource(location));
				map.put(name, replaceNode);
			} catch (IOException e) {
				throw new NullNodeException("Không tìm thấy Node " + name + " tại " + location);
			}
		}
		MainPane.centerProperty().set(replaceNode);
	}

	private void TryLoadNode(String name) {
		try {
			LoadNode(name);
		} catch (NullNodeException e) {
			e.printStackTrace();
		}
	}
}
