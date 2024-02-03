package boj.dp;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
맞췄는데, 뭔가 더 빠른 방향이 있을거같아서 찾아봄
Bottom-up 방식의 DP - > 을 사용하는 거 같기는 한데 해당 값을 직접 저장하고 있지는 않음.
==> 결과적으로 시간이 더 소요 됨.
아래처럼 아예 N값에 맞는 값을 N의 작은 값 경우부터 저장. - 규칙 찾기 중요
        for (int i = 2; i <= X; i++) {
            dp[i] = dp[i - 1] + 1;  // 1을 빼는 경우

            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);  // 2로 나누는 경우
            }

            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);  // 3으로 나누는 경우
            }
        }

        System.out.println(dp[X]);
 */
public class Boj_1463 {
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int result = 0;

        if (N != 1) {
            calculationX(N);

            if (queue.contains(1)) {
                result = 1;
            } else {
                int count = 1;
                while (!queue.contains(1)) {
                    count++;
                    int size = queue.size();

                    for (int i = 0; i < size; i++) {
                        int num = queue.poll();
                        calculationX(num);
                    }
                }
                result = count;
            }
        }

        System.out.println(result);
        sc.close();
    }

    public static void calculationX(int n) {

        int num;
        if (n % 3 == 0) {
            num = n / 3;
            queue.offer(num);
        }

        if (n % 2 == 0) {
            num = n / 2;
            queue.offer(num);
        }

        queue.offer(n - 1);
    }
}
