package summer.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import summer.dao.TemplateDAO;
import summer.pojo.Template;
import summer.ui.PeopleM1Panel.STableModel;

public class TemplatePanel extends JPanel {
	private static final long serialVersionUID = -6823580641869668795L;
	private JTable table;

	// zhenzxie add some code here
	private MainFrame mainFrame;
	private String[] columnNames = new String[] { " ", "模板编号", "模板名称", "创建时间" };
	private STableModel st;

	/**
	 * Create the panel.
	 */
	public TemplatePanel(MainFrame mf) {

		mainFrame = mf;

		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 159, 176, 210, 0 };
		gridBagLayout.rowHeights = new int[] { 184, 117, 47, 42, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0,
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

		JButton button = new JButton("添加模板");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mainFrame.showTemplate(null);

			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 12));
		button.setBounds(66, 23, 106, 35);
		panel.add(button);

		JButton button_1 = new JButton("删除模板");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Integer> rows = st.getSelectedRow();
				if (rows.isEmpty()) {
					JOptionPane.showConfirmDialog(TemplatePanel.this, "未选中模板");
					return;
				}

				int ok = JOptionPane.showConfirmDialog(TemplatePanel.this,
						"真的要删除所选模板吗？");
				System.out.println(ok);
				if (ok != JOptionPane.OK_OPTION)
					return;

				TemplateDAO dao = new TemplateDAO();
				for (Integer integer : rows) {
					dao.delete((Long) st.getValueAt(integer, 1));// 1代表id的那一列
				}
				// 对于这段代码我只能呵呵了。
				reflush();
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 12));
		button_1.setBounds(204, 23, 106, 35);
		panel.add(button_1);

		JButton button_2 = new JButton("浏览模板");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Integer> list = st.getSelectedRow();
				if (list.isEmpty()) {
					JOptionPane.showConfirmDialog(TemplatePanel.this, "未选中模板！");
					return;
				}

				if (list.size() != 1) {
					JOptionPane.showConfirmDialog(TemplatePanel.this,
							"同时只能参看一个模板信息！");
					return;
				}

				Integer row = list.get(0);
				TemplateDAO dao = new TemplateDAO();
				Template template = dao.findById((Long) st.getValueAt(row, 1));
				mainFrame.showTemplate(template);

			}
		});
		button_2.setFont(new Font("宋体", Font.PLAIN, 12));
		button_2.setBounds(343, 23, 106, 35);
		panel.add(button_2);
	}

	public void reflush() {
		st.setDataVector(createObjectsFromDB(), columnNames);
		table.repaint();
	}

	private Object[][] createObjectsFromDB() {
		List<Template> list = findTemplates();
		if (list == null || list.isEmpty()) {
			return new Object[0][0];
		}
		Object[][] objs = new Object[list.size()][6];// 界面上显示template的四个属性
		int i = 0;
		for (Template template : list) {
			objs[i][0] = Boolean.FALSE;
			objs[i][1] = template.getId();
			objs[i][2] = template.getName();
			objs[i++][3] = new Date(template.getCreateTime()).toString();
		}
		return objs;
	}

	@SuppressWarnings("unchecked") private List<Template> findTemplates() {

		TemplateDAO templateDAO = new TemplateDAO();
		return templateDAO.findAll();
	}
}