package Model;

public class LoaiPhim {

	@DBTable(columnName = "MaLoai")
	private String maLoai;
	
	@DBTable(columnName = "TenLoai")
	private String tenLoai;
	
	@DBTable(columnName = "MoTa")
	private String moTa;

	public LoaiPhim() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoaiPhim(String maLoai, String tenLoai, String moTa) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
		this.moTa = moTa;
	}

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	
}
