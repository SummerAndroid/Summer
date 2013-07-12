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

import summer.dao.StuffDAO;
import summer.pojo.Stuff;
import summer.pojo.StuffCategory;
import summer.ui.AddUpdateP.Done;
import summer.ui.PeopleM1Panel.STableModel;

public class EquipmentDetailPanel extends JPanel {
	private static final long serialVersionUID = 4747794806684877973L;
	private JTable table;

	// zhenzxie add some code here
	private ScanEquipment equipment;
	private String[] columnNames = new String[] { " ", "设备编号", "设备名称", "设备使用地" };

	private STableModel st;
	private StuffCategory category;

	/**
	 * Create the panel.
	 */
	public EquipmentDetailPanel(StuffCategory sc) {

		category = sc;

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
		st = new STableModel(table, createObjectsFromDB(sc), columnNames);
		table.setModel(st);
		scrollPane.setViewportView(table);

		JButton button = new JButton("添加设备");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				showStuff(null);

			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 3;
		add(button, gbc_button);
		button.setFont(new Font("宋体", Font.PLAIN, 12));

		JButton button_1 = new JButton("删除设备");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Integer> rows = st.getSelectedRow();
				if (rows.isEmpty()) {
					JOptionPane.showConfirmDialog(EquipmentDetailPanel.this,
							"未选中设备");
					return;
				}

				int ok = JOptionPane.showConfirmDialog(
						EquipmentDetailPanel.this, "真的要删除所选设备吗？");
				System.out.println(ok);
				if (ok != JOptionPane.OK_OPTION)
					return;

				StuffDAO dao = new StuffDAO();
				for (Integer integer : rows) {
					dao.delete((Long) st.getValueAt(integer, 1));// 1代表id的那一列
				}
				// 对于这段代码我只能呵呵了。
				reflush(category);
			}
		});
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 1;
		gbc_button_1.gridy = 3;
		add(button_1, gbc_button_1);
		button_1.setFont(new Font("宋体", Font.PLAIN, 12));

		JButton button_2 = new JButton("浏览设备具体信息");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Integer> list = st.getSelectedRow();
				if (list.isEmpty()) {
					JOptionPane.showConfirmDialog(EquipmentDetailPanel.this,
							"未选中设备！");
					return;
				}

				if (list.size() != 1) {
					JOptionPane.showConfirmDialog(EquipmentDetailPanel.this,
							"同时只能查看一个设备信息！");
					return;
				}

				Integer row = list.get(0);

				StuffDAO dao = new StuffDAO();
				Stuff stuff = dao.findById((Long) st.getValueAt(row, 1));
				System.out.println(stuff);
				showStuff(stuff);
			}
		});
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.insets = new Insets(0, 0, 5, 0);
		gbc_button_2.gridx = 2;
		gbc_button_2.gridy = 3;
		add(button_2, gbc_button_2);
		button_2.setFont(new Font("宋体", Font.PLAIN, 12));

	}

	public void reflush(StuffCategory sc) {

		category = sc;

		// 对于这段代码我只能呵呵了。
		st.setDataVector(createObjectsFromDB(category), columnNames);
		table.repaint();
	}

	public void showStuff(Stuff stuff) {
		if (equipment == null) {
			equipment = new ScanEquipment(new Done() {

				@Override public void done() {
					reflush(category);
				}
			});
			equipment.reset(stuff, category);
			equipment.setVisible(true);
		} else if (equipment.isVisible()) {
			equipment.reset(stuff, category);
			equipment.requestFocus();
		} else {
			equipment.reset(stuff, category);
			equipment.setVisible(true);
		}
	}

	private Object[][] createObjectsFromDB(StuffCategory sc) {

		if (sc == null)
			return new Object[0][0];

		List<Stuff> list = findStuffCategory(sc);
		if (list == null || list.isEmpty()) {
			return new Object[0][0];
		}
		Object[][] objs = new Object[list.size()][4];// 界面上显示Stuff的四个属性
		int i = 0;
		for (Stuff stuff : list) {
			objs[i][0] = Boolean.FALSE;
			objs[i][1] = stuff.getId();
			objs[i][2] = stuff.getCode();
			objs[i++][3] = stuff.getAddress();
		}
		return objs;
	}

	@SuppressWarnings("unchecked") private List<Stuff> findStuffCategory(
			StuffCategory sc) {

		StuffDAO stuffDAO = new StuffDAO();
		return stuffDAO.findByStuffCategoryId(sc.getId());
	}
}