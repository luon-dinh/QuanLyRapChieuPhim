package usercontrol.control;

import java.util.Collection;
import java.util.HashMap;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import plugin.ImagesControler;
import plugin.SceneController;

public class AddEditInfo {
	private Stage stage;
	private Scene scene;
	private GridPane grid = new GridPane();
	private HashMap<String, TextField> map = new HashMap<>();
	public ButtonType boxReturn = null;
	
	public AddEditInfo(String title) {
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(10, 10, 10, 10));
		grid.setHgap(10);
		grid.setVgap(10);
		ColumnConstraints c1 = new ColumnConstraints();
		c1.setHgrow(Priority.ALWAYS);
		ColumnConstraints c2 = new ColumnConstraints();
		c2.setHgrow(Priority.ALWAYS);
		c2.setMinWidth(300);
		grid.getColumnConstraints().add(c1);
		grid.getColumnConstraints().add(c2);
		pane.centerProperty().set(grid);
		
		
		scene = new Scene(pane);
		stage = new Stage();
		stage.setTitle(title);
		stage.setScene(scene);
		stage.getIcons().add(ImagesControler.getInstance().tryGetImage("ApplicationIcon"));
		
		AnchorPane anchor = new AnchorPane();
		pane.bottomProperty().set(anchor);
		HBox box = new HBox(10f);
		box.setPadding(new Insets(10, 0, 0, 0));
		AnchorPane.setRightAnchor(box, 0d);
		anchor.getChildren().add(box);
		Button ok = new Button("Đồng ý");
		ok.setStyle("-fx-pref-width: 80.0;");
		Button cancel = new Button("Hủy");
		cancel.setStyle("-fx-pref-width: 80.0;");
		box.getChildren().add(ok);
		box.getChildren().add(cancel);
		
		ok.setOnAction(e->{
			boxReturn = ButtonType.OK;
			stage.close();
		});
		cancel.setOnAction(e->{
			boxReturn = ButtonType.CANCEL;
			stage.close();
		});
		
		stage.initOwner(SceneController.GetInstance().getCurrentStage());
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setResizable(false);
	}
	
	public void show() {
		stage.showAndWait();
	}
	
	public void Add(String content) {
		grid.add(new Label(content), 0, map.size());
		TextField textField = new TextField();
		grid.add(textField, 1, map.size());
		map.put(content, textField);
	}
	
	public void AddAll(String[] contents) {
		for (String item : contents)
			Add(item);
	}
	
	public void AddAll(Collection<String> contents) {
		for (String item : contents)
			Add(item);
	}
	
	public TextField Get(String contentKey) {
		return map.get(contentKey);
	}
}
