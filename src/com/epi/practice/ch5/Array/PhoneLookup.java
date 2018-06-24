package com.epi.practice.ch5.Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneLookup {

    private static final String[] MAPPING = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

    public static List<String> phoneMnemonic(String phoneNumber){
        char [] partialMnemonic = new char[phoneNumber.length()];
        List<String> mnemonics = new ArrayList<>();

        phoneMnenmonicHelper(phoneNumber,0,partialMnemonic,mnemonics);
        return mnemonics;
    }

    private static void phoneMnenmonicHelper(String phoneNumber, int digit, char[] partialMnemonic,
                                             List<String> mnemonics){

        if (digit == phoneNumber.length()){
            mnemonics.add(new String(partialMnemonic));
        }
        else {
            for (int i =0; i < MAPPING[phoneNumber.charAt(digit) - '0'].length();++i){
                char c = MAPPING[phoneNumber.charAt(digit) - '0'].charAt(i);
                partialMnemonic[digit] = c;
                phoneMnenmonicHelper(phoneNumber,digit+1,partialMnemonic,mnemonics);
            }
        }
    }

    public static boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;
        Map index = new HashMap();
        for (Integer i = 0; i < words.length; ++i)
            if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
                return false;
        return true;
    }

    public static void main(String[] args) {

//        System.out.println(phoneMnemonic("2276696"));
        System.out.println(wordPattern("abba", "dog cat cat dog"));
    }
}
