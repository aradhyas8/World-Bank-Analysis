package ViewerModule;

import AnalysisModule.AnalysisData;

public interface Observer {

	/**
	 * <p> Updates the AnalysisData object 
	 * </p>
	 * @param data
	 */
	public void update(AnalysisData data);
}
