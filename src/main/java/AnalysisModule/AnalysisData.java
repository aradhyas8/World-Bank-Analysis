package AnalysisModule;
import java.util.HashMap;

public class AnalysisData {
	
	private String Title;
	private String Country;
	private int StartYear;
	private int EndYear;
	private HashMap<String, double[]> dataHashMap;
	private String xAxis;
	private String yAxisA;
	private String yAxisB;
	private String yAxisC;
	private boolean isPieType;
	
	public AnalysisData(String country, String title, int startYear, int endYear) {
		
		this.dataHashMap = new HashMap<>();
		this.Country = country;
		this.Title = title;
		this.StartYear = startYear;
		this.EndYear = endYear;
		this.isPieType = false;
	}
	
	public String getTitle() {
		return this.Title;
	}
	
	public String getCountry() {
		return this.Country;
	}
	
	public int getStartYear() {
		return this.StartYear;
	}
	
	public int getEndYear() {
		return this.EndYear;
	}
	
	public HashMap<String, double[]> getData() {
		return this.dataHashMap;
	}
	
	public String getxAxis() {
		return this.xAxis;
	}
	
	public String getyAxisA() {
		return this.yAxisA;
	}
	
	public String getyAxisB() {
		return this.yAxisB;
	}
	
	public String getyAxisC() {
		return this.yAxisC;
	}
	
	public boolean getIsPieType() {
		return this.isPieType;
	}
	
	public void setxAxis(String xAxis) {
		this.xAxis = xAxis;
	}
	
	public void setyAxisA(String yAxis) {
		this.yAxisA = yAxis;
	}
	
	public void setyAxisB(String yAxis) {
		this.yAxisB = yAxis;
	}
	
	public void setyAxisC(String yAxis) {
		this.yAxisC = yAxis;
	}
	
	public void setData(HashMap<String, double[]> data) {
		this.dataHashMap = data;
	}
	
	public void setIsPieType(boolean value) {
		this.isPieType = value;
	}
	
	

}
