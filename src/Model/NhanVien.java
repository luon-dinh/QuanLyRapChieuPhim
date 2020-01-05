package Model;

public class NhanVien {

	@DBTable(columnName = "MaNhanVien")
	private String maNhanVien;
	
	@DBTable(columnName = "MaTaiKhoan")
	private String maTaiKhoan;
	
	@DBTable(columnName = "HoTen")
	private String hoTen;
	
	@DBTable(columnName = "NgaySinh")
	private String ngaySinh;
	
	@DBTable(columnName = "DiaChi")
	private String diaChi;
	
	@DBTable(columnName = "GioiTinh")
	private String gioiTinh;
	
	@DBTable(columnName = "Email")
	private String email;
	
	@DBTable(columnName = "SoDienThoai")
	private String soDienThoai;
	
	@DBTable(columnName = "NgayVaoLam")
	private String ngayVaoLam;
	
	
	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioTinh) {
		this.gioiTinh = gioTinh;
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NhanVien(String maNhanVien, String maTaiKhoan, String hoTen, String ngaySinh, String diaChi, String gioTinh,
			String email, String soDienThoai, String ngayVaoLam) {
		super();
		this.maNhanVien = maNhanVien;
		this.maTaiKhoan = maTaiKhoan;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.gioiTinh = gioTinh;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.ngayVaoLam = ngayVaoLam;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
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

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
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

	public String getNgayVaoLam() {
		return ngayVaoLam;
	}

	public void setNgayVaoLam(String ngayVaoLam) {
		this.ngayVaoLam = ngayVaoLam;
	}

}
