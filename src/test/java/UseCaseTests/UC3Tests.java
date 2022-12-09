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
 * Tests for UC3: Selecting an analysis type after choosing a valid country for data fetching.
 */
public class UC3Tests {

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

//	
	
	@Test (timeout = 50000)
	public void UserSelectsAnalysis1()
	{
		
		//Select Country Australia
		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(4);
		//Select Years for which data fetching is to be done. ( Start)
		window.panel("northJPanel").comboBox("FromDropdown").selectItem(10);
		//Select Years for which data fetching is to be done. ( End )
		window.panel("northJPanel").comboBox("ToDropdown").selectItem(9);
		// Select Analysis "Air Pollution vs Forest Area" 
		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(0);	
	}
	
	@Test (timeout = 50000)
	public void UserSelectsAnalysis2()
	{
		
		//Select Country Australia
		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(4);
		//Select Years for which data fetching is to be done. ( Start)
		window.panel("northJPanel").comboBox("FromDropdown").selectItem(10);
		//Select Years for which data fetching is to be done. ( End )
		window.panel("northJPanel").comboBox("ToDropdown").selectItem(9);
		// Select Analysis "Average Forest Area" 
		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(1);	
	}
	
	@Test (timeout = 50000)
	public void UserSelectsAnalysis3()
	{
		
		//Select Country Australia
		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(4);
		//Select Years for which data fetching is to be done. ( Start)
		window.panel("northJPanel").comboBox("FromDropdown").selectItem(10);
		//Select Years for which data fetching is to be done. ( End )
		window.panel("northJPanel").comboBox("ToDropdown").selectItem(9);
		// Select Analysis "C02 vs Energy vs Population" 
		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(2);	
	}
	
	@Test (timeout = 50000)
	public void UserSelectsAnalysis4()
	{
		
		//Select Country Australia
		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(4);
		//Select Years for which data fetching is to be done. ( Start)
		window.panel("northJPanel").comboBox("FromDropdown").selectItem(10);
		//Select Years for which data fetching is to be done. ( End )
		window.panel("northJPanel").comboBox("ToDropdown").selectItem(9);
		// Select Analysis "C02 vs GDP" 
		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(3);	
	}
	
	@Test (timeout = 50000)
	public void UserSelectsAnalysis5()
	{
		
		//Select Country Australia
		window.panel("northJPanel").comboBox("CountryDropdown").selectItem(4);
		//Select Years for which data fetching is to be done. ( Start)
		window.panel("northJPanel").comboBox("FromDropdown").selectItem(10);
		//Select Years for which data fetching is to be done. ( End )
		window.panel("northJPanel").comboBox("ToDropdown").selectItem(9);
		// Select Analysis "Education Expenditure" 
		window.panel("southJPanel").comboBox("AnalysisDropdown").selectItem(4);	
	}
	@After
	public void tearDown() {
		window.cleanUp();
	}


}
