package codetree;

import java.io.*;
import java.util.*;

/*
택배 하차 - 구현
소요시간 - 4시간 초과
 */
public class Main_25년하반기오전1번 {
    static int N, M;
    static int[][] map;
    static List<Post> posts; // 오름차순 순서용
    static HashMap<Integer, Post> hash; //
    static int[] numbers;

    static class Post {
        int k, h, w, c, r;

        public Post(int k, int h, int w, int c, int r) {
            this.k = k;
            this.h = h;
            this.w = w;
            this.c = c;
            this.r = r;
        }
    }

    // 1. 적재공간 확인 및 적재
    private static void scanMapAndStackPost(Post post, int idx) {
        // 하늘(위)에서 아래로 내려오며 해당 열에 가장 높이 솟아있는 장애물을 찾습니다.
        int obs_r = N + 1; // 기본적으로 바닥(N+1)까지 떨어질 수 있다고 설정

        for (int i = 1; i <= N; i++) {
            boolean isHit = false;
            // 택배가 떨어질 열(Column)들만 검사
            for (int j = post.c; j < post.c + post.w; j++) {
                if (map[i][j] > 0) { // 장애물과 부딪힘!
                    obs_r = i;
                    isHit = true;
                    break;
                }
            }
            if (isHit) break; // 부딪혔으면 더 이상 내려가지 않음
        }

        // 장애물 바로 위(obs_r - 1)가 택배가 안착할 바닥(r)이 됩니다.
        int r = obs_r - 1;

        // map[i][j] 택배 번호로 갱신
        for (int i = r; i > r - post.h; i--) {
            for (int j = post.c; j < post.c + post.w; j++) {
                map[i][j] = post.k;
            }
        }

        hash.put(post.k, new Post(post.k, post.h, post.w, post.c, r));
        numbers[idx] = post.k;
    }

    private static int getPollablePost(Post post, int cnt) {

        boolean isPollable = true;
        // M이 홀수면 좌측 하차 짝수면 우측 하차
        if (cnt % 2 != 0) { // 좌측
            for (int r = post.r; r > post.r - post.h; r--) {
                if (!isPollable) {
                    break;
                }
                for (int c = post.c - 1; c > 0; c--) {
                    // 다른 택배가 걸리면
                    if (map[r][c] > 0) {
                        isPollable = false;
                        break;
                    }
                }
            }
        } else { // 우측
            for (int r = post.r; r > post.r - post.h; r--) {
                if (!isPollable) {
                    break;
                }

                for (int c = post.c + post.w; c <= N; c++) {
                    if (map[r][c] > 0) {
                        isPollable = false;
                        break;
                    }
                }
            }
        }
        return isPollable ? post.k : -1;
    }

    // 위치로 이동
    private static void movePost(Post post, int R) {

        // map[i][j] 택배 번호로 갱신
        int nr = R;
        for (int r = post.r; r > post.r - post.h; r--) {
            for (int c = post.c; c < post.c + post.w; c++) {
                map[r][c] = 0; // 기존 정보 제거
                map[nr][c] = post.k; // 새로운 위치 갱신
            }
            nr--;
        }

        // 내려올 택배의 posts, hash r 갱신
        hash.put(post.k, new Post(post.k, post.h, post.w, post.c, R));

    }

    // 내려올 수 있는 가장 높은 행 리턴
    private static int getDownableMaxR(Post post) {
        // 사라진 택배 기준 위에 존재하는 택배 대상 진행
        // 내려올 수 있는지 확인
        boolean isBlank = true;
        int r = post.r;
        while (r < N) {
            ++r;
            for (int c = post.c; c < post.c + post.w; c++) {
                if (map[r][c] > 0) {
                    isBlank = false;
                    break;
                }
            }
            if (!isBlank) {
                r--;
                break;
            }
        }

        return r;
    }

    // 2. 택배 하차(좌/우 구분)
    private static int pollPost(int cnt) {

        int k = -1;
        int idx = -1;
        // 작은 번호의 택배번호 부터 탐색
        for (int i = 0; i < posts.size(); i++) {
            k = getPollablePost(hash.get(posts.get(i).k), cnt);

            if (k > 0) {
                idx = i;
                break;
            }
        }

        Post pp = hash.get(k);
        // k번 택배상자 하차 시

        // map[r][c]에서 해당 택배번호 없애기
        for (int r = pp.r; r > pp.r - pp.h; r--) {
            for (int c = pp.c; c < pp.c + pp.w; c++) {
                map[r][c] = 0;
            }
        }
        // posts 에서 해당 택배 정보 없애기
        posts.remove(idx);

        applyGravity();

        // 택배 번호 출력
        return k;
    }

    private static void applyGravity() {
        List<Post> alivePosts = new ArrayList<>();
        for (Post p : posts) {
            alivePosts.add(hash.get(p.k));
        }

        alivePosts.sort((p1, p2) -> p2.r - p1.r);

        for (Post p : alivePosts) {
            int nr = getDownableMaxR(p);
            if (p.r != nr) {
                movePost(p, nr);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        hash = new LinkedHashMap<>();
        numbers = new int[M];
        // 1. 택배 투입
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 적재공간 확인 및 적재
            scanMapAndStackPost(new Post(k, h, w, c, 0), i);
        }

        Arrays.sort(numbers);
        posts = new ArrayList<>();
        for (int key : numbers) {
            Post post = hash.get(key);
            posts.add(post);
        }

        // M개의 택배를 좌우 번갈아가며 하차
        int cnt = 1;
        while (cnt <= M) {
            bw.write(pollPost(cnt++) + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
