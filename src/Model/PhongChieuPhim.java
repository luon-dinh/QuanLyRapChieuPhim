package Model;

public class PhongChieuPhim {

	@DBTable(columnName = "MaPhong")
	private String maPhong;

	@DBTable(columnName = "TenPhong")
	private String tenPhong;

	@DBTable(columnName = "TrangThai")
	private String trangThai;

	@DBTable(columnName = "SoGhe")
	private int soGhe;

	@DBTable(columnName = "SucChua")
	private int sucChua;

	@DBTable(columnName = "MoTa")
	private String moTa;

	@DBTable(columnName = "HinhAnh")
	private byte[] hinhAnh;

	public String getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public PhongChieuPhim(String maPhong, String tenPhong, String trangThai, int soGhe, int sucChua, String moTa,
			byte[] hinhAnh) {
		super();
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.trangThai = trangThai;
		this.soGhe = soGhe;
		this.sucChua = sucChua;
		this.moTa = moTa;
		this.hinhAnh = hinhAnh;
	}

	public PhongChieuPhim() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getSoGhe() {
		return soGhe;
	}

	public void setSoGhe(int soGhe) {
		this.soGhe = soGhe;
	}

	public int getSucChua() {
		return sucChua;
	}

	public void setSucChua(int sucChua) {
		this.sucChua = sucChua;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public byte[] getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

}
