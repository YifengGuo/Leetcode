package math.excel_sheet_column;
/**
 * 
 * @author guoyifeng
 * Given a column title as appear in an Excel sheet, return its corresponding column number.

	For example:
	
	    A -> 1
	    B -> 2
	    C -> 3
	    ...
	    Z -> 26
	    AA -> 27
	    AB -> 28 
	    ...
	Example 1:
	
	Input: "A"
	Output: 1
	Example 2:
	
	Input: "AB"
	Output: 28
	Example 3:
	
	Input: "ZY"
	Output: 701
	 */
public class ExcelSheetColumnNumber {
	public int titleToNumber(String s) {
		char[] arr = s.toCharArray();
		int res = 0;
		int index = 0;
		for (int i = arr.length - 1; i >= 0; i--) {
			res += Math.pow(26, index) * parse(arr[i]);
			index++;
		}
		return res;
	}

	private int parse(char a) {
		return (int) (a - 65 + 1);
	}
}
