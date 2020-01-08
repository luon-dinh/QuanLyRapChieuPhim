package Model;

public class Ghe_LichChieu {
	
	@DBTable(columnName = "MaGhe")
	private int maGhe;
	
	@DBTable(columnName = "MaLichChieu")
	private String maLichChieu;
	
	@DBTable(columnName = "TrangThai")
	private int trangThai;
	
	@DBTable(columnName = "MaVe")
	private int maVe;

	public Ghe_LichChieu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ghe_LichChieu(int maGhe, String maLichChieu, int trangThai, int maVe) {
		super();
		this.maGhe = maGhe;
		this.maLichChieu = maLichChieu;
		this.trangThai = trangThai;
		this.maVe = maVe;
	}

	public int getMaVe() {
		return maVe;
	}

	public void setMaVe(int maVe) {
		this.maVe = maVe;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public int getMaGhe() {
		return maGhe;
	}

	public void setMaGhe(int maGhe) {
		this.maGhe = maGhe;
	}

	public String getMaLichChieu() {
		return maLichChieu;
	}

	public void setMaLichChieu(String maLichChieu) {
		this.maLichChieu = maLichChieu;
	}

}
