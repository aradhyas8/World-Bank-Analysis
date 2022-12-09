package ControllerModule;

public interface IParamValidator {
	
	/**
	 * <p> Checks whether data fetching for given country is allowed or not
	 * </p>
	 * @param country to validate
	 * @return boolean indicating whether country is valid or not
	 */
	public boolean validateCountry(String country);
	
	/**
	 * <p> Checks whether end year is after start year
	 * </p>
	 * @param startYear
	 * @param endYear
	 * @return boolean indicating whether the years range is valid or not
	 */
	public boolean validateYearRange(int startYear, int endYear);
	
	/**
	 * <p> Checks whether given year range for the given analysis is valid or not
	 * since certain types of analyses are not available for certain years.
	 * </p>
	 * @param analysis for which to valid year range
	 * @param startYear
	 * @param endYear
	 * @return boolean indicating whether the years range is valid or not
	 */
	public boolean validateYearRangeForAnalysis(String analysis, int startYear, int endYear);
	
	/**
	 * <p> Checks whether given view is valid for the given analysis since
	 * not all viewers are appropriate for all analysis types
	 * </p>
	 * @param analysis for which to valid viewer
	 * @param view to validate
	 * @return boolean indicating whether the view is valid or not
	 */
	public boolean validateViewer(String analysis, String view);

}
