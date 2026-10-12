package datamodel;

public class NumberLogic 
{
	public static boolean isPrime(int n)
	{
		   int factor=0;
		   for(int i=1;i<=n;i++) 
		   {
			   if(n%i==0) 
			   {
				   factor++;
			   }
		   }
		   return factor==2; //質數:除了 1 和它本身以外，無法被其他自然數整除的數
	}
}
