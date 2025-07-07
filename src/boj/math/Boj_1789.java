package boj.math;

import java.util.*;

/*
수들의 합 - 실5
소요시간 - 37분

문제설명:
- S == 서로 다른 N개의 자연수의 합. N의 최댓값을 구하기

풀이설명:
- 수학적 규칙 찾으며 접근.
- 1 ~ 2(2개) 1
- 3 ~ 5(3개) 2
- 6 ~ 9(4개) 3
- ...
- 190 ~ 209(19개) 19 --> 이 패턴이 보였음.

즉, 1 ~ N개의 자연수를 더하는 것이 그리디 ==> 최대 값 N개를 구성하는 경우이기 때문에,
해당 규칙으로 봐서는 반복문이 멈추는 N-2 값으로 정정해주면 알맞는 규칙 값이 생성됨.

p.s
오늘 아침에 못풀었다.
그래서 자기전에 푼다.
규칙은 빨리 찾았지만, 구현하면서 규칙 구현이 헷갈리면서 찾은 규칙에 대해 혼돈이 생겼다.
그래도 빨리 찾아서 덜 헤맸다. 다음에 이런문제는 20분 컷 해보자.
--> 그리디 + 수학 문제.
 */
public class Boj_1789 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long S = sc.nextLong();

        int N = 1;
        long sum = 0;

        while (true) {
            sum += N++;
            if (sum > S) {
                N -= 2;
                break;
            }
        }

        System.out.println(N);
    }
}