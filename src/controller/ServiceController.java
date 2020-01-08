package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import Connector.Connector;
import Model.CTHD;
import Model.HoaDon;
import Model.Phim;
import Model.Phim_LoaiPhim;
import Model.SanPham;
import controller.BookTicketController.SanPhamDaDat;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import plugin.AlertBox;
import plugin.MyWindows;
import plugin.AlertBox.MyButtonType;
import usercontrol.control.AddEditInfo;
import usercontrol.control.CartItem;
import usercontrol.control.MovieScheduleCard;
import usercontrol.control.SellingCard;

public class ServiceController implements Initializable {
	@FXML private CheckBox food, water, combo;
	@FXML private Label text0, SumCost;
	@FXML private VBox cart;
	@FXML private TextField search;
	@FXML private FlowPane pane;
	@FXML private ScrollPane scroll;
	@FXML private MenuButton cartString;
	@FXML private Button btn_timkiem, btn_refresh, btn_themsanpham, btn_thanhtoan;
	@FXML private Label lb_soluongsanpham;
	
	public static ArrayList<CartItem> cartItems=new ArrayList<CartItem>();
	private ArrayList<SanPham> dsSanPham;
	private IntegerProperty number = new SimpleIntegerProperty(0);
	private IntegerProperty cost = new SimpleIntegerProperty(0);
	private MovieScheduleCard card;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(LoginController.taikhoan.getLoaiTaiKhoan().equals("user"))
			card=(MovieScheduleCard)MyWindows.lastStage.getUserData();
		if(LoginController.taikhoan.getLoaiTaiKhoan().equals("user")) {
			btn_themsanpham.setVisible(false);
		}
		else {
			cartString.setVisible(false);
		}
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
		
		combo.setVisible(false);
		
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
				initial(null);
			}
		});

		search.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode()==KeyCode.ENTER) {
					String condition=search.getText();
					xuLiTimKiem(condition);
				}
			}
		});
		
		btn_thanhtoan.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				boolean result=xuLiMuaSanPham();
				//xóa danh sách đã chọn, reset lại thông tin 

				if(result) {
					cart.getChildren().removeAll(cart.getChildren());
					cartItems.clear();
					number.set(0);
					cost.set(0);
					AlertBox.show(AlertType.INFORMATION,"Thành công","", "Đặt sản phẩm thành công!");
					Stage stage=(Stage)btn_timkiem.getScene().getWindow();
					stage.close();
				}
				else {
					AlertBox.show(AlertType.ERROR, "Lỗi","","Đặt sản phẩm thất bại");
				}
			}
		});
		
	}

	protected boolean xuLiMuaSanPham() {
		// TODO Auto-generated method stub
		try {
			if(dsSanPham.size()==0)
				return false;
			Connector<HoaDon> cHoaDon=new Connector<HoaDon>();
			Connector<CTHD> cCTHD=new Connector<CTHD>();
			List<HoaDon> dsHoaDon=cHoaDon.select(HoaDon.class, "select * from HOADON");
			int index=0;
			if(dsHoaDon.size()>0) {
				index=dsHoaDon.get(dsHoaDon.size()-1).getMaHoaDon()+1;
			}
			double tongTien=Double.parseDouble(SumCost.getText());
			String maLichChieu=card.getLichChieu().getMaLichChieu();
			cHoaDon.insert("insert into HOADON values('"+index+"', '"+LoginController.taikhoan.getMaTaiKhoan()+"', '"+maLichChieu+"','"+tongTien+"','"+LocalDate.now().toString()+"')");
			BookTicketController.dsSanPhamDaDat.clear();
			for(CartItem ci:cartItems) {
				int soLuong=ci.NumberProperty().get();
				BookTicketController.dsSanPhamDaDat.add(new SanPhamDaDat(index,maLichChieu,ci.sp.getMaSanPham(), ci.sp.getTenSanPham(),soLuong));
				cCTHD.insert("insert into CTHD values('"+index+"','"+ci.sp.getMaSanPham()+"','"+soLuong+"')");
			}
			return true;
		}
		catch (Exception e) {
			return false;
		}
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
		lb_soluongsanpham.setText(temp.size()+"");
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
			String cond1="";
			String cond2="";
			if(food.isSelected()) {
				cond1+="Thức ăn";
			}
			if(water.isSelected()) {
				cond2+="Nước uống";
			}
			if(sp.getTenSanPham().toLowerCase().contains(condition)&&sp.getLoai().contains(cond1)&&sp.getLoai().contains(cond2)) {
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
			//card.imageProperty().set(Connector.convertToBufferImage(sp.getHinhAnh()));
			Connector.setImage(card.getimageView(),Connector.convertToBufferImage(sp.getHinhAnh()));
		}
		else {
			card.costProperty().set(10000);
			card.nameProperty().set("Bỏng ngô");
			card.descriptionProperty().set("Thơm ngon nức mũi");
		}
		card.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY) {
					if(LoginController.taikhoan.getLoaiTaiKhoan().equals("user")) {
						xuLiThemVaoGioHang(card);
					}
					else {
						xuLiXoaSua(card);
					}
				}
			}
		});
		pane.getChildren().add(card);
	}
	protected void xuLiXoaSua(SellingCard card) {
		// TODO Auto-generated method stub
		card.menu.getItems().clear();
		MenuItem edit = new MenuItem("Sửa");
		edit.setOnAction(e->{
			editSanPham(card);
		});
		MenuItem delete = new MenuItem("Xóa");
		delete.setOnAction(e -> {
			Optional<ButtonType> result =AlertBox.show(AlertType.CONFIRMATION, "Xác nhận", "Bạn có thực sự muốn xóa dịch vụ này?", MyButtonType.YesNo);
			if(result.get()==ButtonType.YES) {
				new Connector<SanPham>().delete("delete from SANPHAM where MaSanPham='"+card.sp.getMaSanPham()+"'");
				pane.getChildren().remove(card);
				AlertBox.show(AlertType.INFORMATION, "Thành công","", "Xoá sản phẩm thành công!");
			}
		});
		if(!LoginController.taikhoan.getLoaiTaiKhoan().equals("user")) {
			card.menu.getItems().add(edit);
			card.menu.getItems().add(delete);
		}
	}

	private void editSanPham(SellingCard card) {
		// TODO Auto-generated method stub
		AddEditInfo sua=new AddEditInfo("Sửa thông tin sản phẩm");
		String[] info= {"Tên sản phẩm", "Giá", "Mô tả"};
		sua.addImageView("Ảnh sản phẩm", card.imageProperty().get());
		sua.AddAll(info);
		sua.Get("Tên sản phẩm").setText(card.sp.getTenSanPham());
		sua.Get("Giá").setText(card.sp.getGiaSanPham()+"");
		sua.Get("Mô tả").setText(card.sp.getMoTa());
		sua.show();
		if(sua.boxReturn==ButtonType.CANCEL)
			return;
		if(sua.boxReturn==ButtonType.OK)
		{
			try {
				String tenSanPham=sua.Get("Tên sản phẩm").getText();
				int gia=Integer.parseInt(sua.Get("Giá").getText());
				String moTa=sua.Get("Mô tả").getText();
				byte[] hinhAnh;
				if(sua.f!=null) {
					hinhAnh=Connector.convertFileToByte(sua.f);
					new Connector<Phim>().update("update SANPHAM set TenSanPham='"+tenSanPham+"', GiaSanPham='"+gia+"', MoTa='"+moTa+"', HinhAnh=? where MaSanPham='"+card.sp.getMaSanPham()+"'",hinhAnh);
				}
				else
					new Connector<Phim>().update("update SANPHAM set TenSanPham='"+tenSanPham+"', GiaSanPham='"+gia+"', MoTa='"+moTa+"' where MaSanPham='"+card.sp.getMaSanPham()+"'");
				initial(null);

				AlertBox.show(AlertType.INFORMATION,"Thành công","","Cập nhật thông tin sản phẩm thành công!");
			}
			catch (Exception e){
				AlertBox.show(AlertType.ERROR,"Nhập sai","","Vui lòng kiểm tra lại thông tin!");

			}
		}
	}

	protected void xuLiThemVaoGioHang(SellingCard card) {
		// TODO Auto-generated method stub
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

	@FXML
    void AddNewServive(ActionEvent event) {
		MyWindows w = new MyWindows("../view/AddNewService.fxml");
		w.Show();
		initial(null);
    }
}
