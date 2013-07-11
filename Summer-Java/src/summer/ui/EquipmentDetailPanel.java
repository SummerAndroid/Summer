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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import summer.dao.StuffDAO;
import summer.pojo.Stuff;

public class EquipmentDetailPanel extends JPanel {
	private static final long serialVersionUID = 4747794806684877973L;
	private JTable table;

	private ScanEquipment equipment;

	/**
	 * Create the panel.
	 */
	public EquipmentDetailPanel() {
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
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(createObjectsFromDB(),
				new String[] { " ", "设备编号", "设备名称", "设备使用地" }) {
			private static final long serialVersionUID = -5649900589575163173L;

			@Override public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(86);
		table.getColumnModel().getColumn(3).setPreferredWidth(91);
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

				// TODO:do delete stuff logical

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

				StuffDAO dao = new StuffDAO();
				Stuff suff = dao.findById(1L);// TODO:now just create a simulate
												// stuff
				showStuff(suff);

			}
		});
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.insets = new Insets(0, 0, 5, 0);
		gbc_button_2.gridx = 2;
		gbc_button_2.gridy = 3;
		add(button_2, gbc_button_2);
		button_2.setFont(new Font("宋体", Font.PLAIN, 12));

	}

	public void showStuff(Stuff stuff) {
		if (equipment == null) {
			equipment = new ScanEquipment(stuff);
			equipment.setVisible(true);
		} else if (equipment.isVisible()) {
			equipment.requestFocus();
		} else {
			equipment.setVisible(true);
		}
	}

	private Object[][] createObjectsFromDB() {

		List<Stuff> list = findStuffCategory();
		if (list == null || list.isEmpty()) {
			return new Object[0][0];
		}
		Object[][] objs = new Object[list.size()][4];// 界面上显示Stuff的四个属性
		int i = 0;
		for (Stuff stuff : list) {
			objs[i][0] = null;
			objs[i][1] = stuff.getId();
			objs[i][2] = stuff.getCode();
			objs[i++][3] = stuff.getAddress();
		}
		return objs;
	}

	@SuppressWarnings("unchecked") private List<Stuff> findStuffCategory() {

		StuffDAO stuffDAO = new StuffDAO();
		return stuffDAO.findAll();
	}
}