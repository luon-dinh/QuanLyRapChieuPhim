package Connector;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.prism.Graphics;

import Model.DBTable;
import Model.LoaiPhim;
import Model.Phim;
import Model.Phim_LoaiPhim;
import Model.PhongChieuPhim;
import Model.TaiKhoan;
import Model.VeXemPhim;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Connector<T> {

	public static Connection connection = null;

	public Connection connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:QuanLyRapChieuPhim.db");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	@SuppressWarnings("unchecked")
	public List<T> select(Class<T> type, String query) {
		Statement statement;
		ResultSet result = null;
		List<T> list = new ArrayList<T>();
		try {
			connect();
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			while (result.next()) {
				T t = type.newInstance();
				loadResultSetIntoObject(result, t);
				list.add(t);
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Integer> selectTicket(Class<T> type, String query) {
		Statement statement;
		ResultSet result = null;
		List<Integer> list = new ArrayList<Integer>();
		try {
			connect();
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			while (result.next()) {
				list.add(result.getInt("TongTien"));
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public int update(String query, byte[] bytes) {
		int result = 0;
		try {
			connect();
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setBytes(1, bytes);
			result = pst.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int update(String query) {
		Statement statement;
		int result = 0;
		try {
			connect();
			statement = connection.createStatement();
			result = statement.executeUpdate(query);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int insert(String query, byte[] bytes) {
		int result = 0;
		try {
			connect();
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setBytes(1, bytes);
			result = pst.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int insert(String query) {
		Statement statement;
		int result = 0;
		try {
			connect();
			statement = connection.createStatement();
			result = statement.executeUpdate(query);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int delete(String query) {
		Statement statement;
		int result = 0;
		try {
			connect();
			statement = connection.createStatement();
			result = statement.executeUpdate(query);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// select dành cho các class có hình ảnh
	public ArrayList<Phim> selectPhim(String query) {
		Statement statement;
		ResultSet rs;
		ArrayList<Phim> dsPhim = new ArrayList<Phim>();
		try {
			connect();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				String maPhim = rs.getString("MaPhim");
				String tenPhim = rs.getString("TenPhim");
				int namSanXuat = rs.getInt("NamSanXuat");
				String nuocSanXuat = rs.getString("TenNuocSanXuat");
				String thoiLuong = rs.getString("ThoiLuong");
				String daoDien = rs.getString("TenDaoDien");
				String moTa = rs.getString("MoTa");
				byte[] hinhAnh = rs.getBytes("HinhAnh");
				float rating = rs.getFloat("Rating");
				int numberVote = rs.getInt("NumberVote");
				dsPhim.add(new Phim(maPhim, tenPhim, namSanXuat, thoiLuong, daoDien, nuocSanXuat, moTa, hinhAnh, rating,
						numberVote));
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsPhim;
	}

	public String getTenPhimByMaPhim(String maPhim) {
		List<Phim> dsPhim = selectPhim("select * from PHIM where MaPhim='" + maPhim + "'");
		if (dsPhim.size() == 0)
			return null;
		return dsPhim.get(0).getTenPhim();
	}

	public String getTenPhongByMaPhong(String maPhong) {
		List<PhongChieuPhim> dsPhongChieuPhims = new Connector().select(PhongChieuPhim.class,
				"select * from PHONGCHIEUPHIM where MaPhong='" + maPhong + "'");
		if (dsPhongChieuPhims.size() == 0)
			return null;
		return dsPhongChieuPhims.get(0).getTenPhong();
	}

	public Image getimageByMaPhim(String maPhim) {
		List<Phim> dsPhim = selectPhim("select * from PHIM where MaPhim='" + maPhim + "'");
		if (dsPhim.size() == 0)
			return null;
		return convertToBufferImage(dsPhim.get(0).getHinhAnh());
	}

	// select dành cho các class có hình ảnh
	public ArrayList<PhongChieuPhim> selectPhongChieuPhim(String query) {
		Statement statement;
		ResultSet rs;
		ArrayList<PhongChieuPhim> dsPhongChieuPhim = new ArrayList<PhongChieuPhim>();
		try {
			connect();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				String maPhongChieuPhim = rs.getString("MaPhong");
				String tenPhongChieuPhim = rs.getString("TenPhong");
				String trangThai = rs.getString("TrangThai");
				int soGhe = rs.getInt("SoGhe");
				int sucChua = rs.getInt("SucChua");
				String moTa = rs.getString("MoTa");
				byte[] hinhAnh = rs.getBytes("HinhAnh");
				dsPhongChieuPhim.add(new PhongChieuPhim(maPhongChieuPhim, tenPhongChieuPhim, trangThai, soGhe, sucChua,
						moTa, hinhAnh));
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsPhongChieuPhim;
	}

	
	
	//tai khoan
	public ArrayList<TaiKhoan> selectTaiKhoan(String query) {
		Statement statement;
		ResultSet rs;
		ArrayList<TaiKhoan> dsTaiKhoan = new ArrayList<TaiKhoan>();
		try {
			connect();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				String maTaiKhoan = rs.getString("MaTaiKhoan");
				String tenDangNhap = rs.getString("TenDangNhap");
				String matKhau = rs.getString("MatKhau");
				String loaiTaiKhoan =rs.getString("LoaiTaiKhoan");
				String ngayTao = rs.getString("NgayTao");
				String trangThai = rs.getString("TrangThai");
				String tenHienThi = rs.getString("TenHienThi");
				byte[] hinhAnh = rs.getBytes("HinhAnh");

				dsTaiKhoan.add(new TaiKhoan(maTaiKhoan,tenDangNhap,matKhau,loaiTaiKhoan,ngayTao,trangThai,tenHienThi,hinhAnh));
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsTaiKhoan;
	}

	public static ArrayList<String> getLoaiPhimByMaPhim(String maPhim) {
		ArrayList<LoaiPhim> dsLoaiPhim = new ArrayList<LoaiPhim>();
		ArrayList<Phim_LoaiPhim> dsPhimLoaiPhim = new ArrayList<Phim_LoaiPhim>();
		ArrayList<String> result = new ArrayList<String>();
		dsPhimLoaiPhim.addAll(new Connector().select(Phim_LoaiPhim.class,
				"select * from PHIM_LOAIPHIM where MaPhim='" + maPhim + "'"));
		for (Phim_LoaiPhim plp : dsPhimLoaiPhim) {
			dsLoaiPhim.addAll(new Connector().select(LoaiPhim.class,
					"select * from LOAIPHIM where MaLoai='" + plp.getMaLoai() + "'"));
		}
		for (LoaiPhim lp : dsLoaiPhim) {
			result.add(lp.getTenLoai());
		}
		return result;
	}

	public static void loadResultSetIntoObject(ResultSet rst, Object object)
			throws IllegalArgumentException, IllegalAccessException, SQLException {
		Class<?> zclass = object.getClass();
		for (Field field : zclass.getDeclaredFields()) {
			field.setAccessible(true);
			DBTable column = field.getAnnotation(DBTable.class);
			Object value = rst.getObject(column.columnName());
			Class<?> type = field.getType();
			if (isPrimitive(type)) {// check primitive type(Point 5)
				Class<?> boxed = boxPrimitiveClass(type);// box if primitive(Point 6)
				value = boxed.cast(value);
			}
			field.set(object, value);
		}
	}

	public static boolean isPrimitive(Class<?> type) {
		return (type == int.class || type == long.class || type == double.class || type == float.class
				|| type == boolean.class || type == byte.class || type == char.class || type == short.class);
	}

	public static Class<?> boxPrimitiveClass(Class<?> type) {
		if (type == int.class) {
			return Integer.class;
		} else if (type == long.class) {
			return Long.class;
		} else if (type == double.class) {
			return Double.class;
		} else if (type == float.class) {
			return Float.class;
		} else if (type == boolean.class) {
			return Boolean.class;
		} else if (type == byte.class) {
			return Byte.class;
		} else if (type == char.class) {
			return Character.class;
		} else if (type == short.class) {
			return Short.class;
		} else {
			String string = "class '" + type.getName() + "' is not a primitive";
			throw new IllegalArgumentException(string);
		}
	}

	public static byte[] convertFileToByte(File image) {

		FileInputStream fis;
		byte[] image_user = null;
		try {
			fis = new FileInputStream(image);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for (int readNum; (readNum = fis.read(buf)) != -1;) {
				bos.write(buf, 0, readNum);
			}
			image_user = bos.toByteArray();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return image_user;
	}

	public static Image convertToBufferImage(byte[] data) {
		Image img = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			BufferedImage bImage2 = ImageIO.read(bis);
//			ImageIO.write(bImage2, "jpg", new File("output.jpg") );
			img = SwingFXUtils.toFXImage(bImage2, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
		return img;
	}

	public static void setImage(ImageView image, Image img) {
		Rectangle2D croppedPortion;
		if (img.getWidth() / 180.0 > img.getHeight() / 250.0) {

			croppedPortion = new Rectangle2D(img.getWidth() / 2.0 - img.getHeight() / 250.0 * 90.0, 0,
					img.getHeight() / 250.0 * 180.0, img.getHeight());

		} else {
			croppedPortion = new Rectangle2D(0, img.getHeight() / 2.0 - img.getWidth() / 180.0 * 125.0, img.getWidth(),
					img.getWidth() / 180.0 * 250.0);
		}

		// target width and height:
		double scaledWidth = 180;
		double scaledHeight = 250;
		image.setImage(img);
		image.setViewport(croppedPortion);
		image.setFitWidth(scaledWidth);
		image.setFitHeight(scaledHeight);
		image.setSmooth(false);
	}

}
