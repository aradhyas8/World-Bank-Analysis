package AnalysisModule;

import java.util.HashMap;

import DataFetcherModule.DataFetcher;
import DataFetcherModule.IDataFetcher;

public class AvgForestArea extends AnalysisStrategy {
	
	public static final String Name = "Average Forest area (% of land area)";
	private IDataFetcher dataFetcher;
	private AnalysisData data; 
	
	public AvgForestArea() {
		super();
		dataFetcher = new DataFetcher();
	}
	
	@Override
	public AnalysisData doAnalysis(String country, int startYear, int endYear) {
	
		// Fetch data required for this analysis
		HashMap<Integer, Double> forestAreaHashMap = dataFetcher.getData(country, "AG.LND.FRST.ZS", startYear, endYear);
		
		// pie chart has 2 sections: 1. % forest area, 2. % not forest area (land for all other uses)
		
		// calculate average forest area % of all years
		double total = 0.0;
		
		if(forestAreaHashMap == null) {
			return null;
		}
		
		for (Double value : forestAreaHashMap.values()) {
		    total += value;
		}
		
		double averageForestArea = total/(forestAreaHashMap.size());
		double allOtherLand = 100.0 - averageForestArea;
		
		HashMap<String, double[]> dataMap = new HashMap<>();
		double[] dataSet1 = {averageForestArea};
		double[] dataSet2 = {allOtherLand};
		
		dataMap.put("Average Forest Area", dataSet1);
		dataMap.put("All Other Land", dataSet2);
		
		this.data = new AnalysisData(country, Name, startYear, endYear);
		
		this.data.setxAxis("% of land area");
		this.data.setData(dataMap);
		this.data.setIsPieType(true);
	
		// Notify observers of this AnalysisStrategy when data is updated
		eventManager.notifyObservers(this.data);
		return this.data;
		
	
		
	}
	
	

}
