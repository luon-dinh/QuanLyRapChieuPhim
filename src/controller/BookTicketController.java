package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Connector.Connector;
import Model.Ghe;
import Model.Ghe_LichChieu;
import Model.LichChieuPhim;
import Model.VeXemPhim;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import plugin.AlertBox;
import plugin.MyWindows;
import usercontrol.control.Chip;
import usercontrol.control.MovieScheduleCard;
import usercontrol.control.SelectableButton;

public class BookTicketController implements Initializable {
	private static final int chairsPreRow = 16;	
	@FXML private FlowPane pane;
	@FXML private Label ticketCounter;
	@FXML private Label lb_tenphim, lb_tenphong, lb_sotien;
	@FXML private Button btn_dongy, btn_huy, btn_muadoanvat;
	@FXML private VBox cb_doandadat;
	@FXML private MenuButton mn_doandadat;
	
	public static ArrayList<SanPhamDaDat> dsSanPhamDaDat;
	private MovieScheduleCard card;
	private List<Integer> indexs=new ArrayList<Integer>(); 
	private IntegerProperty counter = new SimpleIntegerProperty(0);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dsSanPhamDaDat=new ArrayList<BookTicketController.SanPhamDaDat>();
		addEvents();
		card= (MovieScheduleCard) MyWindows.lastStage.getUserData();
		lb_tenphim.setText(card.name.getText());
		lb_tenphong.setText(card.room.getText());
		System.out.println(card.getLichChieu().getMaLichChieu());
		List<Ghe_LichChieu> dsGhe_LichChieu=new Connector<Ghe_LichChieu>().select(Ghe_LichChieu.class, "select * from GHE_LICHCHIEU where MaLichChieu='"+card.getLichChieu().getMaLichChieu()+"'");
		int chairs=Integer.parseInt(card.numberSeats.getText());
		for (int i = 0; i < chairs; i++) {
			int r = i / chairsPreRow;
			int c = i % chairsPreRow;
			int maGhe=dsGhe_LichChieu.get(i).getMaGhe();
			SelectableButton button = new SelectableButton(maGhe);
			button.textProperty().set("" + (char) (65 + r) + (c + 1));
			pane.getChildren().add(button);
			if(new Connector<Ghe_LichChieu>().select(Ghe_LichChieu.class, "select * from GHE_LICHCHIEU where MaGhe='"+maGhe+"' and MaLichChieu='"+card.getLichChieu().getMaLichChieu()+"' and TrangThai='1'").size()>0) {
				button.isSelected.addListener((observable, oldValue, newValue)->{
					if(newValue==null||newValue==oldValue)
						return;
					if (newValue) {
						counter.set(counter.get() + 1);
						lb_sotien.setText(counter.get()*Integer.parseInt(card.cost.getText())+"");
						indexs.add(maGhe);
					}
					else {
						counter.set(counter.get() - 1);	
						lb_sotien.setText(counter.get()*Integer.parseInt(card.cost.getText())+"");
						indexs.remove(maGhe);
					}
				});		
			}
			else {
				button.setDisable(true);
				button.isSelected.set(true);
				button.setTextFill(Color.YELLOWGREEN);
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
				boolean result=xuLiDatVe();
				if(result) {
					AlertBox.show(AlertType.INFORMATION, "Thông báo", "Đặt thành công");
					Stage stage=(Stage)btn_dongy.getScene().getWindow();
					stage.close();
				}
				else {
					
				}
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
		
		btn_muadoanvat.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				MyWindows m=new MyWindows("../view/Service.fxml",card);
				m.Resize(760, 540);
				m.Show();
				int lenght=dsSanPhamDaDat.size();
				mn_doandadat.setText("Đồ ăn đã đặt ("+lenght+")");
				cb_doandadat.getChildren().clear();
				for(SanPhamDaDat spdd:dsSanPhamDaDat) {
					Chip ch = new Chip(spdd.tenSanPham+": "+spdd.soLuong);
					ch.setOnMouseClickedDelete(e->{
						new Connector().delete("delete from HOADON where MaHoaDon='"+spdd.maHoaDon+"'");
						new Connector().delete("delete from CTHD where MaHoaDon='"+spdd.maHoaDon+"'");
						dsSanPhamDaDat.remove(spdd);
						mn_doandadat.setText("Đồ ăn đã đặt ("+dsSanPhamDaDat.size()+")");
						cb_doandadat.getChildren().remove(ch);
					});
					cb_doandadat.getChildren().add(ch);
				}
			}
		});
		
	}
	private boolean xuLiDatVe() {
		// TODO Auto-generated method stub
		try {
			List<VeXemPhim> dsVe=new ArrayList<VeXemPhim>();
			Connector<Ghe_LichChieu> c=new Connector<Ghe_LichChieu>();
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
			for(Integer i:indexs) {
				c.update( "update GHE_LICHCHIEU set TrangThai='0', MaVe='"+index+"' where MaGhe='"+i+"' and MaLichChieu='"+card.getLichChieu().getMaLichChieu()+"'");
			}
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	
	public static class SanPhamDaDat{
		int maHoaDon;
		String maLichChieu;
		String maSanPham;
		String tenSanPham;
		public String getTenSanPham() {
			return tenSanPham;
		}
		public void setTenSanPham(String tenSanPham) {
			this.tenSanPham = tenSanPham;
		}
		int soLuong;
		public String getMaLichChieu() {
			return maLichChieu;
		}
		public void setMaLichChieu(String maLichChieu) {
			this.maLichChieu = maLichChieu;
		}
		public String getMaSanPham() {
			return maSanPham;
		}
		public void setMaSanPham(String maSanPham) {
			this.maSanPham = maSanPham;
		}
		public int getSoLuong() {
			return soLuong;
		}
		public void setSoLuong(int soLuong) {
			this.soLuong = soLuong;
		}
		public int getMaHoaDon() {
			return maHoaDon;
		}
		public void setMaHoaDon(int maHoaDon) {
			this.maHoaDon = maHoaDon;
		}
		public SanPhamDaDat(int maHoaDon, String maLichChieu, String maSanPham, String tenSanPham, int soLuong) {
			super();
			this.maHoaDon = maHoaDon;
			this.maLichChieu = maLichChieu;
			this.maSanPham = maSanPham;
			this.tenSanPham = tenSanPham;
			this.soLuong = soLuong;
		}
		public SanPhamDaDat() {
			super();
			// TODO Auto-generated constructor stub
		}
		
	}

}
