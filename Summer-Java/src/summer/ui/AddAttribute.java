package summer.ui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import summer.dao.StuffArgDAO;
import summer.pojo.Stuff;
import summer.pojo.StuffArg;
import summer.ui.AddUpdateP.Done;


public class AddAttribute extends JFrame {

	private static final long serialVersionUID = 973090086988668967L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	// zhenzxie add some code here
	private Done done;
	private Stuff stuff;
	private JTextArea textArea;
	private StuffArg arg;

	/**
	 * Create the frame.
	 */
	public AddAttribute(Done d) {

		done = d;

		setTitle("\u6DFB\u52A0\u5C5E\u6027");
		setBounds(100, 100, 447, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{106, 131, 0, 0};
		gbl_contentPane.rowHeights = new int[] { 24, 43, 34, 62, 73, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);
		
		JLabel label = new JLabel("\u5C5E\u6027\u540D\uFF1A");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		contentPane.add(label, gbc_label);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u53C2\u8003\u503C\uFF1A");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 2;
		contentPane.add(label_1, gbc_label_1);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JButton button = new JButton("\u63D0\u4EA4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = textField.getText();
				String value = textField_1.getText();
				String comment = textArea.getText();

				if (stuff == null) {
					arg = new StuffArg();
					arg.setName(name);
					arg.setValue(value);
					arg.setComment(comment);
				} else {// 如果是给存在的stuff添加属性，则直接加到数据库中
					StuffArg stuffArg = new StuffArg(stuff.getId(), name,
							value, comment);
					StuffArgDAO dao = new StuffArgDAO();
					dao.save(stuffArg);
				}

				done.done();
				AddAttribute.this.setVisible(false);

			}
		});

		JLabel label_2 = new JLabel("\u5907\u6CE8\uFF1A");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 3;
		contentPane.add(label_2, gbc_label_2);

		textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 3;
		contentPane.add(textArea, gbc_textArea);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.gridx = 2;
		gbc_button.gridy = 4;
		contentPane.add(button, gbc_button);
	}

	public void setStuff(Stuff s) {
		stuff = s;
	}

	public StuffArg getStuffArg() {
		return arg;
	}
}