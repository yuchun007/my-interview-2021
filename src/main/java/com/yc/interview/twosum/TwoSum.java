package com.yc.interview.twosum;

import java.sql.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int taget = 18;
     //   int[] number1 = getArraysByOne(nums, taget);
        int[] number2 = getArraysByTwo(nums, taget);
        System.out.println(Arrays.toString(number2));

    }

    private static int[] getArraysByTwo(int[] nums, int taget) {
        Map<Object, Object> map = new ConcurrentHashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int key = taget - nums[i];
            if(map.containsKey(key)){
                return new int[]{i, (int) map.get(key)};
            }
            map.put(nums[i],i);
        }
        return null;
    }

    private static int[] getArraysByOne(int[] nums, int taget) {
        for (int i = 0; i < nums.length -1; i++) {
            int j;
            for (j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == taget){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }
}
