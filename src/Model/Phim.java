package Model;

import java.sql.Blob;

public class Phim {

	@DBTable(columnName = "MaPhim")
	private String maPhim;
	
	@DBTable(columnName = "TenPhim")
	private String tenPhim;
	
	@DBTable(columnName = "NamSanXuat")
	private int namSanXuat;
	
	public int getNamSanXuat() {
		return namSanXuat;
	}

	public void setNamSanXuat(int namSanXuat) {
		this.namSanXuat = namSanXuat;
	}

	@DBTable(columnName = "TheLoai")
	private String theLoai;
	
	public String getTheLoai() {
		return theLoai;
	}

	public void setTheLoai(String theLoai) {
		this.theLoai = theLoai;
	}

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

	

	public Phim() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Phim(String maPhim, String tenPhim, int namSanXuat, String theLoai, String thoiLuong, String tenDaoDien,
			String tenNuocSanXuat, String mota, byte[] hinhAnh) {
		super();
		this.maPhim = maPhim;
		this.tenPhim = tenPhim;
		this.namSanXuat = namSanXuat;
		this.theLoai = theLoai;
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
