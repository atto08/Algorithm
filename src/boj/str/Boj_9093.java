package boj.str;

import java.io.*;

public class Boj_9093 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb;
        for (int test_case = 0; test_case < T; test_case++) {

            String[] array = br.readLine().split(" ");

            for (int i=0; i< array.length; i++){
                sb = new StringBuilder();
                sb.append(array[i]);
                String reverse = sb.reverse().toString();
                if (i!= array.length-1){
                    bw.write(reverse + " ");
                } else {
                    bw.write(reverse +"\n");
                }
            }
        }
        bw.flush();
    }
}
