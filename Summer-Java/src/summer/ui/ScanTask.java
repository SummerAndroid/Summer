package summer.ui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import summer.dao.StuffDAO;
import summer.dao.TaskletItemArgDAO;
import summer.dao.TaskletItemDAO;
import summer.pojo.Tasklet;
import summer.pojo.TaskletItem;
import summer.pojo.TaskletItemArg;
import summer.ui.PeopleM1Panel.STableModel;

public class ScanTask extends JFrame {

	private static final long serialVersionUID = -3683373675864693091L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;

	// zhenzxie add some code here
	private String[] columnNames = new String[] { "\u5C5E\u6027", "巡视结果",
			"备注", "是否有缺陷", };
	private STableModel st;
	private JTextField textField_4;

	private List<TaskletItem> itemList;
	private JComboBox comboBox;

	public ScanTask() {
		setTitle("\u6D4F\u89C8\u4EFB\u52A1");
		setBounds(100, 100, 573, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 81, 104, 89, 113, 0 };
		gbl_contentPane.rowHeights = new int[] { 35, 29, 30, 190, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNewLabel = new JLabel("\u4EFB\u52A1\u540D\u79F0\uFF1A");
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

		JLabel lblNewLabel_1 = new JLabel("\u4EFB\u52A1\u5468\u671F\uFF1A");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 0;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 0;
		contentPane.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("\u5DE1\u89C6\u4EBA\u5458\uFF1A");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 1;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("\u4EFB\u52A1\u72B6\u6001\uFF1A");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 1;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);

		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 1;
		contentPane.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("   \u9009\u62E9\u8BBE\u5907\uFF1A");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				reflush(itemList.get(comboBox.getSelectedIndex()));

			}
		});
		// comboBox.add
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 2;
		contentPane.add(comboBox, gbc_comboBox);

		JLabel lblNewLabel_5 = new JLabel("\u5DE1\u89C6\u5730\u70B9\uFF1A");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 2;
		gbc_lblNewLabel_5.gridy = 2;
		contentPane.add(lblNewLabel_5, gbc_lblNewLabel_5);

		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 3;
		gbc_textField_4.gridy = 2;
		contentPane.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		contentPane.add(scrollPane, gbc_scrollPane);

		table = new JTable();
		st = new STableModel(table, new Object[0][0], columnNames);
		table.setModel(st);
		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);

			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 14));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 4;
		contentPane.add(btnNewButton, gbc_btnNewButton);

		// set editable false
		textField.setEditable(false);
		textField_1.setEditable(false);
		textField_2.setEditable(false);
		textField_3.setEditable(false);
		textField_4.setEditable(false);
	}

	public void resetTasklet(Tasklet tasklet) {

		if (tasklet != null) {
			textField.setText(tasklet.getName());
			textField_1.setText(String.valueOf(tasklet.getUserId()));
			textField_2.setText(String.valueOf(tasklet.getCycle() / 86400000));
			textField_3.setText(tasklet.getAccount() == 0 ? "已完成" : "未完成");
			TaskletItemDAO dao = new TaskletItemDAO();
			itemList = dao.findByTaskletId(tasklet.getId());
			if (itemList.isEmpty())
				return;
			TaskletItem first = itemList.get(0);
			StuffDAO stuffDAO = new StuffDAO();
			textField_4.setText(stuffDAO.findById(first.getStuffId())
					.getAddress());
			String[] names = new String[itemList.size()];
			int i = 0;
			for (TaskletItem item : itemList) {
				names[i++] = stuffDAO.findById(item.getStuffId()).getCode();
			}
			comboBox.setModel(new DefaultComboBoxModel(names));
			comboBox.setSelectedIndex(0);

			reflush(first);
		}
	}

	private void reflush(TaskletItem first) {
		st.setDataVector(createObjectsFromDB(first), columnNames);
		table.repaint();
	}

	private Object[][] createObjectsFromDB(TaskletItem first) {
		List<TaskletItemArg> list = findTaskletItemArgs(first);
		if (list == null || list.isEmpty()) {
			return new Object[0][0];
		}
		Object[][] objs = new Object[list.size()][4];// 界面上显示User的六个属性
		int i = 0;
		for (TaskletItemArg arg : list) {
			objs[i][0] = arg.getName();
			objs[i][1] = arg.getValue();
			objs[i][2] = arg.getComment();
			objs[i++][3] = arg.getError() == 0 ? "没有缺陷" : "有缺陷";
		}
		return objs;
	}

	@SuppressWarnings("unchecked") private List<TaskletItemArg> findTaskletItemArgs(
			TaskletItem first) {
		TaskletItemArgDAO DAO = new TaskletItemArgDAO();
		return DAO.findByTaskletItemId(first.getId());
	}

}