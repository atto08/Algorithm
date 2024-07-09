package boj.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
A와B 2 - 골5
소요 시간: 15분

1) 시간 초과(15%)
원인: 깊이 우선 탐색 + 중복되는 경우 발생

소요시간: + 10분
2) 메모리 초과(10%)
원인: 너비우선탐색 경우 똑같은 경우가 들어가는 상황이 발생하는 것으로 인한 중복 경우가 존재하기 때문.
해결: 역으로 T -> S로 가는 경우로 전환

1. set을 이용해 String의 중복경우를 제거
2. T -> S를 찾는 방법으로 시간 단축.
소요 시간: + 30분
3) 통과

거꾸로 경우 생각하보자! 또 이런 사고 방식이 필요했다.
 */
public class Boj_12919 {
    static int result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String S = sc.next();
        String T = sc.next();

        bfs(T, S);
        System.out.println(result);
    }

    private static void bfs(String T, String S) {

        Queue<String> q = new LinkedList<>();
        HashSet<String> visit = new HashSet<>();
        q.offer(T);
        visit.add(T);

        while (!q.isEmpty()){
            String now = q.poll();

            if (now.equals(S)){
                result = 1;
                return;
            }

            if (now.charAt(now.length()-1) == 'A' && now.length() >= S.length()){
                String next = now.substring(0, now.length()-1);
                if(!visit.contains(next)) q.offer(next);
            }

            if (now.charAt(0) == 'B' && now.length() >= S.length()){
                String next = new StringBuilder(now.substring(1)).reverse().toString();
                if (!visit.contains(next)) q.offer(next);
            }
        }
    }
}
