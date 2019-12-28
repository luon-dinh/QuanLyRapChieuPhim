package controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.SanPham;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import plugin.MyWindows;
import usercontrol.control.CartItem;
import usercontrol.control.SellingCard;

public class ServiceController implements Initializable {
	@FXML private CheckBox food, water, combo;
	@FXML private Label text0, SumCost;
	@FXML private VBox cart;
	@FXML private TextField search;
	@FXML private FlowPane pane;
	@FXML private ScrollPane scroll;
	@FXML private MenuButton cartString;

	private IntegerProperty number = new SimpleIntegerProperty(0);
	private IntegerProperty cost = new SimpleIntegerProperty(0);
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		food.styleProperty().bind(Bindings.when(food.hoverProperty())
				.then("-fx-text-fill: black;")
				.otherwise("-fx-text-fill: black;"));
		water.styleProperty().bind(Bindings.when(water.hoverProperty())
				.then("-fx-text-fill: black;")
				.otherwise("-fx-text-fill: black;"));
		combo.styleProperty().bind(Bindings.when(combo.hoverProperty())
				.then("-fx-text-fill: black;")
				.otherwise("-fx-text-fill: black;"));
		text0.styleProperty().bind(Bindings.when(combo.hoverProperty())
				.then("-fx-text-fill: black;")
				.otherwise("-fx-text-fill: black;"));
		SumCost.styleProperty().bind(Bindings.when(combo.hoverProperty())
				.then("-fx-text-fill: black;")
				.otherwise("-fx-text-fill: black;"));
		pane.minWidthProperty().bind(scroll.widthProperty().subtract(20));
		pane.maxWidthProperty().bind(scroll.widthProperty().subtract(20));
		SumCost.textProperty().bind(cost.asString());

		number.addListener((observable, oldValue, newValue) -> {
			if ((int) newValue == 0)
				cartString.textProperty().set("Giỏ hàng");
			else
				cartString.textProperty().set("Giỏ hàng (" + newValue + ")");
		});
	}
	
	@FXML
    void SearchKeyPress(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			//xử lí tìm kiếm
		}
    }
	
	private void addNewCard(SanPham sp) {
		SellingCard card = new SellingCard();
		card.costProperty().set(sp.getGiaSanPham());
		card.nameProperty().set(sp.getTenSanPham());
		card.descriptionProperty().set(sp.getMoTa());
		card.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY) {
					CartItem item = new CartItem(card);
					item.NumberProperty().addListener((observable, oldValue, newValue) -> {
						number.set(number.get() - (int) oldValue + (int) newValue);
						if ((int) newValue == 0)
							cart.getChildren().remove(item);
					});
					item.SumCostProperty().addListener((observable, oldValue, newValue) -> {
						cost.set(cost.get() - (int) oldValue + (int) newValue);
					});
					number.set(number.get() + 1);
					cost.set(cost.get() + card.costProperty().get());
					cart.getChildren().add(item);
				}
			}
		});
		pane.getChildren().add(card);
	}
	@FXML
    void AddNewServive(ActionEvent event) {
		MyWindows w = new MyWindows("../view/AddNewService.fxml");
		w.Show();
    }
}
