package summer.ui;

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
import javax.swing.table.DefaultTableModel;

import summer.dao.TaskletDAO;
import summer.pojo.Tasklet;

public class TaskPanel extends JPanel {
	private static final long serialVersionUID = 3744649399083236993L;
	private JTable table;

	// zhenzxie add some code here
	private MainFrame mainFrame;
	private ScanTask scanTask;

	/**
	 * Create the panel.
	 */
	public TaskPanel(MainFrame mf) {

		mainFrame = mf;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 110, 113, 113, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 194, 58, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(createObjectsFromDB(),
				new String[] { "", "\u4EFB\u52A1\u7F16\u53F7",
						"\u4EFB\u52A1\u540D\u79F0", "\u5DE1\u89C6\u5458",
						"\u4EFB\u52A1\u5468\u671F",
						"\u4EFB\u52A1\u72B6\u6001" }) {
			private static final long serialVersionUID = 8969239190347049721L;

			@Override public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table.getColumnModel().getColumn(5).setPreferredWidth(102);
		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("\u6DFB\u52A0\u4EFB\u52A1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mainFrame.showAddTask();

			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 14));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 2;
		add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_1 = new JButton("\u5220\u9664\u4EFB\u52A1");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// TODO:delete task

			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 14));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 2;
		add(btnNewButton_1, gbc_btnNewButton_1);

		JButton btnNewButton_2 = new JButton("\u6D4F\u89C8\u4EFB\u52A1");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAddTask(null);// TODO:add code to find a tasklet
			}
		});
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 14));
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.gridx = 2;
		gbc_btnNewButton_2.gridy = 2;
		add(btnNewButton_2, gbc_btnNewButton_2);

		JButton btnNewButton_3 = new JButton("\u4E0B\u53D1\u4EFB\u52A1");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// TODO:下发任务的动作

			}
		});
		btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 14));
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.gridx = 3;
		gbc_btnNewButton_3.gridy = 2;
		add(btnNewButton_3, gbc_btnNewButton_3);
	}

	private Object[][] createObjectsFromDB() {

		List<Tasklet> list = findAdministrators();
		if (list == null || list.isEmpty()) {
			return new Object[0][0];
		}
		Object[][] objs = new Object[list.size()][6];// 界面上显示User的六个属性
		int i = 0;
		for (Tasklet tasklet : list) {
			objs[i][0] = null;
			objs[i][1] = tasklet.getId();
			objs[i][2] = tasklet.getName();
			objs[i][3] = tasklet.getUserId();
			objs[i][4] = tasklet.getCycle() / 86400;
			objs[i++][5] = tasklet.getAccount() == 0 ? "已完成" : "未完成";// TODO:还有有循环周期的情况
		}
		return objs;
	}

	@SuppressWarnings("unchecked") private List<Tasklet> findAdministrators() {

		TaskletDAO taskletDAO = new TaskletDAO();
		return taskletDAO.findAll();
	}

	public void showAddTask(Tasklet tasklet) {
		if (scanTask == null) {
			scanTask = new ScanTask();
			scanTask.resetTasklet(tasklet);
			scanTask.setVisible(true);
		} else if (scanTask.isVisible()) {
			scanTask.resetTasklet(tasklet);
			scanTask.requestFocus();
		} else {
			scanTask.resetTasklet(tasklet);
			scanTask.setVisible(true);
		}
	}
}