package Model;

public class TaiKhoanNganHang {

	@DBTable(columnName = "SoTaiKhoan")
	private String soTaiKhoan;
	
	@DBTable(columnName = "MatKhau")
	private String matKhau;
	
	@DBTable(columnName = "TenNganHang")
	private String tenNganHang;
	
	@DBTable(columnName = "TenChuSoHuu")
	private String tenChuSoHuu;
	
	@DBTable(columnName = "NgayTao")
	private String ngaytao;
	
	@DBTable(columnName = "SoDu")
	private float soDu;
	
}
