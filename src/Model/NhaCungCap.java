package Model;

public class NhaCungCap {
	
	@DBTable(columnName = "MaNhaCungCap")
	private String maNhaCungCap;
	
	@DBTable(columnName = "TenNhaCungCap")
	private String tenNhaCungCap;
	
	@DBTable(columnName = "DiaChi")
	private String diaChi;
	
	@DBTable(columnName = "Email")
	private String email;
	
	public NhaCungCap() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NhaCungCap(String maNhaCungCap, String tenNhaCungCap, String diaChi, String email, String soDienThoai) {
		super();
		this.maNhaCungCap = maNhaCungCap;
		this.tenNhaCungCap = tenNhaCungCap;
		this.diaChi = diaChi;
		this.email = email;
		this.soDienThoai = soDienThoai;
	}

	public String getMaNhaCungCap() {
		return maNhaCungCap;
	}

	public void setMaNhaCungCap(String maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}

	public String getTenNhaCungCap() {
		return tenNhaCungCap;
	}

	public void setTenNhaCungCap(String tenNhaCungCap) {
		this.tenNhaCungCap = tenNhaCungCap;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	@DBTable(columnName = "SoDienThoai")
	private String soDienThoai;

}
