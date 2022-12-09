package GUIMediator;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartPanel;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ControllerModule.AnalysisController;
import ControllerModule.IParamValidator;
import ControllerModule.ParamValidator;
import GUIComponents.AddViewerButton;
import GUIComponents.AnalysisDropdown;
import GUIComponents.CountryDropdown;
import GUIComponents.FromDropdown;
import GUIComponents.GUIComponent;
import GUIComponents.RecalculateButton;
import GUIComponents.RemoveViewerButton;
import GUIComponents.ToDropdown;
import GUIComponents.ViewsDropdown;

/**
 * Concrete Mediator. All communications between concrete GUIcomponents take place through
 * the mediator. Components only interact with the mediator, which knows who has to handle requests.
 */
public class Mediator extends JFrame implements IMediator {
	
	private static final long serialVersionUID = 1L;
	private RecalculateButton recalculateButton;
	private AddViewerButton addViewerButton;
	private RemoveViewerButton removeViewerButton;
	private CountryDropdown countryDropdown;
	private AnalysisDropdown analysisDropdown;
	private FromDropdown fromDropdown;
	private ToDropdown toDropdown;
	private ViewsDropdown viewsDropdown;
	private JPanel westJPanel;
	private JPanel southJPanel;
	private JPanel northJPanel;
	private JPanel eastJPanel;
	private ArrayList<ChartPanel> chartPanels;
	private JScrollPane jScrollPane;
	private ArrayList<String> views;
	private AnalysisController analysis;
	private String countryString;
	private String analysiString;
	private String currentSelectedView;
	private int startYear;
	private int endYear;
	private JLabel countryError;
	private JLabel yearError;
	private JLabel analysisError;
	private JLabel viewsError;
	private JLabel recalculateError;
	private IParamValidator paramValidator;

	private static Mediator instance;

	private Mediator() {
		
		analysis = AnalysisController.getInstance();
		paramValidator = new ParamValidator();
		startYear = 0;
		endYear = 0;
		views = new ArrayList<>();
		chartPanels = new ArrayList<>();
		
		Vector<String> countriesList = new Vector<String>();
        
		// Contries.json contains a list of countries for which data fetching IS ALLOWED
        try (FileReader reader = new FileReader("Countries.json")) {
            
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(reader);
            JsonObject jsonObject = element.getAsJsonObject();
            
            Set<Map.Entry<String, JsonElement>> countries = jsonObject.entrySet();
            
            for(Map.Entry<String, JsonElement> country: countries) {
               countriesList.add(country.getKey());
            }
            
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        // Also adding countries for which data fetching is NOT allowed 
        countriesList.add("Andorra");
        countriesList.add("Colombia");
        countriesList.add("Ecuador");
        countriesList.add("Fiji");
        countriesList.sort(null);
	
        Vector<Integer> years = new Vector<Integer>();
		for (int i = 2020; i >= 1985; i--) {
			years.add(i);
		}
		
		Vector<String> analysisList = new Vector<String>();
		analysisList.add("Average Forest Area");
		analysisList.add("Education Expenditure");
		analysisList.add("CO2 Vs Energy Vs AirPollution");
		analysisList.add("CO2 Vs GDP");
		analysisList.add("Population Density Vs GDP Growth");
		analysisList.add("Air Pollution Vs Forest Area");
		analysisList.add("Health Expenditure Vs Hospital Beds");
		analysisList.add("Education Vs Health Expenditure");
		analysisList.sort(null);
		
		Vector<String> viewsList = new Vector<String>();
		viewsList.add("Pie Chart");
		viewsList.add("Line Graph");
		viewsList.add("Bar Graph");
		viewsList.add("Scatter Plot");
		viewsList.add("Report");
		viewsList.sort(null);
		this.registerComponent(new RecalculateButton());
		this.registerComponent(new AddViewerButton());
		this.registerComponent(new RemoveViewerButton());
		this.registerComponent(new CountryDropdown(countriesList));
		this.registerComponent(new FromDropdown(years));
		this.registerComponent(new ToDropdown(years));
		this.registerComponent(new AnalysisDropdown(analysisList));
		this.registerComponent(new ViewsDropdown(viewsList));
		this.createMainGUI();
	}
	
	
	public static Mediator getInstance() {
		
		if (instance == null) {
			synchronized(Mediator.class) {
				if (instance == null) {
					instance = new Mediator();
				}
			}            
		}
		return instance;
	} 
	
	@Override
	public void registerComponent(GUIComponent component) {
		component.setMediator(this);
		
		switch (component.getComponentName()) {
			case "RecalculateButton":
				recalculateButton = (RecalculateButton) component;	
				break;
			case "AddViewerButton":
				addViewerButton = (AddViewerButton) component;
				break;
			case "RemoveViewerButton":
				removeViewerButton = (RemoveViewerButton) component;
				break;
			case "CountryDropdown":
				countryDropdown = (CountryDropdown) component;
				break;
			case "AnalysisDropdown":
				analysisDropdown = (AnalysisDropdown) component;
				break;
			case "FromDropdown":
				fromDropdown = (FromDropdown) component;
				break;
			case "ToDropdown":
				toDropdown = (ToDropdown) component;
				break;
			case "ViewsDropdown":
				viewsDropdown = (ViewsDropdown) component;
				break;
		}	
	}
	
	@Override
	public void createMainGUI() {
		
		//frame = new JFrame();
		this.setTitle("Data Visualizer");
		this.setSize(1400, 750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		northJPanel = new JPanel();
		northJPanel.setName("northJPanel");
		
		JLabel countryLabel = new JLabel("Choose a country: ");
		JLabel fromLabel = new JLabel("From");
		JLabel toLabel = new JLabel("To");
		
		recalculateButton.setName("recalculateButton");
		
		northJPanel.add(countryLabel);
		northJPanel.add(countryDropdown);
		northJPanel.add(fromLabel);
		northJPanel.add(fromDropdown);
		northJPanel.add(toLabel);
		northJPanel.add(toDropdown);
		
		southJPanel = new JPanel();
		southJPanel.setName("southJPanel");
		
		JLabel viewsLabel = new JLabel("Available viwes: ");
		JLabel analysisLabel = new JLabel("Choose Analysis: ");
		
		recalculateButton.setName("RecalculateButton");
		
		southJPanel.add(viewsLabel);
		southJPanel.add(viewsDropdown);
		southJPanel.add(addViewerButton);
		southJPanel.add(removeViewerButton);
		southJPanel.add(analysisLabel);
		southJPanel.add(analysisDropdown);
		southJPanel.add(recalculateButton);
		
		eastJPanel = new JPanel();
		eastJPanel.setName("eastJPanel");

		westJPanel = new JPanel();
		westJPanel.setName("westJPanel");
		westJPanel.setLayout(new GridLayout(2, 0));
		
		this.getContentPane().add(northJPanel, BorderLayout.NORTH);
		this.getContentPane().add(southJPanel, BorderLayout.SOUTH);
		this.getContentPane().add(eastJPanel, BorderLayout.EAST);
		this.getContentPane().add(westJPanel, BorderLayout.WEST);
		
		countryError = new JLabel();
		countryError.setName("countryError");
		
		yearError = new JLabel();
		yearError.setName("yearError");
		
		analysisError = new JLabel();
		analysisError.setName("analysisError");
		
		viewsError = new JLabel();
		viewsError.setName("viewsError");
		
		recalculateError = new JLabel();
		recalculateError.setName("recalculateError");
		
		this.setVisible(true);

	}

	@Override
	public void addViewer() {	
		if(!views.contains(currentSelectedView)) {
			views.add(currentSelectedView);
		}
		
		if(analysiString != null && !analysiString.isEmpty()) {
			validateViews();
		}
	}

	@Override
	public void removeViewer() {
		if(views.contains(currentSelectedView)) {
			views.remove(currentSelectedView);
		}
		if(analysiString != null && !analysiString.isEmpty()) {
			validateViews();
		}
	}

	@Override
	public void recalculate() {
		chartPanels = new ArrayList<>();
		jScrollPane = null;
		
		if(countryString != null && !countryString.isEmpty() && analysiString != null && 
				!analysiString.isEmpty() && views.size() > 0 && startYear != 0 && endYear != 0) {
			recalculateError.setText("");
			southJPanel.add(recalculateError);
			this.getContentPane().add(southJPanel, BorderLayout.SOUTH);
			this.setVisible(true);
			// Request analysis
			analysis.requestAnalysis(analysiString, views, countryString, startYear, endYear);
		} else {
			recalculateError.setText("Please select all parameters!");
			southJPanel.add(recalculateError);
			this.getContentPane().add(southJPanel, BorderLayout.SOUTH);
			this.setVisible(true);
		}
	}
	
	@Override
	public void countrySelected(String selectedountry) {
		this.countryString = selectedountry;
		
		boolean result = paramValidator.validateCountry(countryString);
		
		if(result == false) {
			countryError.setText("Data fetching for selected country not allowed.");
			northJPanel.add(countryError);
			this.getContentPane().add(northJPanel, BorderLayout.NORTH);
			this.setVisible(true);	
		} else if (result == true) {
			countryError.setText("");
			northJPanel.add(countryError);
			this.getContentPane().add(northJPanel, BorderLayout.NORTH);
			this.setVisible(true);	
		}
	}

	@Override
	public void fromYearSelected(int selectedYear) {
		this.startYear = selectedYear;
		
		if(startYear != 0 && endYear != 0) {
			
			boolean result = paramValidator.validateYearRange(startYear, endYear);
			
			if(result == false) {
				yearError.setText("Choose start year smaller than end year.");
				northJPanel.add(yearError);
				this.getContentPane().add(northJPanel, BorderLayout.NORTH);
				this.setVisible(true);
			} else if (result == true){
				yearError.setText("");
				northJPanel.add(yearError);
				this.getContentPane().add(northJPanel, BorderLayout.NORTH);
				this.setVisible(true);	
			}
		}
		
		// Validate year range for analysis again if analysis was selected before and from year is changed 
		if(analysiString != null && !analysiString.isEmpty()) {
			boolean result = paramValidator.validateYearRangeForAnalysis(analysiString, startYear, endYear);
			
			if(result == false) {
				analysisError.setText("Analysis not available for selected year range.");
				northJPanel.add(analysisError);
				this.getContentPane().add(northJPanel, BorderLayout.NORTH);
				this.setVisible(true);
			} else if (result == true){
				analysisError.setText("");
				northJPanel.add(analysisError);
				this.getContentPane().add(northJPanel, BorderLayout.NORTH);
				this.setVisible(true);
			}
		}
	}

	@Override
	public void toYearSelected(int selectedYear) {
		this.endYear = selectedYear;
		
		if(startYear != 0 && endYear != 0) {
			
			boolean result = paramValidator.validateYearRange(startYear, endYear);
			
			if(result == false) {
				yearError.setText("Choose end year larger than start year.");
				northJPanel.add(yearError);
				this.getContentPane().add(northJPanel, BorderLayout.NORTH);
				this.setVisible(true);
			} else if (result == true){
				yearError.setText("");
				northJPanel.add(yearError);
				this.getContentPane().add(northJPanel, BorderLayout.NORTH);
				this.setVisible(true);	
			}
		}	
		
		// Validate year range for analysis again if analysis was selected before and to year is changed 
		if(analysiString != null && !analysiString.isEmpty()) {
			boolean result = paramValidator.validateYearRangeForAnalysis(analysiString, startYear, endYear);
			
			if(result == false) {
				analysisError.setText("Analysis not available for selected year range.");
				northJPanel.add(analysisError);
				this.getContentPane().add(northJPanel, BorderLayout.NORTH);
				this.setVisible(true);
			} else if (result == true){
				analysisError.setText("");
				northJPanel.add(analysisError);
				this.getContentPane().add(northJPanel, BorderLayout.NORTH);
				this.setVisible(true);
			}
		}
	}
	
	@Override
	public void analysisSelected(String selectedAnalysis) {
		
		this.analysiString = selectedAnalysis;
		
		if(startYear != 0 && endYear != 0) {
			boolean result = paramValidator.validateYearRangeForAnalysis(analysiString, startYear, endYear);
			
			if(result == false) {
				analysisError.setText("Analysis not available for selected year range.");
				northJPanel.add(analysisError);
				this.getContentPane().add(northJPanel, BorderLayout.NORTH);
				this.setVisible(true);
			} else if (result == true){
				analysisError.setText("");
				northJPanel.add(analysisError);
				this.getContentPane().add(northJPanel, BorderLayout.NORTH);
				this.setVisible(true);
			}
		} 
		
		validateViews();	
	}

	@Override
	public void viewSelected(String selectedView) {
		this.currentSelectedView = selectedView;
		
	}
	
	@Override
	public void addChartPanel(ChartPanel chartPanel) {
		chartPanels.add(chartPanel);
		displayCharts();
	}
	
	@Override
	public void addScrollPane(JScrollPane scrollPane) {
		this.jScrollPane = scrollPane;
		displayCharts();
	}
	
	/**
	 * <p> Displays all CharPanels in the list and JScrollPane
	 * </p>
	 */
	private void displayCharts() {
		for(ChartPanel chartPanel: chartPanels) {
			westJPanel.add(chartPanel);
		}
		
		if(jScrollPane != null) {
			westJPanel.add(jScrollPane);
		}
		
		this.getContentPane().add(westJPanel, BorderLayout.WEST);
		this.setVisible(true);		
	}
	
	/**
	 * <p> Validates if currently added Views are appropriate for the selected analysis 
	 * and displays error message if not.
	 * </p>
	 */
	private void validateViews() {
		
		int startingNumberOfViews = views.size();
		String errorMessage = "The following view is not available for the selected analysis (not added): "; 
		
		Iterator<String> iterator = views.iterator();
		
		 while (iterator.hasNext()) {
		      String view = iterator.next();
		      if (!paramValidator.validateViewer(analysiString, view)) {
		    	  errorMessage += view;
		    	  iterator.remove();  	
		      }
		 }
		
		int finalNumberOfViews = views.size();
		
		if(startingNumberOfViews != finalNumberOfViews) {
			viewsError.setText(errorMessage);
			westJPanel.add(viewsError);
			this.getContentPane().add(westJPanel, BorderLayout.WEST);
			this.setVisible(true);
		} else {
			viewsError.setText("");
			westJPanel.add(viewsError);
			this.getContentPane().add(westJPanel, BorderLayout.WEST);
			this.setVisible(true);
		}
	}

}
