package datamodel;

/**
 * 數學運算邏輯類別
 */
public class NumberLogic {
    public static String discountMessage="不打折";
	/**
	 * 判斷 n 是否為質數
	 */
	public static boolean isPrime(int n) {
		if (n < 2) {
			return false;
		}
		int factor = 0;
		for (int i = 1; i <= n; i++) {
			if (n % i == 0) {
				factor++;
			}
		}
		return factor == 2;
	}

	/**
	 * 計算 base 的 pw 次方
	 */
	public static int power(int base, int pw) {
		int temp = 1;
		for (int i = 1; i <= pw; i++) {
			temp *= base;
		}
		return temp;
	}

	/**
	 * 依金額區間計算打折後金額
	 *
	 * money &lt;= 5000            不打折
	 * 5001 &lt;= money &lt;= 10000   打 9 折
	 * 10001 &lt;= money &lt;= 20000  打 85 折
	 * 20001 &lt;= money           打 8 折
	 */
	public static int discount(int money) {
		if (money <= 5000) {
			discountMessage="";
			return money;
		} else if (money <= 10000) {
			return (int) (money * 0.9);
		} else if (money <= 20000) {
			return (int) (money * 0.85);
		} else {
			return (int) (money * 0.8);
		}
	}

}
