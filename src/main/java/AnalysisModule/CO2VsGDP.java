package AnalysisModule;

import java.util.HashMap;

import DataFetcherModule.DataFetcher;
import DataFetcherModule.IDataFetcher;

public class CO2VsGDP extends AnalysisStrategy {
	
	public static final String Name = "CO2 vs GDP (ratio)";
	private IDataFetcher dataFetcher;
	private AnalysisData data; 
	
	public CO2VsGDP() {
		super();
		dataFetcher = new DataFetcher();
	}

	@Override
	public AnalysisData doAnalysis(String country, int startYear, int endYear) {
		
		HashMap<Integer, Double> emissions = dataFetcher.getData(country, "EN.ATM.CO2E.PC", startYear, endYear);
		HashMap<Integer, Double> gdp = dataFetcher.getData(country, "NY.GDP.MKTP.KD.ZG", startYear, endYear);
		
		if(emissions == null || gdp == null) {
			return null;
		}
		
		HashMap<String, double[]> dm = new HashMap<>();
		double[] dataSet1 = new double[endYear - startYear +1];
		double[] dataSet2 = new double[endYear - startYear +1];
		
		for(int i=0; i<emissions.size(); i++) {
			double emissionsValue = emissions.get(startYear+i);
			dataSet1[i] = emissionsValue;
		}
		for( int j=0; j<gdp.size(); j++) {
			double gdpValue = gdp.get(startYear+j);
			dataSet2[j] = gdpValue;
		}
		int n1 = dataSet1.length;
		
		
		double[] ratio = new double[endYear - startYear +1];

		
		for(int i=0; i<n1; i++) {
			double rat = dataSet1[i]/dataSet2[i];
			ratio[i] = rat;
		}
		
		dm.put("Ratio", ratio);
		
		this.data = new AnalysisData(country, Name, startYear, endYear);
		
		this.data.setxAxis("GDP Per Capita");
		this.data.setyAxisA("CO2 Emissions");
		this.data.setData(dm);
		
		eventManager.notifyObservers(this.data);
		return this.data;
		
	}


}
