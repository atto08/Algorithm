package boj.data_structure;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Boj_17219 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        HashMap<String, String> map = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String site = st.nextToken();
            String password = st.nextToken();

            map.put(site, password);
        }

        for (int i = 0; i < M; i++) {
            String site = br.readLine();
            bw.write(map.get(site) + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
