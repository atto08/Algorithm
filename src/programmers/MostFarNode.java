package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
가장 먼 노드 - level3

소요 시간: 27분
1) 메모리 초과
>> 이중 배열 or node를 받는 Q의 크기 문제로 생각됨

소요 시간: + 3분
>> 이중 배열 graph를 인접리스트로 변환
2) 통과
 */
public class MostFarNode {
    static int max, result;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    static int[] nodeValue;

    public int solution(int n, int[][] edge) {

        graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edge.length; i++) {
            int k = edge[i][0];
            int v = edge[i][1];
            // 양 방향 설정
            graph.get(k).add(v);
            graph.get(v).add(k);
        }

        bfs(n);

        return result;
    }

    private static void bfs(int n) {
        nodeValue = new int[n + 1];
        visited = new boolean[n + 1];

        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        visited[1] = true;
        nodeValue[1] = 0;

        while (!q.isEmpty()) {
            int node = q.poll();
            for (int num : graph.get(node)) {
                if (!visited[num]) {
                    visited[num] = true;
                    nodeValue[num] = nodeValue[node] + 1;
                    if (max < nodeValue[num]) {
                        max = nodeValue[num];
                    }
                    q.offer(num);
                }
            }
        }

        Arrays.sort(nodeValue);
        for (int i = n; i > 0; i--) {
            if (max == nodeValue[i]) {
                result++;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        MostFarNode node = new MostFarNode();
        System.out.println(node.solution(6, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}));
    }
}
