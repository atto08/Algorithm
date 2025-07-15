package programmers.lv1;
/*
[PCCE 기출문제] 9번 / 지폐 접기 - lv1
소요시간 - 7분

문제설명:
- 지폐가 지갑에 들어가기 위해 최소 몇번 접어야하는지 구하기.

풀이설명:
- 1. 지갑과 지폐 정렬하기
    - 1. 지폐 정렬하기
    - 2. 뒷쪽(큰 수) bill[1]을 2로 나누기.
    - 3. wallet[0] >= bill[0] && wallet[1] >= bill[1] 이면 stop

p.s
- 문제 설명 길이에 비해 되게 단순한 문제였다고 생각이 들었음.
- 어제에 이어 오늘도 규칙이 바로 보이는 문제는 빨리 푸는게 당연한듯.
- 부족한 알고리즘 풀이 연습하자.
*/

import java.util.*;

public class Program_340199 {
    public static int solution(int[] wallet, int[] bill) {
        int result = 0;

        Arrays.sort(wallet);
        Arrays.sort(bill);
        while (wallet[0] < bill[0] || wallet[1] < bill[1]) {
            bill[1] /= 2;
            Arrays.sort(bill);
            result++;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{30, 15}, new int[]{26, 17}));
        System.out.println(solution(new int[]{50, 50}, new int[]{100, 241}));
    }
}
