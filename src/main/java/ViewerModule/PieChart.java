package ViewerModule;

import java.util.HashMap;
import java.util.Map.Entry;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import AnalysisModule.AnalysisData;
import GUIMediator.IMediator;
import GUIMediator.Mediator;

public class PieChart implements Observer, IViewer{

	AnalysisData data;
	ChartPanel chartPanel; 
	IMediator mediator;
	
	public PieChart() {
		mediator = Mediator.getInstance();
	}
	
	@Override
	public void update(AnalysisData data) {	
		this.data = data;
		createChartPanel();
	}
	
	@Override
	public void createChartPanel() {
	
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		HashMap<String, double[]> dataHashMap = data.getData();
		
		for (Entry<String, double[]> entry : dataHashMap.entrySet()) {
		   
			String key = entry.getKey();
		    double[] values = entry.getValue();
		    dataset.setValue(key, values[0]);   
		}
		
		String titleString = data.getTitle();
	
		JFreeChart pieChart = ChartFactory.createPieChart(titleString, dataset, true, false, false);
		chartPanel = new ChartPanel(pieChart);
		
		mediator.addChartPanel(chartPanel);

	}
	
}
