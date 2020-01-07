package usercontrol.control;

import java.io.IOException;

import Connector.Connector;
import Model.LichChieuPhim;
import Model.Phim;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class MovieScheduleCard extends AnchorPane {
	@FXML public ImageView image;
    @FXML public Text cost;
    @FXML public Text name;
    @FXML public Text numberSeats;
    @FXML public Text room;
    @FXML public Label time;
    @FXML public ContextMenu contextMenu;
    
    private LichChieuPhim lichChieu;
    
	public Phim phim=null;
    public SimpleObjectProperty<MovieScheduleCard> deleteObject = new SimpleObjectProperty<>(null);
	
	public MovieScheduleCard() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/MovieScheduleCard.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		image.setOnContextMenuRequested(e -> {
			contextMenu.show(this, e.getScreenX(), e.getScreenY());
		});
	}
	
	@FXML
    void delete(ActionEvent event) {
		deleteObject.setValue(this);		
    }
	
	public void setInfo(MovieCard card) throws NullPointerException {
		if (card == null)
			throw new NullPointerException("Card không tồn tại");
		else {
			image.setImage(card.image.getImage());
			name.setText(card.title.getText());
			cost.setText(""+card.getGiaVe());
			numberSeats.setText(""+card.getSoGhe());
			time.setText(card.getGioBatDau()+"");
		}
	}
	public MovieScheduleCard(LichChieuPhim lcp) {
		this.lichChieu=lcp;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/MovieScheduleCard.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//image.setImage( new Connector().getimageByMaPhim(lcp.getMaPhim()));
		Connector.setImage(image, new Connector().getimageByMaPhim(lcp.getMaPhim()));
		name.setText(new Connector().getTenPhimByMaPhim(lcp.getMaPhim()));
		room.setText(new Connector().getTenPhongByMaPhong(lcp.getMaPhongChieu()));
		numberSeats.setText(lcp.getSoVeToiDa()+"");
		cost.setText(lcp.getGiaVe()+"");
		time.setText(lcp.getGioBatDau());
		image.setOnContextMenuRequested(e -> {
			contextMenu.show(this, e.getScreenX(), e.getScreenY());
		});
	}
	
	 public LichChieuPhim getLichChieu() {
			return lichChieu;
		}

		public void setLichChieu(LichChieuPhim lichChieu) {
			this.lichChieu = lichChieu;
		}
}
