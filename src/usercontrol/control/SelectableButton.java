package usercontrol.control;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;

public class SelectableButton extends Button {
	public int maGhe;
	public BooleanProperty isSelected = new SimpleBooleanProperty(false);
	
	public SelectableButton(int maGhe) {
		super();
		this.maGhe=maGhe;
		super.getStylesheets().add(getClass().getResource("../../css/ButtonChair.css").toExternalForm());
		super.getStyleClass().add("button");
		
		super.styleProperty().bind(Bindings.when(isSelected)
				.then("-fx-background-color:gray")
				.otherwise(""));
		
		super.setOnMouseClicked(e -> {
			isSelected.set(!isSelected.get());
		});
	}
}
