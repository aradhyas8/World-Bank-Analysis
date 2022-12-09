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
 * Tests for UC4: Selecting the years for which the analysis type is to be performed
 */
public class UC4Tests {
	
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
	public void smallerToYearSelected() {
	
		// Since years are sorted from largest to smallest, lower index is a larger year
		window.panel("northJPanel").comboBox("FromDropdown").selectItem(2);
		
		// Select a smaller To year
		window.panel("northJPanel").comboBox("ToDropdown").selectItem(6);
		
		// Appropriate error message should be visible
		window.panel("northJPanel").label("yearError").requireText("Choose end year larger than start year.");
	}
	
	@Test (timeout = 50000)
	public void largerFromYearSelected() {
		
		window.panel("northJPanel").comboBox("ToDropdown").selectItem(8);
		
		// Select a larger To year
		window.panel("northJPanel").comboBox("FromDropdown").selectItem(3);
		
		// Appropriate error message should be visible
		window.panel("northJPanel").label("yearError").requireText("Choose start year smaller than end year.");
	}
	
	@Test (timeout = 50000)
	public void validYearRangeSelected() {
		
		// Select a larger To year
		window.panel("northJPanel").comboBox("FromDropdown").selectItem(9);
		
		window.panel("northJPanel").comboBox("ToDropdown").selectItem(3);
		
		// Appropriate error message should be visible
		window.panel("northJPanel").label("yearError").requireText("");
	}
	
	@Test (timeout = 50000)
	public void unavailableYearRangeSelected() {
		
		// Select 2019
		window.panel("northJPanel").comboBox("FromDropdown").selectItem(5);
		
		// Select 2015
		window.panel("northJPanel").comboBox("ToDropdown").selectItem(1);
				
		// Select Analysis "Education Expenditure" which is not available for the selected range
		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(4);
		
		// Appropriate error message should be visible
		window.panel("northJPanel").label("analysisError").requireText("Analysis not available for selected year range.");
	}
	
	@Test (timeout = 50000)
	public void availableYearRangeSelected() {
		
		window.panel("northJPanel").comboBox("FromDropdown").selectItem(15);
		window.panel("northJPanel").comboBox("ToDropdown").selectItem(10);
		
		// Select Analysis "Average Forest Area" which is available for the selected range
		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(1);
		
		// No error message should be visible
		window.panel("northJPanel").label("analysisError").requireText("");
	}
	
	
	
	@After
	 public void tearDown() {
		 window.cleanUp();
	 }


}
