package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
수들의 합2 - 실4
소요시간 - 60분?

풀이설명:
- start = 0, end = 0
- sum <= M : sum += A[++end]; end 다음 숫자 더하기
- sum > M : sum -= A[start++]; 현재 start 숫자 빼기
- start < N , end < N

봉착한 난관 -
start와 end 둘다 < N 인 경우만 생각했는데
예제 중에 인덱스 7~9의 합은 4가 나옴.
-> 4 < 5(M) 임 그러면 나는 +A[++end] 하기 때문에 ArrayIndexOutOfBounds 예외가 발생해버림.
근데 이런 경우엔 어차피 뒤에 더할 숫자도 없고, 앞에 숫자는 빼기만 해보기 때문에 M을 넘는 숫자의 합이 나올 수 없다.
즉, 이때 멈춰 버리면 된다.

p.s
- 다른문제(두 수의 합) 풀어봤는데 이해가 안돼서 이거 다시 품.
- 손으로 예제 경우를 생각하면 조건문을 만들어봄.
- 이제야 좀 이해가 됨.
- 나의 손은 위대하다.
 */
public class Boj_2003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

        int cnt = 0, start = 0, end = 0;

        long sum = 0;
        while (true) {
            if (sum >= M) {
                if (sum == M) cnt++;
                sum -= A[start++];
            } else if (end == N) {
                break;
            } else {
                sum += A[end++];
            }
        }
//        밑에 껄로 해도 됨. 시작 순서차이임.
//        long sum = A[0];
//        while (true) {
//            try {
//                if (sum > M) {
//                    sum -= A[start++];
//                } else if (sum < M) {
//                    sum += A[++end];
//                } else {
//                    cnt++;
//                    sum += A[++end];
//                }
//            } catch (ArrayIndexOutOfBoundsException e) {
//                break;
//            }
//        }

        System.out.println(cnt);
    }
}
