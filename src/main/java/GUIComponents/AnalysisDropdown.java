package GUIComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JComboBox;

import GUIMediator.IMediator;

public class AnalysisDropdown extends JComboBox<String> implements GUIComponent {

	private static final long serialVersionUID = 1L;
	private IMediator mediator;
	public Vector<String> analysisList;
	
	public AnalysisDropdown(Vector<String> analysisList) {
        super(analysisList);
        this.analysisList = analysisList; 
        this.setName("AnalysisDropdown");
        this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> combo = (JComboBox<String>) e.getSource();
				String analysis = (String) combo.getSelectedItem();
				mediator.analysisSelected(analysis);
			}	
        });
    }

	@Override
	public void setMediator(IMediator mediator) {
		this.mediator = mediator;	
	}
	
	@Override
	public String getComponentName() {
		return "AnalysisDropdown";
	}

}

