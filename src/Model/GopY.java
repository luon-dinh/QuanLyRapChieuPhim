package Model;

public class GopY {

	@DBTable(columnName = "MaGopY")
	private String maGopY;
	
	@DBTable(columnName = "MaKhachHang")
	private String maKhachHang;
	
	@DBTable(columnName = "NoiDung")
	private String noiDung;
	
	@DBTable(columnName = "NgayGopY")
	private String ngayGopY;

	public String getMaGopY() {
		return maGopY;
	}

	public void setMaGopY(String maGopY) {
		this.maGopY = maGopY;
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public String getNgayGopY() {
		return ngayGopY;
	}

	public void setNgayGopY(String ngayGopY) {
		this.ngayGopY = ngayGopY;
	}

	public GopY(String maGopY, String maKhachHang, String noiDung, String ngayGopY) {
		super();
		this.maGopY = maGopY;
		this.maKhachHang = maKhachHang;
		this.noiDung = noiDung;
		this.ngayGopY = ngayGopY;
	}

	public GopY() {
		super();
	}
	
}
