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
 * Tests for UC8: Displaying the results
 */
public class UC8Tests {
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
	public void displaySingleSeriesData() {
		//Select Country Australia
		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(4);
		//Select Years for which data fetching is to be done. ( Start)
		window.panel("northJPanel").comboBox("FromDropdown").selectItem(10);
		//Select Years for which data fetching is to be done. ( End )
		window.panel("northJPanel").comboBox("ToDropdown").selectItem(9);
		// Select Analysis "Select Education Expenditure" for which Pie Chart is available
		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(4);
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
	public void displayDoubleSeriesData() {
		//Select Country Canada
		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(9);
		//Select Years for which data fetching is to be done. ( Start)
		window.panel("northJPanel").comboBox("FromDropdown").selectItem(3);
		//Select Years for which data fetching is to be done. ( End )
		window.panel("northJPanel").comboBox("ToDropdown").selectItem(0);
		// Select Analysis "Population vs GDP" for which line graph is available
		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(7);
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
	public void displayTripleSeriesData() {
		//Select Country Australia
		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(4);
		//Select Years for which data fetching is to be done. ( Start)
		window.panel("northJPanel").comboBox("FromDropdown").selectItem(9);
		//Select Years for which data fetching is to be done. ( End )
		window.panel("northJPanel").comboBox("ToDropdown").selectItem(6);
		// Select Analysis "CO2 vs Energy vs Air Pollution" for which line graph is available
		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(7);
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
	public void noDisplayDoubleSeriesData() {
		//Select Country Canada
		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(9);
		//Select Years for which data fetching is to be done. ( Start)
		window.panel("northJPanel").comboBox("FromDropdown").selectItem(3);
		//Select Years for which data fetching is to be done. ( End )
		window.panel("northJPanel").comboBox("ToDropdown").selectItem(0);
		// Select Analysis "Population vs GDP" for which Pie Graph is not available
		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(7);
		// Select Pie Chart
		window.panel("southJPanel").comboBox("ViewsDropdown").selectItem(2);
		// Add viewer
		window.panel("southJPanel").button("AddViewerButton").click();
		// Error message should be visible
		window.panel("westJPanel").label("viewsError").requireText("The following view is not available for the selected analysis (not added): Pie Chart");
		
    }
    
    @Test (timeout = 50000)
  	public void noDisplaySingleSeriesData() {
  		//Select Country Australia
  		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(4);
  		//Select Years for which data fetching is to be done. ( Start)
  		window.panel("northJPanel").comboBox("FromDropdown").selectItem(10);
  		//Select Years for which data fetching is to be done. ( End )
  		window.panel("northJPanel").comboBox("ToDropdown").selectItem(9);
  		// Select Analysis "Average Forest Area" for which line graph is not available
  		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(1);
  		// Select Line Graph
  		window.panel("southJPanel").comboBox("ViewsDropdown").selectItem(1);
  		// Add viewer
  		window.panel("southJPanel").button("AddViewerButton").click();
  		// Error message should be visible
  		window.panel("westJPanel").label("viewsError").requireText("The following view is not available for the selected analysis (not added): Line Graph");
  		
      }

    @After
    public void tearDown() {
        window.cleanUp();
    }
}
