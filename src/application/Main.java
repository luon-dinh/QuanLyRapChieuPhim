package application;
import Connector.Connector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import plugin.Helper;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../Images/ApplicationIcon.png")));
		try {
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Init.fxml")));
			primaryStage.setScene(scene);
			plugin.SceneController.GetInstance().init(primaryStage, scene);
			//plugin.SceneController.GetInstance().ReplaceScene("Main");
			plugin.SceneController.GetInstance().ReplaceScene("Login");
			primaryStage.centerOnScreen();
			primaryStage.sizeToScene();
			//primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Helper.Init();
		launch(args);
	}
}
