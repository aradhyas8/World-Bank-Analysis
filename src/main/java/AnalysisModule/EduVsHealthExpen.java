package AnalysisModule;

import java.util.HashMap;

import DataFetcherModule.DataFetcher;
import DataFetcherModule.IDataFetcher;

public class EduVsHealthExpen extends AnalysisStrategy {
	
	public static final String Name = "Government Expenditure Vs Health Expenditure";
	private IDataFetcher dataFetcher;
	private AnalysisData data; 
	
	public EduVsHealthExpen() {
		super();
		dataFetcher = new DataFetcher();
	}
	
	@Override
	public AnalysisData doAnalysis(String country, int startYear, int endYear) {
		
		HashMap<Integer,Double> govEdu = dataFetcher.getData(country, "SE.XPD.TOTL.GD.ZS", startYear, endYear);
		HashMap<Integer,Double> preGovEdu = dataFetcher.getData(country, "SE.XPD.TOTL.GD.ZS", startYear-1, endYear-1);
		HashMap<Integer,Double> healthExp = dataFetcher.getData(country, "SH.XPD.CHEX.GD.ZS", startYear, endYear);
		HashMap<Integer,Double> preHealthExp = dataFetcher.getData(country, "SH.XPD.CHEX.GD.ZS", startYear-1, endYear-1);
		
		if(govEdu == null || preGovEdu == null || healthExp == null || preHealthExp == null) {
			return null;
		}
		
		HashMap<String, double[]> dm = new HashMap<>();
		double[] dataSet1 = new double[endYear - startYear +1];
		double[] dataSet2 = new double[endYear - startYear +1];
		double[] dataSet3 = new double[endYear - startYear +1];
		double[] dataSet4 = new double[endYear - startYear +1];
		
		
		for( int i=0; i<govEdu.size(); i++) {
			double govEduValue = govEdu.get(startYear+i);
			dataSet1[i] = govEduValue;
			double preEduValue = preGovEdu.get((startYear-1)+i);
			dataSet2[i] = preEduValue;
			double healthValue = healthExp.get(startYear+i);
			dataSet3[i] = healthValue;
			double preHealthValue = preHealthExp.get((startYear-1)+i);
			dataSet4[i] = preHealthValue;
		}
		
		int n1 = dataSet1.length;
		int n3 = dataSet3.length;
		
		double [] apr1 = new double[n1];
		
		for(int i=0; i<n1; i++) {
			double change = (((dataSet1[i] - dataSet2[i])/dataSet2[i])*100);
			apr1[i] = change;
		}
		
		double [] apr2 = new double[n3];
		
		for(int i=0; i<n3; i++) {
			double change = (((dataSet3[i] - dataSet4[i])/dataSet4[i])*100);
			apr2[i] = change;
		}
		
		dm.put("Government expenditure on education, total (% of GDP)", apr1);
		dm.put("Current health expenditure (% of GDP)", apr2);
		
		
		this.data = new AnalysisData(country, Name, startYear, endYear);
		
		this.data.setxAxis("total (% of GDP)");
		this.data.setyAxisA("(% of GDP)");
		
		this.data.setData(dm);
		
		eventManager.notifyObservers(this.data);
		return this.data;

	}

}
