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

import summer.SysConfig.DB;
import summer.dao.UserDAO;
import summer.pojo.User;
import summer.ui.AddUpdateP.Done;
import summer.ui.PeopleM1Panel.STableModel;

public class PeopleM2Panel extends JPanel {
	private static final long serialVersionUID = 4731269115314131107L;
	private JTable table;
	private STableModel st;
	private String[] columnNames = new String[] { " ", "巡视员编号", "姓名", "密码",
			"联系方式", "住址" };
	/**
	 * Create the panel.
	 */
	public PeopleM2Panel() {
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

		JButton button = new JButton("添加巡视员");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUpdateP p = new AddUpdateP(new Done() {

					@Override public void done() {
						// 对于这段代码我只能呵呵了。
						reflush();
					}
				}, null, DB.TYPE_USER);
				p.setVisible(true);
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 3;
		add(button, gbc_button);
		button.setFont(new Font("宋体", Font.PLAIN, 12));

		JButton button_1 = new JButton("删除巡视员");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Integer> rows = st.getSelectedRow();
				if (rows.isEmpty()) {
					JOptionPane.showConfirmDialog(PeopleM2Panel.this, "未选中巡视员");
					return;
				}

				int ok = JOptionPane.showConfirmDialog(PeopleM2Panel.this,
						"真的要删除所选巡视员吗？");
				System.out.println(ok);
				if (ok != JOptionPane.OK_OPTION)
					return;

				UserDAO dao = new UserDAO();
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

		JButton button_2 = new JButton("修改信息");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Integer> list = st.getSelectedRow();
				if (list.isEmpty()) {
					JOptionPane
							.showConfirmDialog(PeopleM2Panel.this, "未选中巡视员！");
					return;
				}

				if (list.size() == 1) {
					JOptionPane.showConfirmDialog(PeopleM2Panel.this,
							"同时只能修改一个巡视员信息！");
					return;
				}

				Integer row = list.get(0);
				User user = new User();
				user.setId((Long) st.getValueAt(row, 1));
				user.setName((String) st.getValueAt(row, 2));
				user.setPassword((String) st.getValueAt(row, 3));
				user.setTellphone((String) st.getValueAt(row, 4));
				user.setAddress((String) st.getValueAt(row, 5));
				user.setType(DB.TYPE_USER);
				user.setPermission(DB.PERMISSION_MIN);

				AddUpdateP p = new AddUpdateP(new Done() {

					@Override public void done() {
						// 对于这段代码我只能呵呵了。
						reflush();
					}
				}, user, DB.TYPE_USER);
				p.setVisible(true);
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

		List<User> list = findAdministrators();
		if (list == null || list.isEmpty()) {
			return new Object[0][0];
		}
		Object[][] objs = new Object[list.size()][6];// 界面上显示User的六个属性
		int i = 0;
		for (User user : list) {
			objs[i][0] = Boolean.FALSE;
			objs[i][1] = user.getId();
			objs[i][2] = user.getName();
			objs[i][3] = user.getPassword();
			objs[i][4] = user.getTellphone();
			objs[i++][5] = user.getAddress();
		}
		return objs;
	}

	@SuppressWarnings("unchecked") private List<User> findAdministrators() {

		UserDAO userDAO = new UserDAO();
		return userDAO.findByType(DB.TYPE_USER);
	}
}