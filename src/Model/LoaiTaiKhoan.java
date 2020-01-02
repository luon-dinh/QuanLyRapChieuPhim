package Model;

import java.util.ArrayList;
import java.util.Collection;

public class LoaiTaiKhoan {
	public static ArrayList<String> loais=new ArrayList<String>();
	public static ArrayList<String> get(){
		loais.add("user");
		loais.add("admin");
		loais.add("staff");
		return loais;
	}
}
