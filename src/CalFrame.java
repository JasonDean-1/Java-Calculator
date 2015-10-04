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
	// ��һ�����鱣��MC,MR,MS,M+�Ȳ�����
	private String[] mOp = { "MC", "MR", "MS", "M+" };
	// ��һ�����鱣����������
	private String[] rOp = { "Back", "CE", "C" };
	// ��һ�����鱣������������������
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
		// �½�һ��panel�����ڷ��ð�ť
		JPanel panel1 = new JPanel();
		// ���ò��ֹ�����
		panel1.setLayout(new GridLayout(5, 1, 0, 5));
		// �������Ӱ�ť
		for (JButton b : mButton)
		{
			panel1.add(b);
		}
		// ���ӽ��������
		JButton[] rButton = getRButton();
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout(1, 5));
		// �½�һ��panel�����ڷ��ð�ť
		JPanel panel21 = new JPanel();
		// ���ò��ֹ�����
		panel21.setLayout(new GridLayout(1, 3, 3, 3));
		// �������Ӱ�ť
		for (JButton b : rButton)
		{
			panel21.add(b);
		}
		// �������������������
		JButton[] nButton = getNButton();
		// �½�һ��panel�����ڷ��ð�ť
		JPanel panel22 = new JPanel();
		// ���ò��ֹ�����
		panel22.setLayout(new GridLayout(4, 5, 3, 5));
		// �������Ӱ�ť
		for (JButton b : nButton)
		{
			panel22.add(b);
		}
		// �������ӵ����ӵ�frame
		panel2.add(panel21, BorderLayout.NORTH);
		panel2.add(panel22, BorderLayout.CENTER);
		panel.add(panel1, BorderLayout.WEST);
		panel.add(panel2, BorderLayout.CENTER);
		this.add(panel);
	}

	/**
	 * �������������ȡ������
	 * 
	 * @return ActionListener
	 */
	public ActionListener getActionListener() {
		if (actionListener == null) {
			actionListener = new ActionListener() {
				/**
				 * ʵ�ֽӿ��е�actionPerformed����
				 * 
				 * @param e
				 *            ActionEvent
				 * @return void
				 */
				public void actionPerformed(ActionEvent e) {
					String cmd = e.getActionCommand();
					String result = null;
					try {
						// ����������
						result = service.callMethod(cmd, showResultTextField.getText());
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
					}
					// ����button�ı��
					if (cmd.indexOf("MC") == 0) {
						button.setText("");
					} else if (cmd.indexOf("M") == 0 && service.getStore() > 0) {
						button.setText("M");
					}
					// ���ü�����
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
	 * ���������ʼ�������
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
	 * �˷�����ü������Ĵ洢������
	 * 
	 * @return ����JButton������
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
	 * �˷�����ü������Ľ��������
	 * 
	 * @return ����JButton������
	 */
	private JButton[] getRButton()
	{
		JButton[] result = new JButton[rOp.length];
		for (int i = 0; i < this.rOp.length; i++)
		{
			// �½���ť
			JButton b = new JButton(this.rOp[i]);
			// Ϊ��ť�����¼�
			b.addActionListener(getActionListener());
			// ���ð�ť��ɫ
			b.setForeground(Color.red);
			result[i] = b;
		}
		return result;
	}

	/**
	 * �˷�����ü�����������������
	 * 
	 * @return ����JButton������
	 */
	private JButton[] getNButton()
	{ // ������鱣����Ҫ����Ϊ��ɫ�Ĳ�����
		String[] redButton = { "/", "*", "-", "+", "=" };
		JButton[] result = new JButton[nOp.length];
		for (int i = 0; i < this.nOp.length; i++)
		{
			// �½���ť
			JButton b = new JButton(this.nOp[i]);
			// Ϊ��ť�����¼�
			b.addActionListener(getActionListener());
			// ��redButton���򣬲ſ���ʹ��binarySearch����
			Arrays.sort(redButton);
			// �����������redButton����
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
