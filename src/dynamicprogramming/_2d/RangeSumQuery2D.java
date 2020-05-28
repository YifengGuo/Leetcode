package dynamicprogramming._2d;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guoyifeng on 5/28/20
 * <p>
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * <p>
 * Range Sum Query 2D
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.
 * <p>
 * Example:
 * Given matrix = [
 * [3, 0, 1, 4, 2],
 * [5, 6, 3, 2, 1],
 * [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5]
 * ]
 * <p>
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 * <p>
 * You may assume that the matrix does not change.
 * There are many calls to sumRegion function.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 *
 * solution:
 * https://leetcode.com/problems/range-sum-query-2d-immutable/discuss/572648/JavaC%2B%2B-Prefix-sum-with-Picture-explain-Clean-code
 */
public class RangeSumQuery2D {

    private int[][] matrix;

    private int[][] dp;

    public RangeSumQuery2D(int[][] matrix) {
        this.matrix = matrix;
        dp = new int[matrix.length + 1][matrix[0].length + 1];
        for (int i = 1; i <= matrix.length; ++i) {
            for (int j = 1; j <= matrix[0].length; ++j) {
                // sum[i][j] is sum of all elements from rectangle (0,0,i,j) as left, top, right, bottom corresponding
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        // Since `sum` start with 1 so we need to increase r1, c1, r2, c2 by 1
        row1++; row2++; col1++; col2++;
        return dp[row2][col2] - dp[row1 - 1][col2] - dp[row2][col1 - 1] + dp[row1 - 1][col1 - 1];
    }

    public static void main(String[] args) {
        int[][] matrix = new int[5][5];
        matrix[0] = new int[]{3, 0, 1, 4, 2};
        matrix[1] = new int[]{5, 6, 3, 2, 1};
        matrix[2] = new int[]{1, 2, 0, 1, 5};
        matrix[3] = new int[]{4, 1, 0, 1, 7};
        matrix[4] = new int[]{1, 0, 3, 0, 5};

        RangeSumQuery2D obj = new RangeSumQuery2D(matrix);
        printMatrix(matrix);
        System.out.println(obj.sumRegion(2, 1, 4, 3));
        printMatrix(matrix);
        System.out.println(obj.sumRegion(1, 1, 2, 2));
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
