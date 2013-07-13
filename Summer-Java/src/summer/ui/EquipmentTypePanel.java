package summer.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import summer.dao.StuffCategoryDAO;
import summer.pojo.StuffCategory;
import summer.ui.PeopleM1Panel.STableModel;

public class EquipmentTypePanel extends JPanel {
	private static final long serialVersionUID = -1660331840875855549L;
	private JTable table;
	private String[] columnNames = new String[] { " ",
			"\u8BBE\u5907\u7C7B\u578B\u7F16\u53F7", "\u8BBE\u5907\u540D\u79F0",
			"\u6A21\u677F\u9879\u7F16\u53F7" };

	private STableModel st;
	private MainFrame mainFrame;

	/**
	 * Create the panel.
	 */
	public EquipmentTypePanel(MainFrame mf) {

		mainFrame = mf;

		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 159, 176, 210, 0 };
		gridBagLayout.rowHeights = new int[] { 184, 111, 0, 38, 42, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, 0.0, 1.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		table = new JTable();
		st = new STableModel(table, createObjectsFromDB(), columnNames);
		table.setModel(st);
		scrollPane.setViewportView(table);

		JButton button = new JButton("添加类型");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.showAddType();
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 3;
		add(button, gbc_button);
		button.setFont(new Font("宋体", Font.PLAIN, 12));

		JButton button_1 = new JButton("删除类型");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Integer> rows = st.getSelectedRow();
				if (rows.isEmpty()) {
					JOptionPane.showConfirmDialog(EquipmentTypePanel.this,
							"未选中设备类型");
					return;
				}

				int ok = JOptionPane.showConfirmDialog(EquipmentTypePanel.this,
						"真的要删除所选设备类型吗？");
				System.out.println(ok);
				if (ok != JOptionPane.OK_OPTION)
					return;

				StuffCategoryDAO dao = new StuffCategoryDAO();
				for (Integer integer : rows) {
					dao.delete((Long) st.getValueAt(integer, 1));// 1代表id的那一列
				}

				// 对于这段代码我只能呵呵了。
				reflush();
			}
		});
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 1;
		gbc_button_1.gridy = 3;
		add(button_1, gbc_button_1);
		button_1.setFont(new Font("宋体", Font.PLAIN, 12));

		JButton button_2 = new JButton("浏览设备");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Integer> list = st.getSelectedRow();
				if (list.isEmpty()) {
					JOptionPane.showConfirmDialog(EquipmentTypePanel.this,
							"未选中设备类型！");
					return;
				}

				if (list.size() != 1) {
					JOptionPane.showConfirmDialog(EquipmentTypePanel.this,
							"同时只能查看一个设备类型！");
					return;
				}

				Integer row = list.get(0);
				StuffCategoryDAO dao = new StuffCategoryDAO();
				StuffCategory category = dao.findById((Long) st.getValueAt(row,
						1));

				mainFrame.showEquipmentDetail(category);
			}
		});
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.insets = new Insets(0, 0, 5, 0);
		gbc_button_2.gridx = 2;
		gbc_button_2.gridy = 3;
		add(button_2, gbc_button_2);
		button_2.setFont(new Font("宋体", Font.PLAIN, 12));
	}

	public void reflush() {
		st.setDataVector(createObjectsFromDB(), columnNames);
		table.repaint();
	}

	private Object[][] createObjectsFromDB() {
		List<StuffCategory> list = findStuff();
		if (list == null || list.isEmpty()) {
			return new Object[0][0];
		}
		Object[][] objs = new Object[list.size()][4];// 界面上显示Stuff属性
		int i = 0;
		for (StuffCategory stuffCategory : list) {
			objs[i][0] = Boolean.FALSE;
			objs[i][1] = stuffCategory.getId();
			objs[i][2] = stuffCategory.getName();
			objs[i++][3] = stuffCategory.getTemplateItemId();
		}
		return objs;
	}

	@SuppressWarnings("unchecked") private List<StuffCategory> findStuff() {
		StuffCategoryDAO stuffCategoryDAO = new StuffCategoryDAO();
		return stuffCategoryDAO.findAll();
	}
}