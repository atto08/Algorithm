package boj.str;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Boj_11478 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] array = br.readLine().toCharArray();
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < array.length; i++) {
            String str = "";
            for (int j = i; j < array.length; j++) {
                str += array[j];
                set.add(str);
            }
        }
        System.out.println(set.size());
    }
}
