package plugin;

import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public final class SceneController {
	private static SceneController instance = null;

	private SceneController() {
	};

	public static SceneController GetInstance() {
		if (instance == null)
			instance = new SceneController();
		return instance;
	}

	private HashMap<String, Pane> screenMap = new HashMap<>();
	private Scene main = null;
	private Stage primaryStage = null;

	public Stage getCurrentStage() {
		return primaryStage;
	}

	public void init(Stage primaryStage, Scene mainScene) {
		main = mainScene;
		this.primaryStage = primaryStage;
	}

	public void ReplaceScene(String scene) throws Exception {
		String location;
		if (scene.equals("Main"))
			location = "../application/Main.fxml";
		else
			location = Helper.FXMLlocation(scene);
		Pane replaceScene = screenMap.get(scene);
		try {
			if (replaceScene == null) {
				replaceScene = FXMLLoader.load(getClass().getResource(location));
				if (!scene.equals("Main"))
					screenMap.put(scene, replaceScene);
			}
		} catch (Exception e) {
			throw new NullSceneException("Không thể load scene " + scene + " tại " + location);
		}
		try {
			main.setRoot(replaceScene);
			primaryStage.sizeToScene();
			primaryStage.centerOnScreen();
		} catch (Exception e) {
			throw new NullSceneException("Scene chưa được khởi tạo");
		}
		if (replaceScene.getUserData()==null)
			throw new Exception("Chưa đặt tên cho Scene");
		else
			primaryStage.setTitle(replaceScene.getUserData().toString());
	}
	
	public void TryReplaceScene(String scene) {
		try {
			ReplaceScene(scene);
		} catch (NullSceneException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}