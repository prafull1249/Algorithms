public class sumPerfectSquares{
	
	public int numSquares(int n) {
		
		int[] dp = new int[n+1];
		dp[0] = 0;
		for(int i =0 ; i< n+1 ; i++)
		{
			dp[i]= Integer.Max_value; // for the first comparison
			
			for(int j = 0; i >= j*j ; j++)
			{
				dp[i] = Math.min(dp[i],dp[i-j*j]+1);
			}
		}
		return dp[n+1];
	}

}