package TopInterviewQuestions;

/**
 * 
 * @author yifengguo 
 * 	       Write a function that takes an unsigned integer and returns
 *         the number of ’1' bits it has (also known as the Hamming weight).
 * 
 *         For example, the 32-bit integer ’11' has binary representation
 *         00000000000000000000000000001011, so the function should return 3.
 */
public class HammingWeight {
    // you need to treat n as an unsigned value
	// 0000......0110
	// 0000......0001
	// only bit is 1 and & with 1 will return 1
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res += n & 1;
            n = n >>> 1;
        }
        return res;
    }
}
