package Model;

public class PhongChieuPhim {

	@DBTable(columnName = "MaPhong")
	private String maPhong;
	
	@DBTable(columnName = "TenPhong")
	private String tenPhong;
	
	public String getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}

	@DBTable(columnName = "TrangThai")
	private String trangThai;

	public String getMaPhong() {
		return maPhong;
	}


	public PhongChieuPhim() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PhongChieuPhim(String maPhong, String tenPhong, String trangThai) {
		super();
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.trangThai = trangThai;
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
	
}
