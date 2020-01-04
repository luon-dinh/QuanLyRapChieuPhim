package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import Connector.Connector;
import Model.PhongChieuPhim;
import Model.TaiKhoanNganHang;
import Model.VeXemPhim;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

public class StatisticController implements Initializable {
	@FXML
	CategoryAxis xAxis1;
	@FXML
	NumberAxis yAxis1;
	@FXML
	LineChart<String, Number> lineChart1;

	@FXML
	MenuButton revenueTypeMenu;

	@FXML
	MenuButton monthsMenu;
	@FXML
	MenuButton yearsMenu;

	MenuItem tong = new MenuItem();
	MenuItem phim = new MenuItem();
	MenuItem dvKhac = new MenuItem();

	private ObservableList<String> monthsList = FXCollections.observableArrayList("Tháng 1", "Tháng 2", "Tháng 3",
			"Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12");
	private ObservableList<String> defaultMonthsList = FXCollections.observableArrayList("Tháng 1", "Tháng 2",
			"Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11",
			"Tháng 12");

	private ObservableList<Integer> dayOfMonthsList = FXCollections.observableArrayList(31, 28, 31, 30, 31, 30, 31, 31,
			30, 31, 30, 31);
	static final int START_DAY = 1;
	static final int START_MONTH = 1;
	static final int START_YEAR = 2019;

	int CUR_DAY;
	int CUR_MONTH;
	int CUR_YEAR;
	int choosenYear;
	int choosenMonth;
	int choosenType;
	String Title;
	int numbersDayOfChoosenMonth;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		KhoiTaoMenu();
		KhoiTaoBieuDo();

	}

	private void KhoiTaoMenu() {
		// TODO Auto-generated method stub
		// Loai doanh thu
		tong.setText("Tổng");
		phim.setText("Doanh thu phim");
		dvKhac.setText("Doanh thu dịch vụ");

		revenueTypeMenu.getItems().add(tong);
		revenueTypeMenu.getItems().add(phim);
		revenueTypeMenu.getItems().add(dvKhac);

		LocalDate now = LocalDate.now();
		CUR_DAY = now.getDayOfMonth();
		CUR_MONTH = now.getMonthValue();
		CUR_YEAR = now.getYear();

		for (int i = START_YEAR; i <= CUR_YEAR; i++) {
			yearsMenu.getItems().add(new MenuItem(i + ""));
		}

		setUpMonthMenuFromYear(CUR_YEAR);

	}

	private void KhoiTaoBieuDo() {
		// TODO Auto-generated method stub

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		LocalDate now = LocalDate.now();
		List<LocalDate> dateList = getDatesBetweenUsingJava8(LocalDate.of(START_YEAR, START_MONTH, START_DAY), now);

		// default value
		// year
		choosenYear = now.getYear();
		yearsMenu.setText(choosenYear + "");

		// month
		choosenMonth = now.getMonthValue();
		monthsMenu.setText("Tháng " + choosenMonth);

		// title
		choosenType = 0;
		Title = "Tổng doanh thu tháng " + choosenMonth + "/" + choosenYear;
		lineChart1.setTitle(Title);

		// menu
		monthsMenu.getItems().clear();
		setUpMonthMenuFromYear(CUR_YEAR);

		// type
		revenueTypeMenu.getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				revenueTypeMenu.setText(revenueTypeMenu.getItems().get(0).getText());
				choosenType = 0;
				Title = "Tổng doanh thu tháng " + choosenMonth + "/" + choosenYear;
				lineChart1.setTitle(Title);

			}
		});

		revenueTypeMenu.getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				revenueTypeMenu.setText(revenueTypeMenu.getItems().get(1).getText());
				choosenType = 1;
				Title = "Doanh thu phim tháng " + choosenMonth + "/" + choosenYear;
				lineChart1.setTitle(Title);
			}
		});

		revenueTypeMenu.getItems().get(2).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				revenueTypeMenu.setText(revenueTypeMenu.getItems().get(2).getText());
				choosenType = 2;
				Title = "Doanh thu dịch vụ tháng " + choosenMonth + "/" + choosenYear;
				lineChart1.setTitle(Title);
			}
		});

		// year
		EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				choosenYear = Integer.parseInt(((MenuItem) e.getSource()).getText());
				yearsMenu.setText(choosenYear + "");
				if (choosenType == 0) {
					Title = "Tổng doanh thu tháng " + choosenMonth + "/" + choosenYear;
					lineChart1.setTitle(Title);
				}
				if (choosenType == 1) {
					Title = "Doanh thu phim tháng " + choosenMonth + "/" + choosenYear;
					lineChart1.setTitle(Title);
				}
				if (choosenType == 1) {
					Title = "Doanh thu dịch vụ tháng " + choosenMonth + "/" + choosenYear;
					lineChart1.setTitle(Title);
				}
				setUpMonthMenuFromYear(choosenYear);
			}
		};
		for (int i = 0; i < CUR_YEAR - START_YEAR + 1; i++)
			yearsMenu.getItems().get(i).setOnAction(event1);

		// month
		EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				choosenMonth = Integer.parseInt(((MenuItem) e.getSource()).getText().substring(6));
				monthsMenu.setText(((MenuItem) e.getSource()).getText());
				if (choosenType == 0) {
					Title = "Tổng doanh thu tháng " + choosenMonth + "/" + choosenYear;
					lineChart1.setTitle(Title);
				}
				if (choosenType == 1) {
					Title = "Doanh thu phim tháng " + choosenMonth + "/" + choosenYear;
					lineChart1.setTitle(Title);
				}
				if (choosenType == 1) {
					Title = "Doanh thu dịch vụ tháng " + choosenMonth + "/" + choosenYear;
					lineChart1.setTitle(Title);
				}
			}
		};

		for (int i = 0; i <= 11; i++)
			monthsMenu.getItems().get(i).setOnAction(event2);

		// Set up strings
		xAxis1.setLabel("Tháng");
		yAxis1.setLabel("Doanh thu");

		// Set up chart
		XYChart.Series<String, Number> sum_series = new XYChart.Series();
		XYChart.Series<String, Number> movie_series = new XYChart.Series();
		XYChart.Series<String, Number> service_series = new XYChart.Series();

		sum_series.setName("Tổng doanh thu");

		// load from database

		setUpSumChart(sum_series, choosenMonth, choosenYear);
//		sum_series.getData().add(new XYChart.Data("30", 33));
		lineChart1.getData().add((Series<String, Number>) sum_series);
	}

	@SuppressWarnings("deprecation")
	private void setUpSumChart(Series<String, Number> sum_series, int choosenMonth2, int choosenYear2) {
		// TODO Auto-generated method stub
		// load from database
		if (choosenYear2 % 4 == 0) {
			if (choosenYear2 % 100 == 0) {
				if (choosenYear2 % 400 == 0) {
					dayOfMonthsList.set(1, 29);
				} else {
					dayOfMonthsList.set(1, 28);
				}
			} else {
				dayOfMonthsList.set(1, 29);
			}
		} else {
			dayOfMonthsList.set(1, 28);
		}
		ArrayList<Date> listDate = new ArrayList<Date>();
		ArrayList<Integer> listIncome = new ArrayList<Integer>();
		ResultSet result = null;
		Connector<VeXemPhim> connector = new Connector<VeXemPhim>();
		try {
			Connection connection = connector.connect();
			Statement statement = connection.createStatement();
			result = statement.executeQuery("select Sum(TongTien) as Sum, NgayDat from VeXemPhim group by NgayDat ");
			while (result.next()) {
				try {
					listDate.add(new SimpleDateFormat("dd-MM-yyyy").parse(result.getString("NgayDat")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				listIncome.add(result.getInt("Sum"));
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int count = 0;
		for (int j = 0; j <= listDate.size() - 1; j++) {
			if ((listDate.get(j).getMonth() == choosenMonth2 - 1)
					&& (listDate.get(j).getYear() == choosenYear2 - 1900)) {
				sum_series.getData().add(new XYChart.Data(listDate.get(j).getDate() + "", listIncome.get(j)));
				count++;
			}
		}

		for (int i = count; i <= dayOfMonthsList.get(choosenMonth2 - 1); i++) {
			sum_series.getData().add(new XYChart.Data(i + "", 0));
		}

	}

	List<LocalDate> getDatesBetweenUsingJava8(LocalDate startDate, LocalDate endDate) {

		long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
		return IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(i -> startDate.plusDays(i))
				.collect(Collectors.toList());
	}

	private Date convertToDateViaSqlDate(LocalDate dateToConvert) {
		return java.sql.Date.valueOf(dateToConvert);
	}

	void setUpMonthMenuFromYear(int year) {
		monthsMenu.getItems().clear();
		if (year == LocalDate.now().getYear()) {
			for (String month : defaultMonthsList) {
				monthsMenu.getItems().add(new MenuItem(month));

			}
			for (int i = CUR_MONTH; i <= 11; i++) {
				monthsMenu.getItems().get(i).setDisable(true);
			}
		} else {
			for (String month : defaultMonthsList) {
				monthsMenu.getItems().add(new MenuItem(month));
			}
		}

		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0) {
					dayOfMonthsList.set(1, 29);
				} else {
					dayOfMonthsList.set(1, 28);
				}
			} else {
				dayOfMonthsList.set(1, 29);
			}
		} else {
			dayOfMonthsList.set(1, 28);
		}

	}

}
