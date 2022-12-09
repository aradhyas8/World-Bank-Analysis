package GUIMediator;

import javax.swing.JScrollPane;

import org.jfree.chart.ChartPanel;

import GUIComponents.GUIComponent;

public interface IMediator {
	
	/**
	 * <p> Registers GUI components
	 * </p>
	 * @param component to register
	 */
	public void registerComponent(GUIComponent component);
	
	/**
	 * <p> Creates main GUI with Frame, Panels, Labels, Dropdowns, etc using GUI components
	 * </p>
	 */
	public void createMainGUI();
	
	/**
	 * <p> Adds currently selected View to the list of Views
	 * </p>
	 */
	public void addViewer();
	
	/**
	 * <p> Removes currently selected View to the list of Views
	 * </p>

	 */
	public void removeViewer();
	
	/**
	 * <p> Processes user request to display selected charts for selected parameters
	 * </p>
	 */
	public void recalculate();

	/**
	 * <p> Sets currently selected country
	 * </p>
	 * @param country 
	 */
	public void countrySelected(String selectedountry);
	
	/**
	 * <p> Sets currently selected start year 
	 * </p>
	 * @param selectedYear - start year
	 */
	public void fromYearSelected(int selectedYear);
	
	/**
	 * <p> Sets currently selected end year 
	 * </p>
	 * @param selectedYear - end year
	 */
	public void toYearSelected(int selectedYear);
	
	/**
	 * <p> Sets currently selected analysis type 
	 * </p>
	 * @param selectedAnalysis
	 */
	public void analysisSelected(String selectedAnalysis);

	/**
	 * <p> Sets currently selected View 
	 * </p>
	 * @param selectedView
	 */
	public void viewSelected(String selectedView);
	
	/**
	 * <p> Adds a ChartPanel to the list of ChartPanels 
	 * </p>
	 * @param chartPanel to add
	 */
	public void addChartPanel(ChartPanel chartPanel);
	
	/**
	 * <p> Sets the scrollPane to the given scrollPane
	 * </p>
	 * @param scrollPane
	 */
	public void addScrollPane(JScrollPane scrollPane);
	
	

}
