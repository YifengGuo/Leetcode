package forusall_OA;

// 1       5     4     9     8    7    12    13    14
//                                           min         
//                                                 max
//                                           cur
//  4     3     2     6     1
//             min
//                    max
//                    cur
public class SortGiraffe {
	public int partition(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int max_idx = 0;
		int min_idx = 0;
		int groupNum = 1;
		for (int i = 0; i < arr.length; i++) {
			System.out.println(min + " " + max);
			int cur = arr[i];
			if (i == 0) {
				max = cur;
				min = cur;
				max_idx = 0;
				min_idx = 0;
				continue;
			}
			if (cur < min) {
				min = cur;
				min_idx = i;
				if (min_idx > max_idx) {
					groupNum--;
				}
				continue;
			}
			if (cur > max) {
				max = cur;
				max_idx = i;
				groupNum++;
			}

		}
		return groupNum < 0 ? 1 : groupNum;
	}

	public static void main(String[] args) {
		// int[] arr = {1,5,4,9,8,7,12,13,14};
		// int[] arr = {4,3,2,6,1};
		int[] arr = {1,2,3,4,5,6,7,8};
		// int[] arr = { 3, 5, 4, 2, 9, 8, 7, 14, 13, 12 };
		System.out.println(new SortGiraffe().partition(arr));
	}
}
