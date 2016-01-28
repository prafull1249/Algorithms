/***
Write a program to find the nth super ugly number.

Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.

Note:
(1) 1 is a super ugly number for any given primes.
(2) The given numbers in primes are in ascending order.
(3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.

***/
//some error in the code debug later but the concept is good using dp we create multiples of all the primes and compare at each turn to find which of those multipelse is minimum
public class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        List<Integer> list = new ArrayList<Integer>(primes.length);
			List<Integer> counter = new ArrayList<Integer>(primes.length);
			int[] dp = new int[n+1];
			dp[0]= 1;
			//array to maintain the lates values of the finding minimum
			for(int i = 0 ; i<primes.length; i++)
				list.set(i,primes[i]);
			
			//array to find the latest values counter to set latest values.
			for(int i = 0 ; i<primes.length; i++)
				counter.set(i,1);
			
			int minIndex = 0;
			int next = 0;

			for(int i = 1; i<n+1;i++)
			{
				minIndex = list.indexOf(Collections.min(list));
				//store the solutions of dp 
				dp[i] = list.get(minIndex);
				//counter set the minIndex 
				counter.set(minIndex,counter.get(minIndex)+1);
				//next is the number toi be set int he lsit to find the minimuum
				next = primes[minIndex] * counter.get(minIndex);
				list.set(minIndex,next);
			}
			return dp[n];
    }
}