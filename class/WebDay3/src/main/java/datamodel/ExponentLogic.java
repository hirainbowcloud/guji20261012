package datamodel;

public class ExponentLogic 
{
	public static long powerByLoop(int base, int exponent)
	{
		long result = 1;
		result = (long) Math.pow(base, exponent); 
		
		/*for(int i = 0; i<exponent; i++)
		{
			result *= base;
		}
		*/
		return result;
	}
	
	public static double[] discount(int money)
	{
		/*
		int discount = 0;
		
		if(money >= 20001)
		{
			discount = (int) Math.round(money * 0.8);	
		}		
		else if(money >= 10001 && money <= 20000)
		{
			discount = (int) Math.round(money * 0.85);			
		}
		else if(money >= 5001 && money <= 10000)
		{
			discount = (int) Math.round(money * 0.9);	
		}
		else if(money <= 5000)
		{
			discount = money;
		}
		*/
		double discount = 0;
		double rate = 0.0;
		if (money <= 5000) {
		    rate = 1.0;
		} else if (money <= 10000) { // 走到這代表一定大於 5000
		    rate = 0.9;
		} else if (money <= 20000) { // 走到這代表一定大於 10000
		    rate = 0.85;
		} else {                     // 剩下的一定大於 20000
		    rate = 0.8;
		}
		discount = Math.round(money * rate);
		return new double[] {discount,rate};
	}
}
