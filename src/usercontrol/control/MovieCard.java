package usercontrol.control;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import Connector.Connector;
import Model.LoaiPhim;
import Model.Phim;
import Model.Phim_LoaiPhim;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import plugin.AlertBox;
import plugin.AlertBox.MyButtonType;

public class MovieCard extends AnchorPane implements Initializable {	
	@FXML public ImageView image;
	@FXML public Text title;
	@FXML public FlowPane category;
	@FXML public FlowPane director;
	@FXML public Text length;
	@FXML public Text sumary;
	@FXML private BorderPane ratting;
	public RattingBar rattingBar = new RattingBar();
	public ContextMenu menu = new ContextMenu();
	public Phim phim=null;

	public MovieCard() {
		super();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/MovieCard.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		image.setOnContextMenuRequested(e -> {
			menu.show(this, e.getScreenX(), e.getScreenY());
		});
	}
	
	public MovieCard(Phim p) {
		super();
		phim=p;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/MovieCard.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		image.setOnContextMenuRequested(e -> {
			menu.show(this, e.getScreenX(), e.getScreenY());
		});
		title.setText(" "+p.getTenPhim());
		Connector<Phim_LoaiPhim> c=new Connector<Phim_LoaiPhim>();
		Image img=c.convertToBufferImage(p.getHinhAnh());
		image.setImage(img);
//		Connector<LoaiPhim> cl=new Connector<LoaiPhim>();
//		List<Phim_LoaiPhim> loais=c.select(Phim_LoaiPhim.class, "select * from PHIM_LOAIPHIM where MaPhim='"+p.getMaPhim()+"'");
		//hiển thị thể loại
		//category.getChildren().add(lb);
		director.getChildren().add(new Label(" "+p.getTenDaoDien()));
		length.setText("Thời lượng: "+p.getThoiLuong());
		sumary.setText("Mô tả: "+p.getMota());
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ratting.setCenter(rattingBar);
		
	}
}
