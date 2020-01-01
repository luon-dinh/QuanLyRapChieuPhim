package usercontrol.control;

import java.io.IOException;

import Model.SanPham;
import controller.ServiceController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class CartItem extends BorderPane {
	@FXML private Text name;
    @FXML private Text description;
    @FXML private ImageView image;
    @FXML private HBox bindPane;
    @FXML private Label _cost;
    @FXML private Label _number;

    public SanPham sp=null;
    public int nb=0;
    private IntegerProperty number = new SimpleIntegerProperty(1);
    private IntegerProperty cost = new SimpleIntegerProperty();
    private SimpleIntegerProperty sumCost = new SimpleIntegerProperty();
    
    private void init() {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/CartItem.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		_number.textProperty().bind(number.asString());
		_cost.textProperty().bind(cost.asString());
		_cost.styleProperty().bind(Bindings.when(_cost.hoverProperty())
				.then("-fx-text-fill: black;")
				.otherwise("-fx-text-fill: black;"));
		sumCost.bind(Bindings.multiply(number, cost));
		name.wrappingWidthProperty().bind(Bindings.subtract(321, bindPane.widthProperty()));
		description.wrappingWidthProperty().bind(Bindings.subtract(351, bindPane.widthProperty()));
    }
    
    public CartItem() {
    	init();
	}
    
    public CartItem(SellingCard card) {
    	init();
    	ImageProperty().set(card.imageProperty().get());
    	NameProperty().set(card.nameProperty().get());
    	DescriptionProperty().set(card.descriptionProperty().get());
    	cost.set(card.costProperty().get());
    }

	@FXML void decrease(ActionEvent event) {
		number.set(number.get() - 1);
		if(number.get()==0) {
			ServiceController.cartItems.remove(this);
		}
    }

	@FXML void increase(ActionEvent event) {
		number.set(number.get() + 1);
    }
	
	public void reset() {
		number.set(number.get()+1);
	}
	
	public ReadOnlyIntegerProperty SumCostProperty() {
		return sumCost;
	}
	
	public ObjectProperty<Image> ImageProperty() {
		return image.imageProperty();
	}

	public StringProperty NameProperty() {
		return name.textProperty();
	}
	
	public StringProperty DescriptionProperty() {
		return description.textProperty();
	}
	
	public IntegerProperty NumberProperty() {
		return number;
	}
	
	public IntegerProperty CostProperty() {
		return cost;
	}
}
