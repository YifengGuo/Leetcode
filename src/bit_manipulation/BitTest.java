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
	}
}
