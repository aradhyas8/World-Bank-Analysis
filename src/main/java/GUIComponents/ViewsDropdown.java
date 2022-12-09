package GUIComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;

import GUIMediator.IMediator;

public class ViewsDropdown extends JComboBox<String> implements GUIComponent {

	private static final long serialVersionUID = 1L;
	private IMediator mediator;
	public Vector<String> viewsList;
	
	public ViewsDropdown(Vector<String> viewsList) {
        super(viewsList);
        this.viewsList = viewsList;
        this.setName("ViewsDropdown");
        this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> combo = (JComboBox<String>) e.getSource();
				String view = (String) combo.getSelectedItem();
				mediator.viewSelected(view);
			}	
        });
    }


	@Override
	public void setMediator(IMediator mediator) {
		this.mediator = mediator;	
	}

	@Override
	public String getComponentName() {
		return "ViewsDropdown";
	}

}
