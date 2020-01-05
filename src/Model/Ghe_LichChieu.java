package Model;

public class Ghe_LichChieu {
	
	public Ghe_LichChieu(int maGhe, String maLichChieu, int trangThai) {
		super();
		this.maGhe = maGhe;
		this.maLichChieu = maLichChieu;
		this.trangThai = trangThai;
	}

	public Ghe_LichChieu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@DBTable(columnName = "MaGhe")
	private int maGhe;
	
	@DBTable(columnName = "MaLichChieu")
	private String maLichChieu;
	
	@DBTable(columnName = "TrangThai")
	private int trangThai;

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
