package LoginModule;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import GUIMediator.Mediator;

import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class LoginUI extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//public JFrame loginFrame;
	public JPanel panel;
	JLabel titleLabel;
	JLabel usernameLabel;
	JLabel passwordLabel;
	private JLabel messageLabel;
	private JButton loginButton;
	private JTextField usernameInput;
	private JPasswordField passwordInput;
	private Handler handler;
	
	
	/**
	 * <p>Constructs the login UI frame
	 * </p>
	 */
	public LoginUI(Handler handler) {
		
		this.handler = handler;
		
		//loginFrame = new JFrame();
		panel = new JPanel();
		panel.setName("loginPanel");
		titleLabel = new JLabel("Welcome to the World Bank!");
		usernameLabel = new JLabel("Username:");
		passwordLabel = new JLabel("Password:");
		messageLabel = new JLabel();
		messageLabel.setName("messageLabel");
		usernameInput = new JTextField(20);
		usernameInput.setName("usernameInput");
		passwordInput = new JPasswordField(20);
		passwordInput.setName("passwordInput");
		loginButton = new JButton("Login");
		loginButton.setName("loginButton");
		
		this.setTitle("World Bank Login");
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		panel.setLayout(null);
		panel.setBackground(new java.awt.Color(227, 229, 230));
		loginButton.addActionListener(this);
		
		titleLabel.setForeground(new java.awt.Color(69, 123, 157));
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		
		usernameLabel.setForeground(new java.awt.Color(29, 53, 87));
		usernameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		
		passwordLabel.setForeground(new java.awt.Color(29, 53, 87));
		passwordLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		
		titleLabel.setBounds(40, 30, 300, 28);
		usernameLabel.setBounds(40, 80, 120, 28);
		passwordLabel.setBounds(40, 120, 120, 28);
		usernameInput.setBounds(135, 80, 178, 28);
		passwordInput.setBounds(135, 120, 178, 28);
		loginButton.setBounds(230, 160, 80, 28);
		messageLabel.setBounds(40, 195, 300, 28);
		
		loginButton.setBackground(new java.awt.Color(29, 53, 87));
		loginButton.setForeground(new java.awt.Color(216, 226, 230));
		loginButton.setOpaque(true);
		loginButton.setBorderPainted(false);
		
		this.setResizable(false);
		panel.add(titleLabel);
		panel.add(usernameLabel);
		panel.add(usernameInput);
		panel.add(passwordLabel);
		panel.add(passwordInput);
		panel.add(loginButton);
		panel.add(messageLabel);
		
		this.setVisible(true);
		
	}

	/**
	 * <p>Handles action on the Login button
	 * </p>
	 * @param e ActionEvent on the Login button
	 */
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == loginButton) {
		
			String userName = usernameInput.getText();
			String password = String.valueOf(passwordInput.getPassword());
			
			if(userName.isEmpty() || password.isEmpty()) {
				messageLabel.setForeground(new java.awt.Color(193, 18, 30));
				messageLabel.setText("Both username and password required.");
				panel.add(messageLabel);
				this.setVisible(true);
				return;
			}
			
			boolean result = handler.handle(userName, password);
			
			if(result == true) {
				messageLabel.setForeground(new java.awt.Color(0, 100, 0));
				messageLabel.setText("Success");
				panel.add(messageLabel);
				this.setVisible(true);
				Mediator mediator = Mediator.getInstance();
			} else {
				messageLabel.setForeground(new java.awt.Color(193, 18, 30));
				messageLabel.setText("Username or password does not match.");
				panel.add(messageLabel);
				this.setVisible(true);
				
			}
		 
		}
	}	

}
