package com.qxy.learning.code;

public class Algorithm4 {

    /**
     * https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/description/?envType=study-plan-v2&envId=top-interview-150
     * <p>
     * <p>
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * <p>
     * <p>
     * <p>
     * 说明：
     * <p>
     * 为什么返回数值是整数，但输出的答案是数组呢？
     * <p>
     * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
     * <p>
     * 你可以想象内部操作如下:
     * <p>
     * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
     * int len = removeDuplicates(nums);
     * <p>
     * // 在函数里修改输入数组对于调用者是可见的。
     * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
     * for (int i = 0; i < len; i++) {
     * print(nums[i]);
     * }
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,1,1,2,2,3]
     * 输出：5, nums = [1,1,2,2,3]
     * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3。 不需要考虑数组中超出新长度后面的元素。
     * <p>
     * 示例 2：
     * <p>
     * 输入：nums = [0,0,1,1,1,1,2,3,3]
     * 输出：7, nums = [0,0,1,1,2,3,3]
     * 解释：函数应返回新长度 length = 7, 并且原数组的前七个元素被修改为 0, 0, 1, 1, 2, 3, 3。不需要考虑数组中超出新长度后面的元素。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 3 * 104
     * -104 <= nums[i] <= 104
     * nums 已按升序排列
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {0,1,2,2,2,2,2,3,4,4,4};
        int count = removeDuplicates(nums);
        System.out.println(count);
    }


    public static int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length <= 2) {
            return nums.length;
        }

        /**
         * 因为保留至少保留两次，
         * 那么就是 slow：代表当前数组长度，保留2次，那么就看 slow - 2 的元素是否和当前fast的元素相同格
         */
        // 结果长度，
        int slow = 2;
        // 扫描的位置
        int fast = 2;
        for (; fast < nums.length;fast++) {
            if (nums[slow-2] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }


    public static int removeDuplicatesOld(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length <= 2) {
            return nums.length;
        }
        int curCount = 0;
        int removeCount = 0;
        for (int i = 0; i < nums.length - removeCount; i++) {

            // 统计出现次数
            if (nums[i] == nums[i + 1]) {
                curCount++;
            } else {
                curCount = 0;
            }

            if (curCount >= 2) {

                int count = 0;
                int tempIndex = i;
                while (tempIndex + 1 < nums.length - removeCount && nums[tempIndex] == nums[tempIndex + 1]) {
                    tempIndex++;
                    count++;
                }
                for (int j = i + 1; j + count < nums.length - removeCount; j++) {
                    nums[j] = nums[j + count];
                }
                removeCount += count;
                curCount = 0;
            }
        }
        return nums.length - removeCount;
    }


}
