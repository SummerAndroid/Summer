package summer.ui;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import summer.SysConfig.Server;
import summer.dao.StuffArgDAO;
import summer.dao.StuffDAO;
import summer.pojo.Stuff;
import summer.pojo.StuffArg;
import summer.pojo.StuffCategory;
import summer.ui.AddUpdateP.Done;
import summer.ui.PeopleM1Panel.STableModel;
import summer.util.MatrixToImageWriter;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class ScanEquipment extends JFrame {

	private static final long serialVersionUID = 2588180552388878820L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTable table;
	private JButton btnNewButton;
	private JButton button;
	private JButton button_1;
	private Icon icon;

	// zhenzxie add some code here
	private Done done;
	private Stuff stuff;
	private StuffCategory category;
	private AddAttribute addAttribute;
	private String[] columnNames = new String[] { " ", "\u5C5E\u6027\u540D",
			"\u53C2\u8003\u503C", "\u5907\u6CE8" };
	private STableModel st;
	private JFrame inner;
	private ImageIcon ii;
	/**
	 * Create the frame.
	 */
	public ScanEquipment(Done d) {

		done = d;

		setBounds(100, 100, 604, 641);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 85, 160, 89, 148 };
		gbl_contentPane.rowHeights = new int[] { 52, 54, 46, 48, 50, 197, 56 };
		gbl_contentPane.columnWeights = new double[] {};
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNewLabel_7 = new JLabel("设备编号：");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 0;
		contentPane.add(lblNewLabel_7, gbc_lblNewLabel_7);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("   使用地：");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 0;
		contentPane.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("    编码：");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 1;
		contentPane.add(lblNewLabel_6, gbc_lblNewLabel_6);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("设备类型：");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 3;
		gbc_textField_4.gridy = 1;
		contentPane.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("产品寿命：");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 2;
		contentPane.add(lblNewLabel_5, gbc_lblNewLabel_5);

		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 2;
		contentPane.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("     价格：");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 2;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

		textField_5 = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 0);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 3;
		gbc_textField_5.gridy = 2;
		contentPane.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("开始日期：");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 3;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);

		textField_7 = new JTextField();
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 1;
		gbc_textField_7.gridy = 3;
		contentPane.add(textField_7, gbc_textField_7);
		textField_7.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("\u751F\u4EA7\u5382\u5BB6\uFF1A");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 3;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);

		textField_6 = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 0);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 3;
		gbc_textField_6.gridy = 3;
		contentPane.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);

		button_2 = new JButton("\u67E5\u770B\u4E8C\u7EF4\u7801\u56FE\u7247");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (stuff == null) {
					JOptionPane.showConfirmDialog(ScanEquipment.this,
							"设备还未保存，没有获取ID，还无法生成二维码图片");
					return;
				}

				JFrame outer = ScanEquipment.this;

				inner = new JFrame();
				inner.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				inner.setBounds(outer.getX() + outer.getWidth(), outer.getY(),
						450, 450);

				JPanel contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				contentPane.setLayout(new BorderLayout(0, 0));
				inner.setContentPane(contentPane);

				JPanel panel = new JPanel();
				panel.setLayout(new BorderLayout());
				panel.add(new JLabel(getImageIcon()));
				JScrollPane sp = new JScrollPane();
				sp.setViewportView(panel);
				contentPane.add(sp);
				inner.setVisible(true);

			}

			private ImageIcon getImageIcon() {

				System.out.println(stuff);

				if (ii != null)
					return ii;
				else {
					MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
					Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
					hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
					BitMatrix bitMatrix;
					try {
						bitMatrix = multiFormatWriter.encode(
								String.valueOf(stuff.getId()),
								BarcodeFormat.QR_CODE, 400, 400, hints);
						File file1 = new File(Server.DIR + Server.ZXING, stuff
								.getId() + ".jpg");
						MatrixToImageWriter
								.writeToFile(bitMatrix, "jpg", file1);
						ii = new ImageIcon(Toolkit.getDefaultToolkit()
								.getImage(file1.getPath()));
						stuff.setZxing(file1.getPath());
						return ii;
					} catch (WriterException e) {
						e.printStackTrace();
						return null;
					} catch (IOException e) {
						e.printStackTrace();
						return null;
					}
				}
			}
		});
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.insets = new Insets(0, 0, 5, 0);
		gbc_button_2.gridx = 3;
		gbc_button_2.gridy = 4;
		contentPane.add(button_2, gbc_button_2);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 5;
		contentPane.add(scrollPane, gbc_scrollPane);

		table = new JTable();
		st = new STableModel(table, new Object[0][0], columnNames);
		table.setModel(st);
		scrollPane.setViewportView(table);

		btnNewButton = new JButton("\u6DFB\u52A0\u5C5E\u6027");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				showAttribute();

			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 6;
		contentPane.add(btnNewButton, gbc_btnNewButton);

		button = new JButton("\u5220\u9664\u5C5E\u6027");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Integer> rows = st.getSelectedRow();
				if (rows.isEmpty()) {
					JOptionPane.showConfirmDialog(ScanEquipment.this, "未选中属性");
					return;
				}

				int ok = JOptionPane.showConfirmDialog(ScanEquipment.this,
						"真的要删除所选属性吗？");
				System.out.println(ok);
				if (ok != JOptionPane.OK_OPTION)
					return;

				if (stuff == null) {

					for (Integer integer : rows) {
						System.out.println(integer);
						st.removeRow(integer);
					}

				} else {
					StuffArgDAO dao = new StuffArgDAO();
					for (Integer integer : rows) {
						System.out.println(integer);
						dao.delete((Long) argList.get(integer).getId());
					}
					// 对于这段代码我只能呵呵了。
					reflush();
				}
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.WEST;
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.gridx = 2;
		gbc_button.gridy = 6;
		contentPane.add(button, gbc_button);

		button_1 = new JButton("\u786E\u8BA4");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// i ++ 。

				long categoryid = category.getId();
				String code = textField_1.getText();
				String zxing = "";
				String str = textField_2.getText();
				int life = Integer.parseInt(str.substring(0, str.length() - 1));
				String address = textField_3.getText();
				String categoryName = textField_4.getText();
				double price = Double.parseDouble(textField_5.getText());
				String factory = textField_6.getText();
				long startTime = 0;
				try {
					startTime = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss")
							.parse(textField_7.getText()).getTime();
				} catch (ParseException e1) {
					e1.printStackTrace();
					JOptionPane.showConfirmDialog(ScanEquipment.this,
							"请使用正确的日期格式：YYYY-MM-dd hh:mm:ss");
					return;
				}

				List<StuffArg> list = new ArrayList<StuffArg>();
				Vector objs = st.getDataVector();
				if (objs.isEmpty()) {} else {
					for (Object object : objs) {
						Vector vector = (Vector) object;
						StuffArg arg = new StuffArg();
						arg.setName((String) vector.get(1));
						arg.setValue((String) vector.get(2));
						arg.setComment((String) vector.get(3));
						list.add(arg);
					}
				}
				System.out.println(list);
				StuffDAO dao = new StuffDAO();
				if (stuff == null) {// 添加设备的情况
					Stuff s = new Stuff(categoryid, code, price, life, address,
							factory, zxing, startTime);
					System.out.println(s);
					Long id = dao.save(s);
					if (!list.isEmpty()) {
						StuffArgDAO stuffArgDAO = new StuffArgDAO();
						for (StuffArg arg : list) {
							arg.setStuffId(id);
							stuffArgDAO.save(arg);
						}
					}
				} else {
					stuff.setCode(code);
					//stuff.setZxing(zxing); //如果是查看以前存在的设备的二维码图片，则在生成二维码后把路径在stuff中设置好了
					stuff.setLife(life);
					stuff.setAddress(address);
					stuff.setCategoryName(categoryName);
					stuff.setPrice(price);
					stuff.setFactory(factory);
					stuff.setStartTime(startTime);
					dao.merge(stuff);
				}

				done.done();
				ScanEquipment.this.setVisible(false);

			}
		});
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.gridx = 3;
		gbc_button_1.gridy = 6;
		contentPane.add(button_1, gbc_button_1);

	}

	public void reset(Stuff s, StuffCategory sc) {

		category = sc;
		stuff = s;
		ii = null;
		
		textField.setEditable(false);// 不允许修改id

		if (stuff != null) {
			textField.setText(String.valueOf(stuff.getId()));
			textField_1.setText(stuff.getCode());
			textField_2.setText(String.valueOf(stuff.getLife()) + "年");
			textField_3.setText(stuff.getAddress());
			textField_4.setText(category.getName());
			textField_5.setText(String.valueOf(stuff.getPrice()));
			textField_6.setText(stuff.getFactory());
			textField_7.setText(new SimpleDateFormat("YYYY-MM-dd hh:mm:ss")
					.format(new Date()));
		} else {
			textField.setText("");
			textField_1.setText("");
			textField_2.setText("");
			textField_3.setText("");
			textField_4.setText("");
			textField_5.setText("");
			textField_6.setText("");
			textField_7.setText("");
		}
		reflush();
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public void showAttribute() {
		if (addAttribute == null) {
			addAttribute = new AddAttribute(new Done() {

				@Override public void done() {
					StuffArg arg = addAttribute.getStuffArg();
					if (arg == null) {
						reflush();
						return;
					} else {// 当前的stuff还没加到数据库中
						st.addRow(new Object[] { Boolean.FALSE, arg.getName(),
								arg.getValue(), arg.getComment() });
					}
				}
			});
			addAttribute.setVisible(true);
		} else if (addAttribute.isVisible()) {
			addAttribute.requestFocus();
		} else {
			addAttribute.setVisible(true);
		}
	}

	public void reflush() {
		st.setDataVector(createObjectsFromDB(stuff), columnNames);
		table.repaint();
	}

	private List<StuffArg> argList;
	private Canvas canvas;
	private JButton button_2;

	private Object[][] createObjectsFromDB(Stuff stuff) {

		if (stuff == null)
			return new Object[0][0];

		argList = findStuffArgs(stuff);
		List<StuffArg> list = argList;
		if (list == null || list.isEmpty()) {
			return new Object[0][0];
		}
		Object[][] objs = new Object[list.size()][4];// 界面上显示StuffArg四个属性
		int i = 0;
		for (StuffArg arg : list) {
			objs[i][0] = Boolean.FALSE;
			objs[i][1] = arg.getName();
			objs[i][2] = arg.getValue();
			objs[i++][3] = arg.getComment();
		}

		return objs;
	}

	@SuppressWarnings("unchecked") private List<StuffArg> findStuffArgs(
			Stuff stuff) {

		StuffArgDAO stuffArgDAO = new StuffArgDAO();
		return stuffArgDAO.findByStuffId(stuff.getId());
	}
}