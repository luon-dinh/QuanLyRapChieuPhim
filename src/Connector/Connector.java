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
import Model.Phim;
import Model.PhongChieuPhim;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
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

	
	public int update(String query, byte[] bytes) {
		int result=0;
		try {
			connect();
			PreparedStatement pst=connection.prepareStatement(query);
			pst.setBytes(1, bytes);
			result=pst.executeUpdate();
			connection.close();
		}
		catch (SQLException e) {
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

	
	public int insert(String query,byte[] bytes) {
		int result=0;
		try {
			connect();
			PreparedStatement pst=connection.prepareStatement(query);
			pst.setBytes(1, bytes);
			result=pst.executeUpdate();
			connection.close();
		}
		catch (SQLException e) {
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
				float rating=rs.getFloat("Rating");
				dsPhim.add(new Phim(maPhim,tenPhim,namSanXuat,thoiLuong,daoDien,nuocSanXuat,moTa,hinhAnh,rating));
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsPhim;
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
		Image theImage = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			BufferedImage bImage2 = ImageIO.read(bis);
//			ImageIO.write(bImage2, "jpg", new File("output.jpg") );
			theImage = SwingFXUtils.toFXImage(bImage2, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theImage;
	}

}
