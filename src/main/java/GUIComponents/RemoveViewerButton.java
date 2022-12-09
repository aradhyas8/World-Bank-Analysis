package GUIComponents;

import java.awt.event.ActionEvent;
import javax.swing.JButton;

import GUIMediator.IMediator;

public class RemoveViewerButton extends JButton implements GUIComponent {
	
	private static final long serialVersionUID = 1L;
	private IMediator mediator;
	
	public RemoveViewerButton() {
        super("-");
        this.setName("RemoveViewerButton");
    }
	
	@Override
    protected void fireActionPerformed(ActionEvent actionEvent) {
        mediator.removeViewer();
    }

	@Override
	public void setMediator(IMediator mediator) {
		this.mediator = mediator;	
	}

	@Override
	public String getComponentName() {
		return "RemoveViewerButton";
	}
	
	
}


