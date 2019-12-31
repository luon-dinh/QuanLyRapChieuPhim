package controller;

import java.awt.List;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class StatisticController implements Initializable {
	@FXML
	CategoryAxis xAxis1;
	@FXML
	NumberAxis yAxis1;
	@FXML
	LineChart<String, Number> lineChart1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		xAxis1.setLabel("Năm");
		yAxis1.setLabel("Doanh thu");
		lineChart1.setTitle("Demo_Năm");

		XYChart.Series series = new XYChart.Series();
		
		series.setName("Demo_1");
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
		lineChart1.getData().add(series);
	}
}
