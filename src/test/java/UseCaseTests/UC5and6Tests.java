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
 * Tests for UC5 and UC6: Adding and Removing visualization graphs to display the obtained/computed data
 */
public class UC5and6Tests {
	
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
	public void addViewerNotAvailableForAnalysis() {
		
		// Select Analysis "Average Forest Area" for which line graph is not available
		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(1);
		// Select Line Graph
		window.panel("southJPanel").comboBox("ViewsDropdown").selectItem(1);
		// Add viewer
		window.panel("southJPanel").button("AddViewerButton").click();
		// Appropriate error message should be visible
		window.panel("westJPanel").label("viewsError").requireText("The following view is not available for the selected analysis (not added): Line Graph");
	}
	
	@Test (timeout = 50000)
	public void addViewersNotAvailableAndAvailableForAnalysis() {
		
		// Select Analysis "Average Forest Area" for which line graph is not available
		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(1);
		// Select Line Graph
		window.panel("southJPanel").comboBox("ViewsDropdown").selectItem(1);
		// Add viewer
		window.panel("southJPanel").button("AddViewerButton").click();
		// Appropriate error message should be visible
		window.panel("westJPanel").label("viewsError").requireText("The following view is not available for the selected analysis (not added): Line Graph");
		// Select Pie Chart
		window.panel("southJPanel").comboBox("ViewsDropdown").selectItem(2);
		// Add viewer
		window.panel("southJPanel").button("AddViewerButton").click();
		// No error message should be visible
		window.panel("westJPanel").label("viewsError").requireText("");
		
	}
	
	@Test (timeout = 50000)
	public void addViewerAvailableForAnalysis() {
		
		// Select Analysis "Average Forest Area"
		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(1);
		// Select Pie Chart
		window.panel("southJPanel").comboBox("ViewsDropdown").selectItem(2);
		// Add viewer
		window.panel("southJPanel").button("AddViewerButton").click();
		// No error message should be visible
		window.panel("westJPanel").label("viewsError").requireText("");
	}
	
	@Test (timeout = 50000)
	public void removeViewerAvailableForAnalysis() {
		
		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(9);
		window.panel("northJPanel").comboBox("FromDropdown").selectItem(15);
		window.panel("northJPanel").comboBox("ToDropdown").selectItem(10);
		// Select Pie Chart
		window.panel("southJPanel").comboBox("ViewsDropdown").selectItem(2);
		// Add viewer
		window.panel("southJPanel").button("AddViewerButton").click();
		// Select Pie Chart
		window.panel("southJPanel").comboBox("ViewsDropdown").selectItem(2);
		// Remove viewer
		window.panel("southJPanel").button("RemoveViewerButton").click();
		// Select Analysis "Average Forest Area"
		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(1);
		// No error message should be visible
		window.panel("westJPanel").label("viewsError").requireText("");
		window.panel("southJPanel").button("RecalculateButton").click();
		// We have selected all required params
		// Error message indicates we removed the 1 viewer that we added (so no viewers currently added)
		window.panel("southJPanel").label("recalculateError").requireText("Please select all parameters!");
	}
	
	@Test (timeout = 50000)
	public void addTwoRemoveOneViewerAvailableForAnalysis() {
		
		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(9);
		window.panel("northJPanel").comboBox("FromDropdown").selectItem(15);
		window.panel("northJPanel").comboBox("ToDropdown").selectItem(10);
		// Select Pie Chart
		window.panel("southJPanel").comboBox("ViewsDropdown").selectItem(2);
		// Add viewer
		window.panel("southJPanel").button("AddViewerButton").click();
		// Select Report
		window.panel("southJPanel").comboBox("ViewsDropdown").selectItem(3);
		// Add viewer
		window.panel("southJPanel").button("AddViewerButton").click();
		// Select Pie Chart
		window.panel("southJPanel").comboBox("ViewsDropdown").selectItem(2);
		// Remove viewer
		window.panel("southJPanel").button("RemoveViewerButton").click();
		// Select Analysis "Average Forest Area"
		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(1);
		// No error message should be visible
		window.panel("westJPanel").label("viewsError").requireText("");
		window.panel("southJPanel").button("RecalculateButton").click();
		// We have selected all required params
		// E// No error message, which indicates we have at least 1 viewer added since we added 2 and removed only 1.
		window.panel("southJPanel").label("recalculateError").requireText("");
	}
	
	public void addViewerAvailableForAnalysisNoRecalculateError() {
		
		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(9);
		window.panel("northJPanel").comboBox("FromDropdown").selectItem(15);
		window.panel("northJPanel").comboBox("ToDropdown").selectItem(10);
		// Select Pie Chart
		window.panel("southJPanel").comboBox("ViewsDropdown").selectItem(2);
		// Add viewer
		window.panel("southJPanel").button("AddViewerButton").click();
		// Select Analysis "Average Forest Area"
		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(1);
		// No error message should be visible
		window.panel("westJPanel").label("viewsError").requireText("");
		window.panel("southJPanel").button("RecalculateButton").click();
		// We have selected all required params
		// No error message, which indicates we have at least 1 viewer added 
		window.panel("southJPanel").label("recalculateError").requireText("");
	}
	
	
	@Test (timeout = 50000)
	public void removeViewersAvailableAndAvailableForDiffAnalysisType() {
		// select the country, start and end years 
		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(9);
		window.panel("northJPanel").comboBox("FromDropdown").selectItem(15);
		window.panel("northJPanel").comboBox("ToDropdown").selectItem(10);

		// Select Analysis "Average Forest Area" for which line graph, scatter plots and bar graph is not available
		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(1);
		
		//select bar graph.
		window.panel("southJPanel").comboBox("ViewsDropdown").selectItem(0);
		window.panel("southJPanel").button("AddViewerButton").click();
		// Appropriate error message should be visible
		window.panel("westJPanel").label("viewsError").requireText("The following view is not available for the selected analysis (not added): Bar Graph");
		
		// Select Line Graph
		window.panel("southJPanel").comboBox("ViewsDropdown").selectItem(1);
		window.panel("southJPanel").button("AddViewerButton").click();
		window.panel("westJPanel").label("viewsError").requireText("The following view is not available for the selected analysis (not added): Line Graph");
		
		//select scatter plot 
		window.panel("southJPanel").comboBox("ViewsDropdown").selectItem(4);
		window.panel("southJPanel").button("AddViewerButton").click();
		window.panel("westJPanel").label("viewsError").requireText("The following view is not available for the selected analysis (not added): Scatter Plot");
	
	}
	
	@After
	 public void tearDown() {
		 window.cleanUp();
	 }


}
