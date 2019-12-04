package Connector;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import Model.DBTable;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Connector<T> {

	public static Connection connection=null;
	public Connection connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection=DriverManager.getConnection("jdbc:sqlite:Account.db");
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
	public List<T> select(Class<T> type,String query){
		Statement statement;
		ResultSet result=null;
		List<T> list = new ArrayList<T>(); 
		try {
			connect();
			statement = connection.createStatement();
			result=statement.executeQuery(query);
			while(result.next()) {
				T t=type.newInstance();
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
	
	public int update(String query) {
		Statement statement;
		int result=0;
		try {
			connect();
			statement = connection.createStatement();
			result=statement.executeUpdate(query);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int insert(String query) {
		Statement statement;
		int result=0;
		try {
			connect();
			statement = connection.createStatement();
			result=statement.executeUpdate(query);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public int insert(String ID, String username, String password, String type, String date, String status) {
		PreparedStatement statement;
		int result=0;
		try {
			connect();
			statement = connection.prepareStatement("insert into account values(?,?,?,?,?,?)");
			statement.setString(1, ID);
			statement.setString(2, username);
			statement.setString(3, password);
			statement.setString(4, type);
			statement.setString(5, date);
			statement.setString(6, status);
			result=statement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public int delete(String query) {
		Statement statement;
		int result=0;
		try {
			connect();
			statement = connection.createStatement();
			result=statement.executeUpdate(query);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	        if (isPrimitive(type)) {//check primitive type(Point 5)
	            Class<?> boxed = boxPrimitiveClass(type);//box if primitive(Point 6)
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

	
	public static Image decodeToImage(String imageString) {
		 
        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Image img = SwingFXUtils.toFXImage(image, null);
        return img;
    }
	
	public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
 
        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();
 
            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);
 
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }
}
