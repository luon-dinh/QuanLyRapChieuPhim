package Model;

public class PhongChieuPhim {

	@DBTable(columnName = "MaPhong")
	private String maPhong;
	
	@DBTable(columnName = "TrangThai")
	private String trangThai;

	public String getMaPhong() {
		return maPhong;
	}

	public PhongChieuPhim() {
		super();
	}

	public PhongChieuPhim(String maPhong, String trangThai) {
		super();
		this.maPhong = maPhong;
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
