package Model;


public class Phim {

	public Phim() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Phim(String maPhim, String tenPhim, int namSanXuat, String thoiLuong, String tenDaoDien,
			String tenNuocSanXuat, String mota, byte[] hinhAnh, float rating, int numberVote) {
		super();
		this.maPhim = maPhim;
		this.tenPhim = tenPhim;
		this.namSanXuat = namSanXuat;
		this.thoiLuong = thoiLuong;
		this.tenDaoDien = tenDaoDien;
		this.tenNuocSanXuat = tenNuocSanXuat;
		this.mota = mota;
		this.hinhAnh = hinhAnh;
		this.rating = rating;
		this.numberVote = numberVote;
	}

	@DBTable(columnName = "MaPhim")
	private String maPhim;
	
	@DBTable(columnName = "TenPhim")
	private String tenPhim;
	
	@DBTable(columnName = "NamSanXuat")
	private int namSanXuat;

	@DBTable(columnName = "ThoiLuong")
	private String thoiLuong;
	
	@DBTable(columnName = "TenDaoDien")
	private String tenDaoDien;
	
	@DBTable(columnName = "TenNuocSanXuat")
	private String tenNuocSanXuat;
	
	@DBTable(columnName = "MoTa")
	private String mota;
	
	@DBTable(columnName = "HinhAnh")
	private byte[] hinhAnh;

	@DBTable(columnName = "Rating")
	private double rating;

	@DBTable(columnName = "NumberVote")
	private int numberVote;
	
	public int getNamSanXuat() {
		return namSanXuat;
	}

	public void setNamSanXuat(int namSanXuat) {
		this.namSanXuat = namSanXuat;
	}
	
	public int getNumberVote() {
		return numberVote;
	}

	public void setNumberVote(int numberVote) {
		this.numberVote = numberVote;
	}

	public double getRating() {
		return rating;
	}

//	public void setRating(float rating) {
//		this.rating = rating;
//	}

	public void setRating(Number rating) {
		this.rating = rating.floatValue();
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

	public String getThoiLuong() {
		return thoiLuong;
	}

	public void setThoiLuong(String thoiLuong) {
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

	public byte[] getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

}
