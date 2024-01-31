package boj.str;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*
ex) 경우를 생각해야됨
1
R
0
[]
 */
public class Boj_5430 {
    static boolean r, d;
    static Deque<Integer> deque;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            r = false;
            d = false;
            char[] order = br.readLine().toCharArray();
            int N = Integer.parseInt(br.readLine());

            String input = br.readLine();
            int[] array = parseArray(input);

            deque = new ArrayDeque<>();
            for (int num : array) {
                deque.offer(num);
            }

            for (char c : order) {
                if (c == 'R') {
                    r = !r;
                } else {
                    D();

                    if (d) {
                        break;
                    }
                }
            }

            if (deque.isEmpty()) {
                if (d) {
                    bw.write("error\n");
                } else {
                    bw.write("[]\n");
                }
            } else {
                StringBuilder result = new StringBuilder();
                result.append("[");
                if (r) {
                    while (!deque.isEmpty()) {
                        result.append(deque.pollLast());
                        if (deque.size() > 0) {
                            result.append(",");
                        }
                    }
                } else {
                    while (!deque.isEmpty()) {
                        result.append(deque.pollFirst());
                        if (deque.size() > 0) {
                            result.append(",");
                        }
                    }
                }
                result.append("]");
                bw.write(result + "\n");
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }

    public static void D() {
        if (deque.isEmpty()) {
            d = true;

        } else {
            if (r) {
                deque.pollLast();
            } else {
                deque.pollFirst();
            }
        }
    }

    public static int[] parseArray(String input) {

        String[] numberStrings = input.replaceAll("[^0-9,]", "").split(",");
        int[] result;

        try {
            result = Arrays.stream(numberStrings)
                    .mapToInt(Integer::parseInt)
                    .toArray();

        } catch (NumberFormatException ignored) {
            result = new int[]{};
        }

        return result;
    }
}
