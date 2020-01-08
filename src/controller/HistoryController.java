package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import Connector.Connector;
import Model.CTHD;
import Model.Ghe_LichChieu;
import Model.HoaDon;
import Model.LichChieuPhim;
import Model.Phim;
import Model.VeXemPhim;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import plugin.AlertBox;
import plugin.AlertBox.MyButtonType;
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
		loadThongTin(null);
		addevents();
		//paneHistory.getChildren().add(new HistoryCard(VeXemPhim veXemPhim));
	}

	private void addevents() {
		// TODO Auto-generated method stub
		btn_timkiem.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				xuLiTimKiem();
			}
		});
		
		searchTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode()==KeyCode.ENTER) {
					xuLiTimKiem();
				}
			}
		});
		
		btn_refresh.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				paneHistory.getChildren().clear();
				loadThongTin(null);
			}
		});
	}

	protected void xuLiTimKiem() {
		// TODO Auto-generated method stub
		String cond=searchTextField.getText().toLowerCase();
		List<VeXemPhim> temp=new Connector<VeXemPhim>().select(VeXemPhim.class ,  "select * from VEXEMPHIM where MaTaiKhoan='"+LoginController.taikhoan.getMaTaiKhoan()+"'");
		ArrayList<VeXemPhim> temp1=new ArrayList<VeXemPhim>();
		temp1.addAll(temp);
		for(VeXemPhim vxp:temp) {
			String maLichChieu=vxp.getMaLichChieu();
			List<LichChieuPhim> dsLichChieu=new Connector<LichChieuPhim>().select(LichChieuPhim.class, "select * from LICHCHIEUPHIM where MaLichChieu='"+maLichChieu+"'");
			if(dsLichChieu.size()>0) {
				String maPhim=dsLichChieu.get(0).getMaPhim();
				List<Phim> dsPhim=new Connector<Phim>().select(Phim.class, "select * from PHIM where MaPhim='"+maPhim+"'");
				if(dsPhim.size()>0)
				{
					if(!dsPhim.get(0).getTenPhim().toLowerCase().contains(cond)) {
						temp1.remove(vxp);
					}
				}
			}
		}
		loadThongTin(temp1);
	}

	private void loadThongTin(ArrayList<VeXemPhim> vxps) {
		// TODO Auto-generated method stub
		paneHistory.getChildren().clear();
		ArrayList<VeXemPhim> temp=new ArrayList<VeXemPhim>();
		if(vxps!=null)
			temp.addAll(vxps);
		else {
			dsVeXemPhim=new Connector().select(VeXemPhim.class, "select * from VEXEMPHIM where MaTaiKhoan='"+LoginController.taikhoan.getMaTaiKhoan()+"'");
			temp.addAll(dsVeXemPhim);
		}
		int veXemPhimLenght=temp.size();
		if(veXemPhimLenght==0)
			return;
		for(int i=0;i<veXemPhimLenght;i++) {
			VeXemPhim vxp=temp.get(i);
			HistoryCard card=new HistoryCard(vxp);
			MenuItem delete=new MenuItem("Xóa");
			delete.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					Optional<ButtonType> result =AlertBox.show(AlertType.CONFIRMATION, "Xác nhận", "Bạn có thực sự muốn xóa bộ phim này?", MyButtonType.YesNo);
					if(result.get()==ButtonType.YES) {
						xuLiXoaVeXemPhim(card);	
					}
					// TODO Auto-generated method stub
				}
			});
			card.contextMenu.getItems().add(delete);
			paneHistory.getChildren().add(card);
		}
		lb_soluongve.setText(veXemPhimLenght+"");
	}

	protected void xuLiXoaVeXemPhim(HistoryCard card) {
		// TODO Auto-generated method stub
		try {
			//xóa các constraint
			Connector<CTHD> cCTHD=new Connector<CTHD>();
			List<CTHD> dsCTHD=card.getDsCTHD();
			for(CTHD cthd:dsCTHD) {
				cCTHD.delete("delete from CTHD where MaHoaDon='"+cthd.getMaHoaDon()+"'");
			}
			
			Connector<HoaDon> cHoaDon=new Connector<HoaDon>();
			List<HoaDon> dsHoaDon=card.getDsHoaDon();
			for(HoaDon hd:dsHoaDon) {
				cHoaDon.delete("delete from HOADON where MaHoaDon='"+hd.getMaHoaDon()+"'");
			}
			
			VeXemPhim vxp=card.getVeXemPhim();
			Connector<Ghe_LichChieu> cGhe_LichChieu=new Connector<Ghe_LichChieu>();
			cGhe_LichChieu.update("update GHE_LICHCHIEU set TrangThai='"+1+"' where MaVe='"+vxp.getMaVe()+"'");
			
			Connector<VeXemPhim> cVeXemPhim=new Connector<VeXemPhim>();
			cVeXemPhim.delete("delete from VEXEMPHIM where MaVe='"+vxp.getMaVe()+"'");
			
			AlertBox.show(AlertType.INFORMATION, "Thông báo", "Xóa thành công");
		}
		catch (Exception e) {
			// TODO: handle exception
			AlertBox.show(AlertType.INFORMATION, "Thông báo", "Xóa thất bại");
		}
	}

}
