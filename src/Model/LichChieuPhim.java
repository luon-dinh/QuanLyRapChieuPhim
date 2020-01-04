package Model;

public class LichChieuPhim {

	@DBTable(columnName = "MaLichChieu")
	private String maLichChieu;
	
	@DBTable(columnName = "MaPhongChieu")
	private String maPhongChieu;
	
	@DBTable(columnName = "MaPhim")
	private String maPhim;
	
	@DBTable(columnName = "NgayChieu")
	private String ngayChieu;
	
	@DBTable(columnName = "GioBatDau")
	private String gioBatDau;
	
	@DBTable(columnName = "GiaVe")
	private int giaVe;
	
	@DBTable(columnName = "ThoiLuong")
	private int thoiLuong;
	
	@DBTable(columnName = "SoVeToiDa")
	private int soVeToiDa;

	public LichChieuPhim() {
		super();
	}

	public LichChieuPhim(String maLichChieu, String maPhongChieu, String maPhim, String ngayChieu, String gioBatDau,
			int giaVe, int thoiLuong, int soVeToiDa) {
		super();
		this.maLichChieu = maLichChieu;
		this.maPhongChieu = maPhongChieu;
		this.maPhim = maPhim;
		this.ngayChieu = ngayChieu;
		this.gioBatDau = gioBatDau;
		this.giaVe = giaVe;
		this.thoiLuong = thoiLuong;
		this.soVeToiDa = soVeToiDa;
	}

	public int getGiaVe() {
		return giaVe;
	}

	public void setGiaVe(int giaVe) {
		this.giaVe = giaVe;
	}

	public String getMaLichChieu() {
		return maLichChieu;
	}

	public void setMaLichChieu(String maLichChieu) {
		this.maLichChieu = maLichChieu;
	}

	public String getMaPhongChieu() {
		return maPhongChieu;
	}

	public void setMaPhongChieu(String maPhongChieu) {
		this.maPhongChieu = maPhongChieu;
	}

	public String getMaPhim() {
		return maPhim;
	}

	public void setMaPhim(String maPhim) {
		this.maPhim = maPhim;
	}

	public String getNgayChieu() {
		return ngayChieu;
	}

	public void setNgayChieu(String ngayChieu) {
		this.ngayChieu = ngayChieu;
	}

	public String getGioBatDau() {
		return gioBatDau;
	}

	public void setGioBatDau(String gioBatDau) {
		this.gioBatDau = gioBatDau;
	}

	public int getThoiLuong() {
		return thoiLuong;
	}

	public void setThoiLuong(int thoiLuong) {
		this.thoiLuong = thoiLuong;
	}

	public int getSoVeToiDa() {
		return soVeToiDa;
	}

	public void setSoVeToiDa(int soVeToiDa) {
		this.soVeToiDa = soVeToiDa;
	}
	
}
