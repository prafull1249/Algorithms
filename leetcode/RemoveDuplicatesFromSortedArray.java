/***

Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.

***/

public class Solution {
    public int removeDuplicates(int[] nums) {
        
		if ((nums == null) || nums.length == 0)
			return 0;
		
		int prev=1,curr=2;
		
		while(cur<num.length)
		{
			if(nums[prev]==nums[curr])&&(prev==nums[prev-1]){
				cur++;
			}
			else{
				prev+=1;;
				nums[prev]=nums[curr];
				curr++;
			}
		}
		return prev+1;
    }
}