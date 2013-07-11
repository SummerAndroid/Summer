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

import summer.pojo.Template;

//主框架
public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9194533281017417772L;
	private JPanel contentPane;
	private JTree scantree;
	private PeopleM1Panel people1;
	private PeopleM2Panel people2;
	private TemplatePanel template;
	private TaskPanel task1;
	private ScanFaultPanel fault;
	private EquipmentTypePanel equipment1;
	private EquipmentDetailPanel equipment2;

	// zhenzxie add some code here
	private AddType addType;
	private AddTemplate addTemplate;
	private AddTask addTask;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
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
			private static final long serialVersionUID = -7386464406712637447L;

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

		people1 = new PeopleM1Panel();
		people2 = new PeopleM2Panel();
		template = new TemplatePanel(this);
		task1 = new TaskPanel();
		equipment1 = new EquipmentTypePanel(this);
		equipment2 = new EquipmentDetailPanel();
		fault = new ScanFaultPanel();

		final GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;

		contentPane.add(people1, gbc_panel);
		contentPane.add(people2, gbc_panel);
		contentPane.add(template, gbc_panel);
		contentPane.add(task1, gbc_panel);
		contentPane.add(equipment1, gbc_panel);
		contentPane.add(equipment2, gbc_panel);
		contentPane.add(fault, gbc_panel);

		changeVisiable(true, false, false, false, false, false, false);

		scantree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) scantree
						.getLastSelectedPathComponent();
				// ////////////////////////////////////////////////
				// //////////////////////呵呵///////////////////////
				// ///////////////////////////////////////////////
				if (node == null)// Nothing is selected
					return;
				Object nodeInfo = node.getUserObject();
				if (nodeInfo instanceof String) {
					if (nodeInfo.equals("管理员管理")) {
						changeVisiable(true, false, false, false, false, false,
								false);
						contentPane.repaint();
					} else if (nodeInfo.equals("巡视员管理")) {
						changeVisiable(false, true, false, false, false, false,
								false);
						contentPane.repaint();
					} else if (nodeInfo.equals("浏览类型")) {
						changeVisiable(false, false, true, false, false, false,
								false);
						contentPane.repaint();
					} else if (nodeInfo.equals("添加类型")) {
						showAddType();
					} else if (nodeInfo.equals("浏览模板")) {
						changeVisiable(false, false, false, false, true, false,
								false);
						contentPane.repaint();
					} else if (nodeInfo.equals("添加模板")) {
						showTemplate(null);
					} else if (nodeInfo.equals("浏览任务")) {
						changeVisiable(false, false, false, false, false, true,
								false);
						contentPane.repaint();
					} else if (nodeInfo.equals("添加任务")) {
						if (addTask == null) {
							addTask = new AddTask();
							addTask.setVisible(true);
						} else if (addTask.isVisible()) {
							addTask.requestFocus();
						} else {
							addTask.setVisible(true);
						}
					} else if (nodeInfo.equals("缺陷管理")) {
						changeVisiable(false, false, false, false, false,
								false, true);
						contentPane.repaint();
					}
				}
			}
		});
	}

	public void showAddType() {
		if (addType == null) {
			addType = new AddType();
			addType.setVisible(true);
			// TODO:other operator
		} else if (addType.isVisible()) {
			addType.requestFocus();
		} else {
			addType.setVisible(true);
		}
	}

	public void showEquipmentDetail() {
		changeVisiable(false, false, false, true, false, false, false);
	}

	private void changeVisiable(boolean arg1, boolean arg2, boolean arg3,
			boolean arg4, boolean arg5, boolean arg6, boolean arg7) {
		people1.setVisible(arg1);
		people2.setVisible(arg2);
		equipment1.setVisible(arg3);
		equipment2.setVisible(arg4);
		template.setVisible(arg5);
		task1.setVisible(arg6);
		fault.setVisible(arg7);
	}

	public void showTemplate(Template template) {
		if (addTemplate == null) {
			addTemplate = new AddTemplate();
			addTemplate.resetTemplate(template);
			addTemplate.setVisible(true);
			// TODO:other operator
		} else if (addTemplate.isVisible()) {
			addTemplate.resetTemplate(template);
			addTemplate.requestFocus();
		} else {
			addTemplate.resetTemplate(template);
			addTemplate.setVisible(true);
		}
	}
}