package Model;

public class VeXemPhim {

	
	@DBTable(columnName = "MaVe")
	private int maVe;
	
	@DBTable(columnName = "MaTaiKhoan")
	private String maTaiKhoan;
	
	@DBTable(columnName = "MaLichChieu")
	private String maLichChieu;
	
	@DBTable(columnName = "TongSoGhe")
	private int tongSoGhe;
	
	@DBTable(columnName = "TongTien")
	private  double tongTien;
	
	@DBTable(columnName = "NgayDat")
	private String ngayDat;
	
	@DBTable(columnName = "TrangThai")
	private String trangThai;

	public VeXemPhim() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VeXemPhim(int maVe, String maTaiKhoan, String maLichChieu, int tongSoGhe, double tongTien, String ngayDat,
			String trangThai) {
		super();
		this.maVe = maVe;
		this.maTaiKhoan = maTaiKhoan;
		this.maLichChieu = maLichChieu;
		this.tongSoGhe = tongSoGhe;
		this.tongTien = tongTien;
		this.ngayDat = ngayDat;
		this.trangThai = trangThai;
	}

	public int getMaVe() {
		return maVe;
	}

	public void setMaVe(int maVe) {
		this.maVe = maVe;
	}

	public String getMaTaiKhoan() {
		return maTaiKhoan;
	}

	public void setMaTaiKhoan(String maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
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

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
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
