package boj.greedy;


/*
수 묶기 - 골4
소요시간 - 60분

문제설명:
- N수열 존재. 1회 묶거나 묶지않은 상태에서 수열의 합이 최대가 되는 경우 구하기.

풀이설명
- 오름차순 정렬.
- 음수가 홀수개면 작은수들끼리 묶고 제일 큰수는 내비두기.
- 짝수개면 순서대로 묶기.

* 무조건 큰수끼리 묶거나 작은수끼리 묶어야 최대합이 나올수 있음.
음수는 0과 곱하면 좋음 --> 0이되니까
양수는 0과 곱하면 안좋음

결론
- 양수는 0과 음수의 갯수에 상관없이 0, 음수와 묶어서는 안된다.

- 음수는 짝수개면 지들끼리만 생각하면 됨.
- 대신 홀수개면 0이 존재하면 좋은거임. 그 중 제일 큰 수는 0이랑 만나서 소거하면 좋기 때문


p.s
- 최댓값이 될수 있는 경우를 최소한으로 잘 구분짓지않았나 생각한다.
- 집중력이 좀 떨어지긴했었는데, 그래도 스스로 경우 잘 생각해서 풀었다 생각한다.
- 나야 상반기까지 달려보자. 제대로. 후회없이.
*/

import java.util.*;

public class Boj_1744 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] numbers = new int[N];

        int m = 0, p = 0, z = 0; // 음수(m), 양수(p) 갯수
        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
            if (numbers[i] < 0) {
                m++;
            } else if (numbers[i] > 0) {
                p++;
            } else {
                z++;
            }
        }

        Arrays.sort(numbers);

        boolean[] visited = new boolean[N];
        int max = 0;

        // 양수 검사
        for (int i = N - 1; i >= N - p; i--) {
            if (visited[i]) continue;

            visited[i] = true;
            if (i - 1 >= N - p) {
                if (numbers[i] == 1 || numbers[i - 1] == 1) { // 1과 함께 있다면 곱하는 것보다 따로따로 더하는 것이 더 큰 값 나옴.
                    max += numbers[i];
                } else {    // 1보다 큰 수들은 곱하는 것이 가장 큰 수가 됨
                    visited[i - 1] = true;
                    max += numbers[i] * numbers[i - 1];
                }
            } else {
                max += numbers[i];
            }
        }

        // 음수 검사
        for (int i = 0; i < m; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            if (i + 1 < m) {
                visited[i + 1] = true;
                max += numbers[i] * numbers[i + 1];
            } else {
                if (z == 0) { // 0이 있으면 0이라 덧셈이 필요없지만, 있다면 그냥 덧셈을 해야함
                    max += numbers[i];
                }
            }
        }

        System.out.println(max);
    }
}