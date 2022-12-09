package DataFetcherModule;

import java.util.HashMap;

public interface IDataFetcher {
	
	/**
	 * <p> Fetches data from the World Bank API with given parameters
	 * </p>
	 * @param country for which to fetch data
	 * @param indicator 
	 * @param startYear - start year of the range
	 * @param endYear - end year of the range
	 * @return HashMap with the year and value pair for the provided year range
	 */
	public HashMap<Integer, Double> getData(String country, String indicator, int startYear, int endYear);

}
