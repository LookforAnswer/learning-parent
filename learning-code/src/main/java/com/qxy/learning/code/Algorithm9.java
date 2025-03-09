package com.qxy.learning.code;

public class Algorithm9 {

    /**
     * https://leetcode.cn/problems/jump-game/description/?envType=study-plan-v2&envId=top-interview-150
     *
     *给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [2,3,1,1,4]
     * 输出：true
     * 解释：可以先跳 1 步，从下标 0
     *
     * 1, 然后再从下标 1 跳 3 步到达最后一个下标。
     * 示例 2：
     *
     * 输入：nums = [3,2,1,0,4]
     * 输出：false
     * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 104
     * 0 <= nums[i] <= 105
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] ints = {3,2,1,0,4};
        System.out.println(canJump(ints));
    }



    public static boolean canJump(int[] nums) {
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            // i = 当前位置，maxLength 代表 能到达最远的位置
            if (i <= maxLength) {
                // 代表 当前位置还未到达最远的位置，从这里边找出大于最远位置的数据，再去更新似
                int tempMaxIndex = i + nums[i];
                if (tempMaxIndex > maxLength) {
                    maxLength = tempMaxIndex;
                }
            }
            if (maxLength >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
}
