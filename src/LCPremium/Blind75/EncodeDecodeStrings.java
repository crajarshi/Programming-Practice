package LCPremium.Blind75;

import java.util.ArrayList;
import java.util.List;

/**
 * Design an algorithm to encode a list of strings to a string. The encoded
 * string is then sent over the network and is decoded back to the original list
 * of strings.
 * <p>
 * Machine 1 (sender) has the function:
 * <p>
 * string encode(vector<string> strs) { // ... your code return encoded_string;
 * } Machine 2 (receiver) has the function: vector<string> decode(string s) {
 * //... your code return strs; } So Machine 1 does:
 * <p>
 * string encoded_string = encode(strs); and Machine 2 does:
 * <p>
 * vector<string> strs2 = decode(encoded_string); strs2 in Machine 2 should be
 * the same as strs in Machine 1.
 * <p>
 * Implement the encode and decode methods.
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * The string may contain any possible characters out of 256 valid ascii
 * characters. Your algorithm should be generalized enough to work on any
 * possible characters. Do not use class member/global/static variables to store
 * states. Your encode and decode algorithms should be stateless. Do not rely on
 * any library method such as eval or serialize methods. You should implement
 * your own encode/decode algorithm.
 */

public class EncodeDecodeStrings {


    /**
     * Double any hashes inside the strings, then use standalone hashes
     * (surrounded by spaces) to mark string endings. For example:
     * <p>
     * {"abc", "def"}    =>  "abc # def # " {'abc', '#def'}   =>  "abc # ##def #
     * " {'abc##', 'def'}  =>  "abc#### # def # " For decoding, just do the
     * reverse: First split at standalone hashes, then undo the doubling in each
     * string.
     */

    public String encode(List<String> strs) {
        StringBuffer out = new StringBuffer();
        for (String s : strs)
            out.append(s.replace("#", "##")).append(" # ");
        return out.toString();
    }

    public List<String> decode(String s) {
        List strs = new ArrayList();
        String[] array = s.split(" # ", -1);
        for (int i = 0; i < array.length - 1; ++i)
            strs.add(array[i].replace("##", "#"));
        return strs;
    }
    /**Or with streaming:

     public String encode(List<String> strs) {
     return strs.stream()
     .map(s -> s.replace("#", "##") + " # ")
     .collect(Collectors.joining());
     }

     public List<String> decode(String s) {
     List strs = Stream.of(s.split(" # ", -1))
     .map(t -> t.replace("##", "#"))
     .collect(Collectors.toList());
     strs.remove(strs.size() - 1);
     return strs;
     }
     */

}

