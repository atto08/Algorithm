package programmers;

import java.util.HashMap;
/*
신고 결과 받기 - level1
소요 시간: 30분
 */
public class Program_92334 {

    public int[] solution(String[] id_list, String[] report, int k) {

        int L = id_list.length;
        HashMap<String, Integer> users = new HashMap<>();
        for (int i = 0; i < L; i++) users.put(id_list[i], i);

        boolean[][] reports = new boolean[L][L];
        int[] rCnt = new int[L];
        for (String now : report) {
            String[] r = now.split(" ");
            int from = users.get(r[0]), to = users.get(r[1]);

            if (!reports[from][to]) {
                reports[from][to] = true;
                rCnt[to]++;
            }
        }

        int[] result = new int[L];
        for (int i = 0; i < L; i++) {
            if (rCnt[i] >= k) {
                for (int j = 0; j < L; j++) if (reports[j][i]) result[j]++;
            }
        }

        return result;
    }
}
