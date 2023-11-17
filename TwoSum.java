import java.util.Arrays;
import java.util.stream.Collectors;

public class TwoSum {

    public static void main(String[] args) {
        int[] nums01 = {2,7,11,15}; int target01 = 9;
        int[] nums12 = {3,2,4}; int target12 = 6;
        int[] nums10 = {3,3}; int target10 = 6;
        int[] solution01 = twoSum(nums01, target01);
        int[] solution12 = twoSum(nums12, target12);
        int[] solution10 = twoSum(nums10, target10);
        System.out.println(toString(solution01));
        System.out.println(toString(solution12));
        System.out.println(toString(solution10));
    }

    public static Object toString(int[] array) {
        return Arrays.stream(array).mapToObj(element -> "" + element).collect(Collectors.joining(",", "[", "]"));
    }

    public static int[] twoSum(int[] nums, int target) {
        for (int out = 0; out < nums.length; out++) {
            int seed = nums[out];
            for (int in = out+1; in < nums.length; in++) {
                if ((seed + nums[in]) == target) {
                    return new int[]{out, in};
                }
            }
        }
        return new int[]{0};
    }
}
