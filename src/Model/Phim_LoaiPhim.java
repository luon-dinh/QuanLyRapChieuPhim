package Model;

public class Phim_LoaiPhim {
	
	@DBTable(columnName = "MaPhim")
	private String maPhim;
	
	@DBTable(columnName = "MaLoai")
	private String maLoai;

	public Phim_LoaiPhim() {
		super();
	}

	public Phim_LoaiPhim(String maPhim, String maLoai) {
		super();
		this.maPhim = maPhim;
		this.maLoai = maLoai;
	}

	public String getMaPhim() {
		return maPhim;
	}

	public void setMaPhim(String maPhim) {
		this.maPhim = maPhim;
	}

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

}
