package boj.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Boj_21919 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean[] arr = new boolean[1000001];
        for (int i = 2; i < arr.length; i++) {
            if (!arr[i]) {
                for (int j = 2; i * j < arr.length; j++) {
                    arr[i * j] = true;
                }
            }
        }

        long result = 1;
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 수열 원소 중 소수가 중복돼서 들어오는 경우를 체크하지 않아 틀렸었음
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (!arr[num]) {
                set.add(num);
            }
        }

        for (int num : set)
            result *= num;

        if (result == 1)
            result = -1;

        System.out.println(result);
        br.close();
    }
}
