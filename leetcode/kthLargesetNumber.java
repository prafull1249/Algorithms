/****

Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.

****/

public class Solution {
    public int findKthLargest(int[] nums, int k) {
			if(nums == null)
			    return 0;
			if(nums.length == 0)
			    return 0;
			return getKth(nums, 0 , nums.length -1 ,nums.length - k + 1);
	}
	
	public int getKth(int[] nums, int start, int end,int k)
	{
		int left=start, right= end;
			int pivot = nums[end];
			
			while(true){
				while((left<right) && (nums[left]<pivot))
				{
					left++;
				}
				while((left<right) && (nums[right]>=pivot))
				{
					right--;
				}
				
				if(left==right)
					break;
				//condition for 
				swap(nums,left,right);
				
			}
			swap(nums,left,right);
			int num_result =0 ;
			if(k == left+1)
				return pivot;
			else if(k<left+1)
			    return getKth(nums,start, left-1,k);
			else 
				return getKth(nums,left+1, end,k);
			

	}
	
	public void swap(int[] nums, int n1, int n2) {
	int tmp = nums[n1];
	nums[n1] = nums[n2];
	nums[n2] = tmp;
}
}