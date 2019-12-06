package Model;

public class KhachHang {

	@DBTable(columnName = "MaKhachHang")
	private String maKhachHang;
	
	@DBTable(columnName = "MaTaiKhoan")
	private String maTaiKhoan;
	
	@DBTable(columnName = "HoTen")
	private String hoTen;
	
	@DBTable(columnName = "Email")
	private String email;
	
	@DBTable(columnName = "SoDienThoai")
	private String soDienThoai;

	public KhachHang() {
		super();
	}

	public KhachHang(String maKhachHang, String maTaiKhoan, String hoTen, String email, String soDienThoai) {
		super();
		this.maKhachHang = maKhachHang;
		this.maTaiKhoan = maTaiKhoan;
		this.hoTen = hoTen;
		this.email = email;
		this.soDienThoai = soDienThoai;
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getMaTaiKhoan() {
		return maTaiKhoan;
	}

	public void setMaTaiKhoan(String maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
}
