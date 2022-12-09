package AnalysisModule;

public abstract class AnalysisStrategy {
	
	public EventManager eventManager;
	
	public AnalysisStrategy() {
		eventManager = new EventManager();
	}
	
	public abstract AnalysisData doAnalysis(String country, int startYear, int endYear);
}
