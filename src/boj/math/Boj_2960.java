package boj.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// while 문 사용 시 시간초과 2중 for 문은 통과. why?
public class Boj_2960 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[] arr = new boolean[N + 1];
        arr[0] = arr[1] = true;
        int count = 0;

        for (int i = 2; i <= N; i++) {
            /*
            if (!arr[i]) { ==> 이거 없는게 더 빠른데 이유는 모르겠음.
             */
                // 이부부에서 증감하는 부분 응용 새로배움
                for (int j = i; j <= N; j += i) {
                    if (!arr[j]) {
                        arr[j] = true;
                        count++;
                        if (count == K) {
                            System.out.println(j);
                            break;
                        }
                    }
                }
            //}
        }
        br.close();
    }
}
