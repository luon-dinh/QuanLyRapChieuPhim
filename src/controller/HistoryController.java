package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Connector.Connector;
import Model.VeXemPhim;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import usercontrol.control.HistoryCard;

public class HistoryController implements Initializable {

    @FXML private FlowPane paneHistory;
    @FXML private Button btn_refresh;
    @FXML private AnchorPane root;
    @FXML private ScrollPane scroll;
    @FXML private Button btn_timkiem;
    @FXML private Label lb_soluongve;
    @FXML private TextField searchTextField;
    
    private List<VeXemPhim> dsVeXemPhim;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		paneHistory.prefWidthProperty().bind(root.widthProperty().subtract(20));
		
		dsVeXemPhim=new Connector().select(VeXemPhim.class, "select * from VEXEMPHIM where MaTaiKhoan='"+LoginController.taikhoan.getMaTaiKhoan()+"'");
		if(dsVeXemPhim.size()==0)
			return;
		for(VeXemPhim vxp:dsVeXemPhim) {
			paneHistory.getChildren().add(new HistoryCard(vxp));
		}
		//paneHistory.getChildren().add(new HistoryCard(VeXemPhim veXemPhim));
	}

}
