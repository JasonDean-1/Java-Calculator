import java.math.BigDecimal;

public class CustomMath
{
	// 保留小数点位数
	public static final int DEFAULT_SCALE = 20;

	/**
	 * 为一个数字创建BigDecimal对象
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
	 * 加法
	 * 
	 * @param num1
	 *            double
	 * @param num2
	 *            double
	 * @param reutrn
	 *            double 两者相加的结果
	 */
	public static double add(double num1, double num2)
	{
		BigDecimal bigNum1 = getBigDecimal(num1);
		BigDecimal bigNum2 = getBigDecimal(num2);
		return bigNum1.add(bigNum2).doubleValue();
	}

	/**
	 * 减法
	 * 
	 * @param num1
	 *            double
	 * @param num2
	 *            double
	 * @param reutrn
	 *            double 两者相减的结果
	 */
	public static double sub(double num1, double num2)
	{
		BigDecimal bigNum1 = getBigDecimal(num1);
		BigDecimal bigNum2 = getBigDecimal(num2);
		return bigNum1.subtract(bigNum2).doubleValue();
	}

	/**
	 * 乘法
	 * 
	 * @param num1
	 *            double
	 * @param num2
	 *            double
	 * @param reutrn
	 *            double 两者相乘的结果
	 */
	public static double multiply(double num1, double num2)
	{
		BigDecimal bigNum1 = getBigDecimal(num1);
		BigDecimal bigNum2 = getBigDecimal(num2);
		return bigNum1.multiply(bigNum2).doubleValue();
	}

	/**
	 * 除法
	 * 
	 * @param num1
	 *            double
	 * @param num2
	 *            double
	 * @param reutrn
	 *            double 两者相除的结果
	 */
	public static double divide(double num1, double num2)
	{
		BigDecimal bigNum1 = getBigDecimal(num1);
		BigDecimal bigNum2 = getBigDecimal(num2);
		return bigNum1.divide(bigNum2, DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
