package GUIComponents;

import javax.swing.JButton;
import java.awt.event.ActionEvent;

import GUIMediator.IMediator;

public class RecalculateButton extends JButton implements GUIComponent {
	
	private static final long serialVersionUID = 1L;
	private IMediator mediator;
	
	public RecalculateButton() {
        super("Recalculate");
        this.setName("RecalculateButton");
    }
	
	@Override
    protected void fireActionPerformed(ActionEvent actionEvent) {
        mediator.recalculate();
    }

	@Override
	public void setMediator(IMediator mediator) {
		this.mediator = mediator;	
	}

	@Override
	public String getComponentName() {
		return "RecalculateButton";
	}
	

}
