package GoldmanSachs;

import java.security.InvalidParameterException;

public class DotProductOfTwoArrays {
	public int dotProduct(int[] nums1, int[] nums2) {
		if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
			return 0;
		}
		if (nums1.length != nums2.length) {
			throw new InvalidParameterException();
		}
		int res = 0;
		for (int i = 0; i < nums1.length; i++) {
			res += nums1[i] * nums2[i];
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[] nums1 = new int[]{1,3,5,7,9};
		int[] nums2 = new int[]{2,4,6,8,10};
		int res = new DotProductOfTwoArrays().dotProduct(nums1, nums2);
		System.out.println(res);
	}
}
