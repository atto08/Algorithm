package programmers.lv2;

import java.util.*;

/*
주차 요금 계산 - lv2
소요시간 - 1시간 초과

풀이(1)
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
        System.out.println(Arrays.toString(solution2(new int[]{180, 5000, 10, 600}, new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"})));
        System.out.println(Arrays.toString(solution2(new int[]{120, 0, 60, 591}, new String[]{"16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN"})));
        System.out.println(Arrays.toString(solution2(new int[]{1, 461, 1, 10}, new String[]{"00:00 1234 IN"})));
    }


    /*
    2회차
    소요시간 - 남은시간 34분 초과 + 60분(94분 소요)
    풀이(2)
    풀이설명:
    1) 입출차 기록순으로 진행 - records
    2) 입차 기록 - inRecord에 넣기
    3) 출차 기록 - inRecord에 있는 입차기록 빼와서 계산하기
    4) 출차기록 없는 남아잇는 inRecord(입차정보) 23:59으로 계산하기.

    p.s
    - 주요 패착원인은 일일 누적시간이라 생각하지 않고, 나갔다오면 리셋으로 계산해버렸다. -> 여기서부터 꼬였다고 생각한다.
    - 제한시간내에 풀수 있었던 문제라고 생각한다.
    - 34분 지나니까 집중력이 흐트려져서 더 오래걸렸다고 생각한다. 문제를 똑바로 읽자~~.
    */

    // records[i] = "HH:MM 차량번호 입출여부"
    public static int[] solution2(int[] fees, String[] records) {

        HashMap<String, Integer> inRecord = new HashMap<>();
        HashMap<String, Integer> addTime = new HashMap<>();
        for (String rc : records) {
            String[] arr = rc.split(" ");

            String[] t = arr[0].split(":");
            int h = Integer.parseInt(t[0]), m = Integer.parseInt(t[1]);
            int time = (60 * h) + m;

            if (arr[2].equals("IN")) {
                inRecord.put(arr[1], time);
            } else {
                int it = inRecord.get(arr[1]);  // in Time
                inRecord.remove(arr[1]);
                time -= it;     // 누적 시간
                if (!addTime.containsKey(arr[1])) {
                    addTime.put(arr[1], time);
                } else {
                    addTime.put(arr[1], addTime.get(arr[1]) + time);
                }
            }
        }

        // 출차기록 없는 차량
        for (String carN : inRecord.keySet()) {
            int time = ((60 * 23) + 59) - inRecord.get(carN);
            if (addTime.containsKey(carN)) {
                time += addTime.get(carN);
                addTime.replace(carN, time);
            } else {
                addTime.put(carN, time);
            }
        }

        List<String> numbers = new ArrayList<>(addTime.keySet());
        Collections.sort(numbers);

        int[] result = new int[numbers.size()];
        for (int i = 0; i < result.length; i++) {
            int time = addTime.get(numbers.get(i));
            result[i] = calculateFee(fees, time);
        }

        return result;
    }

    // fees[0] = 기본시간(분), fees[1] = 기본요금(원), fees[2] = 단위시간(분), fees[3] = 단위요금(원)
    private static int calculateFee(int[] fees, int time) {
        int fee = fees[1];
        time -= fees[0];
        if (time > 0) {  // 기본 주차시간 초과 계산
            fee += (time / fees[2]) * fees[3];
            if (time % fees[2] > 0) fee += fees[3];  // 올림
        }

        return fee;
    }
}