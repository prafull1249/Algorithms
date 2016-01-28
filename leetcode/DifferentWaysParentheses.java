/****

Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.


Example 1
Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]


Example 2
Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10]

****/

public class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        
        for(int i =0; i<input.length ; i++)
		{
			//if the character is not operator continue till youfind an operator and applyy divide and conquer.
			if((input.charAt(i)!= '+') &&(input.charAt(i)!= '*')&&(input.charAt(i)!= '-'))
			{
				continue;
			}
			else{
				List<Integer> left = diffWaysToCompute(input.substring(0,i));  // divide part of the divide and conquer strategy
				List<Integer> right = diffWaysToCompute(input.substring(i+1));
				
				for (int leftnum : left)  // combine part of the divide and conquer strategy
					for(int rightnum: right){
						if (input.charAt(i)=='+')
							result.add(leftnum + rightnum);
						else if (input.charAt(i)=='*')
							result.add(leftnum * rightnum);
						else if (input.charAt(i)=='-')
							result.add(leftnum - rightnum);
						
					}
			}
		}
		
		if(result.length==0)   // base condition in which only a string with a single number is input. 
			result.add(Integer.ParseInt(input));
        return result;
    }
}