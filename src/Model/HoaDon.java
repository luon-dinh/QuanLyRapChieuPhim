package Model;

public class HoaDon {

	@DBTable(columnName = "MaHoaDon")
	private int maHoaDon;
	
	@DBTable(columnName = "TongTien")
	private double tongTien;
	
	@DBTable(columnName = "NgayDat")
	private String ngayDat;
	
	@DBTable(columnName = "MaLichChieu")
	private String maLichChieu;
	
	@DBTable(columnName = "MaTaiKhoan")
	private String maTaiKhoan;
	


	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HoaDon(int maHoaDon, double tongTien, String ngayDat, String maLichChieu, String maTaiKhoan) {
		super();
		this.maHoaDon = maHoaDon;
		this.tongTien = tongTien;
		this.ngayDat = ngayDat;
		this.maLichChieu = maLichChieu;
		this.maTaiKhoan = maTaiKhoan;
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

	public int getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(int maHoaDon) {
		this.maHoaDon = maHoaDon;
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
	
}
