package Model;

public class VeXemPhim {

	
	@DBTable(columnName = "MaVe")
	private String maVe;
	
	@DBTable(columnName = "MaKhachHang")
	private String maKhachHang;
	
	@DBTable(columnName = "MaLichChieu")
	private String maLichChieu;
	
	@DBTable(columnName = "TongSoGhe")
	private int tongSoGhe;
	
	@DBTable(columnName = "TongTien")
	private  float tongTien;
	
	@DBTable(columnName = "NgayDat")
	private String ngayDat;
	
	@DBTable(columnName = "TrangThai")
	private String trangThai;

	public VeXemPhim() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VeXemPhim(String maVe, String maKhachHang, String maLichChieu, int tongSoGhe, float tongTien, String ngayDat,
			String trangThai) {
		super();
		this.maVe = maVe;
		this.maKhachHang = maKhachHang;
		this.maLichChieu = maLichChieu;
		this.tongSoGhe = tongSoGhe;
		this.tongTien = tongTien;
		this.ngayDat = ngayDat;
		this.trangThai = trangThai;
	}

	public String getMaVe() {
		return maVe;
	}

	public void setMaVe(String maVe) {
		this.maVe = maVe;
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getMaLichChieu() {
		return maLichChieu;
	}

	public void setMaLichChieu(String maLichChieu) {
		this.maLichChieu = maLichChieu;
	}

	public int getTongSoGhe() {
		return tongSoGhe;
	}

	public void setTongSoGhe(int tongSoGhe) {
		this.tongSoGhe = tongSoGhe;
	}

	public float getTongTien() {
		return tongTien;
	}

	public void setTongTien(float tongTien) {
		this.tongTien = tongTien;
	}

	public String getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(String ngayDat) {
		this.ngayDat = ngayDat;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
}
