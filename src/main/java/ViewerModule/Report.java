package ViewerModule;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import AnalysisModule.AnalysisData;
import GUIMediator.IMediator;
import GUIMediator.Mediator;

public class Report implements Observer, IViewer{
	
	AnalysisData data;
	JScrollPane reportPanel;
	IMediator mediator;
	
	public Report() {
		mediator = Mediator.getInstance();;
	}
	
	@Override
	public void update(AnalysisData data) {
		this.data = data;
		createChartPanel();
	}
	
	@Override
	public void createChartPanel() {
	
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBackground(Color.white);
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		
		int startYear = data.getStartYear();
		int endYear = data.getEndYear();
		String titString = data.getTitle();
		
		HashMap<String, double[]> dataHashMap = data.getData();
		
		String reportString;
		reportString = titString + "\n" + "====================================\n";
		
		if(data.getIsPieType() == true) {
			
			for (Entry<String, double[]> entry : dataHashMap.entrySet()) {
				   
				String key = entry.getKey();
			    double[] values = entry.getValue();
			    // Get the ith value from the array for each dataset in the HashMap
			    double value = values[0];
			    reportString += key + " => " + value + "\n";
			   
			}
		} else {
			
			int range = endYear - startYear + 1;
			
			for(int i = 0; i < range; i++) {
				
				// Since the arrays for each dataset contain the values in order, we can simple loop through 
				// and display value for each year starting from the start year
				reportString += "Year " + (startYear + i) + ":\n";
				
				// For each year, we loop through all entries in the HashMap to get the corresponding value for that year
				for (Entry<String, double[]> entry : dataHashMap.entrySet()) {
					   
					String key = entry.getKey();
				    double[] values = entry.getValue();
				    // Get the ith value from the array for each dataset in the HashMap
				    double value = values[i];
				    reportString += key + " => " + value + "\n";	   
				}
			}
		}

		report.setText(reportString);
		reportPanel = new JScrollPane(report);
		
		mediator.addScrollPane(reportPanel);
		
	}

}
