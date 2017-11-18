package bit_manipulation;

public class HexadecimalRepresentation {
	public String hex(int num) {
		if (num < 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		// corner case
		if (num == 0) {
			sb.append('0');
		}
		while (num > 0) {
			sb.append(toHex(num % 16)); // append from least to most significant bit
			num /= 16;
		}
		// for we add hex form from least significant bit to most significant bit, so
		// order in StringBuilder is reversed, so we append "0x" as "x0"
		sb.append("x0");
		// then return reversed string of sb
		return sb.reverse().toString();
		
	}

	private char toHex(int i) {
		if (i <= 9) {
			return (char)(i + '0');
		}
		return (char)(i - 10 + 'A');
	}
}
