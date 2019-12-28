package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AddNewServiceController implements Initializable{

	@FXML
	private Button btn_dongy;
	@FXML
	private Button btn_huy;
	@FXML
	private ImageView img_v;
	@FXML
	private TextField txt_ten;
	@FXML
	private TextField txt_gia;
	@FXML
	private TextArea txt_mota;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		addEvents();
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		
	}

}
