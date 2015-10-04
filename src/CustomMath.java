import java.math.BigDecimal;

public class CustomMath
{
	// ����С����λ��
	public static final int DEFAULT_SCALE = 20;

	/**
	 * Ϊһ�����ִ���BigDecimal����
	 * 
	 * @param number
	 *            double
	 * @return BigDecimal
	 */
	private static BigDecimal getBigDecimal(double number)
	{
		return new BigDecimal(number);
	}

	/**
	 * �ӷ�
	 * 
	 * @param num1
	 *            double
	 * @param num2
	 *            double
	 * @param reutrn
	 *            double ������ӵĽ��
	 */
	public static double add(double num1, double num2)
	{
		BigDecimal bigNum1 = getBigDecimal(num1);
		BigDecimal bigNum2 = getBigDecimal(num2);
		return bigNum1.add(bigNum2).doubleValue();
	}

	/**
	 * ����
	 * 
	 * @param num1
	 *            double
	 * @param num2
	 *            double
	 * @param reutrn
	 *            double ��������Ľ��
	 */
	public static double sub(double num1, double num2)
	{
		BigDecimal bigNum1 = getBigDecimal(num1);
		BigDecimal bigNum2 = getBigDecimal(num2);
		return bigNum1.subtract(bigNum2).doubleValue();
	}

	/**
	 * �˷�
	 * 
	 * @param num1
	 *            double
	 * @param num2
	 *            double
	 * @param reutrn
	 *            double ������˵Ľ��
	 */
	public static double multiply(double num1, double num2)
	{
		BigDecimal bigNum1 = getBigDecimal(num1);
		BigDecimal bigNum2 = getBigDecimal(num2);
		return bigNum1.multiply(bigNum2).doubleValue();
	}

	/**
	 * ����
	 * 
	 * @param num1
	 *            double
	 * @param num2
	 *            double
	 * @param reutrn
	 *            double ��������Ľ��
	 */
	public static double divide(double num1, double num2)
	{
		BigDecimal bigNum1 = getBigDecimal(num1);
		BigDecimal bigNum2 = getBigDecimal(num2);
		return bigNum1.divide(bigNum2, DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
