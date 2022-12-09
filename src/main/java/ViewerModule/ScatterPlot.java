package ViewerModule;

import java.util.HashMap;
import java.util.Map.Entry;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;  
import org.jfree.data.xy.XYSeries;  
import org.jfree.data.xy.XYSeriesCollection; 

import AnalysisModule.AnalysisData;
import GUIMediator.IMediator;
import GUIMediator.Mediator;

public class ScatterPlot implements Observer, IViewer {
	
	AnalysisData data;
	ChartPanel chartPanel; 
	IMediator mediator;
	
	public ScatterPlot() {
		mediator = Mediator.getInstance();
	}
	
	@Override
	public void update(AnalysisData data) {
		this.data = data;
		createChartPanel();
	}

	@Override
	public void createChartPanel() {
		
		XYSeriesCollection scatterDataset = new XYSeriesCollection();
		
		HashMap<String, double[]> dm = data.getData();
		
		for(Entry<String, double[]> entry : dm.entrySet()) {
			String key = entry.getKey();
			double [] values = entry.getValue();
			XYSeries series = new XYSeries(key);
			double start = data.getStartYear();
			for(int i=0; i<values.length; i++) {
				series.add(start+i, values[i]);
			}
			scatterDataset.addSeries(series);
		} 
		String titleString = data.getTitle();
		
		JFreeChart scatterplot = ChartFactory.createScatterPlot(titleString, data.getxAxis() , data.getyAxisA(), scatterDataset);
		chartPanel = new ChartPanel(scatterplot);
		
		mediator.addChartPanel(chartPanel);
		
	}

}
