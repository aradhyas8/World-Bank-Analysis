package AnalysisModule;

import java.util.HashMap;

import DataFetcherModule.DataFetcher;
import DataFetcherModule.IDataFetcher;

public class AirPollutionVsForestArea extends AnalysisStrategy {
	
	public static final String Name = "Air Pollution Vs Forest Area";
	private IDataFetcher dataFetcher;
	private AnalysisData data;


	public AirPollutionVsForestArea() {
		super();
		dataFetcher = new DataFetcher();
	}

	public AnalysisData doAnalysis(String country, int startYear, int endYear) {
		
		HashMap<Integer, Double> airP = dataFetcher.getData(country, "EN.ATM.PM25.MC.M3", startYear, endYear);
		HashMap<Integer, Double> preAirP = dataFetcher.getData(country, "EN.ATM.PM25.MC.M3", startYear-1, endYear-1);
		HashMap<Integer, Double> forestArea = dataFetcher.getData(country, "AG.LND.FRST.ZS", startYear, endYear);
		HashMap<Integer, Double> preForestArea = dataFetcher.getData(country, "AG.LND.FRST.ZS", startYear-1, endYear-1);
		
		if(airP == null || preAirP == null || forestArea == null || preForestArea == null) {
			return null;
		}
		
		HashMap<String, double[]> dm = new HashMap<>();
		double[] dataSet1 = new double[endYear - startYear +1];
		double[] dataSet2 = new double[endYear - startYear +1];
		double[] dataSet3 = new double[endYear - startYear +1];
		double[] dataSet4 = new double[endYear - startYear +1];
		
		//Get values of for each and store it in an array
		for(int i=0; i<airP.size();i++) {
			double airPValue = airP.get(startYear+i);
			dataSet1[i] = airPValue;
			double preAirPValue = preAirP.get(startYear+i-1);
			dataSet2[i] = preAirPValue;
			double forestAreaValue = forestArea.get(startYear+i);
			dataSet3[i] = forestAreaValue;
			double preForestValue = preForestArea.get(startYear+i-1);
			dataSet4[i] = preForestValue;
		}
		
		int n1 = dataSet1.length;
		int n3 = dataSet3.length;
		
		// Calculate APR for CO2 Emissions
		double [] apr1 = new double [n1];
		
		for(int i=0; i<n1; i++) {
			double change = (((dataSet1[i] - dataSet2[i])/dataSet2[i])*100);
			apr1[i] = change;
		}
		
		// Calculate APR for Forest Area
		double [] apr2 = new double [n3];
		
		for(int i=0; i<n3; i++) {
			double change = (((dataSet3[i] - dataSet4[i])/dataSet4[i])*100);
			apr2[i] = change;
		}
		
		dm.put("PM 2.5 Air Pollution", apr1);
		dm.put("Forest Area", apr2);
		
		this.data = new AnalysisData(country, Name, startYear, endYear);
		
		this.data.setxAxis("Year");
		this.data.setyAxisA("micrograms/cubic meter");
		this.data.setyAxisB("% of land");
		this.data.setData(dm);
		
		eventManager.notifyObservers(this.data);
		return this.data;
		
	}


}
