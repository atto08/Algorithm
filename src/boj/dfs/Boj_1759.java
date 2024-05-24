package boj.dfs;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
암호 만들기 - 골5

소요 시간: 29분
풀이:
1) 입력받은 문자들을 알파벳 순서로 정렬
2) 길이가 L이 되는 경우 중 모음 1개이상 & 자음 2개이상 을 만족하는 경우 모두 출력
>> 지문을 읽은 후 중복제거 백트래킹 문제라고 생각함
 */
public class Boj_1759 {
    static int L, C;
    static String[] alphabets;
    static char[] vowels = {'a', 'e', 'i', 'o', 'u'};
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        alphabets = br.readLine().split(" ");
        Arrays.sort(alphabets);

        for (int i = 0; i < C; i++) {
            dfs(new StringBuilder().append(alphabets[i]), i);
        }

        System.out.println(result);
    }

    private static void dfs(StringBuilder sb, int node) {

        if (sb.length() == L) {
            int vowelCnt = 0, consCnt = 0;

            for (int i = 0; i < sb.length(); i++) {
                boolean isVowel = false;
                for (char vowel : vowels) {
                    if (vowel == sb.charAt(i)) {
                        isVowel = true;
                        break;
                    }
                }
                if (isVowel) {
                    vowelCnt++;
                } else {
                    consCnt++;
                }
            }

            if (vowelCnt > 0 && consCnt > 1) {
                result.append(sb).append("\n");
            }

        } else if (sb.length() < L) {
            for (int i = node + 1; i < C; i++) {
                sb.append(alphabets[i]);
                dfs(sb, i);
                sb.delete(sb.length() - 1, sb.length());
            }
        }
    }
}
