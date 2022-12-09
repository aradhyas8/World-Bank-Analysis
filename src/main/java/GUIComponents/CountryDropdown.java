package GUIComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;

import GUIMediator.IMediator;

public class CountryDropdown extends JComboBox<String> implements GUIComponent {

	private static final long serialVersionUID = 1L;
	private IMediator mediator;
	public Vector<String> countriesList;
	
	public CountryDropdown(Vector<String> countriesList) {
        super(countriesList);
        this.countriesList = countriesList; 
        this.setName("CountryDropdown");
        this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> combo = (JComboBox<String>) e.getSource();
				String country = (String) combo.getSelectedItem();
				mediator.countrySelected(country);
			}	
        });
    }

	@Override
	public void setMediator(IMediator mediator) {
		this.mediator = mediator;
	}

	@Override
	public String getComponentName() {
		return "CountryDropdown";
	}


}
