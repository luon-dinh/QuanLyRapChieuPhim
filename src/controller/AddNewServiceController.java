package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import Connector.Connector;
import Model.NhaCungCap;
import Model.SanPham;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import plugin.AlertBox;
import plugin.MyWindows;
import plugin.AlertBox.MyButtonType;

public class AddNewServiceController implements Initializable {

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
	@FXML
	private ComboBox<String> cb_nhacungcap;
	@FXML
	private CheckBox chk_thucan, chk_nuocuong;

	private File f;
	private Image img;
	private List<NhaCungCap> dsNhaCungCap;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initial();
		addEvents();
	}

	private void initial() {
		f = null;
		dsNhaCungCap = null;
		img = null;
		// TODO Auto-generated method stub
		Connector<NhaCungCap> c = new Connector<NhaCungCap>();
		dsNhaCungCap = c.select(NhaCungCap.class, "select * from NHACUNGCAP");
		ObservableList<String> items = FXCollections.observableArrayList();
		for (NhaCungCap ncc : dsNhaCungCap) {
			items.add(ncc.getTenNhaCungCap());
		}
		cb_nhacungcap.setItems(items);
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		btn_dongy.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				boolean result = addService();
				if (result) {
					Stage stage = (Stage) btn_dongy.getScene().getWindow();
					stage.close();
					AlertBox.show(AlertType.INFORMATION, "Thành công","", "Thêm dịch vụ thành công!");
				} else {
					//AlertBox.show(AlertType.ERROR, "Lỗi","", "Thông tin nhập không đúng định dạng, vui lòng nhập lại!");
				}
			}
		});
		btn_huy.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage stage = (Stage) btn_huy.getScene().getWindow();
				stage.close();
			}
		});

		img_v.setOnMouseClicked(e -> {
			chooseFile();
		});
	}

	private void chooseFile() {
		// TODO Auto-generated method stub
		FileChooser fileChooser = new FileChooser();
		// File
		// f=fileChooser.showOpenDialog(SceneController.GetInstance().getCurrentStage());
		f = fileChooser.showOpenDialog(MyWindows.lastStage);
		if (f != null) {
			try {
				BufferedImage bimg = ImageIO.read(f);
				img = SwingFXUtils.toFXImage(bimg, null);
				img_v.setImage(img);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	protected boolean addService() {
		// TODO Auto-generated method stub
		try {
			Connector<SanPham> c = new Connector<SanPham>();
			List<SanPham> dsSanPham = c.select(SanPham.class, "select * from SANPHAM");
			int index = 0;
			if (dsSanPham.size() > 0) {
				int to = dsSanPham.get(dsSanPham.size() - 1).getMaSanPham().length();
				index = Integer.parseInt(dsSanPham.get(dsSanPham.size() - 1).getMaSanPham().substring(2, to));
			}
			String maSanPham = "SP" + (index + 1);
			String tenSanPhan = lb_ten.getText();
			int gia = Integer.parseInt(lb_gia.getText());
			String moTa = lb_mota.getText();
			String maNhaCungCap = "";
			for (NhaCungCap ncc : dsNhaCungCap) {
				if (ncc.getTenNhaCungCap().equalsIgnoreCase(cb_nhacungcap.getValue())) {
					maNhaCungCap = ncc.getMaNhaCungCap();
					break;
				}
			}
			byte[] hinhAnh = null;
			if (f != null) {
				hinhAnh = Connector.convertFileToByte(f);

			}
			String loai = "";
			if (chk_thucan.isSelected()) {
				loai += "Thức ăn";
			}
			if (chk_nuocuong.isSelected()) {
				loai += "Nước uống";
			}
			// xử lí lây hình ảnh và thêm vào csdl
			c.insert("insert into SANPHAM values('" + maSanPham + "', '" + maNhaCungCap + "','" + tenSanPhan + "','"
					+ gia + "','" + moTa + "',?,'" + loai + "')", hinhAnh);
			return true;

		} catch (Exception e) {
			AlertBox.show(AlertType.ERROR, "Nhập sai", "", "Vui lòng kiểm tra lại thông tin!");
			e.printStackTrace();
			return false;
		} finally {

		}
	}

}
