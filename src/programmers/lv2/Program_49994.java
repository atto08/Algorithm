package programmers.lv2;
/*
방문 길이 - lv2
소요시간 - 63분

풀이설명:
1) U:0, D:1, R:2, L:3
2) 음수부분 배열만큼 양수로 밀기
    - (-5, -5) >> (0, 0)
    - (0, 0) >> (5, 5)
    - (5, 5) >> (10, 10)
- 현재 위치 & 방문예정 위치 -> 방문O 노카운트

놓친 부분 -
- 현재 코드에서는 방문처리를 해도 꼭짓점(x1,y1)과 꼭짓점(x2,y1)의 시작 끝은 필요없고 둘이 이어졌는지의 여부가 중요한 단계.
- 즉, 지나간 선분은 카운트 무시 & 지나가지 않은 선분은 카운트
- set에 (시작 + 목표) 좌표를 문자열로 만들어 선분을 넣기
    - set에 없으면 지나지 않은 선분 & 있다면 지나간 선분

p.s
- 1시간 조금 넘었는데 꽤 발전한 듯.
- lv2니까 방심하지 말자고요. (실제 코테는 더 어렵다구요.)
*/

import java.util.*;

public class Program_49994 {
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static int solution(String dirs) {

        HashSet<String> set = new HashSet<>();
        Queue<VL> q = new LinkedList<>();
        q.offer(new VL(5, 5));

        int answer = 0;
        for (char c : dirs.toCharArray()) {
            VL cur = q.poll();
            // 인덱스로 변경
            int i = changeToNum(c);

            // 다음 위치
            int nx = cur.x + dx[i];
            int ny = cur.y + dy[i];

            // 범위 넘어가는 경우는 무시
            if (nx < 0 || ny < 0 || nx > 10 || ny > 10) {
                q.offer(new VL(cur.x, cur.y));
                continue;
            }
            // 선분 문자열 만들기
            String str = buildStr(new int[]{cur.x, cur.y, nx, ny});
            String rev = buildStr(new int[]{nx, ny, cur.x, cur.y});

            // 방문한 선분인지 체크
            if (!set.contains(str) || !set.contains(rev)) answer++;
            // 선분 넣기
            set.add(str);
            set.add(rev);
            q.offer(new VL(nx, ny));
        }

        return answer;
    }

    private static int changeToNum(char c) {
        if (c == 'U') {
            return 0;
        } else if (c == 'D') {
            return 1;
        } else if (c == 'R') {
            return 2;
        }
        return 3;
    }

    private static String buildStr(int[] arr) {
        StringBuilder str = new StringBuilder();
        for (int n : arr) str.append(n);
        return str.toString();
    }

    static class VL {
        int x, y;

        public VL(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        System.out.println(solution("ULURRDLLU"));
        System.out.println(solution("LULLLLLLU"));
    }
}
