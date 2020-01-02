package usercontrol.control;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.beans.binding.Bindings;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class AdvanceMenuFilterContent extends AnchorPane{	
	private class MenuNode extends VBox {
		private FlowPane content = new FlowPane(5d, 5d);
		private HashMap<String, CheckBox> map = new HashMap<>();

		public MenuNode(String name) {
			super();
			if (name != null) {
				Label label = new Label(name);
				label.styleProperty().bind(Bindings.when(label.hoverProperty())
						.then("-fx-text-fill: black;")
						.otherwise("-fx-text-fill: black;"));
				super.getChildren().add(label);
			}
			super.getChildren().add(content);
		}
		
		public void Add(String content) {
			if (map.get(content) != null)
				return;
			CheckBox child = new CheckBox(content);
			child.styleProperty().bind(Bindings.when(child.hoverProperty())
					.then("-fx-text-fill: black;")
					.otherwise("-fx-text-fill: black;"));
			map.put(content, child);
			this.content.getChildren().add(child);
		}

		public Boolean IsCheck(String content) {
			CheckBox checkBox = map.get(content);
			if (checkBox == null)
				return null;
			return new Boolean(checkBox.isSelected());
		}
		
		public ArrayList<String> getChecked() {
			ArrayList<String> ret = new ArrayList<>();
			map.forEach((key, value) -> {
				if (value.isSelected())
					ret.add(key);
			});
			return ret;
		}
	}

	private HashMap<String, MenuNode> map = new HashMap<>();
	private VBox pane = new VBox(10d);

	public AdvanceMenuFilterContent() {
		super();
		super.getChildren().add(pane);
	}

	public void Add(String label, String content) {
		try {
			MenuNode node = map.get(label);
			if (node == null) {
				node = new MenuNode(label);
				map.put(label, node);
				pane.getChildren().add(node);
			}
			node.Add(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean IsCheck(String label, String content) {
		return map.get(label).IsCheck(content);
	}
}
