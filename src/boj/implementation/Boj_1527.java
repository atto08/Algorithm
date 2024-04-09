package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
실1 - 금민수의 개수
소요 시간: 10분
1) 메모리 초과
원인: A<= ~ <=B 범위의 모든 수를 문자열로 바꾸어 인덱스의 값을 4&7과 비교함
>> 모든 수를 문자열로 변환하는 것이 메모리 초과 야기

소요 시간: +14분
2) 해결
일 > 십 > 백 > 천 ... 자리 수 씩 체크하는 형식으로 전환
 */
public class Boj_1527 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int cnt = 0;
        for (int i = A; i <= B; i++) {
            if (isValid(i)) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    private static boolean isValid(int num) {
        while (num > 0) {
            int digit = num % 10;
            if (digit != 4 && digit != 7) {
                return false;
            }
            num /= 10;
        }
        return true;
    }
}

