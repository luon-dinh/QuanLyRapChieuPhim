package Model;

public class Ghe {
	@DBTable(columnName = "MaGhe")
	private int maGhe;
	
	@DBTable(columnName = "MaPhong")
	private String maPhong;
	
	public int getMaGhe() {
		return maGhe;
	}

	public void setMaGhe(int maGhe) {
		this.maGhe = maGhe;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public Ghe(int maGhe, String maPhong) {
		super();
		this.maGhe = maGhe;
		this.maPhong = maPhong;
	}

	public Ghe() {
		super();
	}	

}
