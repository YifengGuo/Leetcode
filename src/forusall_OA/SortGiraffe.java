package forusall_OA;

public class SortGiraffe {
	public int partition(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int groupNum = 1;
		for (int i = 0; i < arr.length; i++) {
			int cur = arr[i];
			if (i == 0) {
				max = cur;
				min = cur;
				continue;
			}
			if (cur < min) {
				min = cur;
				continue;
			}
			if (cur > max) {
				max = cur;
				if (i - 1 >= 0 && arr[i - 1] > min) {
					min = arr[i - 1];
					groupNum += 1;
				}
				continue;
			}
		}
		return groupNum;
	}
	
	public static void main(String[] args) {
		int[] arr = {1,5,4,9,8,7,12,13,14};
		System.out.println(new SortGiraffe().partition(arr));
	}
}
