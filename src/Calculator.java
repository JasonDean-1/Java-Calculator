import javax.swing.JFrame;

public class Calculator
{
	public static void main(String[] args)
	{
		CalFrame calculator = new CalFrame();
		calculator.pack();
		calculator.setVisible(true);
		calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
