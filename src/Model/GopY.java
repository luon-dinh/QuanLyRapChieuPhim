package Model;

public class GopY {
	
	@DBTable(columnName = "MaTaiKhoan")
	private String maTaiKhoan;
	
	@DBTable(columnName = "MaPhim")
	private String maPhim;
	
	@DBTable(columnName = "NoiDung")
	private String noiDung;
	
	@DBTable(columnName = "NgayGopY")
	private String ngayGopY;
	
	public GopY() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GopY(String maTaiKhoan, String maPhim, String noiDung, String ngayGopY) {
		super();
		this.maTaiKhoan = maTaiKhoan;
		this.maPhim = maPhim;
		this.noiDung = noiDung;
		this.ngayGopY = ngayGopY;
	}

	public String getMaTaiKhoan() {
		return maTaiKhoan;
	}

	public void setMaTaiKhoan(String maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
	}

	public String getMaPhim() {
		return maPhim;
	}

	public void setMaPhim(String maPhim) {
		this.maPhim = maPhim;
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

}
