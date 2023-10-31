import java.util.*;
//https://leetcode.com/problems/single-number/      EASY
public class SingleNumber {

    public static void main(String[] args) {
        int[] n1 = {2,2,1};
        int[] n4 = {4,1,2,1,2};
        int[] n11 = {1};
        System.out.println("The n1 array contains " + singleNumber2(n1) + " unique number.");
        System.out.println("The n4 array contains " + singleNumber2(n4) + " unique number.");
        System.out.println("The n11 array contains " + singleNumber2(n11) + " unique number.");
    }

    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result = result ^ nums[i];
        }
        return result;
    }

    public static int singleNumber2(int[] nums) {
        List<Integer> tmp = new ArrayList<>();
        for (int num : nums) {
            tmp.add(num);
        }
        for (int num : nums) {
            if(tmp.indexOf(num) == tmp.lastIndexOf(num)) {
                return num;
            }
        }
        return 0;
    }
}
