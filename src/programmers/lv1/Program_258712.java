package programmers.lv1;

import java.util.Arrays;
import java.util.HashMap;

/*
가장 많이받은 선물 - lv1
소요시간 - 40분

[문제 설명]
A와 B간에 선물을 주고받은 기록
주고 받은 수 동일(==) or 기록 존재 X
선물지수가 더 큰 사람이 작은사람에게 선물 하나 받음
    - 선물 지수 = 이번 달까지 (선물 한 선물 수) - (받은 선물)

목표: 가장 선물을 많이 받을 친구의 받을 선물의 수를 구하기

[풀이 설명]
해시 맵으로 친구이름 별 번호부여
key : value
이름 : 친구번호

int N = friends.length
int[][] graph = new int[N][N] -> 2차원 배열로 주고받은 선물 정보 기억하기
해시맵 사용
graph[A][B] A가 B에게 선물 준 횟수

0 1 2 3
1 2 3
2 3
3
순으로 확인

p.s
- 정답률 보고 쫄았음.
- 돌아갈 뻔했는데, 예제보고 이차원 배열로 정보를 갖고있으면 편하겠다는 생각이 들고 풀이설명대로 구현
*/
public class Program_258712 {
    public int solution(String[] friends, String[] gifts) {
        // 선수별 번호 기억
        HashMap<String, Integer> fn = new HashMap<>();
        int N = friends.length;
        for (int i = 0; i < N; i++) {
            fn.put(friends[i], i);
        }

        // 선수 간 선물 주고받기 체크
        int[][] graph = new int[N][N];
        for (String info : gifts) {
            String[] names = info.split(" ");
            int A = fn.get(names[0]);
            int B = fn.get(names[1]);
            graph[A][B]++;
        }

        int[] gift = new int[N]; // 선물지수
        // 선물지수 계산
        for (int i = 0; i < N; i++) {
            int receive = 0, give = 0;
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                receive += graph[j][i];
                give += graph[i][j];
            }
            gift[i] = give - receive;
        }

        int[] result = new int[N]; // 다음달 받을 선물 수
        // 준선물과 받은선물 비교
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                int r = graph[j][i];
                int g = graph[i][j];

                if (g > r) {
                    result[i]++;
                } else if (g < r) {
                    result[j]++;
                } else { // 주고받은 선물 동률 or 없는 경우
                    // 선물지수가 다른 경우
                    if (gift[i] > gift[j]) {
                        result[i]++;
                    } else if (gift[i] < gift[j]) {
                        result[j]++;
                    }
                }
            }
        }

        Arrays.sort(result);
        return result[N - 1];
    }
}