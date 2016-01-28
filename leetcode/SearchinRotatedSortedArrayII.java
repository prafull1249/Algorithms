/***
Suppose a sorted array is rotated at some pivot unknown to you beforehand. (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1. You may assume no duplicate exists in the array.
what if duplicates are allowed? Write a function to determine if a given target is in the array.

***/

public class Solution {
    public boolean search(int[] nums, int target) {
			int left = 0;
			int right = nums.length -1;
			int mid = left + (right  - left) / 2;
			
			if(num[mid]==target)
				return mid;
			
			while(left<right)
			{
				
				if(num[left]<num[mid])
				{
					if((target<=num[left]) && target<=num[left]) )
					{
						right = mid - 1;
					}
					else{
						left = mid + 1;
					}
				}
				else if (num[left]>num[mid])
				{
					if(target<=num[right]&&target>num[mid])
					{
						left = mid + 1;
					}
					else{
						right = mid - 1;
					}
				}
				else{
					left++;
				}
			}
    }
}