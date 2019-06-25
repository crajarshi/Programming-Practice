package Facebook;

import java.util.HashMap;
import java.util.Map;

/**
 * The core idea is comparing current symbol and its next to decide whether current value should be added to result or subsracted from result.
 */
public class RomanToInteger {

    public static int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int i = 0, j = 1, res = 0;
        char[] ch = s.toCharArray();
        for (; j < s.length(); i++, j++) {
            int sym1 = map.get(ch[i]), sym2 = map.get(ch[j]);
            if (sym1 >= sym2) {
                res += sym1;
            } else
                res -= sym1;
        }
        res += map.get(ch[ch.length - 1]);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("XLXL"));
    }
}
