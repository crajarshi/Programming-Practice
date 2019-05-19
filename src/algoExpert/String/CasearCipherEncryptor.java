package algoExpert.String;

/**
 * Given a non-empty string of lowercase letters and a non-negative integer value representing a key,
 * write a function that returns a new string obtained by shifting every letter in the input string by
 * k positions in the alphabet, where k is the key. Note that letters should "wrap" around the alphabet;
 * in other words, the letter "z" shifted by 1 returns the letter "a".

 Sample input: "xyz", 2
 Sample output: "zab"

 */
public class CasearCipherEncryptor {

    // O(n) time | O(n) space
    public static String caesarCypherEncryptor(String str, int key) {
        char[] newLetters = new char[str.length()];
        int newKey = key % 26;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < str.length(); i++) {
            newLetters[i] = getNewLetter(str.charAt(i), newKey, alphabet);
        }
        return new String(newLetters);
    }

    public static char getNewLetter(char letter, int key, String alphabet) {
        int newLetterCode = alphabet.indexOf(letter) + key;
        return newLetterCode <= 25 ? alphabet.charAt(newLetterCode) : alphabet.charAt(-1 + newLetterCode % 25);
    }

}
