package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Connector.Connector;
import Model.Ghe;
import Model.Ghe_LichChieu;
import Model.VeXemPhim;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import plugin.MyWindows;
import usercontrol.control.MovieScheduleCard;
import usercontrol.control.SelectableButton;

public class BookTicketController implements Initializable {
	private static final int chairsPreRow = 16;	
	@FXML private FlowPane pane;
	@FXML private Label ticketCounter;
	@FXML private Label lb_tenphim, lb_tenphong, lb_sotien;
	@FXML private Button btn_dongy, btn_huy;
	
	private MovieScheduleCard card;
	private List<Integer> indexs=new ArrayList<Integer>(); 
	private IntegerProperty counter = new SimpleIntegerProperty(0);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addEvents();
		card= (MovieScheduleCard) MyWindows.lastStage.getUserData();
		lb_tenphim.setText(card.name.getText());
		lb_tenphong.setText(card.room.getText());
		List<Ghe_LichChieu> dsGhe_LichChieu=new Connector().select(Ghe_LichChieu.class, "select * from Ghe_LichChieu where MaLichChieu='"+card.getLichChieu().getMaLichChieu()+"'");
		int chairs=Integer.parseInt(card.numberSeats.getText());
//		if(chairs!=dsGhe_LichChieu.size()) {
//			return;
//		}
		for (int i = 0; i < chairs; i++) {
			int r = i / chairsPreRow;
			int c = i % chairsPreRow;
			int maGhe=dsGhe_LichChieu.get(i).getMaGhe();
			SelectableButton button = new SelectableButton(maGhe);
			button.textProperty().set("" + (char) (65 + r) + (c + 1));
			pane.getChildren().add(button);
			if(new Connector().select(Ghe_LichChieu.class, "select * from Ghe_LichChieu where MaGhe='"+maGhe+"' and MaLichChieu='"+card.getLichChieu().getMaLichChieu()+"' and TrangThai='1'").size()>0) {
				button.isSelected.addListener((observable, oldValue, newValue)->{
					if(newValue==null||newValue==oldValue)
						return;
					if (newValue) {
						counter.set(counter.get() + 1);
						lb_sotien.setText(counter.get()*Integer.parseInt(card.cost.getText())+"");
						indexs.add(maGhe);
						new Connector().update( "update Ghe_LichChieu set TrangThai='0' where MaGhe='"+maGhe+"' and MaLichChieu='"+card.getLichChieu().getMaLichChieu()+"'");
					}
					else {
						counter.set(counter.get() - 1);	
						lb_sotien.setText(counter.get()*Integer.parseInt(card.cost.getText())+"");
						indexs.remove(maGhe);
						new Connector().update( "update Ghe_LichChieu set TrangThai='1' where MaGhe='"+maGhe+"' and MaLichChieu='"+card.getLichChieu().getMaLichChieu()+"'");
					}
				});		
			}
			else {
				button.isSelected.set(true);
				button.isSelected.addListener((observable, oldValue, newValue)->{
					button.isSelected.set(true);
				});
			}
		}
		ticketCounter.textProperty().bind(counter.asString());
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		btn_dongy.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				xuLiDatVe();
				Stage stage=(Stage)btn_dongy.getScene().getWindow();
				stage.close();
			}

		});
		
		btn_huy.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Stage stage=(Stage)btn_dongy.getScene().getWindow();
				stage.close();
			}
		});
	}
	private void xuLiDatVe() {
		// TODO Auto-generated method stub
		List<VeXemPhim> dsVe=new ArrayList<VeXemPhim>();
		dsVe=new Connector().select(VeXemPhim.class, "select * from VEXEMPHIM");
		int index=0;
		if(dsVe.size()>0) {
			index=dsVe.get(dsVe.size()-1).getMaVe()+1;
		}
		int tongTien=Integer.parseInt(lb_sotien.getText());
		String ngayDat=LocalDate.now().toString();
		String trangThai="Đã đặt";
		if(indexs.size()>0) {
			new Connector().insert("insert into VEXEMPHIM values('"+index+"','"+LoginController.taikhoan.getMaTaiKhoan()+"','"+card.getLichChieu().getMaLichChieu()+"','"+indexs.size()+"','"+tongTien+"','"+ngayDat+"','"+trangThai+"')");
		}
	}

}
