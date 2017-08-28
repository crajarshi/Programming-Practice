package org.String;

public class StringtoInt {

    public static int stringToInt(String s) {
        int result = 0;
        for (int i = s.charAt(0) == '-' ? 1 : 0; i < s.length(); ++i) {
            final int digit = s.charAt(i) - '0';
            result = result * 10 + digit;
        }
        return s.charAt(0) == '-' ? -result : result;
    }

    public static String intToString(int num){
        StringBuilder stringBuilder = new StringBuilder();
        boolean isNegative = false;
        if (num < 0){
            isNegative = true;
        }
        while(num != 0){
            stringBuilder.append((char)('0' +Math.abs(num % 10)));

            num /= 10;
        }

        if (isNegative){
            stringBuilder.append('-');
        }

//            return isNegative ? stringBuilder.append('-').reverse().toString() : stringBuilder.reverse().toString();
        return stringBuilder.reverse().toString();
    }

    public static void main(String[] args) {
//        System.out.println(stringToInt("12356"));
//        System.out.println(stringToInt("Raj"));
        String str =intToString(3948);
        System.out.println(str);
    }
}
