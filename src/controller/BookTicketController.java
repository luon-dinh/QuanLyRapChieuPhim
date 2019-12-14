package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import plugin.MyWindows;
import usercontrol.control.SelectableButton;

public class BookTicketController implements Initializable {
	private static final int chairsPreRow = 16;	
	@FXML private FlowPane pane;
	@FXML private Label ticketCounter;

	private IntegerProperty counter = new SimpleIntegerProperty(0);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		int chairs = (int) MyWindows.lastStage.getUserData();
		for (int i = 0; i < chairs; i++) {
			int r = i / chairsPreRow;
			int c = i % chairsPreRow;
			SelectableButton button = new SelectableButton();
			button.textProperty().set("" + (char) (65 + r) + (c + 1));
			pane.getChildren().add(button);
			button.isSelected.addListener((observable, oldValue, newValue)->{
				if (newValue)
					counter.set(counter.get() + 1);
				else
					counter.set(counter.get() - 1);
			});
		}
		ticketCounter.textProperty().bind(counter.asString());
	}

}
