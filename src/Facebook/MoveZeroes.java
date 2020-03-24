package Facebook;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

 Example:

 Input: [0,1,0,3,12]
 Output: [1,3,12,0,0]
 Note:

 You must do this in-place without making a copy of the array.
 Minimize the total number of operations.
 */
public class MoveZeroes {

    public static void moveZeroes(int[] nums) {
        int leftMostZeroIndex = 0; // The index of the leftmost zero
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i > leftMostZeroIndex) { // There are zero(s) on the left side of the current non-zero number, swap!
                    nums[leftMostZeroIndex] = nums[i];
                    nums[i] = 0;
                }
                leftMostZeroIndex++;
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 0, 9, 7, 0, 8};
        int[] arr1 = {3, 2, 0, 0, 9, 7, 0, 8};
        int[] arr12 = {0, 1, 0, 3, 12};
        moveZeroes(arr12);
        for (int num : arr12)
            System.out.println(num);

    }
}
