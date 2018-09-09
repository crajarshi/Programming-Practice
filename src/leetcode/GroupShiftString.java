package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Raj on 9/8/18.
 */
public class GroupShiftString {
    public static List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strings) {
            String key = getKey(s);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(s);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    private static String getKey(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < chars.length; i++) {
            int diff = chars[i] - chars[i - 1];
            sb.append(diff < 0 ? diff + 26 : diff);
            sb.append(",");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] str = new String[]{"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
        String[] str1 = new String[]{"abc", "bcd", "mno", "xyz", "az", "ba", "a", "z"};
        System.out.println(groupStrings(str1));

    }
}
