package LeetCodeMedium.SortSearch;

/**
 * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 * <p>
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * <p>
 * Note: You are not suppose to use the library's sort function for this problem.
 * <p>
 * Example:
 * <p>
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Follow up:
 * <p>
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
 * Could you come up with a one-pass algorithm using only constant space?
 */
public class SortColors {

    private static void swap(int i1, int i2) {
        int temp = i2;
        i2 = i1;
        i1 = temp;

    }

    public static void main(String[] args) {
        int att[] = {2, 0, 2, 1, 1, 0};
        SortColors sortColors = new SortColors();
        sortColors.sortColors(att);
        for (int i : att)
            System.out.print(i);
    }

    // two pass O(m+n) space
    void sortColors(int A[], int n) {
        int num0 = 0, num1 = 0, num2 = 0;

        for (int i = 0; i < n; i++) {
            if (A[i] == 0) ++num0;
            else if (A[i] == 1) ++num1;
            else if (A[i] == 2) ++num2;
        }

        for (int i = 0; i < num0; ++i) A[i] = 0;
        for (int i = 0; i < num1; ++i) A[num0 + i] = 1;
        for (int i = 0; i < num2; ++i) A[num0 + num1 + i] = 2;
    }

    // one pass in place solution
    void sortColors(int A[]) {
        int n0 = -1, n1 = -1, n2 = -1;
        for (int i = 0; i < A.length; ++i) {
            if (A[i] == 0) {
                A[++n2] = 2;
                A[++n1] = 1;
                A[++n0] = 0;
            } else if (A[i] == 1) {
                A[++n2] = 2;
                A[++n1] = 1;
            } else if (A[i] == 2) {
                A[++n2] = 2;
            }
        }
    }

    // one pass in place solution
    void sortColors2(int A[], int n) {
        int j = 0, k = n - 1;
        for (int i = 0; i <= k; ++i) {
            if (A[i] == 0 && i != j)
                swap(A[i--], A[j++]);
            else if (A[i] == 2 && i != k)
                swap(A[i--], A[k--]);
        }
    }

    // one pass in place solution
    void sortColors3(int A[], int n) {
        int j = 0, k = n - 1;
        for (int i = 0; i <= k; i++) {
            if (A[i] == 0)
                swap(A[i], A[j++]);
            else if (A[i] == 2)
                swap(A[i--], A[k--]);
        }
    }
}
