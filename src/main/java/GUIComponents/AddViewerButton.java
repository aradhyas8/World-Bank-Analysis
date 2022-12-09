package GUIComponents;

import java.awt.event.ActionEvent;
import javax.swing.JButton;

import GUIMediator.IMediator;

public class AddViewerButton extends JButton implements GUIComponent {
	
	private static final long serialVersionUID = 1L;
	private IMediator mediator;
	
	public AddViewerButton() {
        super("+");
        this.setName("AddViewerButton");
    }
	
	@Override
    public void fireActionPerformed(ActionEvent actionEvent) {
        mediator.addViewer();
    }

	@Override
	public void setMediator(IMediator mediator) {
		this.mediator = mediator;	
	}

	@Override
	public String getComponentName() {
		return "AddViewerButton";
	}
	
}

