package boj.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
Icy perimeter - 골5
소요 시간: 1시간

1) 틀렸습니다 (4%)
원인: 인덱스를 넘어가는 순간 해당 칸을 테두리로 인식하고 perimeter++ >> map의 인덱스를 벗어나면 즉시 종료하는 것이 남아있는 확인하지 않은 테두리가 존재했음
해결: 위 과정에 break문을 없애고 모두 확인 시키는 것으로 변경

소요 시간: +40분
2) 틀렸습니다 (4%)
원인: 가장 큰 면적이 여러개 일 때, 테두리가 가장 작은 것을 출력해야함
해결: 가장 큰 면적과 테두리를 확인하는 조건문 수정

소요 시간: +20분
3) 틀렸습니다 (4%)

원인: 문제를 잘못 이해했음. 내가 생각한 테두리는 대각선도 포함한다고 생각했는데 그렇지 않았음..
해결: 면적 중 '#'를 갖고잇는 애들의 테두리를 구할때 8면이아닌 4면(상하좌우)만 검색하도록 변경함.

문제의 문장
>>
블롭은 가운데에 "구멍"(아이스크림으로 둘러싸인 빈 공간)이 있을 수 있습니다.
그렇다면 구멍과의 경계도 블롭의 둘레에 포함됩니다. 블롭은 다른 블롭 내에 중첩되어 나타날 수도 있으며, 이 경우 별도의 블롭으로 처리됩니다.
예를 들어, 이 경우 영역 1의 블롭이 영역 16의 블롭 내에 중첩되어 있습니다.
#####
#...#
#.#.#
#...#
#####

결론 - 문제를 제대로 이해하지 못한 상황(번역을 이상하게 하고 예제의 테스트 케이스 결과만 보고 문제를 잘못 이해함)
+ 정확하게 테두리가 어떻게 업데이트 되는지 이해하지 못했음.
 */
public class Boj_17025 {
    static int N, maxArea, maxPerimeter;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new char[N][N];
        for (int i = 0; i < N; i++) map[i] = sc.next().toCharArray();

        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == '#') {
                    measurePerimeter(i, j);
                }
            }
        }

        System.out.println(maxArea + " " + maxPerimeter);
    }

    private static void measurePerimeter(int x, int y) {

        visited[x][y] = true;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});

        int area = 1, perimeter = 0;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            int cnt = 4;
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if (map[nx][ny] == '#') {
                    cnt--;
                    if (!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        q.offer(new int[]{nx, ny});
                        area++;
                    }
                }
            }
            perimeter += cnt;
        }

        System.out.println(area +" " + perimeter);
        if (maxArea < area) {
            maxArea = area;
            maxPerimeter = perimeter;
        } else if (maxArea == area) {
            maxPerimeter = Math.min(maxPerimeter, perimeter);
        }
    }
}
