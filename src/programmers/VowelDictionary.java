package programmers;
/*
모음 사전 - level2
소요 시간: 약 30분
노드를 깊이 우선 탐색으로 방문하도록 구현할 수 있는지를 보는 문제 - 백트래킹으로 구현
 */
public class VowelDictionary {
    static int cnt, result;
    static char[] c = { 'A', 'E', 'I', 'O', 'U' };
    static String word;

    public int solution(String str) {

        word = str;
        for (int i = 0; i < 5; i++) {
            dfs(i, 0, new StringBuilder().append(c[i]));
        }

        return result;
    }

    public static void dfs(int node, int depth, StringBuilder sb) {
        cnt++;
        if(sb.toString().equals(word)) {
            result = cnt;
        }

        if(depth == 4) {
            return;
        }

        else if(depth < 4) {
            for(int i=0; i<5; i++) {
                sb.append(c[i]);
                dfs(i,depth+1, sb);
                sb.delete(sb.length()-1, sb.length());
            }
        }
    }
}
