package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Connector.Connector;
import Model.SanPham;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
	@FXML private Button btn_timkiem, btn_refresh;
	
	public static ArrayList<CartItem> cartItems=new ArrayList<CartItem>();
	private ArrayList<SanPham> dsSanPham;
	private IntegerProperty number = new SimpleIntegerProperty(0);
	private IntegerProperty cost = new SimpleIntegerProperty(0);
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		initial(null);
		addEvents();
		styleControls();
	}
	
	private void styleControls() {
		// TODO Auto-generated method stub
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

	private void addEvents() {
		// TODO Auto-generated method stub
		btn_timkiem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String condition=search.getText();
				xuLiTimKiem(condition);
			}
		});
		
		btn_refresh.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
			search.setText("");
			}
		});
		
		search.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				xuLiTimKiem(newValue);
			}
		});
		
	}

	private void initial(ArrayList<SanPham> sps) {
		// TODO Auto-generated method stub
		pane.getChildren().removeAll(pane.getChildren());
		ArrayList<SanPham> temp=null;
		if(sps==null) {
			dsSanPham=new ArrayList<SanPham>();
			dsSanPham.addAll(new Connector<SanPham>().select(SanPham.class, "select * from SANPHAM"));
			temp=dsSanPham;
		}
		else {
			temp=sps;
		}
		for(SanPham sp:temp) {
			addNewCard(sp);
		}
	}

	@FXML
    void SearchKeyPress(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			//xử lí tìm kiếm
			String condition=search.getText();
			xuLiTimKiem(condition);
		}
    }
	
	private void xuLiTimKiem(String condition) {
		// TODO Auto-generated method stub
		ArrayList<SanPham> sps=new ArrayList<SanPham>();
		condition=condition.toLowerCase();
		for(SanPham sp:dsSanPham) {
			if(sp.getTenSanPham().toLowerCase().contains(condition)) {
				sps.add(sp);	
			}
		}
		initial(sps);
	}

	public void addNewCard(SanPham sp) {
		SellingCard card = new SellingCard();
		card.sp=sp;
		if(sp!=null) {
			card.costProperty().set(sp.getGiaSanPham());
			card.nameProperty().set(sp.getTenSanPham());
			card.descriptionProperty().set(sp.getMoTa());
		}
		else {
			card.costProperty().set(10000);
			card.nameProperty().set("Bỏng ngô");
			card.descriptionProperty().set("Thơm ngon nức mũi");
		}
		card.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY) {
					boolean hasitem=false;
					for(CartItem it:cartItems) {
						if(it.sp.getMaSanPham().equals(card.sp.getMaSanPham())) {
							it.reset();
							hasitem=true;
							break;
						}
					}
					if(!hasitem) {
						CartItem item = new CartItem(card);
						item.sp=card.sp;
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
						cartItems.add(item);
						cart.getChildren().add(item);
					}
				}
			}
		});
		pane.getChildren().add(card);
	}
	@FXML
    void AddNewServive(ActionEvent event) {
		MyWindows w = new MyWindows("../view/AddNewService.fxml");
		w.Show();
		initial(null);
    }
}
