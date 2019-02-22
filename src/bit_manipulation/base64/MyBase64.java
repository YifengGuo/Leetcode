package bit_manipulation.base64;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * The implementation of base64 algorithm
 * 
 * @author guoyifeng
 *
 */
public class MyBase64 {

	private static final char[] TABLE = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4',
			'5', '6', '7', '8', '9', '+', '/' };

	public static String encode(String input) {
		String res = "";
		List<String> list = divideDataBy3Bytes(input);
		// TABLE length == 64 == 2 ^ 6 so 6 bit can represent the whole TABLE
		// encode data in the list with TABLE, each complete element is 24-bit long so
		// each complete element will need 4 table items to cover;
		for (String s : list) {
			for (int i = 0; i < Math.min(4, s.length() / 6 + 1); i++) {
				String curr = s.substring(i * 6, Math.min((i + 1) * 6, s.length()));
				if (curr.length() < 6) {
					while (curr.length() < 6) {
						curr += "0";
					}
				}
				res += TABLE[Integer.parseInt(curr, 2)];
			}
		}

		// pad '=' if last data is not 24-bit long
		if (list.get(list.size() - 1).length() != 24) {
			int extra = (24 - list.get(list.size() - 1).length()) / 8;
			for (int i = 0; i < extra; i++) {
				res += "=";
			}
		}
		return res;
	}

	public static String decode(String s) {
		int index = 0;
		StringBuilder strBuff = new StringBuilder();
		StringBuilder resultBuff = new StringBuilder();
		while (true) {
			// pad '0'
			if (index == s.length() || s.charAt(index) == '=') {
				int zeroCount = 8 - strBuff.length();
				for (int i = 0; i < zeroCount; i++) {
					strBuff.append('0');
				}
				index = -1;
			}
			// update buffer
			while (index != -1 && strBuff.length() < 8 && index < s.length()) {
				int charIndex = getIndex(s.charAt(index++));
				strBuff.append(get6BitStr(charIndex));
			}
			// fetch 8 chars from buffer
			String temp2 = strBuff.substring(0, 8);
			int temp10 = Integer.valueOf(temp2, 2);
			resultBuff.append((char) temp10);
			strBuff.delete(0, 8);
			
			if (index == -1)
				break;
		}
		return resultBuff.toString().trim();
	}

	private static String get6BitStr(int ascii) {
		StringBuilder s = new StringBuilder(Integer.toBinaryString(ascii));
		if (s.length() < 6) {
			int zeroCount = 6 - s.length();
			for (int j = 0; j < zeroCount; j++) {
				s.insert(0, '0');
			}
		}
		return s.toString();
	}

	private static int getIndex(char curr) {
		return IntStream.range(0, TABLE.length).filter(i -> curr == TABLE[i]).findFirst().orElse(-1);
	}

	private static List<String> divideDataBy3Bytes(String input) {
		byte[] byteArr = input.getBytes();
		List<String> list = new ArrayList<>(); // each element represents 3 bytes data which is 24-bit long
		int count = 0;
		String tmp = "";
		for (byte b : byteArr) {
			count++;
			String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'); // convert byte number
																									// to binary string
			tmp += s1;
			if (count % 3 == 0) {
				list.add(tmp);
				tmp = "";
			}
		}
		if (count != 0) {
			list.add(tmp);
		}
		return list;
	}

	public static void main(String[] args) {
		String test = "zhupengtao@shzu.edu.cn";
		String code = "emh1cGVuZ3Rhb0BzaHp1LmVkdS5jbg==";

		System.out.println(encode(test));
		System.out.println(decode(code));
	}
}
