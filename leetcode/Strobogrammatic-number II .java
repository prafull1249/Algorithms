/*A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Find all strobogrammatic numbers that are of length = n.
For example,
Given n = 2, return ["11","69","88","96"].*/

public class Solution{
	private List <Integer> result = new ArrayList<>();
		private Map<Character,Character> map = new HashMap<>();
		
	public List<Integer> StrobogrammaticNumber(int n){
		map.put('0','0');
		map.put('1','1');
		map.put('8','8');
		map.put('9','6');
		map.put('6','9');
		char[] arr = new char[n];
		result = StrobogrammaticNumberHelper(arr,0,n-1);
	}
	
	public List<Integer> StrobogrammaticNumberHelper(char[] arr, int left, int right){
		
		if(left>right){ // boundary condition - base case of the recursion that if the co-ordinates of the left and right overcross each other.
			if(arr.length == 1) || (arr.length > 1 && arr[0]!='0'){ // if the arr length i.e n is 1. then just add the result if arr[0] != '0'
				result.add(new String(arr)); // before adding to the result set convert the resultant array to string
			}
		}
		
			for(Character c: map.keySet()){ // map.keySet - is the whole set of keys 
					arr[left]= c;
					arr[right]= c;
					if((left==right)&&(map.get(c)==c) ||(left<right))  // if the left and right are in proper range. 
						StrobogrammaticNumberHelper(left+1,right-1);
					
			}
	}

}