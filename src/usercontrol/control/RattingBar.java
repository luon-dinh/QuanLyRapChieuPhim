package usercontrol.control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import plugin.ImagesControler;
import plugin.SimpleRangeFloatProperty;
import plugin.SimpleRangeIntProperty;

public class RattingBar extends HBox implements Initializable {
	@FXML private HBox hbox;
	@FXML private ImageView Ratting1;
    @FXML private ImageView Ratting2;
    @FXML private ImageView Ratting3;
    @FXML private ImageView Ratting4;
    @FXML private ImageView Ratting5;
    @FXML private Label Info;    

	private static final Image NonVote = ImagesControler.getInstance().tryGetImage("StarRattingUnselected");
	private static final Image Vote = ImagesControler.getInstance().tryGetImage("StarRattingSelected");

	public SimpleBooleanProperty readOnly = new SimpleBooleanProperty(false);
	public SimpleBooleanProperty showVote = new SimpleBooleanProperty(true);
	public SimpleRangeIntProperty vote = new SimpleRangeIntProperty(0, Integer.MAX_VALUE);

	private SimpleRangeIntProperty intRatting = new SimpleRangeIntProperty(0, 5);
	public SimpleRangeFloatProperty ratting = new SimpleRangeFloatProperty(0f,5f);
	
	public SimpleStringProperty info = new SimpleStringProperty("");
	public SimpleDoubleProperty starSize = new SimpleDoubleProperty(30);
	
	public ImageView[] bar;
	
    public RattingBar() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/RattingBar.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    @FXML
    void ChangeRatting(MouseEvent event) {
    	intRatting.set(Integer.parseInt(((ImageView)event.getSource()).getUserData().toString()));
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		bar = new ImageView[5];
		bar[0] = Ratting1;
		bar[1] = Ratting2;
		bar[2] = Ratting3;
		bar[3] = Ratting4;
		bar[4] = Ratting5;
		
		for (int i = 0; i < 5; i++) {
			bar[i].setImage(NonVote);
			bar[i].fitHeightProperty().bind(starSize);
			bar[i].fitWidthProperty().bind(starSize);
		}
		
		hbox.disableProperty().bind(readOnly);
		intRatting.addListener((observable, oldValue, newValue) -> {
			if (oldValue == newValue)
				return;
			int oldv = (int)oldValue, newv = (int)newValue;
			if (oldv < newv)
				for (int i = oldv; i < newv; i++)
					bar[i].setImage(Vote);
			else
				for (int i = newv; i < oldv; i++)
					bar[i].setImage(NonVote);
		});
		ratting.addListener((observable, oldValue, newValue) -> {
			if (oldValue == newValue)
				return;
			intRatting.set((int) Math.round((float) newValue));
		});
		Info.textProperty().bind(Bindings.when(showVote).then(info).otherwise(""));
		UpdateInfo();
	}
    
    private void UpdateInfo() {
    	info.set("(" + ratting.getValue().toString() + " - " + vote.get() + " vote)");
    }
}
