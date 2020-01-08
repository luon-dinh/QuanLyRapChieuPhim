package controller;

import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import Connector.Connector;
import Model.Ghe;
import Model.Ghe_LichChieu;
import Model.LichChieuPhim;
import Model.LoaiPhim;
import Model.Phim;
import Model.PhongChieuPhim;
import Model.VeXemPhim;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import plugin.AlertBox;
import plugin.MyWindows;
import plugin.AlertBox.MyButtonType;
import usercontrol.control.AddEditInfo;
import usercontrol.control.AdvanceMenuFilterContent;
import usercontrol.control.MovieCard;

public class AddMovieToScheduleController implements Initializable{
    @FXML private MenuButton advance;
    @FXML private FlowPane paneMovie;
    @FXML private ScrollPane root;
    @FXML private TextField condition;
    @FXML private Button btn_huy;
//    @FXML private ComboBox<String> cb_phong;
//    @FXML private DatePicker datePicker;
    
    private ArrayList<Phim> dsPhim;
    private ArrayList<LoaiPhim> dsLoaiPhim;
    private static final String[] contents = { "Giá vé", "Số vé", "Thời lượng (phút)"};
    private AddEditInfo w2 = new AddEditInfo("Sửa lịch chiếu phim");
    private String cond1="";
    
    
	private void loadLoaiPhim() {
		// TODO Auto-generated method stub
		Connector<LoaiPhim> c=new Connector<LoaiPhim>();
		dsLoaiPhim=new ArrayList<LoaiPhim>();
		dsLoaiPhim.clear();
		dsLoaiPhim.addAll(c.select(LoaiPhim.class, "select * from LOAIPHIM"));
	}

    
    @FXML void FindMovies(KeyEvent event) {
    	if (event.getCode() == KeyCode.ENTER)
    	{
    		String cond=condition.getText();
    		xuLiTimKiem(cond);
    	}
    }

    private void xuLiTimKiem(String cond) {
		// TODO Auto-generated method stub
    	cond=cond.toLowerCase();
		// TODO Auto-generated method stub
		ArrayList<Phim> ds=new ArrayList<Phim>();
		for(LoaiPhim lp:dsLoaiPhim) {
			String tenLoai=lp.getTenLoai();
			if(menuContent.IsCheck("Thể loại",tenLoai )) {
				cond1+=tenLoai;
			}
		}
		cond=cond.toLowerCase();
		for(Phim p:dsPhim) {
			ArrayList<String> checkCond=Connector.getLoaiPhimByMaPhim(p.getMaPhim());
			boolean contain=false;
			if(cond1.equals("")) {
				contain=true;
			}
			else {
				for(String s:checkCond) {
					if(cond1.contains(s)) {
						contain=true;
						break;
					}
				}
			}
			if(p.getTenPhim().toLowerCase().contains(cond)&&contain) {
				ds.add(p);
			}
		}
		initial(ds);
	}

	private AdvanceMenuFilterContent menuContent = new AdvanceMenuFilterContent();
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initial(null);
		addEvents();
		for(String content:contents) {
			w2.Add(content, true);
		}
		//w2.AddDatePicker("Giờ bắt đầu");
		w2.AddTimePicker("Giờ bắt đầu");
		paneMovie.prefWidthProperty().bind(root.widthProperty().subtract(10));
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		btn_huy.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Stage stage=(Stage)btn_huy.getScene().getWindow();
				stage.close();
				MyWindows.lastStage.setUserData(null);
			}
		});
	}


	private void initial(ArrayList<Phim> ds) {
		loadLoaiPhim();
		cond1="";
		ArrayList<Phim> temp=new ArrayList<Phim>();
		if(ds==null) {
			dsPhim=new ArrayList<Phim>();
			dsPhim.addAll(new Connector().selectPhim( "select * from PHIM"));
			temp=dsPhim;
		}
		else {
			temp=ds;
		}
		// TODO Auto-generated method stub
		advance.getItems().add(new CustomMenuItem(menuContent, false));
		paneMovie.getChildren().removeAll(paneMovie.getChildren());
		for(Phim p:temp) {
			MovieCard card=new MovieCard(p);
//			MenuItem edit = new MenuItem("Sửa");
//			edit.setOnAction(e->{
//				
//			});
			MenuItem delete = new MenuItem("Xóa");
			delete.setOnAction(e -> {
				Optional<ButtonType> result =AlertBox.show(AlertType.CONFIRMATION, "Xác nhận", "Bạn có thực sự muốn xóa bộ phim này?", MyButtonType.YesNo);
				if(result.get()==ButtonType.YES) {
					new Connector<Phim>().delete("delete from PHIM where MaPhim='"+p.getMaPhim()+"'");
					paneMovie.getChildren().remove(card);
					AlertBox.show(AlertType.INFORMATION,"Thành công","", "Xoá phim thành công!");
				}
			});
//			card.menu.getItems().add(edit);
			card.menu.getItems().add(delete);
			card.rattingBar.readOnly.set(true);
    		card.cursorProperty().set(Cursor.HAND);
    		card.setOnMouseClicked(e->{
				if (e.getButton() == MouseButton.PRIMARY)
				{
					MyWindows.lastStage.setUserData(card);
					xuLiShowThongTinLichChieu(card);
				}
    		});
			paneMovie.getChildren().add(card);
		}
		for(LoaiPhim lp:dsLoaiPhim) {
			menuContent.Add("Thể loại", lp.getTenLoai());
		}
	}


	private void xuLiShowThongTinLichChieu(MovieCard card) {
		// TODO Auto-generated method stub
		if(!w2.isShow()) {
			w2.show();	
		}
		if(w2.boxReturn==ButtonType.OK) {
			try {
				int giaVe=Integer.parseInt(w2.Get("Giá vé").getText());
				int soGhe=Integer.parseInt(w2.Get("Số vé").getText());
				int thoiLuong=Integer.parseInt(w2.Get("Thời lượng (phút)").getText());
				LocalTime time=w2.getTimePicker("Giờ bắt đầu").getValue();
				List<PhongChieuPhim> dsPhong=new Connector().select(PhongChieuPhim.class, "select * from PHONGCHIEUPHIM where MaPhong='"+ScheduleController.phong.getMaPhong()+"'");
				if(dsPhong.size()>0) {
					int maxSoGhe=dsPhong.get(dsPhong.size()-1).getSoGhe();
					if(soGhe>maxSoGhe) {
						AlertBox.show(AlertType.ERROR,"Lỗi","", "Số vé nhiều hơn số ghế hiện có của phòng!");
						return;
					}
				}
				card.setGiaVe(giaVe);
				card.setSoGhe(soGhe);
				card.setThoiLuong(thoiLuong);
				card.setGioBatDau(time);
				//xử lí thêm lịch chiếu phim
				xuLiThemLichChieuPhim(card);
			
			}
			catch (Exception e) {
				// TODO: handle exception
				AlertBox.show(AlertType.ERROR,"Nhập sai","", "Vui lòng kiểm tra lại thông tin!");
			}
			finally {
			}
		}
	}


	private void xuLiThemLichChieuPhim(MovieCard card) {
		// TODO Auto-generated method stub
		try {
			ArrayList<LichChieuPhim> dsLichChieu=new ArrayList<LichChieuPhim>();
			Connector<LichChieuPhim> c=new Connector<LichChieuPhim>();
			dsLichChieu.addAll(c.select(LichChieuPhim.class, "select * from LICHCHIEUPHIM"));
			List<Ghe> dsGhe=new Connector<Ghe>().select(Ghe.class, "select * from GHE where MaPhong='"+ScheduleController.phong.getMaPhong()+"'");
			int index=0;
			if(dsLichChieu.size()>0) {
				String maLichChieu=dsLichChieu.get(dsLichChieu.size()-1).getMaLichChieu();
				index=Integer.parseInt(maLichChieu.substring(2,maLichChieu.length()));
			}
			String maLichChieu="LC"+(index+1);
			c.insert("insert into LICHCHIEUPHIM values('"+maLichChieu+"','"+ScheduleController.phong.getMaPhong()+"','"+card.phim.getMaPhim()+"','"+ScheduleController.date+"','"+card.getGioBatDau().toString()+"','"+card.getThoiLuong()+"','"+card.getSoGhe()+"','"+card.getGiaVe()+"')");
			for(Ghe ghe:dsGhe) {
				new Connector<Ghe_LichChieu>().insert("insert into GHE_LICHCHIEU values('"+ghe.getMaGhe()+"','"+maLichChieu+"','"+1+"','"+(-1)+"')");
			
			}
			MyWindows.lastStage.close();
			AlertBox.show(AlertType.INFORMATION, "Thành công","","Thêm lịch chiếu phim thành công!");
		}
		catch (Exception e) {
			AlertBox.show(AlertType.ERROR, "Lỗi","", "Thông tin không đúng định dạng, vui lòng nhập lại!");
		}
		
	}
}
