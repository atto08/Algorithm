package boj.implementation;
import java.util.*;
import java.io.*;
/*
창고 다각형 - 실1
소요시간 - 1시간 초과

[풀이설명]
1) 가장 낮고 높은 건물번호를 min, max로 기억하기
-> 배열 크기 만들고 사이즈 체크하는 범위 구하기
2) 입력된 값을 건물 번호 오름차순으로 확인 할 수 있도록 우선순위큐에 넣기
    - 이전건물 ~ 현재건물까지 현재건물 높이를 제외한 가장 높은 건물높이로 배열 채워넣기(반복)
    - 건물번호/높이 스택에 쌓기
3) 스택에서 가장 높은 건물의 높이가 나올때까지 우측에서부터 확인하며 쓸데없이 높은 건물높이 낮추기

p.s
집중력이 너무 떨어졌다. 오늘은 무슨 ADHD였다. 반성해라. 그리고 공부와 프로그래밍 할때는 집중좀 해라.
 */
public class Boj_2304 {

    static class Building implements Comparable<Building> {
        int l, h;

        public Building(int l, int h) {
            this.l = l;
            this.h = h;
        }

        @Override
        public int compareTo(Building other) {
            return Integer.compare(this.l, other.l);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int min = 1001, max = 0;

        PriorityQueue<Building> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            pq.offer(new Building(L, H));

            if (min > L) min = L;
            if (max < L) max = L;
        }

        int[] map = new int[max + 1];

        Building prevB = pq.poll();
        int highestH = prevB.h;
        int highestL = prevB.l;

        Stack<Building> stack = new Stack<>();
        stack.add(prevB);
        while (!pq.isEmpty()) {
            Building nowB = pq.poll();
            stack.add(nowB);

            for (int i = prevB.l; i <= nowB.l; i++) {
                map[i] = highestH;
            }

            if (highestH < nowB.h) {
                highestL = nowB.l;
            }

            prevB = nowB;
            highestH = Math.max(nowB.h, highestH);
        }

        // 오른쪽 끝이 가장 높은 경우
        if (prevB.h == highestH) {
            map[prevB.l] = highestH;
        }

        int rHighestH = prevB.h;
        while (!stack.isEmpty()) {
            Building nowB = stack.pop();

            for (int i = prevB.l; i > nowB.l; i--) {
                map[i] = rHighestH;
            }
            if (nowB.l == highestL) {
                break;
            }
            prevB = nowB;
            rHighestH = Math.max(nowB.h, rHighestH);
        }

        int result = 0;
        for (int i = min; i <= max; i++) {
            result += map[i];
        }

        System.out.println(result);
    }
}