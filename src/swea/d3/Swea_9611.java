package swea.d3;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;
/*
명진이와 동휘의 숫자맞추기 - D3
소요 시간: 20분
1) 6/10(pass)
원인: YES만 들어왔을 경우를 고려하지 않음

소요 시간: +10분
2) 8/10(pass)
원인: NO가 먼저 들어왔을 경우를 고려하지 않음

소요 시간: +8분
3) 9/10(pass)
원인: NO만 들어왔을 경우를 고려하지 않음

소요 시간: +12분
4) Pass

총평: 딴 생각 금지. 문제 풀 때는 집중하기.
 */
public class Swea_9611 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());

            StringTokenizer st;

            HashSet<Integer> yes = new HashSet<>();
            HashSet<Integer> no = new HashSet<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int[] numbers = new int[4];
                for (int j = 0; j < 4; j++) {
                    numbers[j] = Integer.parseInt(st.nextToken());
                }
                String result = st.nextToken();

                if (result.equals("YES")) {
                    HashSet<Integer> set = new HashSet<>();
                    for (int j = 0; j < 4; j++) {
                        if (!yes.contains(numbers[j]) && !no.contains(numbers[j])) {
                            yes.add(numbers[j]);
                        } else if (yes.contains(numbers[j]) && no.contains(numbers[j])) {
                            yes.remove(numbers[j]);
                        } else if (yes.contains(numbers[j]) && !no.contains(numbers[j])) {
                            set.add(numbers[j]);
                        }
                    }

                    if (!set.isEmpty()) {
                        yes.clear();
                        for (int num : set) {
                            yes.add(num);
                        }
                    }
                } else {
                    for (int j = 0; j < 4; j++) {
                        yes.remove(numbers[j]);
                        no.add(numbers[j]);
                    }
                }
            }
            int result = 0;
            if (yes.size() == 1) {
                for (int num : yes) {
                    result = num;
                }
            } else if (no.size() == 9) {
                int idx = 45;
                for (int num : no) {
                    idx -= num;
                }
                result = idx;
            }
            bw.write("#" + test_case + " " + result + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}