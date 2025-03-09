package com.qxy.learning.code;

public class Algorithm6 {

    /**
     * https://leetcode.cn/problems/rotate-array/description/?envType=study-plan-v2&envId=top-interview-150
     * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     * 示例 1:
     * <p>
     * 输入: nums = [1,2,3,4,5,6,7], k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右轮转 1 步: [7,1,2,3,4,5,6]
     * 向右轮转 2 步: [6,7,1,2,3,4,5]
     * 向右轮转 3 步: [5,6,7,1,2,3,4]
     * <p>
     * <p>
     * 示例 2:
     * <p>
     * 输入：nums = [-1,-100,3,99], k = 2
     * 输出：[3,99,-1,-100]
     * 解释:
     * 向右轮转 1 步: [99,-1,-100,3]
     * 向右轮转 2 步: [3,99,-1,-100]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 105
     * -231 <= nums[i] <= 231 - 1
     * 0 <= k <= 105
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, 5, 6, 7};
        rotate(ints, 3);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + ",");
        }
        System.out.println("");
    }

    public static void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0,nums.length - k - 1);
        reverse(nums, nums.length - k, nums.length-1);
        reverse(nums, 0 , nums.length-1);
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void rotate1(int[] nums, int k) {
        if (k > nums.length) {
            k = k % nums.length;
        }

        int rightStart = nums.length - k;

        int[] ints = new int[nums.length];
        for (int i = 0; i < ints.length; i++) {
            if (i < k) {
                ints[i] = nums[i + rightStart];
            } else {
                ints[i] = nums[i - k];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = ints[i];
        }
    }
}
