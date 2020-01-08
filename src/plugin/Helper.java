package plugin;

import java.util.HashMap;

public final class Helper {
	private Helper() {
	}

	public static String FXMLlocation(String name) {
		return "../view/" + name + ".fxml";
	}
	
	public static void Init() {
		// Load từ database or load = code
		HomeMenuInfo.put("Home", "Trang chủ");
		HomeMenuDescription.put("Home", "Xem trang tổng quan");
		
		HomeMenuInfo.put("Schedule", "Lịch chiếu phim");
		HomeMenuDescription.put("Schedule", "Xem, quản lý, chỉnh sửa lịch chiếu phim");
		
		HomeMenuInfo.put("Movies", "Phim bộ");
		HomeMenuDescription.put("Movies", "Xem, quản lý, chỉnh sửa các bộ phim");
		
		HomeMenuInfo.put("Rooms", "Phòng");
		HomeMenuDescription.put("Rooms", "Xem, quản lý phòng chiếu, thiết bị chiếu");
		
		HomeMenuInfo.put("Customer", "Khách hàng");
		HomeMenuDescription.put("Customer", "Xem, quản lý tập khách hàng");
		
		HomeMenuInfo.put("Account", "Tài khoản");
		HomeMenuDescription.put("Account", "Xem thông tin, cập nhật tài khoản cá nhân");
		
		HomeMenuInfo.put("CustomerCare", "Chăm sóc khách hàng");
		HomeMenuDescription.put("CustomerCare", "Phản hồi xử lý phản hồi");
		
		HomeMenuInfo.put("Service", "Dịch vụ");
		HomeMenuDescription.put("Service", "Mua thức ăn, nước uống, combo");
		
		HomeMenuInfo.put("Statistic","Thống kê");
		HomeMenuDescription.put("Statistic","Thống kê doanh thu theo thời gian");
		
		HomeMenuInfo.put("Staff","Nhân viên");
		HomeMenuDescription.put("Staff","Xem, quản lí danh sách nhân viên");
		
		HomeMenuInfo.put("History","Lịch sử đặt vé");
		HomeMenuDescription.put("History","Xem thông tin các vé đã đặt");
	}
	
	public static HashMap<String, String> HomeMenuInfo = new HashMap<>();
	public static HashMap<String, String> HomeMenuDescription = new HashMap<>();
}
