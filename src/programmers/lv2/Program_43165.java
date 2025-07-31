package programmers.lv2;
/*
2회차 풀이
타겟넘버 - lv2
소요시간 - 12분

풀이설명:
깊이 우선 탐색으로 +,-가되는 모든 경우를 확인

p.s
- 처음 dfs/bfs 문제 풀때 이해하느라 3일은 걸렸던 문제.
- 이제는 당연하게 배운대로 풀고있다. 이전풀이와 똑같다.
- 이번주 쉬운문제만 짜여있어서 다 풀기는하는데, 개념 연습한다는 생각으로 제대로하자.
*/

public class Program_43165 {
    static int answer;

    public static int solution(int[] numbers, int target) {
        answer = 0;
        dfs(numbers, target, 0, 0);
        return answer;
    }

    private static void dfs(int[] numbers, int target, int node, int sum) {
        if (node == numbers.length) {
            if (sum == target) answer++;
            return;
        }
        dfs(numbers, target, node + 1, sum + numbers[node]);
        dfs(numbers, target, node + 1, sum - numbers[node]);
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 1, 1, 1, 1}, 3));
        System.out.println(solution(new int[]{4, 1, 2, 1}, 4));
    }
}
