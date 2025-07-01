package boj.str;

import java.util.*;

/*
전화번호 목록 - 골4
소요시간 - 1시간 초과

문제설명:
- 전화번호 목록의 일관성 여부를출력하기.
- 일관성이 없는 경우: 한번호 == 다른번호 접두어

풀이설명:
- 번호 인덱스별 계단 만들어놓기.
    - ArrayList에 0 ~ 9계단 만들기
    - 길이가 긴 전화번호에서 부터 입력 -> 짧은 전화번호는 이미 입력된 값임 -> 접두어 존재여부 판단.

문제 - long으로 바꿔 받으면 0으로 시작하는 번호는 0이 사라짐.

p.s
+ String 클래스에는 접두어와 일치여부를 판별할 수 있는 startsWith메서드가 존재함.
이거 쓰면 그냥 쉽게 해결가능했음..

*/

public class Boj_5052 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        while (T-- > 0) {
            int N = sc.nextInt();
            String[] numbers = new String[N];

            for (int i = 0; i < N; i++) numbers[i] = sc.next();

            Arrays.sort(numbers);

            boolean isConsistent = true;
            for (int i = 0; i < N - 1; i++) {
                if (numbers[i + 1].startsWith(numbers[i])) {
                    isConsistent = false;
                    break;
                }
            }

            System.out.println(isConsistent ? "YES" : "NO");
        }
    }
}