package usercontrol.control;

import java.io.IOException;
import java.util.List;

import Connector.Connector;
import Model.CTHD;
import Model.HoaDon;
import Model.LichChieuPhim;
import Model.Phim;
import Model.SanPham;
import Model.VeXemPhim;
import controller.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


public class HistoryCard extends AnchorPane {

	@FXML private ImageView image;
	@FXML private Label lb_tenphim, lb_sovedadat, lb_ngay, lb_gio;
	@FXML private VBox vb_tenmon, vb_soluong;
	private List<Phim> dsPhim; 
	private List<LichChieuPhim> dsLichChieu;
	private List<HoaDon> dsHoaDon;
	private List<CTHD> dsCTHD;
	private List<SanPham> dsSanPham;
	
	public HistoryCard()
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/HistoryCard.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public HistoryCard(VeXemPhim veXemPhim)
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/HistoryCard.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		dsLichChieu=new Connector<LichChieuPhim>().select(LichChieuPhim.class, "select * from LICHCHIEUPHIM where MaLichChieu='"+veXemPhim.getMaLichChieu()+"'");
		if(dsLichChieu.size()==0)
			return;
		LichChieuPhim lcp=dsLichChieu.get(0);
		dsPhim=new Connector<Phim>().selectPhim("select * from PHIM where MaPhim='"+lcp.getMaPhim()+"'");
		if(dsPhim.size()==0)
			return;
		lb_tenphim.setText(dsPhim.get(0).getTenPhim());
		Connector.setImage(image, Connector.convertToBufferImage(dsPhim.get(0).getHinhAnh()));
		lb_sovedadat.setText(veXemPhim.getTongSoGhe()+"");
		lb_ngay.setText(lcp.getNgayChieu());
		lb_gio.setText(lcp.getGioBatDau());
		dsHoaDon=new Connector<HoaDon>().select(HoaDon.class, "select * from HOADON where MaTaiKhoan='"+LoginController.taikhoan.getMaTaiKhoan()+"' and MaLichChieu='"+lcp.getMaLichChieu()+"'");
		if(dsHoaDon.size()==0)
			return;
		dsCTHD=new Connector<CTHD>().select(CTHD.class, "select * from CTHD where MaHoaDon='"+dsHoaDon.get(0).getMaHoaDon()+"'");
		for(CTHD cthd:dsCTHD) {
			dsSanPham=new Connector<SanPham>().select(SanPham.class, "select * from SANPHAM where MaSanPham='"+cthd.getMaSanPham()+"'");
			if(dsSanPham.size()>0) {
				vb_tenmon.getChildren().add(new Label(dsSanPham.get(0).getTenSanPham()));
				vb_soluong.getChildren().add(new Label(cthd.getSoLuong()+""));
			}
		}
	}
}
