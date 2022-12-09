package AnalysisModule;

import java.util.HashMap;

import DataFetcherModule.DataFetcher;
import DataFetcherModule.IDataFetcher;

public class CO2VsEnergyVsAirPollution extends AnalysisStrategy {
	
	public static final String Name = "CO2 EMissions vs Energy Use & Air Pollution";
	private IDataFetcher dataFetcher;
	private AnalysisData data; 
	
	public CO2VsEnergyVsAirPollution() {
		super();
		dataFetcher = new DataFetcher();
	}
	 
	@Override
	public AnalysisData doAnalysis(String country, int startYear, int endYear) {
	
		HashMap<Integer,Double> emissions = dataFetcher.getData(country, "EN.ATM.CO2E.PC", startYear, endYear);
		HashMap<Integer,Double> preEmissions = dataFetcher.getData(country, "EN.ATM.CO2E.PC", startYear-1, endYear-1);
		HashMap<Integer, Double> energyUse = dataFetcher.getData(country, "EG.USE.PCAP.KG.OE", startYear, endYear);
		HashMap<Integer, Double> preEnergyUse = dataFetcher.getData(country, "EG.USE.PCAP.KG.OE", startYear-1, endYear-1);
		HashMap<Integer, Double> airP = dataFetcher.getData(country, "EN.ATM.PM25.MC.M3", startYear, endYear);
		HashMap<Integer, Double> preAirP = dataFetcher.getData(country, "EN.ATM.PM25.MC.M3", startYear-1, endYear-1);
		
		if(emissions == null || preEmissions == null || energyUse == null || preEnergyUse == null || 
				airP == null || preAirP == null) {
			return null;
		}
		HashMap<String, double[]> dm = new HashMap<>();
		double[] dataSet1 = new double[endYear - startYear +1];
		double[] dataSet2 = new double[endYear - startYear +1];
		double[] dataSet3 = new double[endYear - startYear + 1];
		double[] dataSet4 = new double[endYear - startYear + 1];
		double[] dataSet5 = new double[endYear - startYear + 1];
		double[] dataSet6 = new double[endYear - startYear + 1];
		
		//Get values of for each and store it in an array
		for( int i=0; i<emissions.size(); i++) {
			double emissionsValue = emissions.get(startYear+i);
			dataSet1[i] = emissionsValue;
			double preEmValue = preEmissions.get((startYear-1)+i);
			dataSet2[i] = preEmValue;
			double energyValue = energyUse.get(startYear+i);
			dataSet3[i] = energyValue;
			double preEnergyValue = preEnergyUse.get((startYear-1)+i);
			dataSet4[i] = preEnergyValue;
			double airpolValue = airP.get(startYear+i);
			dataSet5[i] = airpolValue;
			double preAirPValue = preAirP.get((startYear-1)+i);
			dataSet6[i] = preAirPValue;
		}
		
		int n1 = dataSet1.length;
		int n3 = dataSet3.length;
		int n5 = dataSet5.length;
			
		// Calculate APR for CO2 Emissions
		double[] apr1 = new double [n1];
		
		for(int i=0; i<n1; i++) {
			double change = (((dataSet1[i] - dataSet2[i])/dataSet2[i])*100);
			apr1[i] = change;
		}
		
		// Calculate APR for Energy Use
		double[] apr2 = new double [n3];
		
		for(int i=0; i<n3; i++) {
			double change = (((dataSet3[i] - dataSet4[i])/dataSet4[i])*100);
			apr2[i] = change;
		}
		
		// Calculate APR for Air Pollution
		double[] apr3 = new double [n5];
		
		for(int i=0; i<n5; i++) {
			double change = (((dataSet5[i] - dataSet6[i])/dataSet6[i])*100);
			apr3[i] = change;
		}
		
		dm.put("CO2 emissions (as metric tons per capita)", apr1);
		dm.put("Energy use (as kg of oil equivalent per capita)", apr2);
		dm.put("PM2.5 air pollution, mean annual exposure (as micrograms per cubic meter)", apr3);
		
		this.data = new AnalysisData(country, Name, startYear, endYear);
		
		this.data.setxAxis("Year");
		this.data.setyAxisA("Emissions");
		this.data.setyAxisB("Emissions");
		this.data.setyAxisC("PM2.5 AirP");
		this.data.setData(dm);
		
		eventManager.notifyObservers(this.data);
		return this.data;
	}

}

