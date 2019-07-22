package Facebook;

/**
 * Given a file and assume that you can only read the file using a given method read4, implement a method to read n characters.
 * <p>
 * <p>
 * <p>
 * Method read4:
 * <p>
 * The API read4 reads 4 consecutive characters from the file, then writes those characters into the buffer array buf.
 * <p>
 * The return value is the number of actual characters read.
 * <p>
 * Note that read4() has its own file pointer, much like FILE *fp in C.
 * <p>
 * Definition of read4:
 * <p>
 * Parameter:  char[] buf
 * Returns:    int
 * <p>
 * Note: buf[] is destination not source, the results from read4 will be copied to buf[]
 * Below is a high level example of how read4 works:
 * <p>
 * File file("abcdefghijk"); // File is "abcdefghijk", initially file pointer (fp) points to 'a'
 * char[] buf = new char[4]; // Create buffer with enough space to store characters
 * read4(buf); // read4 returns 4. Now buf = "abcd", fp points to 'e'
 * read4(buf); // read4 returns 4. Now buf = "efgh", fp points to 'i'
 * read4(buf); // read4 returns 3. Now buf = "ijk", fp points to end of file
 * <p>
 * <p>
 * Method read:
 * <p>
 * By using the read4 method, implement the method read that reads n
 * characters from the file and store it in the buffer array buf. Consider that you cannot manipulate the file directly.
 * <p>
 * The return value is the number of actual characters read.
 * <p>
 * Definition of read:
 * <p>
 * Parameters:	char[] buf, int n
 * Returns:	int
 * <p>
 * Note: buf[] is destination not source, you will need to write the results to buf[]
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: file = "abc", n = 4
 * Output: 3
 * Explanation: After calling your read method, buf should contain "abc". We read a total of 3 characters from the file, so return 3.
 * Note that "abc" is the file's content, not buf. buf is the destination buffer that you will have to write the results to.
 * Example 2:
 * <p>
 * Input: file = "abcde", n = 5
 * Output: 5
 * Explanation: After calling your read method, buf should contain "abcde". We read a total of 5 characters from the file, so return 5.
 * Example 3:
 * <p>
 * Input: file = "abcdABCD1234", n = 12
 * Output: 12
 * Explanation: After calling your read method, buf should contain "abcdABCD1234". We read a total of 12 characters from the file, so return 12.
 * Example 4:
 * <p>
 * Input: file = "leetcode", n = 5
 * Output: 5
 * Explanation: After calling your read method, buf should contain "leetc". We read a total of 5 characters from the file, so return 5.
 * <p>
 * <p>
 * Note:
 * <p>
 * Consider that you cannot manipulate the file directly, the file is only accesible for read4 but not for read.
 * The read function will only be called once for each test case.
 * You may assume the destination buffer array, buf, is guaranteed to have enough space for storing n characters.
 */
public class ReadNCharacters {

    /**
     *
     */

    private int prev = 4;
    private int count = 0;
    private char[] readChars = new char[4];

    public int read(char[] buf, int n) {

        char[] temp = new char[4];  //Store our read chars from Read4
        int total = 0;

        while (total < n) {
            /*Read and store characters in Temp. Count will store total chars read from Read4*/
            int count = read4(temp);

            /*Even if we read 4 chars from Read4,
            we don't want to exceed N and only want to read chars till N.*/
            count = Math.min(count, n - total);

            //Transfer all the characters read from Read4 to our buffer
            for (int i = 0; i < count; i++) {
                buf[total] = temp[i];
                total++;
            }

            //done. We can't read more characters.
            if (count < 4) break;
        }

        return total;
    }

    public int read4(char[] arr) {
        return 4;
    }

    /*
    Basically, call multiple times means, you want to resume where you left off last time.
    That means, if there are chars left in readChars (from the Read4 from the previous call of ReadN), you want to transfer them into buffer first.
    */
    public int read2(char[] buf, int n) {
        int total = 0;

        /**
         EdgeCases:
         If N < 4, do you transfer all the remaining chars from previous call or just N?
         what if there are still characters left in readChars after transfering N to the buffer? In that case, you still want to pick up from there next time.

         **/
        while (total < n) {
            while (prev < count && total < n) {
                buf[total] = readChars[prev];
                prev++;
                total++;
            }

            if (total < n) {
                count = read4(readChars);
                prev = 0;

                //You only want to read till N even if there are more chars returned by read4.
                int toRead = Math.min(count, n - total);

                while (prev < toRead) {
                    buf[total] = readChars[prev];
                    prev++;
                    total++;
                }

                //Done. There are no more chars to read from Read4.
                if (count < 4) break;
            }
        }

        return total;
    }
}
