package programmers.lv2;

import java.util.*;

/*
주차요금 - lv2
소요시간 - 1시간 초과

풀이설명:
- 입차와 출차를 우선순위 큐에서 관리
    - 입차와 동시에 출차에 있는 peek()이 같으면 출차기록 O
    -                           " 다르면 출차기록 X -> 23:59분
- 입차 우선순위 큐를 통해 차번호 오름차순 -> 시 오름차순 -> 분 오름차순 으로 뽑도록 compareTo 메서드구현
- 입차 우선순위 큐가 비워질때 까지 출차 우선순위 큐에 현재 인수와 비교하며 시간계산
- 이를 map에 순차적으로 입력.
    - LinkedHashMap을 사용했기 때문에 삽입순서를 유지하게됨. -> 우선순위 큐를 사용했기 때문에 차번호 오름차순 유지
- map에 있는 차번호별 누적 주차시간을 계산하여 주차요금 정산.

p.s
- 풀이는 구상한 순서대로 잘 구현했다.
문제점
- 올림할때 double끼리 나눠야했다. -> 올림하는 부분을 구현하는데서 시간을 제일 많이 소모했다.
- 23:59를 719분으로 착각했었다.
- 우선순위 compareTo메서드 우선순위가 완벽하지 않았다.
 */
public class Program_92341 {
    public static int[] solution(int[] fees, String[] records) {
        PriorityQueue<Car> ic = new PriorityQueue<>(); // 들어온 차
        PriorityQueue<Car> oc = new PriorityQueue<>(); // 나간 차

        // fees - 기본시간, 기본요금, 단위시간(분), 단위요금(원)
        for (String record : records) {
            String[] arr = record.split(" ");
            String[] time = arr[0].split(":");

            if (arr[2].equals("IN")) {
                ic.offer(new Car(Integer.parseInt(arr[1]), Integer.parseInt(time[0]), Integer.parseInt(time[1])));
            } else {
                oc.offer(new Car(Integer.parseInt(arr[1]), Integer.parseInt(time[0]), Integer.parseInt(time[1])));
            }
        }

        HashMap<Integer, Integer> map = new LinkedHashMap<>(); // 차번호 & 요금
        while (!ic.isEmpty()) {
            Car icn = ic.poll();
            // 누적시간 계산
            int time = 0;
            try {
                if (icn.cn == oc.peek().cn) {    // 출차기록 O -> 출차기록 - 입차기록
                    Car ocn = oc.poll();
                    time = ((60 * ocn.h) + ocn.m) - ((60 * icn.h) + icn.m);
                } else {                        // 출차기록 X -> 23:59 - 입차기록
                    time = 1439 - ((60 * icn.h) + icn.m);
                }
            } catch (RuntimeException ignore) {
                time = 1439 - ((60 * icn.h) + icn.m);
            }

            if (!map.containsKey(icn.cn)) {
                map.put(icn.cn, time);
            } else {
                map.put(icn.cn, map.get(icn.cn) + time);
            }
        }
        int[] result = new int[map.size()];

        int idx = 0;
        for (int key : map.keySet()) {
            // 기본요금
            int fee = fees[1];

            // (누적시간 - 기본시간) / 단위시간 * 단위요금
            // useTime = 누적시간 - 기본시간
            double ut = map.get(key) - fees[0];     // 올림해야해서 double
            double at = Math.ceil(ut / fees[2]);    // 올림해야돼서 double
            if (at > 0) fee += (at * fees[3]);
            result[idx] = fee;
            idx++;
        }

        return result;
    }

    static class Car implements Comparable<Car> {
        int cn, h, m;

        public Car(int cn, int h, int m) {
            this.cn = cn;
            this.h = h;
            this.m = m;
        }

        @Override
        public int compareTo(Car other) {
            if (this.cn != other.cn) {
                return Integer.compare(this.cn, other.cn);
            }
            if (this.h != other.h) {
                return Integer.compare(this.h, other.h);
            }
            return Integer.compare(this.m, other.m);
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{180, 5000, 10, 600}, new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"})));
        System.out.println(Arrays.toString(solution(new int[]{120, 0, 60, 591}, new String[]{"16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN"})));
        System.out.println(Arrays.toString(solution(new int[]{1, 461, 1, 10}, new String[]{"00:00 1234 IN"})));
    }
}