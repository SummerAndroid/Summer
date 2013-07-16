package summer.ui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import summer.dao.StuffDAO;
import summer.dao.TaskletDAO;
import summer.dao.TaskletItemArgDAO;
import summer.dao.TaskletItemDAO;
import summer.pojo.Stuff;
import summer.pojo.Tasklet;
import summer.pojo.TaskletItem;
import summer.pojo.TaskletItemArg;
import summer.pojo.Template;
import summer.ui.AddUpdateP.Done;
import summer.ui.PeopleM1Panel.STableModel;
import summer.ui.SelectTemplate.Callback;
import summer.ui.SelectTemplate.UsefulArg;

public class AddTask extends JFrame {

	private static final long serialVersionUID = 2718565150394812091L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblNewLabel_5;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JButton btnNewButton_3;

	private Done done;

	private STableModel st;
	private JButton btnNewButton;
	private JLabel lblNewLabel_1;
	private JTable table;
	private String[] columnNames = new String[] { "\u4EFB\u52A1\u9879\u540D",
			"\u8BBE\u5907\u540D", "\u5C5E\u6027\u540D", "\u53C2\u8003\u503C" };

	private List<UsefulArg> usefulArgs;

	public AddTask(Done d) {

		done = d;

		setTitle("\u4EFB\u52A1");
		setBounds(100, 100, 506, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 86, 151, 85, 156, 0 };
		gbl_contentPane.rowHeights = new int[] { 62, 53, 61, 51, 228, 93, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNewLabel = new JLabel(" \u4EFB\u52A1\u540D\u79F0\uFF1A");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("\u4EFB\u52A1\u5468\u671F\uFF1A");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 0;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 0;
		contentPane.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel(" \u5DE1\u89C6\u4EBA\u5458\uFF1A");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 1;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("\u4EFB\u52A1\u6B21\u6570\uFF1A");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 2;
		gbc_lblNewLabel_4.gridy = 1;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);

		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 1;
		contentPane.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);

		lblNewLabel_5 = new JLabel("\u5907\u6CE8\u4FE1\u606F\uFF1A");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 2;
		contentPane.add(lblNewLabel_5, gbc_lblNewLabel_5);

		textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 3;
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 2;
		contentPane.add(textArea, gbc_textArea);

		btnNewButton = new JButton("\u4F7F\u7528\u6A21\u7248");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SelectTemplate selectTemplate = new SelectTemplate(
						AddTask.this, new Callback() {

							@Override public void done(Template template,
									List<UsefulArg> usefulArgs,
									Object[][] data) {

								AddTask.this.usefulArgs = usefulArgs;

								table.setModel(new DefaultTableModel(data,
										columnNames));
								table.repaint();

							}
						});
				selectTemplate.setVisible(true);

			}
		});

		lblNewLabel_1 = new JLabel("\u4EFB\u52A1\u5185\u5BB9\uFF1A");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 3;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 3;
		contentPane.add(btnNewButton, gbc_btnNewButton);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		contentPane.add(scrollPane, gbc_scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[0][0], columnNames));
		scrollPane.setViewportView(table);

		btnNewButton_3 = new JButton("\u63D0\u4EA4");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// TODO:使用事务管理，不然很难弄。。。但现在时间不够 了。。目前能够运行。。:(
				String taskletName = textField.getText();// 任务名称
				long userId = Long.parseLong(textField_1.getText());
				long cycle = Integer.parseInt(textField_2.getText()) * 86400000;
				int account = Integer.parseInt(textField_3.getText());

				// 保存Tasklet
				Tasklet tasklet = new Tasklet();
				tasklet.setName(taskletName);
				tasklet.setUserId(userId);
				tasklet.setCycle(cycle);
				tasklet.setAccount(account);
				TaskletDAO taskletDAO = new TaskletDAO();
				long taskletId = taskletDAO.save(tasklet);

				Vector<Vector<Object>> data = ((DefaultTableModel) table
						.getModel()).getDataVector();

				// 保存TaskletItem和TaskletItemArg
				StuffDAO stuffDAO = new StuffDAO();
				TaskletItemDAO itemDAO = new TaskletItemDAO();
				TaskletItemArgDAO argDAO = new TaskletItemArgDAO();
				int i = 0;
				long lastTemplateItemId = -1;
				long itemId = 0;
				for (Vector v : data) {
					List<Stuff> stuff = stuffDAO.findByCode(v.get(1));
					if (stuff == null || stuff.isEmpty()) {
						JOptionPane.showConfirmDialog(AddTask.this,
								"有存在不合法的设备名，请确保他们正确");
						taskletDAO.delete(taskletId);
						return;
					}
					UsefulArg usefulArg = usefulArgs.get(i++);
					if (lastTemplateItemId != usefulArg.templateItemId) {
						// 保存TaskletItem
						lastTemplateItemId = usefulArg.templateItemId;
						TaskletItem item = new TaskletItem();
						item.setName((String) v.get(0));
						item.setTaskletId(taskletId);
						item.setStuffId(stuff.get(0).getId());
						itemId = itemDAO.save(item);
					}
					// 保存TaskletItemArg
					TaskletItemArg arg = new TaskletItemArg();
					arg.setTaskletItemId(itemId);
					arg.setName((String) v.get(2));
					arg.setValue((String) v.get(3));
					arg.setError(0);
					arg.setComment("");
					argDAO.save(arg);
				}


				done.done();
				setVisible(false);

			}
		});
		btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 14));
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_3.gridx = 3;
		gbc_btnNewButton_3.gridy = 5;
		contentPane.add(btnNewButton_3, gbc_btnNewButton_3);
	}
}