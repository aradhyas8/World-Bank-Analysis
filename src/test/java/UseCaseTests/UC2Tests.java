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
 * Tests for UC2: Selecting a country to fetch and visualize data for
 */
public class UC2Tests {
	
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
	public void availableCountrySelected() {
	
		// Index 2 is Argentina for which data fetching is allowed. 
		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(3);
		
		// Error message should be blank
		window.panel("northJPanel").label("countryError").requireText("");
	}
	
	@Test (timeout = 50000)
	public void unavailableCountrySelected() {
	
		// Index 2 is Andorra for which data fetching is not allowed. 
		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(2);
		
		// Expect error message expected since country is not valid
		window.panel("northJPanel").label("countryError").requireText("Data fetching for selected country not allowed.");
	} 
	
	@Test (timeout = 50000)
	public void availableCountrySelectedAfterUnavailableCountry() {
	
		// Index 2 is Colombia for which data fetching is not allowed. 
		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(12);
		
		// Expect error message expected since country is not valid
		window.panel("northJPanel").label("countryError").requireText("Data fetching for selected country not allowed.");
		// Index 2 is Canada for which data fetching is allowed. 
		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(9);
				
		// Error message should be blank
		window.panel("northJPanel").label("countryError").requireText("");
	}

	@Test (timeout = 50000)
	public void availableCountrySelected2() {
	
		// Index 0 is Albania for which data fetching is allowed. 
		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(0);
		
		// Error message should be blank
		window.panel("northJPanel").label("countryError").requireText("");
	}
	

	@After
	 public void tearDown() {
		 window.cleanUp();
	 }

}
