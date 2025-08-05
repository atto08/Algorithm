package boj.sort;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
수 찾기 - 실4
소요시간 - 측정 X

풀이설명:
- 이분탐색 풀이

 */
public class Boj_1920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int num;
        for (int i = 0; i < M; i++) {
            num = Integer.parseInt(st.nextToken());
            bw.write(binarySearch(num, numbers) + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static int binarySearch(int target, int[] numbers) {

        int left = 0, right = numbers.length - 1;
        int result = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (numbers[mid] == target) {
                result = 1;
                break;
            } else if (target < numbers[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }
}
