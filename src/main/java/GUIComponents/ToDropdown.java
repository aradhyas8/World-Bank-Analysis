package GUIComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;

import GUIMediator.IMediator;

public class ToDropdown extends JComboBox<Integer> implements GUIComponent {

	private static final long serialVersionUID = 1L;
	private IMediator mediator;
	public Vector<Integer> years;
	
	public ToDropdown(Vector<Integer> range) {
        super(range);
        this.years = range;
        this.setName("ToDropdown");
        this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<Integer> combo = (JComboBox<Integer>) e.getSource();
				int year = (int) combo.getSelectedItem();
				mediator.toYearSelected(year);
			}	
        });
    }

	@Override
	public void setMediator(IMediator mediator) {
		this.mediator = mediator;	
	}

	@Override
	public String getComponentName() {
		return "ToDropdown";
	}

}
