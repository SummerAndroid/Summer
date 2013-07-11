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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import summer.dao.TemplateDAO;
import summer.pojo.Template;

public class TemplatePanel extends JPanel {
	private static final long serialVersionUID = -6823580641869668795L;
	private JTable table;

	// zhenzxie add some code here
	private MainFrame mainFrame;

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
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setModel(new DefaultTableModel(createObjectsFromDB(),
				new String[] { " ", "ģ����", "ģ������", "����ʱ��" }) {
			private static final long serialVersionUID = -154069599985593156L;

			@Override public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(86);
		table.getColumnModel().getColumn(2).setPreferredWidth(91);
		table.getColumnModel().getColumn(3).setPreferredWidth(113);
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

		JButton button = new JButton("���ģ��");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mainFrame.showTemplate(null);

			}
		});
		button.setFont(new Font("����", Font.PLAIN, 12));
		button.setBounds(66, 23, 106, 35);
		panel.add(button);

		JButton button_1 = new JButton("ɾ��ģ��");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// TODO:add delete template logical here

			}
		});
		button_1.setFont(new Font("����", Font.PLAIN, 12));
		button_1.setBounds(204, 23, 106, 35);
		panel.add(button_1);

		JButton button_2 = new JButton("���ģ��");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TemplateDAO dao = new TemplateDAO();
				Template template = dao.findById(1L);
				mainFrame.showTemplate(template);

			}
		});
		button_2.setFont(new Font("����", Font.PLAIN, 12));
		button_2.setBounds(343, 23, 106, 35);
		panel.add(button_2);
	}

	private Object[][] createObjectsFromDB() {
		List<Template> list = findTemplates();
		if (list == null || list.isEmpty()) {
			return new Object[0][0];
		}
		Object[][] objs = new Object[list.size()][6];// ��������ʾtemplate���ĸ�����
		int i = 0;
		for (Template template : list) {
			objs[i][0] = null;
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