package programmers.lv3;
/*
숫자 게임 - lv3
소요시간 - 74분

문제 설명:
- B팀이 획득할 수 있는 최대 승점을 구하시오
- 숫자카드는 10억이하 자연수, 각사원 별 1회씩 경기
- A와 B팀의 숫자카드를 한번씩 비교하면서 대결 N번 진행
- 이기는 경우만 승점 1점 비김/패배하는 경우 0점
- A팀은 순서 고정. B팀 순서 변경 가능.

풀이 설명:
1차시도 - 40분(효율성 all Fail)
- A팀 B팀 모두 오름차순 정렬
- 작은 수부터 비교해서 더 큰경우만 사용 처리
원인:
    - visited[i] = true 인곳을 continue로 패스해도 그곳을 체크하는 건 변함 X
    - 즉, 이중포문으로 계속 N^2의 시간복잡도를 가짐.
    - 이게 가장 큰 시간 초과 발생원인 이라생각함.

2차시도 - 27분(Pass)
- 이중포문 X
- 오름차순으로 정렬된 카드는 작은수끼리 부터 비교함
- 즉, A에서 제일 작은 카드숫자에 사용할 수 없는 카드들은 이후 숫자카드에서도 사용불가
- 그렇기 때문에 Queue를 사용해 작은 수부터 뽑아서 비교(탐욕법)
    -> 효율성 테스트 시간초과 문제 해결!

p.s
생각보다 오래 헤매지않았다.
그래도 1시간안에 끝내자.
예제의 경우를 빨리 찾을 수 있어서 다행인 케이스였다.
-> 탐욕법 케이스
*/

import java.util.*;

public class Program_12987 {
    public static int solution(int[] A, int[] B) {
        int result = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        Queue<Integer> q = new LinkedList<>();
        for (int card : B) q.offer(card);
        for (int card : A) {
            if (q.isEmpty()) break;

            while (!q.isEmpty()) {
                int now = q.poll();
                if (now > card) {
                    result++;
                    break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{5, 1, 3, 7}, new int[]{2, 2, 6, 8}));
        System.out.println(solution(new int[]{2, 2, 2, 2}, new int[]{1, 1, 1, 1}));
    }
}