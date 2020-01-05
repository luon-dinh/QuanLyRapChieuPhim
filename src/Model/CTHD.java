package Model;

public class CTHD {
	
	@DBTable(columnName = "MaHoaDon")
	private int maHoaDon;
	
	@DBTable(columnName = "MaSanPham")
	private String maSanPham;
	
	@DBTable(columnName = "SoLuong")
	private int soLuong;

	public CTHD(int maHoaDon, String maSanPham, int soLuong) {
		super();
		this.maHoaDon = maHoaDon;
		this.maSanPham = maSanPham;
		this.soLuong = soLuong;
	}

	public CTHD() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(int maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public String getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
}
