package Interviewcamp;

/**
 * Created by Raj on 7/13/20.
 */
public class CloneEvenNumbers {
    public static void main(String[] args) {
        int[] a = {1, 2, 4, 5, 6, -1, -1};
        System.out.println(cloneEvenNumbers(a));

    }

    public static int[] cloneEvenNumbers(int[] a) {
        int i = a.length - 1;
        int end = i;
        while (i >= 0) {
            if (a[i] % 2 == 0) {
                a[--end] = a[i];

            }
            a[--end] = a[i];
            i--;
        }
        return a;
    }
}
