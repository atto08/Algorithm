package boj.greedy;

import java.util.*;

/*
볼 모으기 - 실1
소요시간 - 85분

[문제설명]
- 볼을 옮겨 같은 색끼리 구분하는 최소 이동 값 구하기


[풀이설명]
- 해당 문제는 하나의 방향에서 순차적으로 가장가까운 녀석부터 빼는 경우가 최소경우임
- 총 4가지 경우 수 중 가장 최소 이동 값을 출력하면 됨
- 왼쪽에서 빨강/파랑 하나씩 뽑거나 오른쪽에서 똑같이 수행하는 경우의 수를 구하면 됨

0 ~ 3 인덱스 각각 (빨강 우측, 파랑 우측, 빨강 좌측, 파랑 좌측) 뽑기 경우
isBlock[] - 경우 별 볼 다름 여부 체크
move[] - 경우 별 볼 이동 횟수

[회고]
- 집중력 저하 + 시작부터 모듈화 시도로 인해 오류지점 파악지체
    - isBlock을 만들어 놓고, 빨강 파랑을 좌우측에서 한번씩 검사하는게 아니라 전부 빨강과 같냐 다르냐로만 취급함.
 */
public class BOJ_17615 {
    static int N;
    static int[] move;
    static char[] arr;
    static boolean[] isBlock;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        arr = sc.next().toCharArray();
        isBlock = new boolean[4];
        move = new int[4];

        if (arr[N - 1] == 'R') {
            isBlock[1] = true;
        } else {
            isBlock[0] = true;
        }

        if (arr[0] == 'R') {
            isBlock[3] = true;
        } else {
            isBlock[2] = true;
        }

        for (int i = N - 2; i >= 0; i--) {
            check(0, i, 'R');
            check(1, i, 'B');
        }

        for (int i = 1; i < N; i++) {
            check(2, i, 'R');
            check(3, i, 'B');
        }

        Arrays.sort(move);
        System.out.println(move[0]);
    }

    static void check(int d, int i, char rb) {
        if (isBlock[d]) {
            if (rb == arr[i]) {
                move[d]++;
            }
        } else {
            if (rb != arr[i]) {
                isBlock[d] = true;
            }
        }
    }
}
