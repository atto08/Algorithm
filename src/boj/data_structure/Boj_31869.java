package boj.data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;
/*
선배님 밥 사주세요! - 실3
소요 시간: 13분
1) 틀렸습니다. (1%)
원인: 연속으로 밥을 얻어먹는 최대 일. >> 을 놓치고 그냥 밥만 얻어먹는 모든 경우를 day++로 추가했었음.
해결: 얻어먹을 수 있는 (visited[w][d])인 경우가 연속으로 있는 day 중 최대 일자보다 값이 크면 변경하는 식으로 코드 구현

소요 시간: +16분
2) 통과
 */
public class Boj_31869 {
    static HashMap<String, WDP> hashMap = new LinkedHashMap<>();
    static boolean[][] visited = new boolean[11][7];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            int week = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());
            int pay = Integer.parseInt(st.nextToken());

            hashMap.put(name, new WDP(week, day, pay));
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            int money = Integer.parseInt(st.nextToken());

            WDP now = hashMap.get(name);
            if (money >= now.p && !visited[now.w][now.d]) {
                visited[now.w][now.d] = true;
            }
        }
        // 연속.. 으로 얻어먹는..
        int max = 0, day = 0;
        for (int w = 1; w <= 10; w++) {
            for (int d = 0; d <= 6; d++) {
                if (visited[w][d]) {
                    day++;
                    if (max < day) {
                        max = day;
                    }
                } else {
                    day = 0;
                }
            }
        }

        System.out.println(max);
    }

    static class WDP {
        int w, d, p;

        private WDP(int w, int d, int p) {
            this.w = w;
            this.d = d;
            this.p = p;
        }
    }
}
