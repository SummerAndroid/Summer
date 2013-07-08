package summer.ui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ListSelectionModel;

public class PeopleM2 extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PeopleM2() {
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{159, 176, 210, 0};
		gridBagLayout.rowHeights = new int[]{184, 168, 47, 42, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
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
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				" ", "巡视员编号", "姓名", "密码", "联系方式", "住址"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(86);
		table.getColumnModel().getColumn(3).setPreferredWidth(91);
		table.getColumnModel().getColumn(4).setPreferredWidth(113);
		table.getColumnModel().getColumn(5).setPreferredWidth(189);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 2;
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);
		
		JButton button = new JButton("添加巡视员");
		button.setFont(new Font("宋体", Font.PLAIN, 12));
		button.setBounds(66, 23, 106, 35);
		panel.add(button);
		
		JButton button_1 = new JButton("删除巡视员");
		button_1.setFont(new Font("宋体", Font.PLAIN, 12));
		button_1.setBounds(204, 23, 106, 35);
		panel.add(button_1);
		
		JButton button_2 = new JButton("修改信息");
		button_2.setFont(new Font("宋体", Font.PLAIN, 12));
		button_2.setBounds(343, 23, 106, 35);
		panel.add(button_2);

	}
}
