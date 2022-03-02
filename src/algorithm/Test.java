package algorithm;

import java.util.Arrays;

public class Test {
    static boolean use[], success;
    static int[] answer;
    
    static public int[] twoSum(int[] nums, int target) {
        use = new boolean[nums.length];
        
        com(nums, 0,0,target,0);
        
        return answer;
    }
    
    static public void com(int[] nums, int sum, int size, int target, int start){        
        if(success) return;
        
        if(sum == target){
            answer = new int[size];
            
            for(int i=0, ans=0; i<use.length; i++){
                if(use[i]) answer[ans++] = i;
            }
            success=true;
            return;
        }
        
        for(int i=start; i<use.length; i++){
            if(!use[i]){
                use[i] = true;
                com(nums, sum+nums[i], size+1, target, i+1);
                use[i] = false;
            }
        }        
    }
    public static void main(String[] args) {
    	int[] nums = {3,2,4};
    	int target = 6;
		System.out.println(Arrays.toString(twoSum(nums, target)));
		
	}
}