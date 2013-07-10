package summer.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

//主框架
public class PeoppleManage extends JFrame {

	private static final long serialVersionUID = 4780419787472420973L;
	private JPanel contentPane;
	private JTree scantree;
	private PeopleM1 people1;
	private PeopleM2 people2;
	private TemplateDefault template;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PeoppleManage frame = new PeoppleManage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PeoppleManage() {

		setTitle("电力管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 472);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 158, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 371, 67, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JScrollPane leftscrollPane = new JScrollPane();
		GridBagConstraints gbc_leftscrollPane = new GridBagConstraints();
		gbc_leftscrollPane.gridheight = 2;
		gbc_leftscrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_leftscrollPane.fill = GridBagConstraints.BOTH;
		gbc_leftscrollPane.gridx = 0;
		gbc_leftscrollPane.gridy = 0;
		contentPane.add(leftscrollPane, gbc_leftscrollPane);

		scantree = new JTree();
		scantree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode(
				"电力管理系统") {
			private static final long serialVersionUID = -8742899218517267391L;
			{
				DefaultMutableTreeNode node_1;
				node_1 = new DefaultMutableTreeNode("人员管理");
				node_1.add(new DefaultMutableTreeNode("管理员管理"));
				node_1.add(new DefaultMutableTreeNode("巡视员管理"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("设备管理");
				node_1.add(new DefaultMutableTreeNode("浏览类型"));
				node_1.add(new DefaultMutableTreeNode("添加类型"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("模板管理");
				node_1.add(new DefaultMutableTreeNode("浏览模板"));
				node_1.add(new DefaultMutableTreeNode("添加模板"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("任务管理");
				node_1.add(new DefaultMutableTreeNode("浏览任务"));
				node_1.add(new DefaultMutableTreeNode("添加任务"));
				node_1.add(new DefaultMutableTreeNode("缺陷管理"));
				add(node_1);
			}
		}));
		leftscrollPane.setViewportView(scantree);

		people1 = new PeopleM1();
		people2 = new PeopleM2();
		template = new TemplateDefault();
		final GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		contentPane.add(people1, gbc_panel);
		contentPane.add(people2, gbc_panel);
		// contentPane.add(template, gbc_panel);

		scantree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) scantree
						.getLastSelectedPathComponent();
				if (node == null)// Nothing is selected
					return;
				Object nodeInfo = node.getUserObject();
				if (nodeInfo instanceof String) {
					if (nodeInfo.equals("管理员管理")) {
						contentPane.remove(people2);// 移除
						contentPane.add(people1, gbc_panel);
						contentPane.repaint();
					} else if (nodeInfo.equals("巡视员管理")) {
						contentPane.remove(people1);
						contentPane.add(people2, gbc_panel);
						contentPane.repaint();
					} else if (nodeInfo.equals("")) {

					}
				}
			}
		});
	}
}