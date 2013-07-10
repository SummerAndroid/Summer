package summer.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import summer.dao.UserDAO;
import summer.pojo.User;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1336994716187860193L;
	private JPanel contentPane;
	private JTextField idField;
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
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 56, 83, 213, 49, 0 };
		gbl_contentPane.rowHeights = new int[] { 64, 32, 31, 32, 32, 32, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel label = new JLabel("\u7BA1\u7406\u5458\u7F16\u53F7\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 12));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.fill = GridBagConstraints.VERTICAL;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 1;
		contentPane.add(label, gbc_label);

		idField = new JTextField();
		GridBagConstraints gbc_idField = new GridBagConstraints();
		gbc_idField.anchor = GridBagConstraints.SOUTH;
		gbc_idField.fill = GridBagConstraints.HORIZONTAL;
		gbc_idField.insets = new Insets(0, 0, 5, 5);
		gbc_idField.gridx = 2;
		gbc_idField.gridy = 1;
		contentPane.add(idField, gbc_idField);
		idField.setColumns(10);

		JLabel label_1 = new JLabel("  \u5BC6   \u7801 \uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 12));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.fill = GridBagConstraints.BOTH;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 3;
		contentPane.add(label_1, gbc_label_1);

		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.anchor = GridBagConstraints.SOUTH;
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 3;
		contentPane.add(passwordField, gbc_passwordField);

		JButton btnNewButton = new JButton("\u767B\u9646");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// DO LOGIN OPERATOR
				try {
					long id = Long.parseLong(idField.getText());
					String password = String.valueOf(passwordField
							.getPassword());
					UserDAO dao = new UserDAO();
					User target = dao.findById(id);
					if (target != null && target.getPassword().equals(password)) {
						JOptionPane.showConfirmDialog(LoginFrame.this, "登录成功！",
								"提示", JOptionPane.OK_OPTION);
					}
					setVisible(false);
					System.out.println(target);
					MainFrame manage = new MainFrame();
					manage.setVisible(true);
					return;
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				JOptionPane.showConfirmDialog(LoginFrame.this, "登录失败！", "提示",
						JOptionPane.OK_OPTION);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 5;
		contentPane.add(btnNewButton, gbc_btnNewButton);
	}
}
