import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalFrame extends JFrame
{
	// 用一个数组保存MC,MR,MS,M+等操作符
	private String[] mOp = { "MC", "MR", "MS", "M+" };
	// 用一个数组保存结果操作符
	private String[] rOp = { "Back", "CE", "C" };
	// 用一个数组保存数字与其它操作符
	private String[] nOp = { "7", "8", "9", "/", "sqrt", "4", "5", "6", "*", "%", "1", "2", "3", "-", "1/x", "0", "+/-",
			".", "+", "=" };
	private JTextField showResultTextField = null;
	private JButton button = null;
	private CalService service = new CalService();
	private ActionListener actionListener = null;
	private final int PANEL_WIDTH = 360;
	private final int PANEL_HEIGHT = 250;

	public CalFrame()
	{
		super();
		initialize();
	}

	private void initialize()
	{
		this.setTitle("Calculator");
		this.setResizable(false);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(10, 1));
		panel.add(getTextField(), BorderLayout.NORTH);
		panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		JButton[] mButton = getMButton();
		// 新建一个panel，用于放置按钮
		JPanel panel1 = new JPanel();
		// 设置布局管理器
		panel1.setLayout(new GridLayout(5, 1, 0, 5));
		// 迭代增加按钮
		for (JButton b : mButton)
		{
			panel1.add(b);
		}
		// 增加结果操作键
		JButton[] rButton = getRButton();
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout(1, 5));
		// 新建一个panel，用于放置按钮
		JPanel panel21 = new JPanel();
		// 设置布局管理器
		panel21.setLayout(new GridLayout(1, 3, 3, 3));
		// 迭代增加按钮
		for (JButton b : rButton)
		{
			panel21.add(b);
		}
		// 增加数字与其它运算符
		JButton[] nButton = getNButton();
		// 新建一个panel，用于放置按钮
		JPanel panel22 = new JPanel();
		// 设置布局管理器
		panel22.setLayout(new GridLayout(4, 5, 3, 5));
		// 迭代增加按钮
		for (JButton b : nButton)
		{
			panel22.add(b);
		}
		// 把新增加的面板加到frame
		panel2.add(panel21, BorderLayout.NORTH);
		panel2.add(panel22, BorderLayout.CENTER);
		panel.add(panel1, BorderLayout.WEST);
		panel.add(panel2, BorderLayout.CENTER);
		this.add(panel);
	}

	/**
	 * 这个方法用来获取监听器
	 * 
	 * @return ActionListener
	 */
	public ActionListener getActionListener() {
		if (actionListener == null) {
			actionListener = new ActionListener() {
				/**
				 * 实现接口中的actionPerformed方法
				 * 
				 * @param e
				 *            ActionEvent
				 * @return void
				 */
				public void actionPerformed(ActionEvent e) {
					String cmd = e.getActionCommand();
					String result = null;
					try {
						// 计算操作结果
						result = service.callMethod(cmd, showResultTextField.getText());
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
					}
					// 处理button的标记
					if (cmd.indexOf("MC") == 0) {
						button.setText("");
					} else if (cmd.indexOf("M") == 0 && service.getStore() > 0) {
						button.setText("M");
					}
					// 设置计算结果
					if (result != null) {
						showResultTextField.setText(result);
					}
				}
			};
		}
		return actionListener;
	}

	/**
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getButton()
	{
		if (button == null)
		{
			button = new JButton();
		}
		return button;
	}

	/**
	 * 这个方法初始化输入框
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTextField()
	{
		if (showResultTextField == null)
		{
			showResultTextField = new JTextField("0");
			showResultTextField.setEditable(false);
			showResultTextField.setBackground(Color.WHITE);
		}
		return showResultTextField;
	}

	/**
	 * 此方法获得计算器的存储操作键
	 * 
	 * @return 保存JButton的数组
	 */
	private JButton[] getMButton()
	{
		JButton[] result = new JButton[mOp.length + 1];
		result[0] = getButton();
		for (int i = 0; i < this.mOp.length; i++)
		{
			JButton b = new JButton(this.mOp[i]);
			b.addActionListener(getActionListener());
			b.setForeground(Color.red);
			result[i + 1] = b;
		}
		return result;
	}

	/*
	 * 此方法获得计算器的结果操作键
	 * 
	 * @return 保存JButton的数组
	 */
	private JButton[] getRButton()
	{
		JButton[] result = new JButton[rOp.length];
		for (int i = 0; i < this.rOp.length; i++)
		{
			// 新建按钮
			JButton b = new JButton(this.rOp[i]);
			// 为按钮增加事件
			b.addActionListener(getActionListener());
			// 设置按钮颜色
			b.setForeground(Color.red);
			result[i] = b;
		}
		return result;
	}

	/**
	 * 此方法获得计算器的其它操作键
	 * 
	 * @return 保存JButton的数组
	 */
	private JButton[] getNButton()
	{ // 这个数组保存需要设置为红色的操作符
		String[] redButton = { "/", "*", "-", "+", "=" };
		JButton[] result = new JButton[nOp.length];
		for (int i = 0; i < this.nOp.length; i++)
		{
			// 新建按钮
			JButton b = new JButton(this.nOp[i]);
			// 为按钮增加事件
			b.addActionListener(getActionListener());
			// 对redButton排序，才可以使用binarySearch方法
			Arrays.sort(redButton);
			// 如果操作符在redButton出现
			if (Arrays.binarySearch(redButton, nOp[i]) >= 0)
			{
				b.setForeground(Color.red);
			} else
			{
				b.setForeground(Color.blue);
			}
			result[i] = b;
		}
		return result;
	}

}
