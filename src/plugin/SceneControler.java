package plugin;

import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public final class SceneControler {
	private static SceneControler instance = null;

	private SceneControler() {
	};

	public static SceneControler GetInstance() {
		if (instance == null)
			instance = new SceneControler();
		return instance;
	}

	private HashMap<String, Pane> screenMap = new HashMap<>();
	private Scene main = null;
	private Stage primaryStage = null;

	public void init(Stage primaryStage, Scene mainScene) {
		main = mainScene;
		this.primaryStage = primaryStage;
	}

	public void ReplaceScene(String scene) throws NullSceneException {
		String location;
		if (scene.equals("Main"))
			location = "../application/Main.fxml";
		else
			location = "../" + Character.toLowerCase(scene.charAt(0)) + scene.substring(1) + "/" + scene + ".fxml";
		Pane replaceScene = screenMap.get(scene);
		try {
			if (replaceScene == null) {
				replaceScene = FXMLLoader.load(getClass().getResource(location));
				screenMap.put(scene, replaceScene);
			}
		} catch (Exception e) {
			throw new NullSceneException("Không tìm thấy scene " + scene + " tại " + location);
		}
		try {
			main.setRoot(replaceScene);
			primaryStage.sizeToScene();
			primaryStage.centerOnScreen();
		} catch (Exception e) {
			throw new NullSceneException("Scene chưa được khởi tạo");
		}
	}
	
	public void TryReplaceScene(String scene) {
		try {
			ReplaceScene(scene);
		} catch (NullSceneException e) {
			e.printStackTrace();
		}
	}
}