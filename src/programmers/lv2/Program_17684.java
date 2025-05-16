package programmers.lv2;

/*
[3차]압축 - lv2
소요시간 - 58분

풀이설명:
- 주어진 문자열을 압축하여 사전 색인 번호를 배열로 출력


- 현재 인덱스 길이 i+1 ~ w까지 확인하며 되는 곳 까지만.
- 이후 되는 곳 이후의 인덱스 부터.

*/
import java.util.*;

public class Program_17684 {
    static String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    public static int[] solution(String msg) {

        HashMap<String, Integer> dic = new LinkedHashMap<>();
        int num = 1;    // 색인 번호
        for(String a : alphabet) {
            dic.put(a, num);
            num++;
        }
        // -- 길이가 1인 사전 모두 들어있음. --

        Queue<int[]> q = new LinkedList<>();
        int idx = 0;    // 확인하려는 인덱스
        int cnt = 0;    // 결과의 갯수
        StringBuilder sb;

        while(idx < msg.length()) { // 문자열의 길이보다 작을때 까지만 확인 및 비교

            sb = new StringBuilder().append(msg.charAt(idx)); // 문자열 초기화
            int p = dic.get(sb.toString());     // 현재 문자열의 색인번호
            int addIdx = 1;
            for(int i = idx + 1; i < msg.length(); i++) {
                sb.append(msg.charAt(i));
                if(!dic.containsKey(sb.toString())) {   // 사전에 문자열 존재X
                    dic.put(sb.toString(), num);        // 사전에 색인번호와 함께 입력
                    num++;
                    break;
                } else {    // 사전에 문자열 존재O
                    p = dic.get(sb.toString());         // 출력할 색인번호 변경
                    addIdx++;
                }
            }
            q.offer(new int[]{p, cnt});
            cnt++;
            idx += addIdx;
        }

        int[] answer = new int[cnt];
        while(!q.isEmpty()) {
            int[] now = q.poll();
            answer[now[1]] = now[0];
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution("KAKAO")));
        System.out.println(Arrays.toString(solution("TOBEORNOTTOBEORTOBEORNOT")));
        System.out.println(Arrays.toString(solution("ABABABABABABABAB")));
        System.out.println(Arrays.toString(solution("THATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITISTHATTHATISISTHATTHATISNOTISNOTISTHATITITIS")));
    }
}