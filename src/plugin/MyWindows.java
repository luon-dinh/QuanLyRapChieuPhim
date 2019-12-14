package plugin;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MyWindows {
	public static Stage lastStage = null;
	private Stage stage, holder;
	private Scene scene;
	private Pane root;
	
	private void init(String fxmlLocation, Object userData)
	{
		Pane pane = new Pane();
		scene = new Scene(pane);
		stage = new Stage();
		stage.setScene(scene);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setResizable(false);
		stage.initOwner(SceneController.GetInstance().getCurrentStage());
		stage.getIcons().add(ImagesControler.getInstance().tryGetImage("ApplicationIcon"));
		stage.setUserData(userData);
		holder = lastStage;
		lastStage = stage;
		
		try {
			root = FXMLLoader.load(getClass().getResource(fxmlLocation));
			scene.setRoot(root);
			if (root.getUserData()!=null)
				stage.setTitle(root.getUserData().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		stage.sizeToScene();
		stage.centerOnScreen();
	}
	
	public MyWindows(String fxmlLocation) {
		init(fxmlLocation, null);
	}
	
	public MyWindows(String fxmlLocation, Object userData) {
		init(fxmlLocation, userData);
	}
	
	public void Show() {
		stage.showAndWait();
		lastStage = holder;
	}
	
	public void Resize(double width, double height) {
		root.setMinWidth(width);
		root.setMinHeight(height);
		root.setMaxWidth(width);
		root.setMaxHeight(height);
	}
	
	public Object getUserData() {
		return stage.getUserData();
	}
	
	public void setUserData(Object value) {
		stage.setUserData(value);
	}
}
