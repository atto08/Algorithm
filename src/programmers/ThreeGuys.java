package programmers;
/*
삼총사 - level1
소요 시간: 12분

dfs로 방문 체크 구현
중복되는 문제가 발생함
>> dfs 반복문 안에 시작점을 node+1로 지정하여 중복경우 제거함
 */
public class ThreeGuys {
    static int result;
    static boolean[] visited;
    static int[] numbers;
    public int solution(int[] number) {
        numbers = number;

        for(int i = 0; i < number.length; i++){
            visited = new boolean[number.length];
            visited[i] = true;
            dfs(i, 1, numbers[i]);
        }

        return result;
    }

    private static void dfs(int node, int depth, int sum){

        if(depth == 3){
            if(sum == 0){
                result++;
            }
            return;
        }

        for(int i=node+1; i<numbers.length; i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(i, depth + 1, sum + numbers[i]);
                visited[i] = false;
            }
        }
    }
}
