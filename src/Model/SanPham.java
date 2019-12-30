package Model;

public class SanPham {
	
	@DBTable(columnName = "MaSanPham")
	private String maSanPham;
	
	@DBTable(columnName = "MaNhaCungCap")
	private String maNhaCungCap;
	
	@DBTable(columnName = "TenSanPham")
	private String tenSanPham;
	
	@DBTable(columnName = "GiaSanPham")
	private int giaSanPham;
	
	@DBTable(columnName = "MoTa")
	private String moTa;
	
	public SanPham() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SanPham(String maSanPham, String maNhaCungCap, String tenSanPham, int giaSanPham, String moTa) {
		super();
		this.maSanPham = maSanPham;
		this.maNhaCungCap = maNhaCungCap;
		this.tenSanPham = tenSanPham;
		this.giaSanPham = giaSanPham;
		this.moTa = moTa;
	}

	public String getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	public String getMaNhaCungCap() {
		return maNhaCungCap;
	}

	public void setMaNhaCungCap(String maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public int getGiaSanPham() {
		return giaSanPham;
	}

	public void setGiaSanPham(int giaSanPham) {
		this.giaSanPham = giaSanPham;
	}
	
	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

}
