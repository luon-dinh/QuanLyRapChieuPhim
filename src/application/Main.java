package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../Images/ApplicationIcon.png")));
		primaryStage.setTitle("Login");
		try {
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../login/Login.fxml")));
			//Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Main.fxml")));
			plugin.SceneControler.GetInstance().init(primaryStage, scene);
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.sizeToScene();
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
