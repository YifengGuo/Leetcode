package bit_manipulation;

public class BitTest {
	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		int a = 4;
		System.out.println(a ^ 1);
		int b = -1;
		// pad 0 on the significant bits
		System.out.println(String.format("%32s", Integer.toBinaryString(b >>> 3)).replace(' ', '0'));
		
		char c = '%';
		int indexOfChar = c; // char and int can be converted automatically
		System.out.println(indexOfChar);
		
		// if we want to convert integer to x-form (binary or hex)
		// we could simply keep MOD x and divided by x
		// eg: int to hex
//		while (i > 0) {
//			sb.append(toHex(i % 16));
//			i /= 16;
//		}
	}
}
