package ControllerModule;

import java.util.ArrayList;

import javax.swing.JScrollPane;

import org.jfree.chart.ChartPanel;

import AnalysisModule.AirPollutionVsForestArea;
import AnalysisModule.AnalysisData;
import AnalysisModule.AnalysisStrategy;
import AnalysisModule.AvgForestArea;
import AnalysisModule.CO2VsEnergyVsAirPollution;
import AnalysisModule.CO2VsGDP;
import AnalysisModule.EduExpenditure;
import AnalysisModule.EduVsHealthExpen;
import AnalysisModule.PopulationDensityVsGDPGrowth;
import AnalysisModule.HealthExpenVsHospitalBeds;
import ViewerModule.BarGraph;
import ViewerModule.IViewer;
import ViewerModule.LineGraph;
import ViewerModule.PieChart;
import ViewerModule.Report;
import ViewerModule.ScatterPlot;

public class AnalysisController {
	
	private AnalysisStrategy analysisType;
	private ArrayList<IViewer> viewers;
	private AnalysisData analysisData;
	private static AnalysisController instance;
	
	private AnalysisController() {
		this.viewers = new ArrayList<>();
	}
	
	public static AnalysisController getInstance() {
		
		if (instance == null) {
			synchronized(AnalysisController.class) {
				if (instance == null) {
					instance = new AnalysisController();
				}
			}            
		}
		return instance;
	}
	
	public void requestAnalysis(String analysis, ArrayList<String> views, String country, int startYear, int endYear) {
		this.viewers = new ArrayList<>();
		setAnalysisStrategy(analysis);
		registerObservers(views);
		analysisData = executeStrategy(country, startYear, endYear);
	}
	
	private void setAnalysisStrategy(String analysis) {
		
		switch(analysis) {
			case "Average Forest Area":
				this.analysisType = new AvgForestArea();
				break;
			case "Air Pollution Vs Forest Area":
				this.analysisType = new AirPollutionVsForestArea();
				break;
			case "Education Expenditure":
				this.analysisType = new EduExpenditure();
				break;
			case "Education Vs Health Expenditure":
				this.analysisType = new EduVsHealthExpen();
				break;
			case "Population Density Vs GDP Growth":
				this.analysisType = new PopulationDensityVsGDPGrowth();
				break;
			case "Health Expenditure Vs Hospital Beds":
				this.analysisType = new HealthExpenVsHospitalBeds();
				break;
			case "CO2 Vs Energy Vs AirPollution":
				this.analysisType = new CO2VsEnergyVsAirPollution();
				break;
			case "CO2 Vs GDP":
				this.analysisType = new CO2VsGDP();
		}			
	}
	
	private void registerObservers(ArrayList<String> views) {
		
		for(String view: views) {
			switch (view) {
				case "Report":
					analysisType.eventManager.registerObserver(new Report());
					viewers.add(new Report());
					break;
				case "Pie Chart":
					analysisType.eventManager.registerObserver(new PieChart());
					viewers.add(new PieChart());
					break;
				case "Line Graph":
					analysisType.eventManager.registerObserver(new LineGraph());
					viewers.add(new LineGraph());
					break;
				case "Bar Graph":
					analysisType.eventManager.registerObserver(new BarGraph());
					viewers.add(new BarGraph());
					break;
				case "Scatter Plot":
					analysisType.eventManager.registerObserver(new ScatterPlot());
					viewers.add(new ScatterPlot());
					break;
			}
		}
	}
	
	private AnalysisData executeStrategy(String country, int startYear, int endYear) {
		
		return this.analysisType.doAnalysis(country, startYear, endYear);
		
	}
	
	public ArrayList<IViewer> getViewers() {
		return viewers;
	}
	
	public AnalysisData getAnalysisData() {
		return analysisData;	
	}
}
