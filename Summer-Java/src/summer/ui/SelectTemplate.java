package summer.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import summer.dao.TemplateDAO;
import summer.dao.TemplateHasTemplateItemDAO;
import summer.dao.TemplateItemArgDAO;
import summer.dao.TemplateItemDAO;
import summer.pojo.Template;
import summer.pojo.TemplateHasTemplateItem;
import summer.pojo.TemplateItem;
import summer.pojo.TemplateItemArg;

/**
 * 
 * @author zhenzxie
 * @since 1.0
 */
public class SelectTemplate extends JFrame {

	private static final long serialVersionUID = 6292984331453379840L;
	private JPanel contentPane;
	private JTable table;
	private List<Template> templateList;
	private String[] columnsName = new String[] { "\u9879\u76EE\u540D",
			"\u5C5E\u6027\u540D", "\u7F3A\u7701\u503C" };

	public SelectTemplate() {
		setTitle("\u9009\u62E9\u6A21\u7248");
		setBounds(100, 100, 471, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 80, 146, 146, 75, 0 };
		gbl_contentPane.rowHeights = new int[] { 45, 241, 48, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNewLabel = new JLabel("\u9009\u62E9\u6A21\u7248\uFF1A");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(createFromDB()));
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				int index = comboBox.getSelectedIndex();
				if (index < 0)
					return;
				Template template = templateList.get(index);
				table.setModel(new DefaultTableModel(createFromDB(template),
						columnsName));
				table.repaint();
			}

			class Arg{
				public String itemName;
				public String name;
				public String value;
			}
			
			private Object[][] createFromDB(Template template) {

				List<TemplateHasTemplateItem> list = findTemplateItemIds(template);
				if (list == null || list.isEmpty()) {
					return new Object[0][0];
				}
				ArrayList<Arg> tempList = new ArrayList<Arg>();
				TemplateItemDAO itemDao = new TemplateItemDAO();
				TemplateItemArgDAO argDao = new TemplateItemArgDAO();
				for (TemplateHasTemplateItem has : list) {// TODO:非常低效
					long templateItemId = has.getTemplateItemId();
					TemplateItem item = itemDao.findById(templateItemId);
					@SuppressWarnings("unchecked") List<TemplateItemArg> argList = argDao.findByTemplateItemId(templateItemId);
					for (TemplateItemArg templateItemArg : argList) {
						Arg arg = new Arg();
						arg.itemName = item.getName();
						arg.name = templateItemArg.getName();
						arg.value = templateItemArg.getValue();
						tempList.add(arg);
					}
				}
				Object[][] objs = new Object[tempList.size()][3];
				int i = 0;
				for (Arg arg : tempList) {
					objs[i][0] = arg.itemName;
					objs[i][1] = arg.name;
					objs[i++][2] = arg.value;
				}
				return objs;
			}

			@SuppressWarnings("unchecked") private List<TemplateHasTemplateItem> findTemplateItemIds(
					Template template) {

				TemplateHasTemplateItemDAO dao = new TemplateHasTemplateItemDAO();
				return dao.findByTemplateId(template.getId());
			}
		});
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		contentPane.add(comboBox, gbc_comboBox);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, columnsName));
		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int index = comboBox.getSelectedIndex();
				if (index < 0) {
					int ok = JOptionPane.showConfirmDialog(SelectTemplate.this,
							"没有模版，确定不选模版吗？");
					if (ok == JOptionPane.OK_OPTION)
						SelectTemplate.this.setVisible(false);
					else
						return;
				}

			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 2;
		contentPane.add(btnNewButton, gbc_btnNewButton);
	}

	private String[] createFromDB() {
		TemplateDAO dao = new TemplateDAO();
		templateList = dao.findAll();
		if (templateList.isEmpty())
			return new String[0];
		String[] str = new String[templateList.size()];
		int i = 0;
		for (Template t : templateList) {
			str[i++] = t.getName();
		}
		return str;
	}
}
