package LCPremium.Google;

/**
 * Given a file and assume that you can only read the file using a given method
 * read4, implement a method to read n characters.
 * <p>
 * <p>
 * <p>
 * Method read4:
 * <p>
 * The API read4 reads 4 consecutive characters from the file, then writes those
 * characters into the buffer array buf.
 * <p>
 * The return value is the number of actual characters read.
 * <p>
 * Note that read4() has its own file pointer, much like FILE *fp in C.
 * <p>
 * Definition of read4:
 * <p>
 * Parameter:  char[] buf Returns:    int
 * <p>
 * Note: buf[] is destination not source, the results from read4 will be copied
 * to buf[] Below is a high level example of how read4 works:
 * <p>
 * File file("abcdefghijk"); // File is "abcdefghijk", initially file pointer
 * (fp) points to 'a' char[] buf = new char[4]; // Create buffer with enough
 * space to store characters read4(buf); // read4 returns 4. Now buf = "abcd",
 * fp points to 'e' read4(buf); // read4 returns 4. Now buf = "efgh", fp points
 * to 'i' read4(buf); // read4 returns 3. Now buf = "ijk", fp points to end of
 * file
 * <p>
 * <p>
 * Method read:
 * <p>
 * By using the read4 method, implement the method read that reads n characters
 * from the file and store it in the buffer array buf. Consider that you cannot
 * manipulate the file directly.
 * <p>
 * The return value is the number of actual characters read.
 * <p>
 * Definition of read:
 * <p>
 * Parameters:	char[] buf, int n Returns:	int
 * <p>
 * Note: buf[] is destination not source, you will need to write the results to
 * buf[]
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: file = "abc", n = 4 Output: 3 Explanation: After calling your read
 * method, buf should contain "abc". We read a total of 3 characters from the
 * file, so return 3. Note that "abc" is the file's content, not buf. buf is the
 * destination buffer that you will have to write the results to. Example 2:
 * <p>
 * Input: file = "abcde", n = 5 Output: 5 Explanation: After calling your read
 * method, buf should contain "abcde". We read a total of 5 characters from the
 * file, so return 5. Example 3:
 * <p>
 * Input: file = "abcdABCD1234", n = 12 Output: 12 Explanation: After calling
 * your read method, buf should contain "abcdABCD1234". We read a total of 12
 * characters from the file, so return 12. Example 4:
 * <p>
 * Input: file = "leetcode", n = 5 Output: 5 Explanation: After calling your
 * read method, buf should contain "leetc". We read a total of 5 characters from
 * the file, so return 5.
 * <p>
 * <p>
 * Note:
 * <p>
 * Consider that you cannot manipulate the file directly, the file is only
 * accesible for read4 but not for read. The read function will only be called
 * once for each test case. You may assume the destination buffer array, buf, is
 * guaranteed to have enough space for storing n characters.
 */
public class ReadNcharacterGivenRead4Q1 {//abcdABCD1234

    /**
     * buf[] is a user buffer where we strore result to. Basic idea is we create
     * a internal buffer (buff) and every time we read from file, we read from
     * this internal buffer first to avoid expensive system call( here you can
     * imagine sys call is read4()). buffPtr point to next unread char in buff
     * and buffcount indicate length of buff. So when we call read(), we read
     * from buff starting with buffPtr. Once buffPtr == buffCount we finish
     * reading buff and we have to call read4() to refill internal buff and read
     * from buff again. It is important to note that read4() is a system call
     * and every call increament file position by 4 (like C *File)
     *
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return The number of characters read
     */
    private int buffPtr = 0;//buffPtr point to next unread char in buff
    private int buffCount = 0;//buffcount indicate length of buff
    private char[] temp = new char[4];

    public int read(char[] buf, int n) {
        boolean eof = false;      // end of file flag
        int total = 0;            // total bytes have read
        char[] tmp = new char[4]; // temp buffer

        while (!eof && total < n) {
            int count = read4(tmp);

            // check if it's the end of the file
            eof = count < 4;

            // get the actual count
            count = Math.min(count, n - total);

            // copy from temp buffer to buf
            for (int i = 0; i < count; i++)
                buf[total++] = tmp[i];
        }

        return total;
    }

    //Dummy method
    public int read4(char[] tmp) {
        return 4;
    }

    public int read2(char[] buf, int n) {
        int ptr = 0;
        while (ptr < n) {
            //no remaining characters in the temp
            if (buffPtr == 0) {
                buffCount = read4(temp);
            }
            if (buffCount == 0) {
                break;// end of file
            }
            // copy from temp buffer to buf
            while (ptr < n && buffPtr < buffCount) {
                buf[ptr++] = temp[buffPtr++];
            }
            // not because of ptr < n broken out of loop, read to the end of temp buffer
            if (buffPtr >= buffCount) {
                buffPtr = 0;
            }
        }
        return ptr;
    }

}
