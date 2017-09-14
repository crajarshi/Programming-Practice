package org.String;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Raj on 9/10/17.
 */
public class validateNumericString {

//    public static boolean isNumber(String s) {
//        Pattern p = Pattern.compile("[-+]?(([0-9]+(.[0-9]*)?)|.[0-9]+)(e[-+]?[0-9]+)?");
//        Matcher m = p.matcher(s);
//        if (m.find()) {
//            return true;
//        }
//        return false;
//    }

    public static boolean isNumber(String s) {
        s = s.trim();

        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        boolean numberAfterE = true;
        for(int i=0; i<s.length(); i++) {
            if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
                numberAfterE = true;
            } else if(s.charAt(i) == '.') {
                if(eSeen || pointSeen) {
                    return false;
                }
                pointSeen = true;
            } else if(s.charAt(i) == 'e') {
                if(eSeen || !numberSeen) {
                    return false;
                }
                numberAfterE = false;
                eSeen = true;
            } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
                if(i != 0 && s.charAt(i-1) != 'e') {
                    return false;
                }
            } else {
                return false;
            }
        }

        return numberSeen && numberAfterE;
    }

    public static void main(String[] args) {
        System.out.println(isNumber("123a"));
        System.out.println(isNumber("abcd"));
        System.out.println(isNumber("abg35"));
        System.out.println(isNumber("  "));
        System.out.println(isNumber("e9"));
        System.out.println(isNumber("0.1"));
        System.out.println(isNumber("2e10"));

    }
}
