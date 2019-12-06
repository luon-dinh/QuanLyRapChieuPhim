package Model;

public class Phim {

	@DBTable(columnName = "MaPhim")
	private String maPhim;
	
	@DBTable(columnName = "TenPhim")
	private String tenPhim;
	
	@DBTable(columnName = "ThoiLuong")
	private int thoiLuong;
	
	@DBTable(columnName = "TenDaoDien")
	private String tenDaoDien;
	
	@DBTable(columnName = "TenNuocSanXuat")
	private String tenNuocSanXuat;
	
	@DBTable(columnName = "MoTa")
	private String mota;
	
	@DBTable(columnName = "HinhAnh")
	private String hinhAnh;

	public Phim() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Phim(String maPhim, String tenPhim, int thoiLuong, String tenDaoDien, String tenNuocSanXuat, String mota,
			String hinhAnh) {
		super();
		this.maPhim = maPhim;
		this.tenPhim = tenPhim;
		this.thoiLuong = thoiLuong;
		this.tenDaoDien = tenDaoDien;
		this.tenNuocSanXuat = tenNuocSanXuat;
		this.mota = mota;
		this.hinhAnh = hinhAnh;
	}

	public String getMaPhim() {
		return maPhim;
	}

	public void setMaPhim(String maPhim) {
		this.maPhim = maPhim;
	}

	public String getTenPhim() {
		return tenPhim;
	}

	public void setTenPhim(String tenPhim) {
		this.tenPhim = tenPhim;
	}

	public int getThoiLuong() {
		return thoiLuong;
	}

	public void setThoiLuong(int thoiLuong) {
		this.thoiLuong = thoiLuong;
	}

	public String getTenDaoDien() {
		return tenDaoDien;
	}

	public void setTenDaoDien(String tenDaoDien) {
		this.tenDaoDien = tenDaoDien;
	}

	public String getTenNuocSanXuat() {
		return tenNuocSanXuat;
	}

	public void setTenNuocSanXuat(String tenNuocSanXuat) {
		this.tenNuocSanXuat = tenNuocSanXuat;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

}
