package UseCaseTests;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import GUIMediator.Mediator;

/**
 * Tests for UC7: Performing the analysis. More tests in AnalysisStrategyTest
 */
public class UC7Tests {
	
private FrameFixture window;
	
	@BeforeClass
	public static void setUpOnce() {
		FailOnThreadViolationRepaintManager.install();
	}
	
	@Before
	public void setUp() {
		
		Mediator mediator = GuiActionRunner.execute(() -> Mediator.getInstance());
		
		window = new FrameFixture(mediator);
	    window.show(); 
	    window.maximize();
	}
	
	@Test (timeout = 50000)
	public void CalculatePieAnalysis() {
		//Select Country Australia
		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(4);
		//Select Years for which data fetching is to be done. ( Start)
		window.panel("northJPanel").comboBox("FromDropdown").selectItem(10);
		//Select Years for which data fetching is to be done. ( End )
		window.panel("northJPanel").comboBox("ToDropdown").selectItem(9);
		// Select Analysis "Select Education Expenditure" for which line graph is available
		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(4);
		window.panel("northJPanel").label("yearError").requireText("");
		// Select Pie Chart
		window.panel("southJPanel").comboBox("ViewsDropdown").selectItem(2);
		// Add viewer
		window.panel("southJPanel").button("AddViewerButton").click();
		// No error message should be visible
		window.panel("westJPanel").label("viewsError").requireText("");
		window.panel("southJPanel").button("RecalculateButton").click();
		// Pie Chart should be visible
		window.panel("southJPanel").label("recalculateError").requireText("");
    }
	
	@Test (timeout = 50000)
	public void calculateLineGraphAnalysis() {
		//Select Country Canada
		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(9);
		//Select Years for which data fetching is to be done. ( Start)
		window.panel("northJPanel").comboBox("FromDropdown").selectItem(3);
		//Select Years for which data fetching is to be done. ( End )
		window.panel("northJPanel").comboBox("ToDropdown").selectItem(0);
		// Select Analysis "Population vs GDP" for which line graph is available
		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(7);
		window.panel("northJPanel").label("yearError").requireText("");
		// Select Line Graph
		window.panel("southJPanel").comboBox("ViewsDropdown").selectItem(1);
		// Add viewer
		window.panel("southJPanel").button("AddViewerButton").click();
		// No error message should be visible
		window.panel("westJPanel").label("viewsError").requireText("");
		window.panel("southJPanel").button("RecalculateButton").click();
		// Line Graph should be visible
		window.panel("southJPanel").label("recalculateError").requireText("");
		
    }
	
	@Test (timeout = 50000)
	public void calculateBarGraphAnalysis() {
		//Select Country Australia
		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(4);
		//Select Years for which data fetching is to be done. ( Start)
		window.panel("northJPanel").comboBox("FromDropdown").selectItem(9);
		//Select Years for which data fetching is to be done. ( End )
		window.panel("northJPanel").comboBox("ToDropdown").selectItem(6);
		// Select Analysis "CO2 vs Energy vs Air Pollution" for which line graph is available
		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(7);
		window.panel("northJPanel").label("yearError").requireText("");
		// Select Bar Graph
		window.panel("southJPanel").comboBox("ViewsDropdown").selectItem(0);
		// Add viewer
		window.panel("southJPanel").button("AddViewerButton").click();
		// No error message should be visible
		window.panel("westJPanel").label("viewsError").requireText("");
		window.panel("southJPanel").button("RecalculateButton").click();
		// Bar Graph should be visible
		window.panel("southJPanel").label("recalculateError").requireText("");
    }
	
	@Test (timeout = 50000)
	public void calculateScatterPlotAnalysis() {
		//Select Country Australia
		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(4);
		//Select Years for which data fetching is to be done. ( Start)
		window.panel("northJPanel").comboBox("FromDropdown").selectItem(9);
		//Select Years for which data fetching is to be done. ( End )
		window.panel("northJPanel").comboBox("ToDropdown").selectItem(6);
		// Select Analysis "CO2 vs Energy vs Air Pollution" for which line graph is available
		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(7);
		window.panel("northJPanel").label("yearError").requireText("");
		// Select Scatter Plot
		window.panel("southJPanel").comboBox("ViewsDropdown").selectItem(4);
		// Add viewer
		window.panel("southJPanel").button("AddViewerButton").click();
		// No error message should be visible
		window.panel("westJPanel").label("viewsError").requireText("");
		window.panel("southJPanel").button("RecalculateButton").click();
		// Scatter Plot should be visible
		window.panel("southJPanel").label("recalculateError").requireText("");
    }
	
	@Test (timeout = 50000)
	public void CalculateReportAnalysis() {
		//Select Country Australia
		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(4);
		//Select Years for which data fetching is to be done. ( Start)
		window.panel("northJPanel").comboBox("FromDropdown").selectItem(10);
		//Select Years for which data fetching is to be done. ( End )
		window.panel("northJPanel").comboBox("ToDropdown").selectItem(9);
		// Select Analysis "Select Education Expenditure" for which line graph is available
		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(4);
		window.panel("northJPanel").label("yearError").requireText("");
		// Select Report
		window.panel("southJPanel").comboBox("ViewsDropdown").selectItem(3);
		// Add viewer
		window.panel("southJPanel").button("AddViewerButton").click();
		// No error message should be visible
		window.panel("westJPanel").label("viewsError").requireText("");
		window.panel("southJPanel").button("RecalculateButton").click();
		// Report should be visible
		window.panel("southJPanel").label("recalculateError").requireText("");
    }
	
	@Test (timeout = 50000)
	public void AvgForestAreaForPieChartAndReport() {
		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(9);
		window.panel("northJPanel").label("countryError").requireText("");
		window.panel("northJPanel").comboBox("FromDropdown").selectItem(15);
		window.panel("northJPanel").comboBox("ToDropdown").selectItem(10);
		window.panel("northJPanel").label("yearError").requireText("");
		// Select Pie Chart
		window.panel("southJPanel").comboBox("ViewsDropdown").selectItem(2);
		// Add viewer
		window.panel("southJPanel").button("AddViewerButton").click();
		// Select Report
		window.panel("southJPanel").comboBox("ViewsDropdown").selectItem(3);
		// Add viewer
		window.panel("southJPanel").button("AddViewerButton").click();
		// Select Analysis "Average Forest Area" which is available for the selected range
		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(1);
		// No error message should be visible
		window.panel("northJPanel").label("analysisError").requireText("");
		
		window.panel("southJPanel").button("RecalculateButton").click();
		
		window.panel("southJPanel").label("recalculateError").requireText("");
	}
	
	
	@After
	 public void tearDown() {
		 window.cleanUp();
	 }


}