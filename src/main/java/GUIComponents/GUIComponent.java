package GUIComponents;

import GUIMediator.IMediator;

public interface GUIComponent {
	
	/**
	 * <p> Sets the concrete mediator for the component 
	 * </p>
	 * @param mediator
	 */
	public void setMediator(IMediator mediator);
	
	/**
	 * <p> Returns name of the GUIComponent 
	 * </p>
	 * @return name of component
	 */
	public String getComponentName();

}
