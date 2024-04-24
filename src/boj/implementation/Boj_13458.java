package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
시험 감독 - 브2
소요 시간: 33분 +3분
1) 틀렸습니다
>> 최소 감독의 수가 int의 범위를 넘어갈 수 있는데 최소 감독의 수 sum을 int타입으로 선언함
ex)
n=1,000,000
모든 ai=1,000,000 이고
b=1,c=1 일때,
1,000,000 * 1,000,000 = 1,000,000,000,000 >> int의 최대값인 약 21억 보다 훨씬 큰 값이 나올 수 있다.
 */
public class Boj_13458 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 시험장 갯수 N
        int N = Integer.parseInt(br.readLine());
        // 시험장 응시자 수
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        // 감독관이 감독할 수 있는 응시자 수
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long sum = 0;
        for (int i = 0; i < N; i++) {
            int a = A[i] - B; // 총감독관이 감독하는 응시자를 제외한 응시자
            int num = 1; // 총감독관 1명
            if (a > 0) {
                num += (int) Math.ceil((double) a / C);
            }
            sum += num;
        }

        System.out.println(sum);
    }
}
