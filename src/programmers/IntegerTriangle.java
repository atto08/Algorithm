package programmers;

/*
정수 삼각형 - level3
소요 시간: 30분
1) 통과
>> Bottom - Up 방식으로 메모이제이션 구현
 */
public class IntegerTriangle {
    public int solution(int[][] triangle) {
        int cnt = triangle.length - 2;
        for (int i = cnt; i >= 0; i--) {
            for (int j = 0; j < triangle[i].length; j++) {
                triangle[i][j] += Math.max(triangle[i + 1][j], triangle[i + 1][j + 1]);
            }
        }

        return triangle[0][0];
    }

    public static void main(String[] args) {
        IntegerTriangle it = new IntegerTriangle();
        System.out.println(it.solution(new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}}));
    }
}
