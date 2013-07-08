package summer.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("\u767B\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 426, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u7BA1\u7406\u5458\u7F16\u53F7\uFF1A");
		label.setFont(new Font("ËÎÌו", Font.PLAIN, 12));
		label.setBounds(67, 64, 72, 32);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("  \u5BC6   \u7801 \uFF1A");
		label_1.setFont(new Font("ËÎÌו", Font.PLAIN, 12));
		label_1.setBounds(56, 127, 83, 32);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(149, 70, 165, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(149, 133, 165, 26);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("\u767B\u9646");
		btnNewButton.setFont(new Font("ËÎÌו", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(167, 191, 65, 32);
		contentPane.add(btnNewButton);
	}
}
