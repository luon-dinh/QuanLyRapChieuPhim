package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import Connector.Connector;
import Model.Phim;
import Model.Phim_LoaiPhim;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import plugin.AlertBox;
import plugin.MyWindows;
import plugin.AlertBox.MyButtonType;
import usercontrol.control.AdvanceMenuFilterContent;
import usercontrol.control.MovieCard;

public class MoviesController implements Initializable {
	@FXML private AnchorPane root;
    @FXML private MenuButton advance;
    @FXML private FlowPane paneMovie;
    @FXML private TextField condition;
    @FXML private Button btn_refresh, btn_timkiem;
    
    private AdvanceMenuFilterContent menuContent = new AdvanceMenuFilterContent();
    private ArrayList<Phim> dsPhim;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initial(null);
		addEvents();
	}
	
	private void addEvents() {
		// TODO Auto-generated method stub
		btn_timkiem.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String cond=condition.getText();
				xuLiTimKiem(cond);
			}
		});
		
		btn_refresh.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				condition.setText("");
			}
		});
		
		condition.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				xuLiTimKiem(newValue);
			}
		});
		
	}

	private void initial(ArrayList<Phim> ps) {
		// TODO Auto-generated method stub
		paneMovie.getChildren().removeAll(paneMovie.getChildren());
		dsPhim=new ArrayList<Phim>();
		dsPhim.addAll(new Connector().selectPhim( "select * from PHIM"));
		ArrayList<Phim> temp=new ArrayList<Phim>();
		if(ps==null) {
			temp=dsPhim;
		}
		else {
			temp=ps;
		}
		
		for(Phim p:temp) {
			MovieCard card=new MovieCard(p);
			MenuItem edit = new MenuItem("Sửa");
			edit.setOnAction(e->{
				
			});
			MenuItem delete = new MenuItem("Xóa");
			delete.setOnAction(e -> {
				Optional<ButtonType> result =AlertBox.show(AlertType.CONFIRMATION, "Xác nhận", "Bạn có thực sự muốn xóa bộ phim này?", MyButtonType.YesNo);
				if(result.get()==ButtonType.YES) {
					new Connector<Phim>().delete("delete from PHIM where MaPhim='"+p.getMaPhim()+"'");
					new Connector<Phim_LoaiPhim>().delete("delete from PHIM_LOAIPHIM where MaPhim='"+p.getMaPhim()+"'");
					paneMovie.getChildren().remove(card);
				}
			});
			card.menu.getItems().add(edit);
			card.menu.getItems().add(delete);
			paneMovie.getChildren().add(card);
		}
		advance.getItems().add(new CustomMenuItem(menuContent, false));
		
		menuContent.Add("Thể loại", "Hài hước");
		menuContent.Add("Thể loại", "Hành động");
		menuContent.Add("Năm", "2018");
		
		paneMovie.prefWidthProperty().bind(root.widthProperty().subtract(20));
		
	}

	@FXML private void FindMovies(KeyEvent event) {
		//xử lí lọc
		if (event.getCode()==KeyCode.ENTER) {
			String cond=condition.getText();
			xuLiTimKiem(cond);
		}
    }
	
	private void xuLiTimKiem(String cond) {
		// TODO Auto-generated method stub
		ArrayList<Phim> ds=new ArrayList<Phim>();
		cond=cond.toLowerCase();
		for(Phim p:dsPhim) {
			if(p.getTenPhim().toLowerCase().contains(cond)) {
				ds.add(p);
			}
		}
		initial(ds);
	}

	@FXML void AddNewMovie(ActionEvent event) {
		MyWindows w = new MyWindows("../view/AddNewMovie.fxml");
		w.Show();
		initial(null);
    }
}