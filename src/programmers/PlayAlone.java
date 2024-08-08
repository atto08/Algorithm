package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
혼자 놀기의 달인 - level2
소요 시간: 45분

1) 40점
원인: 문제를 잘못 이해함. 문제에서 상자는 임의로 뽑는다.! >> 상자번호 순서대로 뽑는다고 생각하고 문제를 풀었음
해결: 모든 그룹의 점수를 list로 전부 받아 그 중 최댓값 두개를 산출

2) 90점
원인: 그룹 상자의 갯수가 2개이상 일 거라는 보장이 없음.
해결: 그룹 상자의 갯수가 2개 미만이면 무조건 0을 리턴.

3) 100점
 */
public class PlayAlone {
    static int[] cardArr;
    static boolean[] visited;
    static List<Integer> scores = new ArrayList<>();

    public int solution(int[] cards) {

        cardArr = new int[cards.length + 1];
        visited = new boolean[cards.length + 1];
        for(int i = 0; i < cards.length; i++) cardArr[i + 1] = cards[i];

        for(int i = 1; i < cardArr.length; i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(i, 1);
            }
        }

        scores.sort(Collections.reverseOrder());

        if(scores.size() < 2) return 0;

        return scores.get(0) * scores.get(1);
    }

    private static void dfs(int node, int depth){
        int num = cardArr[node];

        if(visited[num]) {
            scores.add(depth);
            return;
        }

        visited[num] = true;
        dfs(num, depth + 1);
    }

    public static void main(String[] args) {
        PlayAlone playAlone = new PlayAlone();

        System.out.println(playAlone.solution(new int[]{8,6,3,7,2,5,1,4}));
    }
}
