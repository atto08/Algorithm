package boj.implementation;

/*
빙고 - 실4
소요시간 - 46분

풀이설명:
- 빙고가 되는 경우 12가지 bingo[][]
- 번호 하나하나 부를때마타 set에 빙고위치를 넣어 확인하기.

00 01 02 03 04
10 11 12 13 14
20 21 22 23 24
30 31 32 33 34
40 41 42 43 44

p.s
- 구현하는데 생각보다 시간을 많이썼다고 생각한다.
- 노가다 작업으로 bingo를 만들었는데, 다른 규칙을 이용할 수 있지않았을까 싶고, 5*5라는 조건과 bingo 라인이 12가지였기 때문에 가능한 시도였다고 생각함.
 */

import java.io.*;
import java.util.*;

public class Boj_2578 {
    static String[][] bingo = {{"00", "01", "02", "03", "04"}, {"10", "11", "12", "13", "14"},
            {"20", "21", "22", "23", "24"}, {"30", "31", "32", "33", "34"}, {"40", "41", "42", "43", "44"},
            {"00", "10", "20", "30", "40"}, {"01", "11", "21", "31", "41"}, {"02", "12", "22", "32", "42"},
            {"03", "13", "23", "33", "43"}, {"04", "14", "24", "34", "44"}, {"00", "11", "22", "33", "44"},
            {"40", "31", "22", "13", "04"}};
    static boolean isBingo = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, String> map = new HashMap<>();
        StringTokenizer st;
        StringBuilder sb;
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                int num = Integer.parseInt(st.nextToken());
                sb = new StringBuilder().append(i).append(j);
                map.put(num, sb.toString());
            }
        }

        int result = 0;
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                int num = Integer.parseInt(st.nextToken());
                set.add(map.get(num));
                if (isBingo) continue;

                isBingo(set);
                result++;
            }
        }

        System.out.println(result);
    }

    private static void isBingo(HashSet<String> set) {
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int cnt1 = 0;
            for (int j = 0; j < 5; j++) {
                if (set.contains(bingo[i][j])) {
                    cnt1++;
                }
            }

            if (cnt1 == 5) sum++;
        }
        if (sum >= 3) isBingo = true;
    }
}