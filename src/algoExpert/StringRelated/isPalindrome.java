package algoExpert.StringRelated;

/**
 * Created by Raj on 5/18/19.
 */
public class isPalindrome {

    public static boolean isPalindrome(String str) {
        // Write your code here.
        int leftIdx = 0, rightIdx = str.length() - 1;
        while (leftIdx < rightIdx) {
            if (str.charAt(leftIdx++) != str.charAt(rightIdx--))
                return false;
        }

        return true;
    }


}
