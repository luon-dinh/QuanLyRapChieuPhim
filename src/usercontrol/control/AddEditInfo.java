package usercontrol.control;

import java.util.Collection;
import java.util.HashMap;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class AddEditInfo {
	private Stage stage;
	private Scene scene;
	private GridPane grid = new GridPane();
	private HashMap<String, TextField> map = new HashMap<>();
	
	public AddEditInfo(String title) {
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		ColumnConstraints c1 = new ColumnConstraints();
		c1.setHgrow(Priority.ALWAYS);
		ColumnConstraints c2 = new ColumnConstraints();
		c2.setHgrow(Priority.ALWAYS);
		c2.setMinWidth(300);
		grid.getColumnConstraints().add(c1);
		grid.getColumnConstraints().add(c2);
		scene = new Scene(grid);
		stage = new Stage();
		stage.setTitle(title);
		stage.setScene(scene);
	}
	
	public void show() {
		stage.show();
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
