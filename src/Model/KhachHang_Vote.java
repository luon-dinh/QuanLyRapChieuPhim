package Model;

public class KhachHang_Vote {

	@DBTable(columnName = "MaTaiKhoan")
	private String maTaiKhoan;

	@DBTable(columnName = "MaPhim")
	private String maPhim;

	@DBTable(columnName = "Vote")
	private String vote;

	public KhachHang_Vote(String maTaiKhoan, String maPhim, String vote) {
		super();
		this.maTaiKhoan = maTaiKhoan;
		this.maPhim = maPhim;
		this.vote = vote;
	}

	public String getMaTaiKhoan() {
		return maTaiKhoan;
	}

	public KhachHang_Vote() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setMaTaiKhoan(String maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
	}

	public String getMaPhim() {
		return maPhim;
	}

	public void setMaPhim(String maPhim) {
		this.maPhim = maPhim;
	}

	public String getVote() {
		return vote;
	}

	public void setVote(String vote) {
		this.vote = vote;
	}

}