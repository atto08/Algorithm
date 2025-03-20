package programmers.lv2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
다리를 지나는 트럭 - lv2
소요 시간 - 1시간

풀이 설명:
다리(bridge) 큐에 트럭의 무게와 현재 트럭의 위치(전진시간)을 W이하의 경우까지만 넣어서 이동

p.s
어제보다는 집중력이 향상됨
하지만 더 집중했다면 40분안에 풀 수 있는 내용이였음.
더 집중 해보자.
 */
public class Program_42583 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int L = truck_weights.length;
        int W = 0;      // 다리에 있는 트럭 무게
        int result = 0; // 최소 시간

        Queue<Integer> trucks = new LinkedList<>();
        for (int i = 0; i < L; i++) trucks.offer(i);

        Queue<Truck> bridge = new LinkedList<>();
        Stack<Integer> finish = new Stack<>();

        while (finish.size() < L && !trucks.isEmpty()) {
            result++;
//            System.out.println(result + "초");
            // 다리 위에 있는 트럭들 전진
            int s = bridge.size();
            for (int i = 0; i < s; i++) {
                if (bridge.peek().t == bridge_length) { // 트럭이 다리를 넘어온 경우 - 도착
                    Truck ti = bridge.poll();
                    W -= ti.w;
                    finish.add(ti.w);
//                    System.out.println("도착 - (" + ti.w + "," + ti.t + ")");
                } else { // 트럭이 다리를 넘어가는 중 - 전진
                    Truck ti = bridge.poll();
                    bridge.offer(new Truck(ti.w, ti.t + 1));
//                    System.out.println("전진 - (" + ti.w + "," + (ti.t + 1) + ")");
                }
            }

            // 다리 위 수용 무게 초과 X && 다리 위에 존재할 수 있는 트럭 수
            if (W + truck_weights[trucks.peek()] <= weight && bridge.size() + 1 <= L) {
                int idx = trucks.poll();
                bridge.offer(new Truck(truck_weights[idx], 1));
                W += truck_weights[idx];
//                System.out.println("진입 - (" + truck_weights[idx] + "," + 1 + ")");
            }

//            System.out.println("완료:" + finish);
        }

        return result + bridge_length;
    }

    static class Truck {
        int w, t;

        public Truck(int w, int t) {
            this.w = w;
            this.t = t;
        }
    }

    public static void main(String[] args) {
        Program_42583 pg = new Program_42583();
        System.out.println(pg.solution(2, 10, new int[]{7, 4, 5, 6}));
        System.out.println(pg.solution(100, 100, new int[]{10}));
        System.out.println(pg.solution(100, 100, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10}));
    }
}
