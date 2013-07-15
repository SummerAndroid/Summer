package summer.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import summer.dao.TemplateHasTemplateItemDAO;
import summer.dao.TemplateItemDAO;
import summer.pojo.Template;
import summer.pojo.TemplateHasTemplateItem;
import summer.pojo.TemplateItem;
import summer.ui.PeopleM1Panel.STableModel;

public class AddTemplate extends JFrame {

	private static final long serialVersionUID = -6073818112572677571L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	//zhenzxie add some code here
	private String[] columnNames = new String[] {
			" ", "\u6A21\u677F\u9879\u7F16\u53F7",
			"\u8BBE\u5907\u540D\u79F0" };

	private STableModel st;
	private STableModel st1;

	public AddTemplate() {
		setBackground(Color.WHITE);
		setTitle("\u6A21\u677F\u4FE1\u606F");
		setBounds(100, 100, 759, 513);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 28, 33, 107, 89, 56, 52, 54,
				52, 85, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 28, 20, 100, 109, 80, 28,
				76, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNewLabel_1 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("\u6A21\u677F\u540D\u79F0\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel label = new JLabel("\u521B\u5EFA\u65F6\u95F4\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridwidth = 2;
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 5;
		gbc_label.gridy = 1;
		contentPane.add(label, gbc_label);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 7;
		gbc_textField_1.gridy = 1;
		contentPane.add(textField_1, gbc_textField_1);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		contentPane.add(scrollPane, gbc_scrollPane);

		table = new JTable();
		st = new STableModel(table, createObjectsFromDB(null), columnNames);
		table.setModel(st);
		scrollPane.setViewportView(table);

		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 3;
		gbc_scrollPane_1.gridwidth = 3;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 6;
		gbc_scrollPane_1.gridy = 3;
		contentPane.add(scrollPane_1, gbc_scrollPane_1);

		table_1 = new JTable();
		st1 = new STableModel(table_1, new Object[0][0], columnNames);
		table.setModel(st1);
		scrollPane_1.setViewportView(table_1);

		JButton btnNewButton_1 = new JButton("\u6DFB\u52A0");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (editableFlag) {
					// TODO:add item
				} else {
					JOptionPane.showConfirmDialog(AddTemplate.this, "先点击编辑按钮");
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 4;
		gbc_btnNewButton_1.gridy = 4;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 12));

		JButton button_1 = new JButton("\u5220\u9664");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (editableFlag) {
					// TODO: delete item
				} else {
					JOptionPane.showConfirmDialog(AddTemplate.this, "先点击编辑按钮");
				}
			}
		});
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 5;
		gbc_button_1.gridy = 5;
		contentPane.add(button_1, gbc_button_1);
		button_1.setFont(new Font("宋体", Font.PLAIN, 12));

		JButton btnNewButton = new JButton("\u7F16 \u8F91");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textField.setEditable(true);
				textField_1.setEditable(true);
				editableFlag = true;

			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 7;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 14));

		JButton button = new JButton("\u63D0 \u4EA4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// TODO: add template to db

			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.gridx = 7;
		gbc_button.gridy = 7;
		contentPane.add(button, gbc_button);
		button.setFont(new Font("宋体", Font.PLAIN, 14));
	}

	private boolean editableFlag = false;
	private JTable table_1;

	public void resetTemplate(Template t) {

		if (t != null) {
			textField.setText(t.getName());
			textField_1.setText(new Date(t.getCreateTime()).toString());
			// 对于这段代码我只能呵呵了。
			st.setDataVector(createObjectsFromDB(t), columnNames);
			table.repaint();
			textField.setEditable(false);
			textField_1.setEditable(false);
			editableFlag = false;
		}
	}

	private Object[][] createObjectsFromDB(Template template) {

		if (template == null)
			return new Object[0][0];

		List<TemplateItem> list = findTemplateItems(template);
		if (list == null || list.isEmpty()) {
			return new Object[0][0];
		}
		Object[][] objs = new Object[list.size()][3];
		int i = 0;
		for (TemplateItem item : list) {
			objs[i][0] = null;
			objs[i][1] = item.getId();
			objs[i++][2] = item.getName();
		}
		return objs;
	}

	@SuppressWarnings("unchecked") private List<TemplateItem> findTemplateItems(
			Template template) {

		TemplateHasTemplateItemDAO dao = new TemplateHasTemplateItemDAO();
		List<TemplateHasTemplateItem> hasTemplateItems = dao.findByProperty(
				TemplateHasTemplateItemDAO.TEMPLATE_ID, template.getId());
		List<TemplateItem> items = new ArrayList<TemplateItem>(
				hasTemplateItems.size());
		TemplateItemDAO itemDAO = new TemplateItemDAO();
		for (TemplateHasTemplateItem obj : hasTemplateItems) {
			items.add(itemDAO.findById(obj.getId().getTemplateItemId()));
		}
		return items;
	}
}