package Model;

public class HoaDon {

	@DBTable(columnName = "MaHoaDon")
	private int maHoaDon;
	
	@DBTable(columnName = "TongTien")
	private double tongTien;
	
	@DBTable(columnName = "NgayDat")
	private String ngayDat;
	
	public HoaDon(int maHoaDon, double tongTien, String ngayDat) {
		super();
		this.maHoaDon = maHoaDon;
		this.tongTien = tongTien;
		this.ngayDat = ngayDat;
	}

	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
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
