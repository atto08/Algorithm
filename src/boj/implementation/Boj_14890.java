package boj.implementation;

import java.io.*;
import java.util.*;
/*
경사로 - 골3
소요시간 - 80분

[풀이설명]
** 경사로를 놓을 수 없는 경우 **
1. 경사로가 놓여 있는 곳
2. 낮은칸, 높은칸 높이 차이가 1이 아닌 경우
3. 경사로를 쌓아야하는 범위가 N*N 격자를 벗어난 경우
4. 낮은칸이 L개 연속으로
    - 존재하지 않는 경우
    - 높이가 같지 않은 경우

경사로를 놓을 수 없는 경우를 조건부로 삼아
가로 N번, 세로 N번 길(street)별로 2*N 번 확인하는 메서드를 구현

1) 앞선 블록과 높이가 다른가?
- NO  -> 다음 블록 확인
- YES -> (2)
2) 높이 차이가 1인가?
- NO  -> *지나갈 수 없음 - break
- YES -> (3)
3) 현재 위치가 이전 블록보다 높이가 높은가?
- YES
    3-1) 현재 위치에서 (i-1)~(i-L) 확인
    - 범위 블록 존재여부 확인
    - 범위 경사로 설치여부 확인
    - 범위 높이 일치확인
- NO
    3-2) 현재 위치에서 i~(i+L) 확인
    - 범위 블록 존재여부 확인
    - 범위 경사로 설치여부 확인
    - 범위 높이 일치확인
x or y 값 증가

p.s
- 스스로 정한 1시간 내에 풀기를 시도했다.
- 제한시간안에 풀고 제출까지는 했는데, 조건중에 3번 조건을 내가 걸지 않았다.
- (3-1) 조건만 걸어두었고, 그렇기에 현재 위치보다 이전블록이 높이가 높은경우는 무시되는 코드가 되었다.
- 20분이내에 빨리 잡아서 풀기는 했다.
- 그치만 이전에 손으로 적어둔 조건에서 이를 캐치하지 못한 아쉬움과 하나의 메서드로 구분할 수 있었을텐데 하는 아쉬움이 남는다.
 */
public class Boj_14890 {

    static int N, L, result;
    static int[][] map;

    private static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = 0;
    }

    private static void checkRoads() {
        // 가로 길 확인
        for (int i = 0; i < N; i++) {
            canGoStreetW(i, 1);
        }

        // 세로 길 확인
        for (int j = 0; j < N; j++) {
            canGoStreetH(1, j);
        }

        System.out.println(result);
    }

    private static void canGoStreetH(int x, int y) {
        boolean canGo = true;
        boolean isPresent = true;
        boolean isInstall = false;
        boolean isEqualHeight = true;
        // 경사로 설치여부
        boolean[] visited = new boolean[N];
        while (x < N) {

            // 1. 앞 블록과 높이가 다른가?
            if (map[x][y] == map[x - 1][y]) {
                x++;
                continue;
            }
            // 2. 높이 차이가 1인가?
            if (Math.abs(map[x][y] - map[x - 1][y]) != 1) {
                canGo = false;
                break;
            }

            // 현재위치가 앞쪽보다 크면
            if (map[x][y] > map[x - 1][y]) {
                // 3. 현재 위치에서 i-1 ~ i-L까지 존재하는가?
                for (int l = 1; l <= L; l++) {
                    if (x - l < 0 || x - l >= N) {
                        isPresent = false;
                        break;
                    }
                }
                if (!isPresent) {
                    canGo = false;
                    break;
                }
                // 4. 현재 위치에서 i-1 ~ i-L까지 경사로 설치가 되었나?
                for (int l = 1; l <= L; l++) {
                    if (visited[x - l]) {
                        isInstall = true;
                        break;
                    }
                }
                if (isInstall) {
                    canGo = false;
                    break;
                }
                // 5. 현재 위치에서 i-1 ~ i-L까지 높이가 전부 같은가?
                int height = map[x - 1][y];
                for (int l = 2; l <= L; l++) {
                    if (height != map[x - l][y]) {
                        isEqualHeight = false;
                        break;
                    }
                }
                if (!isEqualHeight) {
                    canGo = false;
                    break;
                }
                // 6. 갈 수 있으면 경사로 체크
                for (int l = 1; l <= L; l++) {
                    if (!visited[x - l]) {
                        visited[x - l] = true;
                    }
                }
            }
            // 현재위치가 앞쪽보다 작으면 현재위치 ~ +L만큼 확인
            else {
                // 3. 현재 위치에서 i-1 ~ i-L까지 존재하는가?
                for (int l = 0; l < L; l++) {
                    if (x + l < 0 || x + l >= N) {
                        isPresent = false;
                        break;
                    }
                }
                if (!isPresent) {
                    canGo = false;
                    break;
                }
                // 4. 현재 위치에서 i-1 ~ i-L까지 경사로 설치가 되었나?
                for (int l = 0; l < L; l++) {
                    if (visited[x + l]) {
                        isInstall = true;
                        break;
                    }
                }
                if (isInstall) {
                    canGo = false;
                    break;
                }
                // 5. 현재 위치에서 i-1 ~ i-L까지 높이가 전부 같은가?
                int height = map[x][y];
                for (int l = 1; l < L; l++) {
                    if (height != map[x + l][y]) {
                        isEqualHeight = false;
                        break;
                    }
                }
                if (!isEqualHeight) {
                    canGo = false;
                    break;
                }
                // 6. 갈 수 있으면 경사로 체크
                for (int l = 0; l < L; l++) {
                    if (!visited[x + l]) {
                        visited[x + l] = true;
                    }
                }
            }
            x++;
        }

        if (x == N && canGo) {
            result++;
        }
    }

    private static void canGoStreetW(int x, int y) {
        boolean canGo = true;
        boolean isPresent = true;
        boolean isInstall = false;
        boolean isEqualHeight = true;
        // 경사로 설치여부
        boolean[] visited = new boolean[N];
        while (y < N) {

            // 1. 앞 블록과 높이가 다른가?
            if (map[x][y] == map[x][y - 1]) {
                y++;
                continue;
            }
            // 2. 높이 차이가 1인가?
            if (Math.abs(map[x][y] - map[x][y - 1]) != 1) {
                canGo = false;
                break;
            }
            // 현재위치가 앞쪽보다 크면
            if (map[x][y] > map[x][y - 1]) {
                // 3. 현재 위치에서 i-1 ~ i-L까지 존재하는가?
                for (int l = 1; l <= L; l++) {
                    if (y - l < 0 || y - l >= N) {
                        isPresent = false;
                        break;
                    }
                }
                if (!isPresent) {
                    canGo = false;
                    break;
                }
                // 4. 현재 위치에서 i-1 ~ i-L까지 경사로 설치가 되었나?
                for (int l = 1; l <= L; l++) {
                    if (visited[y - l]) {
                        isInstall = true;
                        break;
                    }
                }
                if (isInstall) {
                    canGo = false;
                    break;
                }
                // 5. 현재 위치에서 i-1 ~ i-L까지 높이가 전부 같은가?
                int height = map[x][y - 1];
                for (int l = 2; l <= L; l++) {
                    if (height != map[x][y - l]) {
                        isEqualHeight = false;
                        break;
                    }
                }
                if (!isEqualHeight) {
                    canGo = false;
                    break;
                }
                // 6. 갈 수 있으면 경사로 체크
                for (int l = 1; l <= L; l++) {
                    if (!visited[y - l]) {
                        visited[y - l] = true;
                    }
                }
            }
            // 현재위치가 앞쪽보다 작으면 현재위치 ~ +L만큼 확인
            else {
                // 3. 현재 위치에서 i-1 ~ i-L까지 존재하는가?
                for (int l = 0; l < L; l++) {
                    if (y + l < 0 || y + l >= N) {
                        isPresent = false;
                        break;
                    }
                }
                if (!isPresent) {
                    canGo = false;
                    break;
                }
                // 4. 현재 위치에서 i-1 ~ i-L까지 경사로 설치가 되었나?
                for (int l = 0; l < L; l++) {
                    if (visited[y + l]) {
                        isInstall = true;
                        break;
                    }
                }
                if (isInstall) {
                    canGo = false;
                    break;
                }
                // 5. 현재 위치에서 i-1 ~ i-L까지 높이가 전부 같은가?
                int height = map[x][y];
                for (int l = 1; l < L; l++) {
                    if (height != map[x][y + l]) {
                        isEqualHeight = false;
                        break;
                    }
                }
                if (!isEqualHeight) {
                    canGo = false;
                    break;
                }
                // 6. 갈 수 있으면 경사로 체크
                for (int l = 0; l < L; l++) {
                    if (!visited[y + l]) {
                        visited[y + l] = true;
                    }
                }
            }
            y++;
        }

        if (y == N && canGo) {
            result++;
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();
        checkRoads();
    }
}
