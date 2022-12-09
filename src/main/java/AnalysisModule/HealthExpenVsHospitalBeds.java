package AnalysisModule;

import java.util.HashMap;

import DataFetcherModule.DataFetcher;
import DataFetcherModule.IDataFetcher;

public class HealthExpenVsHospitalBeds extends AnalysisStrategy {
	
	public static final String Name = "Current health expenditure Vs Hospital beds";
	private IDataFetcher dataFetcher;
	private AnalysisData data; 
	
	public HealthExpenVsHospitalBeds() {
		super();
		dataFetcher = new DataFetcher();
	}
	
	@Override
	public AnalysisData doAnalysis(String country, int startYear, int endYear) {
		
		HashMap<Integer, Double> healthExp = new HashMap<>();
		HashMap<Integer, Double> hospitalBeds = new HashMap<>();
		
		healthExp = dataFetcher.getData(country, "SH.XPD.CHEX.PC.CD", startYear, endYear);
		hospitalBeds = dataFetcher.getData(country, "SH.MED.BEDS.ZS", startYear, endYear);
		
		if(healthExp == null || hospitalBeds == null) {
			return null;
		}
		
		HashMap<String, double[]> dataMap = new HashMap<>();
		double[] dataSet1 = new double[endYear - startYear + 1];
		double[] dataSet2 = new double[endYear - startYear + 1];
		double[] ratio = new double[endYear - startYear + 1]; // ratio to be calculated
		
		// Save value for each year in order from start year to end year in the arrays
		for(int i = 0; i < healthExp.size(); i++) {
			
			double healthExpValue = healthExp.get(startYear+i);
			dataSet1[i] = healthExpValue;
			
			double hospitalBedValue = hospitalBeds.get(startYear+i);
			dataSet2[i] = hospitalBedValue;	
			
			ratio[i] = dataSet1[i] / dataSet2[i];	
		
		}

		dataMap.put("Ratio", ratio);
		
		this.data = new AnalysisData(country, Name, startYear, endYear);
		
		this.data.setxAxis("Years");
		this.data.setyAxisA("Ratio");
		this.data.setData(dataMap);
		
		eventManager.notifyObservers(this.data);
		return this.data;
		
	}

}
