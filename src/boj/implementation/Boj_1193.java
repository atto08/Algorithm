package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
첨에 배열에 저장해서 사용했다가 메모리 + 시간 모두 잡아먹음
저장하지 않고 N 값이 될때까지 i와 j를 증감하여 N 값과 같아지면 반복문 탈출.
 */
public class Boj_1193 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int count = 2;
        int num = 1;
        int i = 1;
        int j = 1;

        if (N > 1) {

            boolean direction = false;

            while (N > num) {
                if (!direction) {
                    j += 1;
                } else {
                    i += 1;
                }

                for (int k = 0; k < count; k++) {
                    num++;

                    if (num == N)
                        break;

                    if (k != count - 1) {
                        if (!direction) {
                            i += 1;
                            j -= 1;
                        } else {
                            i -= 1;
                            j += 1;
                        }
                    }
                }
                direction = !direction;
                count++;
            }
        }

        System.out.println(i + "/" + j);
    }
}
