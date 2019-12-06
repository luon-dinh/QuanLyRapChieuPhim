package Model;

public class Ghe {
	@DBTable(columnName = "MaGhe")
	private String maGhe;
	
	@DBTable(columnName = "MaPhong")
	private String maPhong;
	
	@DBTable(columnName = "TrangThai")
	private String trangThai;

	public String getMaGhe() {
		return maGhe;
	}

	public void setMaGhe(String maGhe) {
		this.maGhe = maGhe;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public Ghe(String maGhe, String maPhong, String trangThai) {
		super();
		this.maGhe = maGhe;
		this.maPhong = maPhong;
		this.trangThai = trangThai;
	}

	public Ghe() {
		super();
	}	

}
