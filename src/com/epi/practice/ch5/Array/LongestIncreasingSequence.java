package com.epi.practice.ch5.Array;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.max;

/**
 * Created by Raj on 3/4/18.
 */
public class LongestIncreasingSequence {

    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = max(maxval, dp[j]);
                }
            }
            dp[i] = maxval + 1;
            maxans = max(maxans, dp[i]);
        }
        return maxans;
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }

    public static String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) return "";
        String pre = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(pre) != 0)
                pre = pre.substring(0, pre.length() - 1);
        return pre;
    }

    public static int wordsTyping(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int start = 0, l = s.length();
        for (int i = 0; i < rows; i++) {
            start += cols;
            if (s.charAt(start % l) == ' ') {
                start++;
            } else {
                while (start > 0 && s.charAt((start - 1) % l) != ' ') {
                    start--;
                }
            }
        }

        return start / s.length();
    }


    public static boolean validUtf8(int[] data) {
        int count = 0;
        for (int d : data) {
            if (count == 0) {
                if ((d >> 5) == 0b110) count = 1;
                else if ((d >> 4) == 0b1110) count = 2;
                else if ((d >> 3) == 0b11110) count = 3;
                else if ((d >> 7) == 1) return false;
            } else {
                if ((d >> 6) != 0b10) return false;
                else count--;
            }
        }
        return count == 0;
    }


    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> list = new ArrayList<String>();
        if (nums == null) return list;
        int n = nums.length;
        int lt = 0, gt = 0;
        for (int i = 0; i <= n; i++) {

            if (i == 0)
                lt = lower;
            else if (nums[i - 1] == Integer.MAX_VALUE)
                break;
            else
                lt = nums[i - 1] + 1;

            if (i == n)
                gt = upper;
            else if (nums[i] == Integer.MIN_VALUE)
                continue;
            else
                gt = nums[i] - 1;

            addRange(list, lt, gt);
        }
        return list;
    }

    private static void addRange(List<String> list, int lo, int hi) {
        if (lo > hi) return;
        else if (lo == hi) list.add(String.valueOf(lo));
        else list.add(lo + "->" + hi);
    }

    public static List<String> findMissingRanges1(int[] nums, int lower, int upper) {
        List<String> list = new ArrayList<String>();
        if (nums == null) return list;
        int n = nums.length;
        for (int i = 0; i <= n; i++) {

            if (nums[i] - nums[i - 1] > 2) {

            }

        }
        return list;
    }


    /**
     * 0 E 0 0
     * E 0 W E
     * 0 E 0 0
     */

    public static int maxKilledEnemies(char[][] grid) {
        int m = grid.length, n = m == 0 ? 0 : grid[0].length;
        int result = 0, rowHits = 0, colHits[] = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0 || grid[i][j - 1] == 'W') {
                    rowHits = 0;
                    // reset rowHits when hit wall, re calculate enemy after
                    for (int k = j; k < n && grid[i][k] != 'W'; k++) {
                        rowHits += grid[i][k] == 'E' ? 1 : 0;
                        ;
                    }
                }
                if (i == 0 || grid[i - 1][j] == 'W') {
                    colHits[j] = 0;
                    for (int k = i; k < m && grid[k][j] != 'W'; k++) {
                        colHits[j] += grid[k][j] == 'E' ? 1 : 0;
                    }
                }
                if (grid[i][j] == '0') {
                    result = Math.max(result, rowHits + colHits[j]);
                }
            }
        }
        return result;
    }

    public static int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            digits[i] = 0;
        }

        int[] newNumber = new int[n + 1];
        newNumber[0] = 1;

        return newNumber;
    }


    public static boolean validWordSquare(List<String> words) {
        if (words == null || words.size() == 0) {
            return true;
        }
        int n = words.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words.get(i).length(); j++) {
                if (j >= n || words.get(j).length() <= i || words.get(j).charAt(i) != words.get(i).charAt(j))
                    return false;
            }
        }
        return true;
    }

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] count = new int[256];
        char[] cs = s.toCharArray();
        int distinctNum = 0, leftI = 0, res = 0;
        for (int rightI = 0; rightI < cs.length; rightI++) {
            if (count[cs[rightI]]++ == 0) distinctNum++;
            if (distinctNum > k) {
                while (--count[cs[leftI++]] > 0) ;
                distinctNum--;
            }
            res = Math.max(res, rightI - leftI + 1);
        }
        return res;
    }

    public static int lengthOfLongestSubstringKDistinct1(String s, int k) {
        int lo = 0, hi = 0;
        int n = s.length();
        int max = 0;
        if (k == 0) return 0;
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();

        while (hi < n) {
            char ch = s.charAt(hi);
            if (map.containsKey(ch) || map.size() < k) {
                map.remove(ch);
                map.put(ch, hi);
                max = Math.max(max, hi++ - lo + 1);
            } else {
                Character key = map.keySet().iterator().next();
                lo = map.get(key);
                map.remove(key);
                lo++;
            }
        }
        return max;
    }

    public static String addBoldTag(String s, String[] dict) {
        boolean[] bold = new boolean[s.length()];
        for (int i = 0, end = 0; i < s.length(); i++) {
            for (String word : dict) {// For every `word` in `dict`, we test:
                // If substring s[i, i + word.length()] == word, meaning characters between i,
                // i + word.length() should be `bold`.
                if (s.startsWith(word, i)) {
                    // Use variable `end` to store known longest end of current continuous `bold` characters
                    end = Math.max(end, i + word.length());
                }
            }
            // If `end` > `i`, meaning character at position `i` is within the current continuous `bold`
            // characters, we mark it as `bold`.
            bold[i] = end > i;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!bold[i]) {
                result.append(s.charAt(i));
                continue;
            }
            int j = i;
            while (j < s.length() && bold[j]) j++;
            result.append("<b>").append(s.substring(i, j)).append("</b>");
            i = j - 1;
        }

        return result.toString();
    }

    public static String removeDuplicatesStrings(String s1) {

        char[] ch = s1.toCharArray();
        int writeIndex = 1;
        for (int i = 1; i < ch.length; i++) {
            if (ch[writeIndex - 1] != ch[i])
                ch[writeIndex++] = ch[i];
        }
        StringBuilder sb = new StringBuilder();
        for (char c : ch)
            sb.append(c);
        return sb.toString();
    }

    public static String firstUniqChar(String s) {

//        char[] chars = s.toCharArray();
        Set<Character> charSet = new LinkedHashSet<Character>(26);
        for (char c : s.toCharArray()) {
            charSet.add(c);
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : charSet) {
            sb.append(character);
        }
        return sb.toString();
    }

    public static String removeDuplicates(String myString) {
        return Arrays.asList(myString.split("")).stream().distinct().collect(Collectors.joining());
    }

    public static double[] calcEquation(String[][] eq, double[] vals, String[][] q) {
        Map<String, Map<String, Double>> m = new HashMap<>();
        for (int i = 0; i < vals.length; i++) {
            m.putIfAbsent(eq[i][0], new HashMap<>());
            m.putIfAbsent(eq[i][1], new HashMap<>());
            m.get(eq[i][0]).put(eq[i][1], vals[i]);
            m.get(eq[i][1]).put(eq[i][0], 1 / vals[i]);
        }
        double[] r = new double[q.length];
        for (int i = 0; i < q.length; i++)
            r[i] = dfs(q[i][0], q[i][1], 1, m, new HashSet<>());
        return r;
    }

    static double dfs(String s, String t, double r, Map<String, Map<String, Double>> m, Set<String> seen) {
        if (!m.containsKey(s) || !seen.add(s)) return -1;
        if (s.equals(t)) return r;
        Map<String, Double> next = m.get(s);
        for (String c : next.keySet()) {
            double result = dfs(c, t, r * next.get(c), m, seen);
            if (result != -1) return result;
        }
        return -1;
    }

    public static int testDir(int[][] dirs) {
        for (int[] dir : dirs) {
            int x = dir[0];
            int y = dir[1];
            return x + y;
        }

        return 0;

    }

    public static List<String> summaryRanges(int[] nums) {
        List<String> summary = new ArrayList<>();
        for (int i = 0, j = 0; j < nums.length; ++j) {
            // check if j + 1 extends the range [nums[i], nums[j]]
            if (j + 1 < nums.length && nums[j + 1] == nums[j] + 1)
                continue;
            // put the range [nums[i], nums[j]] into the list
            if (i == j)
                summary.add(nums[i] + "");
            else
                summary.add(nums[i] + "->" + nums[j]);
            i = j + 1;
        }
        return summary;
    }

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

    public static void main(String[] args) {
//        int [] num = {10, 9, 2, 5, 3, 7, 101, 18};
//           int [] num1 = {197,130,1};
//           int [] num2 = {0,1,2,50,51};
//           int [] num3 = {9,9,9};
//        char [][] grid = {["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]};
//        String [] str = {"cat","ca","cats","calcu"};
//        String [] str = {"abc", "de", "f"};
//        int[][] dirs={{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
//        System.out.println(testDir(dirs));
//        List<String> stringList = Arrays.asList("abcd","bnrt","crmy","dtye");
//        System.out.println("THe OT:" + wordsTyping(str,4,6));
//        System.out.println("Nos:" + validUtf8(num1));
//        System.out.println("Longest:" +lengthOfLIS(num));
//        System.out.println("Longest:" +longestCommonPrefix(str));
//        System.out.println("Longest:" +longestCommonPrefix1(str));
//        String s1 = "cat";
//        String s2 = "ca";
//
//        System.out.println("index is :" + s2.indexOf(s1));

//        System.out.println("Range Check:" + findMissingRanges(num2,0,99));
//        System.out.println("Add one  Check:" + plusOne(num3).toStrin
// g());
//        System.out.println(validWordSquare(stringList));
//        System.out.println(lengthOfLongestSubstringKDistinct1("eceba",2));
//        System.out.println(addBoldTag("catccalcu",str));
//        System.out.println(removeDuplicatesStrings("Helloo World"));
//        System.out.println(firstUniqChar("leetcode"));
//        System.out.println(removeDuplicates("leetcode"));
//        System.out.println("Equation:" + calcEquation([["a","b"]["b","c"]],[[2.0,3.0]["a","c"]],["b","c"],["a","e"],["a","a"],["x","x"]));
//        System.out.println(summaryRanges(num2));LongestIncreasingSequence
    }

    public int removeDuplicates(int[] nums) {
        int writeIndex = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[writeIndex - 1] == nums[i])
                nums[writeIndex] = nums[i];
            writeIndex++;
        }
        return writeIndex;
    }

    public void removeDuplicatesInString(String str) {

        System.out.println("str = " + str);
        int[] count = new int[256];

        for (int i = 0; i < str.length(); i++) {
            count[str.charAt(i)]++;
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                if (count[i] - 1 <= 0)
                    continue;
                else {
                    count[i] = 0;
                }
            }
        }
        System.out.print("De-duplicated String = ");
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                System.out.print(Character.toString((char) i));
            }
        }
    }


}
