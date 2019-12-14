package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import plugin.AlertBox;
import plugin.MyWindows;
import plugin.SceneController;
import usercontrol.control.AddEditInfo;

public class AccountController implements Initializable {
	@FXML private Line line;
	@FXML private BorderPane pane;

	private AddEditInfo EditInfo = new AddEditInfo("Cập nhật thông tin cá nhân");
	private AddEditInfo EditNickname = new AddEditInfo("Thay đổi tên hiển thị");
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		line.startXProperty().bind(pane.getCenter().layoutXProperty().subtract(14));
		line.endXProperty().bind(pane.getCenter().layoutXProperty().subtract(14));
		line.setStartY(40);
		line.endYProperty().bind(pane.heightProperty().add(4));
		String[] tmp = {"Họ và tên","Giới tính","Ngày sinh","Email","Số điện thoại","Địa chỉ","Ngày vào làm"};
		EditInfo.AddAll(tmp);
		EditNickname.Add("Tên nickname mới");
	}

	@FXML void UpdateInfo(ActionEvent event) {
		EditInfo.show();
	}

	@FXML void Logout(ActionEvent event) {
		application.MainController.SelectedButton.setValue("Home");
		plugin.SceneController.GetInstance().TryReplaceScene("Login");
	}

	@FXML void ChangePass(ActionEvent event) {
		MyWindows w = new MyWindows("../view/ChangePassword.fxml");
		w.Show();
	}

	@FXML void ChangeNickname(ActionEvent event) {
		EditNickname.show();
	}

	@FXML void ChangeAvatar(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.showOpenDialog(SceneController.GetInstance().getCurrentStage());
	}

	@FXML void CopyEmail(ActionEvent event) {
		AlertBox.show(AlertType.INFORMATION, "Thông tin", "Đã chép vào bộ nhớ tạm");
	}
}

