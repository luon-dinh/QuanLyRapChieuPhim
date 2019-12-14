package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import plugin.MyWindows;
import usercontrol.control.AddEditInfo;

public class CustomerController implements Initializable {
	private AddEditInfo add = new AddEditInfo("Thêm khách hàng");
	private AddEditInfo edit = new AddEditInfo("Sửa khách hàng");
	@FXML private TableView<?> table;
	
    @FXML void AddAction(ActionEvent event) {
    	add.show();
    }

    @FXML void DeleteAction(ActionEvent event) {
    	MyWindows w = new MyWindows("../view/Login.fxml");
    	w.Show();
    }

    @FXML void EditAction(ActionEvent event) {
    	edit.show();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String[] tmp = { "Mã tài khoản", "Họ tên", "Email", "Số điện thoại" };
		add.AddAll(tmp);
		edit.AddAll(tmp);
	}

}
