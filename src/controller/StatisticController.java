package controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import java.time.format.DateTimeFormatter;
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

	@SuppressWarnings("unchecked")
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

		// Thang
		for (String month : monthsList)
			monthsMenu.getItems().add(new MenuItem(month));

		// Nam
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		LocalDate now = LocalDate.now();
		CUR_DAY = now.getDayOfMonth();
		CUR_MONTH = now.getMonthValue();
		CUR_YEAR = now.getYear();

		for (int i = START_YEAR; i <= CUR_YEAR; i++) {
			yearsMenu.getItems().add(new MenuItem(i + ""));
		}

	}

	private void KhoiTaoBieuDo() {
		// TODO Auto-generated method stub

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		LocalDate now = LocalDate.now();
		List<LocalDate> dateList = getDatesBetweenUsingJava8(LocalDate.of(START_YEAR, START_MONTH, START_DAY), now);

		for (int i = START_YEAR; i <= CUR_YEAR; i++) {
			if (i % 4 == 0) {
				if (i % 100 == 0) {
					if (i % 400 == 0) {
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

		// Lay doanh thu

//		dateList.get(dateList.size()-1).getYear();		
		// for (LocalDate date : dateList) {
//			System.out.println(simpleDateFormat.format(convertToDateViaSqlDate(date)));
//			
//		}

		// default value
		choosenYear = now.getYear();
		choosenMonth = now.getMonthValue();
		choosenType = 0;

		// type
		revenueTypeMenu.getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				revenueTypeMenu.setText("Tổng");
				choosenType = 0;
			}
		});

		revenueTypeMenu.getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				revenueTypeMenu.setText("Doanh thu phim");
				choosenType = 1;
			}
		});

		revenueTypeMenu.getItems().get(2).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				revenueTypeMenu.setText("Doanh thu dịch vụ");
				choosenType = 2;
			}
		});

		// year
//		EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
//			public void handle(ActionEvent e) {
//				choosenYear = Integer.parseInt(((MenuItem) e.getSource()).getText().substring(0, 3));
//			}
//		};
//		for (int i = 0; i <= CUR_YEAR - START_YEAR + 1; i++)
//			yearsMenu.getItems().get(i).setOnAction(event1);

//		// month
//		EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
//			public void handle(ActionEvent e) {
//				choosenMonth = Integer.parseInt(((MenuItem) e.getSource()).getText());
//			}
//		};
//		for (int i = 1; i <= 12; i++)
//			monthsMenu.getItems().get(i).setOnAction(event2);

		String Title = "Tổng doanh thu tháng " + choosenMonth + "/" + choosenYear;
		
		if (choosenType == 0) {
			Title = "Tổng doanh thu tháng " + choosenMonth + "/" + choosenYear;
		}
		if (choosenType == 1) {
			Title = "Doanh thu phim tháng " + choosenMonth + "/" + choosenYear;
		}

		if (choosenType == 2) {
			Title = "Doanh thu dịch vụ tháng " + choosenMonth + "/" + choosenYear;
		}

		// Set up strings
		xAxis1.setLabel("Tháng");
		yAxis1.setLabel("Doanh thu");
		lineChart1.setTitle(Title);

		XYChart.Series<String, Number> series = new XYChart.Series();

		series.setName("Tổng doanh thu");
		// populating the series with data

		series.getData().add(new XYChart.Data("1", 23));
		series.getData().add(new XYChart.Data("2", 14));
		series.getData().add(new XYChart.Data("3", 15));
		series.getData().add(new XYChart.Data("4", 24));
		series.getData().add(new XYChart.Data("5", 34));
		series.getData().add(new XYChart.Data("6", 36));
		series.getData().add(new XYChart.Data("7", 22));
		series.getData().add(new XYChart.Data("8", 45));
		series.getData().add(new XYChart.Data("9", 43));
		series.getData().add(new XYChart.Data("10", 17));
		series.getData().add(new XYChart.Data("11", 29));
		series.getData().add(new XYChart.Data("12", 25));
		series.getData().add(new XYChart.Data("13", 14));
		series.getData().add(new XYChart.Data("14", 15));
		series.getData().add(new XYChart.Data("15", 24));
		series.getData().add(new XYChart.Data("16", 34));
		series.getData().add(new XYChart.Data("17", 36));
		series.getData().add(new XYChart.Data("18", 22));
		series.getData().add(new XYChart.Data("19", 45));
		series.getData().add(new XYChart.Data("20", 130));
		series.getData().add(new XYChart.Data("21", 17));
		series.getData().add(new XYChart.Data("22", 29));
		series.getData().add(new XYChart.Data("23", 25));
		series.getData().add(new XYChart.Data("24", 36));
		series.getData().add(new XYChart.Data("25", 22));
		series.getData().add(new XYChart.Data("26", 45));
		series.getData().add(new XYChart.Data("27", 43));
		series.getData().add(new XYChart.Data("28", 17));
		series.getData().add(new XYChart.Data("29", 29));
		series.getData().add(new XYChart.Data("30", 33));
		lineChart1.getData().add((Series<String, Number>) series);
	}

	List<LocalDate> getDatesBetweenUsingJava8(LocalDate startDate, LocalDate endDate) {

		long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
		return IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(i -> startDate.plusDays(i))
				.collect(Collectors.toList());
	}

	public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
		return java.sql.Date.valueOf(dateToConvert);
	}

}
