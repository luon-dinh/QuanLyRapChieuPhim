package plugin;

import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

public final class ImagesControler {
	private ImagesControler() {
	}
	private static ImagesControler Instance = null; 
	public static ImagesControler getInstance() {
		if (Instance == null)
			Instance = new ImagesControler();
		return Instance;
	}
	
	private static HashMap<String, Image> images = new HashMap<>();
	private static HashMap<String, Background> backgrounds = new HashMap<>();

	public Image getImage(String key) throws Exception {
		Image ret = images.get(key);
		if (ret == null) { 
			Add(key);
			ret = images.get(key);
		}
		return ret;
	}
	
	public Image tryGetImage(String key) {
		try {
			return getImage(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Background getBackground(String key) throws Exception {
		Background ret = backgrounds.get(key);
		if (ret == null) { 
			Add(key);
			ret = backgrounds.get(key);
		}
		return ret;
	}
	
	public Background tryGetBackground(String key) {
		try {
			return getBackground(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void Add(String key) throws Exception {
		try {
			Image image = new Image(getClass().getResource("../images/" + key + ".png").toExternalForm());
			images.put(key, image);
			backgrounds.put(key, new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		} catch (Exception e) {
			throw new Exception("Không tìm thấy hình ảnh " + key + "tại ../images/" + key + ".png");
		}
	}
}
