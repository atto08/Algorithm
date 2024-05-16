package boj.implementation;

import java.io.*;
/*
iSharp - 실3
소요 시간: 21분

1) 틀렸습니다.
원인: 변수명의 길이가 1일거라고 생각함

소요 시간: +8분
2) Pass
 */
public class Boj_3568 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] arr = br.readLine().split(" ");

        for (int i = 1; i < arr.length; i++) {
            StringBuilder sb = new StringBuilder().append(arr[0]);

            String now = arr[i].substring(0, arr[i].length() - 1);
            int endIdx = 1;
            for (int j = now.length() - 1; j > 0; j--) {
                if (now.charAt(j) == '[' || now.charAt(j) == ']' || now.charAt(j) == '&' || now.charAt(j) == '*') {
                    if (now.charAt(j) == '[') {
                        sb.append(']');
                    } else if (now.charAt(j) == ']') {
                        sb.append('[');
                    } else {
                        sb.append(now.charAt(j));
                    }
                } else {
                    endIdx = j + 1;
                    break;
                }
            }
            sb.append(" ").append(now.substring(0, endIdx)).append(";");
            System.out.println(sb.toString());
        }
    }
}

