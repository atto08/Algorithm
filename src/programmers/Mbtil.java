package programmers;

import java.util.HashMap;

// 시작 시간: 16:41
// 종료 시간: 17:58(22분 휴식)
// 총 소요 시간: 55분
public class Mbtil {
    static int[][] scores = new int[4][2];
    static char[][] mbti = {{'R', 'T'}, {'C', 'F'}, {'J', 'M'}, {'A', 'N'}};

    public String solution(String[] survey, int[] choices) {

        HashMap<Character, int[]> hashMap = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                hashMap.put(mbti[i][j], new int[]{i, j});
            }
        }

        for (int i = 0; i < survey.length; i++) {
            char now;
            int score;

            if (choices[i] - 1 < 3) {
                now = survey[i].charAt(0);
                if (choices[i] - 1 == 0) {
                    score = 3;
                } else if (choices[i] - 1 == 1) {
                    score = 2;
                } else {
                    score = 1;
                }
                int[] xy = hashMap.get(now);

                int x = xy[0], y = xy[1];
                scores[x][y] += score;

            } else if (choices[i] - 1 > 3) {
                now = survey[i].charAt(1);

                if (choices[i] - 1 == 6) {
                    score = 3;
                } else if (choices[i] - 1 == 5) {
                    score = 2;
                } else {
                    score = 1;
                }
                int[] xy = hashMap.get(now);

                int x = xy[0], y = xy[1];
                scores[x][y] += score;
            }
        }

        String result = "";
        for (int i = 0; i < 4; i++) {
            if (scores[i][0] >= scores[i][1]) {
                result += mbti[i][0];
            } else {
                result += mbti[i][1];
            }
        }

        return result;
    }
}
