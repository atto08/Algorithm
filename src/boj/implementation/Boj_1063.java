package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
킹 - 실3
소요 시간: 52분

구현문제
헷갈려서 그려봄
        00 01 02 03 04 05 06 07     A8 B8 C8 D8 E8 F8 G8 H8
        10 11 12 13 14 15 16 17     A7 B7 C7 D7 E7 F7 G7 H7
        20 21 22 23 24 25 26 27     A6 B6 C6 D6 E6 F6 G6 H6
        30 31 32 33 34 35 36 37     A5 B5 C5 D5 E5 F5 G5 H5
        40 41 42 43 44 45 46 47     A4 B4 C4 D4 E4 F4 G4 H4
        50 51 52 53 54 55 56 57     A3 B3 C3 D3 E3 F3 G3 H3
        60 61 62 63 64 65 66 67     A2 B2 C2 D2 E2 F2 G2 H2
        70 71 72 73 74 75 76 77     A1 B1 C1 D1 E1 F1 G1 H1
*/

public class Boj_1063 {
    static int[] dx = {0, 0, 1, -1, -1, -1, 1, 1}, dy = {1, -1, 0, 0, 1, -1, 1, -1};
    static String[] orderList = {"R", "L", "B", "T", "RT", "LT", "RB", "LB"};
    static char[] cx = {'8', '7', '6', '5', '4', '3', '2', '1'}, cy = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    static int kx, ky, rx, ry;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        char[] king = st.nextToken().toCharArray();
        char[] rock = st.nextToken().toCharArray();
        convertLocation(king, rock);

        int N = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            String order = br.readLine();
            for (int j = 0; j < orderList.length; j++) {
                if (order.equals(orderList[j])) {
                    int nkx = kx + dx[j];
                    int nky = ky + dy[j];

                    if (nkx == rx && nky == ry) {
                        int nrx = rx + dx[j];
                        int nry = ry + dy[j];
                        if (nkx < 0 || nky < 0 || nkx >= 8 || nky >= 8 || nrx < 0 || nry < 0 || nrx >= 8 || nry >= 8)
                            break;

                        kx = nkx;
                        ky = nky;
                        rx = nrx;
                        ry = nry;
                    } else {
                        if (nkx < 0 || nky < 0 || nkx >= 8 || nky >= 8) break;

                        kx = nkx;
                        ky = nky;
                    }
                    break;
                }
            }
        }

        System.out.println(cy[ky] + "" + cx[kx] + "\n" + cy[ry] + "" + cx[rx]);
    }

    private static void convertLocation(char[] king, char[] rock) {
        for (int i = 0; i < cy.length; i++) {
            if (cy[i] == king[0]) {
                ky = i;
                break;
            }
        }

        for (int i = 0; i < cx.length; i++) {
            if (cx[i] == king[1]) {
                kx = i;
                break;
            }
        }

        for (int i = 0; i < cy.length; i++) {
            if (cy[i] == rock[0]) {
                ry = i;
                break;
            }
        }

        for (int i = 0; i < cx.length; i++) {
            if (cx[i] == rock[1]) {
                rx = i;
                break;
            }
        }
    }
}
