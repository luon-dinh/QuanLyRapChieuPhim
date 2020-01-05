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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import Connector.Connector;
import Model.HoaDon;
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
import java.time.format.DateTimeFormatter;

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

	// init chart
	XYChart.Series<String, Number> sum_series = new XYChart.Series();
	XYChart.Series<String, Number> movie_series = new XYChart.Series();
	XYChart.Series<String, Number> service_series = new XYChart.Series();

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

		// chart
		setUpSumChart(choosenMonth, choosenYear);

		// type
		revenueTypeMenu.getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				revenueTypeMenu.setText(revenueTypeMenu.getItems().get(0).getText());
				choosenType = 0;
				Title = "Tổng doanh thu tháng " + choosenMonth + "/" + choosenYear;
				lineChart1.setTitle(Title);
				setUpSumChart(choosenMonth, choosenYear);

			}
		});

		revenueTypeMenu.getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				revenueTypeMenu.setText(revenueTypeMenu.getItems().get(1).getText());
				choosenType = 1;
				Title = "Doanh thu phim tháng " + choosenMonth + "/" + choosenYear;
				lineChart1.setTitle(Title);
				setUpMovieChart(choosenMonth, choosenYear);
			}
		});

		revenueTypeMenu.getItems().get(2).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				revenueTypeMenu.setText(revenueTypeMenu.getItems().get(2).getText());
				choosenType = 2;
				Title = "Doanh thu dịch vụ tháng " + choosenMonth + "/" + choosenYear;
				lineChart1.setTitle(Title);
				setUpServiceChart(choosenMonth, choosenYear);
			}
		});

		// month
		EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				choosenMonth = Integer.parseInt(((MenuItem) e.getSource()).getText().substring(6));
				monthsMenu.setText(((MenuItem) e.getSource()).getText());
				if (choosenType == 0) {
					Title = "Tổng doanh thu tháng " + choosenMonth + "/" + choosenYear;
					lineChart1.setTitle(Title);
					setUpSumChart(choosenMonth, choosenYear);
				}
				if (choosenType == 1) {
					Title = "Doanh thu phim tháng " + choosenMonth + "/" + choosenYear;
					lineChart1.setTitle(Title);
					setUpMovieChart(choosenMonth, choosenYear);
				}
				if (choosenType == 2) {
					Title = "Doanh thu dịch vụ tháng " + choosenMonth + "/" + choosenYear;
					lineChart1.setTitle(Title);
					setUpServiceChart(choosenMonth, choosenYear);
				}

			}
		};

		// year
		EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				choosenYear = Integer.parseInt(((MenuItem) e.getSource()).getText());
				yearsMenu.setText(choosenYear + "");
				if (choosenType == 0) {
					Title = "Tổng doanh thu tháng " + choosenMonth + "/" + choosenYear;
					lineChart1.setTitle(Title);
					setUpSumChart(choosenMonth, choosenYear);
				}
				if (choosenType == 1) {
					Title = "Doanh thu phim tháng " + choosenMonth + "/" + choosenYear;
					lineChart1.setTitle(Title);
					setUpMovieChart(choosenMonth, choosenYear);
				}
				if (choosenType == 2) {
					Title = "Doanh thu dịch vụ tháng " + choosenMonth + "/" + choosenYear;
					lineChart1.setTitle(Title);
					setUpServiceChart(choosenMonth, choosenYear);
				}
				setUpMonthMenuFromYear(choosenYear);
				for (int i = 0; i <= 11; i++)
					monthsMenu.getItems().get(i).setOnAction(event2);
			}
		};

		for (int i = 0; i < CUR_YEAR - START_YEAR + 1; i++)
			yearsMenu.getItems().get(i).setOnAction(event1);

		// Set up strings
		xAxis1.setLabel("Tháng");
		yAxis1.setLabel("Doanh thu");

	}

	@SuppressWarnings("deprecation")
	private void setUpSumChart(int choosenMonth2, int choosenYear2) {
		// TODO Auto-generated method stub
		// load from database
		lineChart1.getData().clear();
		sum_series = new Series<String, Number>();
		sum_series.setName("Tổng doanh thu");

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
		Date startDate = null;
		Date finishDate = null;

		try {
			startDate = new SimpleDateFormat("yyyy-mm-dd").parse(choosenYear2 + "-" + choosenMonth2 + "-1");
			finishDate = new SimpleDateFormat("yyyy-mm-dd")
					.parse(choosenYear2 + "-" + (choosenMonth2) + "-" + dayOfMonthsList.get(choosenMonth2 - 1));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ArrayList<Date> listDate_movie = new ArrayList<Date>();
		ArrayList<Integer> listIncome_movie = new ArrayList<Integer>();
		ResultSet result_movie = null;
		ArrayList<Date> listDate_service = new ArrayList<Date>();
		ArrayList<Integer> listIncome_service = new ArrayList<Integer>();
		ResultSet result_service = null;
		ArrayList<Date> listDate_sum = new ArrayList<Date>();
		ArrayList<Integer> listIncome_sum = new ArrayList<Integer>();
		ResultSet result_sum = null;

		listDate_sum.addAll(getDatesBetweenUsingJava7(startDate, finishDate));
		for (int i = 0; i <= dayOfMonthsList.get(choosenMonth2 - 1) - 1; i++) {
			listIncome_sum.add(i, 0);
		}

		Connector<VeXemPhim> connector_movie = new Connector<VeXemPhim>();
		Connector<HoaDon> connector_service = new Connector<HoaDon>();

		try {
			Connection connection = connector_movie.connect();
			Statement statement = connection.createStatement();
			result_movie = statement
					.executeQuery("select Sum(TongTien) as Sum, NgayDat from VeXemPhim group by NgayDat ");

			while (result_movie.next()) {
				try {
					listDate_movie.add(new SimpleDateFormat("yyyy-mm-dd").parse(result_movie.getString("NgayDat")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				listIncome_movie.add(result_movie.getInt("Sum"));
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Connection connection = connector_service.connect();
			Statement statement = connection.createStatement();
			result_service = statement
					.executeQuery("select Sum(TongTien) as Sum, NgayDat from HoaDon group by NgayDat ");

			while (result_service.next()) {
				try {
					listDate_service.add(new SimpleDateFormat("yyyy-mm-dd").parse(result_service.getString("NgayDat")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				listIncome_service.add(result_service.getInt("Sum"));
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < listDate_movie.size(); i++) {
			for (int j = 0; j < listDate_sum.size(); j++)
				if (listDate_movie.get(i).equals(listDate_sum.get(j)))
					listIncome_sum.set(j, listIncome_sum.get(j) + listIncome_movie.get(i));
		}

		for (int i = 0; i < listDate_service.size(); i++) {
			for (int j = 0; j < listDate_sum.size(); j++)
				if (listDate_service.get(i).equals(listDate_sum.get(j)))
					listIncome_sum.set(j, listIncome_sum.get(j) + listIncome_service.get(i));
		}

		for (int j = 0; j <= listDate_sum.size() - 1; j++) {
			if ((listDate_sum.get(j).getMonth() == choosenMonth2 - 1)
					&& (listDate_sum.get(j).getYear() == choosenYear2 - 1900)) {
				sum_series.getData().add(new XYChart.Data(listDate_sum.get(j).getDate() + "", listIncome_sum.get(j)));
			}
		}

		lineChart1.getData().add((Series<String, Number>) sum_series);
	}

	private void setUpMovieChart(int choosenMonth2, int choosenYear2) {
		// TODO Auto-generated method stub
		// load from database
		lineChart1.getData().clear();
		sum_series = new Series<String, Number>();
		sum_series.setName("Doanh thu phim");

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
		Date startDate = null;
		Date finishDate = null;

		try {
			startDate = new SimpleDateFormat("yyyy-mm-dd").parse(choosenYear2 + "-" + choosenMonth2 + "-1");
			finishDate = new SimpleDateFormat("yyyy-mm-dd")
					.parse(choosenYear2 + "-" + (choosenMonth2) + "-" + dayOfMonthsList.get(choosenMonth2 - 1));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ArrayList<Date> listDate_movie = new ArrayList<Date>();
		ArrayList<Integer> listIncome_movie = new ArrayList<Integer>();
		ResultSet result_movie = null;
		ArrayList<Date> listDate_sum = new ArrayList<Date>();
		ArrayList<Integer> listIncome_sum = new ArrayList<Integer>();

		listDate_sum.addAll(getDatesBetweenUsingJava7(startDate, finishDate));
		for (int i = 0; i <= dayOfMonthsList.get(choosenMonth2 - 1) - 1; i++) {
			listIncome_sum.add(i, 0);
		}

		Connector<VeXemPhim> connector_movie = new Connector<VeXemPhim>();
		try {
			Connection connection = connector_movie.connect();
			Statement statement = connection.createStatement();
			result_movie = statement
					.executeQuery("select Sum(TongTien) as Sum, NgayDat from VeXemPhim group by NgayDat ");

			while (result_movie.next()) {
				try {
					listDate_movie.add(new SimpleDateFormat("yyyy-mm-dd").parse(result_movie.getString("NgayDat")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				listIncome_movie.add(result_movie.getInt("Sum"));
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < listDate_movie.size(); i++) {
			for (int j = 0; j < listDate_sum.size(); j++)
				if (listDate_movie.get(i).equals(listDate_sum.get(j)))
					listIncome_sum.set(j, listIncome_sum.get(j) + listIncome_movie.get(i));
		}

		for (int j = 0; j <= listDate_sum.size() - 1; j++) {
			if ((listDate_sum.get(j).getMonth() == choosenMonth2 - 1)
					&& (listDate_sum.get(j).getYear() == choosenYear2 - 1900)) {
				sum_series.getData().add(new XYChart.Data(listDate_sum.get(j).getDate() + "", listIncome_sum.get(j)));
			}
		}

		lineChart1.getData().add((Series<String, Number>) sum_series);
	}

	private void setUpServiceChart(int choosenMonth2, int choosenYear2) {
		// TODO Auto-generated method stub
		// load from database
		lineChart1.getData().clear();
		sum_series = new Series<String, Number>();
		sum_series.setName("Doanh thu dịch vụ");

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
		Date startDate = null;
		Date finishDate = null;

		try {
			startDate = new SimpleDateFormat("yyyy-mm-dd").parse(choosenYear2 + "-" + choosenMonth2 + "-1");
			finishDate = new SimpleDateFormat("yyyy-mm-dd")
					.parse(choosenYear2 + "-" + (choosenMonth2) + "-" + dayOfMonthsList.get(choosenMonth2 - 1));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<Date> listDate_service = new ArrayList<Date>();
		ArrayList<Integer> listIncome_service = new ArrayList<Integer>();
		ResultSet result_service = null;
		ArrayList<Date> listDate_sum = new ArrayList<Date>();
		ArrayList<Integer> listIncome_sum = new ArrayList<Integer>();

		listDate_sum.addAll(getDatesBetweenUsingJava7(startDate, finishDate));
		for (int i = 0; i <= dayOfMonthsList.get(choosenMonth2 - 1) - 1; i++) {
			listIncome_sum.add(i, 0);
		}

		Connector<HoaDon> connector_service = new Connector<HoaDon>();

		try {
			Connection connection = connector_service.connect();
			Statement statement = connection.createStatement();
			result_service = statement
					.executeQuery("select Sum(TongTien) as Sum, NgayDat from HoaDon group by NgayDat ");

			while (result_service.next()) {
				try {
					listDate_service.add(new SimpleDateFormat("yyyy-mm-dd").parse(result_service.getString("NgayDat")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				listIncome_service.add(result_service.getInt("Sum"));
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < listDate_service.size(); i++) {
			for (int j = 0; j < listDate_sum.size(); j++)
				if (listDate_service.get(i).equals(listDate_sum.get(j)))
					listIncome_sum.set(j, listIncome_sum.get(j) + listIncome_service.get(i));
		}

		for (int j = 0; j <= listDate_sum.size() - 1; j++) {
			if ((listDate_sum.get(j).getMonth() == choosenMonth2 - 1)
					&& (listDate_sum.get(j).getYear() == choosenYear2 - 1900)) {
				sum_series.getData().add(new XYChart.Data(listDate_sum.get(j).getDate() + "", listIncome_sum.get(j)));
			}
		}

		lineChart1.getData().add((Series<String, Number>) sum_series);
	}

	List<LocalDate> getDatesBetweenUsingJava8(LocalDate startDate, LocalDate endDate) {

		long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
		return IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(i -> startDate.plusDays(i))
				.collect(Collectors.toList());
	}

	List<Date> getDatesBetweenUsingJava7(Date startDate, Date endDate) {
		List<Date> datesInRange = new ArrayList<>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);

		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(getNextDate(endDate));

		while (calendar.before(endCalendar)) {
			Date result = calendar.getTime();
			datesInRange.add(result);
			calendar.add(Calendar.DATE, 1);
		}
		return datesInRange;
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
	}

	public Date getNextDate(Date curDate) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(curDate);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		return calendar.getTime();
	}
}
