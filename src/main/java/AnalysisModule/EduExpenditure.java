package AnalysisModule;

import java.util.HashMap;

import DataFetcherModule.DataFetcher;
import DataFetcherModule.IDataFetcher;

public class EduExpenditure extends AnalysisStrategy {

	public static final String Name = "Average Government Expenditure on Education (% of GDP)";
	private IDataFetcher dataFetcher;
	private AnalysisData data; 
	
	public EduExpenditure() {
		super();
		dataFetcher = new DataFetcher();
	}
	
	@Override
	public AnalysisData doAnalysis(String country, int startYear, int endYear) {
	
		// Fetch data required for this analysis
		HashMap<Integer, Double> eduExpenditureHashMap = dataFetcher.getData(country, "EG.FEC.RNEW.ZS", startYear, endYear);
		
		if(eduExpenditureHashMap == null) {
			return null;
		}
		
		// pie chart has 2 sections: 1. Education Expenditure (% of GDP), 2. Expenditure for all other areas

		double total = 0.0;
		
		for (Double value : eduExpenditureHashMap.values()) {
		    total += value;
		}
		
		double averageEduExpenditure = total/(eduExpenditureHashMap.size());
		double allOtherExpenditures = 100.0 - averageEduExpenditure;
		
		HashMap<String, double[]> dataMap = new HashMap<>();
		double[] dataSet1 = {averageEduExpenditure};
		double[] dataSet2 = {allOtherExpenditures};
		
		dataMap.put("Average Education Expensiture", dataSet1);
		dataMap.put("All Other Expenditures", dataSet2);
		
		this.data = new AnalysisData(country, Name, startYear, endYear);
		
		this.data.setxAxis("% of GDP");
		this.data.setData(dataMap);
		this.data.setIsPieType(true);
		
		// Notify observers of this AnalysisStrategy when data is updated
		eventManager.notifyObservers(this.data);
		return this.data;
		
	}
	

}
