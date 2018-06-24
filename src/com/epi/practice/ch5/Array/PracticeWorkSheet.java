package com.epi.practice.ch5.Array;

import java.util.*;

/**
 * Created by Raj on 6/3/18.
 */
public class PracticeWorksheet {

    public static int lengthLongestPath(String input) {
        Deque<Integer> stack = new ArrayDeque<>();
        String[] arr = input.split("\n");
        int maxLen = 0;
        stack.push(0); //dummy null length
        for (String s : arr) {
            /*
            numOfTabs is the number of "\t", numOfTabs = 0
            when "\t" is not found, because s.lastIndexOf("\t") returns -1.
            So normally, the first parent "dir" have numOfTabs 0.
            */
            int numOfTabs = s.lastIndexOf("\t") + 1;
            /* Level is defined as numOfTabs + 1.
            For example, in "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext",
            dir is level 1, subdir1 and subdir2 are level 2, file.ext is level3
            */
            int level = numOfTabs + 1;
            /*
            The following part of code is the case that we want to consider when there are
            several subdirectories in a same level. We want to remove
            the path length of the directory or the file of same level
            that we added during previous step, and calculate
            the path length of current directory or file that we are currently looking at.
            */
            while (level < stack.size()) stack.poll();
            int curLen = stack.peek() + s.length() - numOfTabs + 1;
            stack.push(curLen);
            if (s.contains("."))
                maxLen = Math.max(maxLen, curLen - 1); //Only update the maxLen when a file is discovered,
            // And remove the "/" at the end of file
        }
        return maxLen;
    }

    public static String nextClosestTime(String time) {
        /**
         Simulate the clock going forward by one minute. Each time it moves forward, if all the digits are allowed, then return the current time.
         The natural way to represent the time is as an integer t in the range 0 <= t < 24 * 60. Then the hours are t / 60, the minutes are t % 60, and each digit of the hours and minutes can be found by hours / 10, hours % 10 etc.
         */
        int cur = 60 * Integer.parseInt(time.substring(0, 2));
        cur += Integer.parseInt(time.substring(3));
        Set<Integer> allowed = new HashSet();
        for (char c : time.toCharArray())
            if (c != ':') {
                allowed.add(c - '0');
            }

        while (true) {
            cur = (cur + 1) % (24 * 60);
            int[] digits = new int[]{cur / 60 / 10, cur / 60 % 10, cur % 60 / 10, cur % 60 % 10};
            search:
            {
                for (int d : digits) if (!allowed.contains(d)) break search;
                return String.format("%02d:%02d", cur / 60, cur % 60);
            }
        }
    }

    public static int longestSubArray(List<Integer> A) {
        Map<Integer, Integer> hm = new HashMap<>();
        int startIdx = 0, result = 0;
        for (int i = 0; i < A.size(); ++i) {
            Integer dupIdx = hm.put(A.get(i), i);
            if (dupIdx != null) {
                if (dupIdx >= startIdx) {
                    result = Math.max(result, i - startIdx);
                    startIdx = dupIdx + 1;
                }
            }
        }
        return Math.max(result, A.size() - startIdx);
    }

    public static int checkHm(List<String> A) {
        Map<String, Integer> hm = new LinkedHashMap<>();
        int i = 0;
        for (String str : A) {
            hm.put(str, i++);
        }
        return hm.size();
    }


    public static void printKSmallest(int[] nums, int k) {
        if (k > nums.length) {
            return;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });

        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                heap.add(nums[i]);
            } else if (nums[i] < heap.peek()) {
                heap.remove();
                heap.add(nums[i]);
            }
        }
        System.out.println(heap.peek());
    }


    public static void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
        for (int num : nums)
            System.out.println(num);
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

    public static boolean isValid(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') count++;
            if (c == ')' && count-- == 0) return false;
        }

        return count == 0;
    }

    public static String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 10);
            carry = sum / 10;
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }

    public static String multiplyBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 1;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) sum *= b.charAt(j--) - '0';
            if (i >= 0) sum *= a.charAt(i--) - '0';
            sb.append(sum % 10);
            carry = sum / 10;
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }


    public static List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if (digits.isEmpty()) return ans;
        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for (int i = 0; i < digits.length(); i++) {
            int x = Character.getNumericValue(digits.charAt(i));
            while (ans.peek().length() == i) {
                String t = ans.remove();
                for (char s : mapping[x].toCharArray())
                    ans.add(t + s);
            }
        }
        return ans;
    }

    public static int maxSubArrayLen(int[] nums, int k) {
        int sum = 0, max = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            if (sum == k) max = i + 1;
            else if (map.containsKey(sum - k)) max = Math.max(max, i - map.get(sum - k));
            if (!map.containsKey(sum)) map.put(sum, i);
        }
        return max;
    }



    public static void main(String[] args) {
//        System.out.println(lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
//        System.out.println(nextClosestTime("19:34"));
//                List<Integer> stringList = Arrays.asList(1,2,3,1,4,3);
//                List<String> strList = Arrays.asList("cat","act");
//        System.out.println(checkHm(strList));

//        System.out.println(longestSubArray(stringList));
//        int [] num = {10, 9, 2, 5, 3, 7, 101, 18};
        int[] nums = {2, 4, -2, 1, -3, -3, 6};
        //18,101,7,3,5,2,9,10
//        printKSmallest(num,4);
//        System.out.println(zeroSumSubArray(num));
//        rotate(num,4);
//        isValid("(()))");

//        System.out.println(multiplyBinary("10","12"));
//        System.out.println(letterCombinations("23"));
        System.out.println(maxSubArrayLen(nums, 0));
    }


}
