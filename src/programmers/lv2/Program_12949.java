package programmers.lv2;

import java.util.Arrays;

/*
행렬곱셈 - lv2
소요시간 - 약 50분

풀이설명:
- 아래에서 찾은 규칙 토대로 반복문 간단구현
- arr1행과 arr2열은 같은 길이(갯수)
- i,j에 대해서 arr1의 x는 i로 arr2의 y는 j로 지정하고 arr1[x] 길이만큼 반복문동안
- sum += arr1[x][i] * arr2[i][y]

arr1
// >방향 y1
2(00) 3(01) 2(02)  V방향 x1
4(10) 2(11) 4(12)
3(20) 1(21) 4(22)
arr2
> 방향 y2
5(00) 4(01) 3(02)  V방향 x2
2(10) 4(11) 1(12)
3(20) 1(21) 1(22)

-> 10 +  6 +  6
->  8 + 12 +  2
->  6 +  3 +  2

-> 20 +  4 + 12

p.s
- 문제는 쉬웠다.
- 근데 행렬곱셈의 계산을 어떻게 하는지 이해하는데 좀 걸렸다.
- + 배열의 크기를 잘못 설정했었다.. 후.. 괜히 calculate 메서드에서 반복문만 건드렸네..
*/
public class Program_12949 {
    public static int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] result = new int[arr1.length][arr2[0].length];

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2[0].length; j++) {
                result[i][j] = calculate(arr1, arr2, i, j);
            }
        }

        return result;
    }

    private static int calculate(int[][] arr1, int[][] arr2, int x, int y) {
        int sum = 0;
        // arr1[x][] 와 arr2[][y]
        for (int i = 0; i < arr1[x].length; i++) {
            sum += arr1[x][i] * arr2[i][y];
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[][]{{1, 4}, {3, 2}, {4, 1}}, new int[][]{{3, 3}, {3, 3}})));
        System.out.println(Arrays.toString(solution(new int[][]{{2, 3, 2}, {4, 2, 4}, {3, 1, 4}}, new int[][]{{5, 4, 3}, {2, 4, 1}, {3, 1, 1}})));
    }
}