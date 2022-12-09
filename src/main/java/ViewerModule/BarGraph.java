package ViewerModule;

import java.util.HashMap;
import java.util.Map.Entry;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import org.jfree.chart.ChartPanel;


import AnalysisModule.AnalysisData;
import GUIMediator.IMediator;
import GUIMediator.Mediator;

public class BarGraph implements Observer, IViewer {

	AnalysisData data;
	ChartPanel chartPanel; 
	IMediator mediator;
	
	public BarGraph() {
		mediator = Mediator.getInstance();
	}
	
	@Override
	public void update(AnalysisData data) {
		
		this.data = data;
		createChartPanel();
	}

	@Override
	public void createChartPanel() {
		
		DefaultCategoryDataset bardataSet = new DefaultCategoryDataset();
		
		String titleString = data.getTitle();
		
		HashMap<String, double[]> dm = data.getData();
		
		for(Entry<String, double[]> entry : dm.entrySet()) {
			
			String key = entry.getKey();
			double [] values = entry.getValue();
			
			for(int i = 0; i < values.length; i++) {
				int year = i + data.getStartYear();
				String yearString = "" + year;
				bardataSet.setValue(values[i], key, yearString);
			}		
		}
		
		JFreeChart barGraph = ChartFactory.createBarChart(titleString, data.getxAxis(), null, bardataSet);
		chartPanel = new ChartPanel(barGraph);

		mediator.addChartPanel(chartPanel);
		
	}
	

}
