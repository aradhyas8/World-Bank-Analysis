package ViewerModule;

import java.util.HashMap;
import java.util.Map.Entry;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import AnalysisModule.AnalysisData;
import GUIMediator.IMediator;
import GUIMediator.Mediator;

public class LineGraph implements Observer, IViewer{
	
	AnalysisData data;
	ChartPanel chartPanel; 
	IMediator mediator;
	
	public LineGraph() {
		mediator = Mediator.getInstance();
	}
	
	@Override
	public void update(AnalysisData data) {
		
		this.data = data;
		createChartPanel();
		
	}
	
	@Override
	public void createChartPanel(){
    	
        DefaultCategoryDataset linedataset = new DefaultCategoryDataset();
        
        String titleString = data.getTitle();
        
        HashMap<String, double[]> dataHashMap = data.getData();
		
		for (Entry<String, double[]> entry : dataHashMap.entrySet()) {
		   
			String key = entry.getKey();
		    double[] values = entry.getValue();	
		    
			for(int i = 0; i < values.length; i++) {
				int year = i + data.getStartYear();
				String yearString = "" + year;
				linedataset.setValue(values[i], key, yearString);
			}	
		}
	
		JFreeChart lineChart = ChartFactory.createLineChart(titleString, data.getxAxis(), "", linedataset, PlotOrientation.VERTICAL, true, true, false);
		chartPanel = new ChartPanel(lineChart);
		
		mediator.addChartPanel(chartPanel);
	}
}
		
