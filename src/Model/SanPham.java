package Model;

public class SanPham {
	
	@DBTable(columnName = "MaSanPham")
	private String maSanPham;
	
	@DBTable(columnName = "MaNhaCungCap")
	private String maNhaCungCap;
	
	@DBTable(columnName = "TenSanPham")
	private String tenSanPham;
	
	@DBTable(columnName = "GiaSanPham")
	private float giaSanPham;

	public SanPham() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SanPham(String maSanPham, String maNhaCungCap, String tenSanPham, float giaSanPham) {
		super();
		this.maSanPham = maSanPham;
		this.maNhaCungCap = maNhaCungCap;
		this.tenSanPham = tenSanPham;
		this.giaSanPham = giaSanPham;
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

	public float getGiaSanPham() {
		return giaSanPham;
	}

	public void setGiaSanPham(float giaSanPham) {
		this.giaSanPham = giaSanPham;
	}

}
