package Model;

import java.util.Date;

public class TaiKhoan {

	@DBTable(columnName = "MaTaiKhoan")
	private String maTaiKhoan;

	@DBTable(columnName = "TenDangNhap")
	private String tenDangNhap;

	@DBTable(columnName = "MatKhau")
	private String matKhau;

	@DBTable(columnName = "LoaiTaiKhoan")
	private String loaiTaiKhoan;

	@DBTable(columnName = "NgayTao")
	private String ngayTao;

	@DBTable(columnName = "TrangThai")
	private String trangThai;

	@DBTable(columnName = "TenHienThi")
	private String tenHienThi;

	@DBTable(columnName = "HinhAnh")
	private byte[] hinhAnh;

	public byte[] getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public TaiKhoan() {
		super();
	}

	public TaiKhoan(String maTaiKhoan, String tenDangNhap, String matKhau, String loaiTaiKhoan, String ngayTao,
			String trangThai,String tenHienThi, byte[] hinhAnh) {
		super();
		this.maTaiKhoan = maTaiKhoan;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.loaiTaiKhoan = loaiTaiKhoan;
		this.ngayTao = ngayTao;
		this.trangThai = trangThai;
		this.hinhAnh = hinhAnh;
		this.tenHienThi = tenHienThi;
	}

	public String getTenHienThi() {
		return tenHienThi;
	}

	public void setTenHienThi(String tenHienThi) {
		this.tenHienThi = tenHienThi;
	}

	public String getMaTaiKhoan() {
		return maTaiKhoan;
	}

	public void setMaTaiKhoan(String maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getLoaiTaiKhoan() {
		return loaiTaiKhoan;
	}

	public void setLoaiTaiKhoan(String loaiTaiKhoan) {
		this.loaiTaiKhoan = loaiTaiKhoan;
	}

	public String getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(String ngayTao) {
		this.ngayTao = ngayTao;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

}
