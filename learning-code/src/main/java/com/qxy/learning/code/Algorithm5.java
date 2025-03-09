package com.qxy.learning.code;

import java.util.HashMap;
import java.util.Map;

public class Algorithm5 {

    /**
     * https://leetcode.cn/problems/majority-element/description/?envType=study-plan-v2&envId=top-interview-150
     * <p>
     * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * <p>
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * 输入：nums = [3,2,3]
     * 输出：3
     * <p>
     * <p>
     * 示例 2：
     * 输入：nums = [2,2,1,1,1,2,2]
     * 输出：2
     * <p>
     * <p>
     * 提示：
     * n == nums.length
     * 1 <= n <= 5 * 104
     * -109 <= nums[i] <= 109
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] ints = {2, 2, 1, 1, 1, 2, 2};
        int value = majorityElement(ints);
        System.out.println(value);
    }

    public static int majorityElement(int[] nums) {
        int count = 0;
        Integer target = null;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                target = nums[i];
            }
            if (target == nums[i]) {
                count += 1;
            } else {
                count -= 1;
            }
        }
        return target;
    }

    public static int majorityElementOld(int[] nums) {
        Map<Integer, Integer> keyMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (keyMap.get(nums[i]) == null) {
                keyMap.put(nums[i], 1);
            } else {
                keyMap.put(nums[i], keyMap.get(nums[i]) + 1);
            }
        }

        for (Integer key : keyMap.keySet()) {
            Integer count = keyMap.get(key);
            if (count > nums.length / 2) {
                return key;
            }
        }
        return -1;
    }
}
