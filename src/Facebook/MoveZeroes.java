package Facebook;

/**
 * Created by Raj on 6/24/19.
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
        moveZeroes(arr);
        for (int num : arr)
            System.out.println(num);

    }
}
