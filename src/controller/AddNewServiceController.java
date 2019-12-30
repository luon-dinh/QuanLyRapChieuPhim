package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import plugin.SceneController;

public class AddNewServiceController implements Initializable{

	@FXML
	private Button btn_dongy;
	@FXML
	private Button btn_huy;
	@FXML
	private ImageView img_v;
	@FXML
	private TextField lb_ten;
	@FXML
	private TextField lb_gia;
	@FXML
	private TextArea lb_mota;
	
	private BufferedImage img;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		addEvents();
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		btn_dongy.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					String tenSanPhan=lb_ten.getText();
					int gia=Integer.parseInt(lb_gia.getText());
					String moTa=lb_mota.getText();
					
					//xử lí thêm vào csdl
					
					Stage stage=(Stage)btn_huy.getScene().getWindow();
					
					stage.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		btn_huy.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Stage stage=(Stage)btn_huy.getScene().getWindow();
				stage.close();
			}
		});
		
		img_v.setOnMouseClicked(e->{
			FileChooser fileChooser = new FileChooser();
			File f=fileChooser.showOpenDialog(SceneController.GetInstance().getCurrentStage());
			if(f!=null) {
				try {
					img=ImageIO.read(f);
					//Image i=(Image)img;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

}
