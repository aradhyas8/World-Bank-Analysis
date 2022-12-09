package AnalysisModule;

import java.util.HashMap;

import DataFetcherModule.DataFetcher;
import DataFetcherModule.IDataFetcher;

public class PopulationDensityVsGDPGrowth extends AnalysisStrategy {
	
	public static final String Name = "Population Density Vs GDP Growth";
	private IDataFetcher dataFetcher;
	private AnalysisData data; 
	
	public PopulationDensityVsGDPGrowth() {
		super();
		dataFetcher = new DataFetcher();
	}
	
	@Override
	public AnalysisData doAnalysis(String country, int startYear, int endYear) {
		
		// Fetch data required for this analysis
		HashMap<Integer, Double> gdpGrowth = dataFetcher.getData(country, "NY.GDP.MKTP.KD.ZG", startYear, endYear);
		HashMap<Integer, Double> populationDensity = dataFetcher.getData(country, "EN.POP.DNST", startYear, endYear);

		if(gdpGrowth == null || populationDensity == null) {
			return null;
		}
		
		HashMap<String, double[]> dataMap = new HashMap<>();
		double[] dataSet1 = new double[endYear - startYear + 1];
		double[] dataSet2 = new double[endYear - startYear + 1];
		
		// Save value for each year in order from start year to end year in the arrays
		for(int i = 0; i < gdpGrowth.size(); i++) {
			
			double gdpGrowthValue = gdpGrowth.get(startYear+i);
			dataSet1[i] = gdpGrowthValue;
			
			double populationDensityValue = populationDensity.get(startYear+i);
			dataSet2[i] = populationDensityValue;	
		}
		
		dataMap.put("GDP Growth", dataSet1);
		dataMap.put("Population Density", dataSet2);
		
		this.data = new AnalysisData(country, Name, startYear, endYear);
		
		this.data.setxAxis("Year");
		this.data.setyAxisA("annual %");
		this.data.setyAxisB("people per sq.km of land area");
		this.data.setData(dataMap);
		
		// Notify observers of this AnalysisStrategy when data is updated
		eventManager.notifyObservers(this.data);
		return this.data;
		
	}
	
	
}
