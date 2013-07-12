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

import summer.SysConfig.DB;
import summer.dao.UserDAO;
import summer.pojo.User;
import summer.ui.AddUpdateP.Done;

public class PeopleM1Panel extends JPanel {
	private static final long serialVersionUID = 7236850909210491604L;
	private JTable table;
	/**
	 * Create the panel.
	 */
	public PeopleM1Panel() {
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
		table.setModel(new DefaultTableModel(createObjectsFromDB(),
				new String[] { " ", "管理员编号", "姓名", "密码", "联系方式", "住址" }) {
			private static final long serialVersionUID = -497771659651433794L;

			@Override public Class<?> getColumnClass(int columnIndex) {
				return getValueAt(1, columnIndex).getClass();
			}

			@Override public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(86);
		table.getColumnModel().getColumn(3).setPreferredWidth(91);
		table.getColumnModel().getColumn(4).setPreferredWidth(113);
		table.getColumnModel().getColumn(5).setPreferredWidth(189);
		scrollPane.setViewportView(table);

		JButton button = new JButton("添加管理员");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUpdateP p = new AddUpdateP(new Done() {

					@Override public void done() {
						// 对于这段代码我只能呵呵了。
						table.setModel(new DefaultTableModel(
								createObjectsFromDB(), new String[] { " ",
										"管理员编号", "姓名", "密码", "联系方式", "住址" }) {
							private static final long serialVersionUID = -497771659651433794L;

							@Override public boolean isCellEditable(int row,
									int column) {
								return false;
							}
						});
					}
				}, null, DB.TYPE_ADMINISTRATOR);
				p.setVisible(true);
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 3;
		add(button, gbc_button);
		button.setFont(new Font("宋体", Font.PLAIN, 12));

		JButton button_1 = new JButton("删除管理员");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserDAO userDAO = new UserDAO();
				User user = userDAO.findById(10005L);// create a simulation user
				userDAO.delete(user);
				// 对于这段代码我只能呵呵了。
				table.setModel(new DefaultTableModel(createObjectsFromDB(),
						new String[] { " ", "管理员编号", "姓名", "密码", "联系方式", "住址" }) {
					private static final long serialVersionUID = -497771659651433794L;

					@Override public boolean isCellEditable(int row, int column) {
						return false;
					}
				});
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
				// create a simulation user
				User user = new User();
				user.setId(10000L);
				user.setName("sduxzz");
				user.setPassword(String.valueOf(System.currentTimeMillis()));
				user.setPermission(DB.PERMISSION_MAX);
				user.setType(DB.TYPE_ADMINISTRATOR);
				user.setTellphone("18769783279");
				user.setAddress("天国");

				AddUpdateP p = new AddUpdateP(new Done() {

					@Override public void done() {
						// 对于这段代码我只能呵呵了。
						table.setModel(new DefaultTableModel(
								createObjectsFromDB(), new String[] { " ",
										"管理员编号", "姓名", "密码", "联系方式", "住址" }) {
							private static final long serialVersionUID = -497771659651433794L;

							@Override public boolean isCellEditable(int row,
									int column) {
								return false;
							}
						});
					}
				}, user, DB.TYPE_ADMINISTRATOR);
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

	private Object[][] createObjectsFromDB() {
		List<User> list = findAdministrators();
		if (list == null || list.isEmpty()) {
			return new Object[0][0];
		}
		Object[][] objs = new Object[list.size()][6];// 界面上显示User的六个属性
		int i = 0;
		for (User user : list) {
			if (i % 2 == 0)
			objs[i][0] = Boolean.TRUE;
			else
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
		return userDAO.findByType(DB.TYPE_ADMINISTRATOR);
	}
}