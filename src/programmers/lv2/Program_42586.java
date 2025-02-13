package programmers.lv2;

import java.util.*;

/*
기능개발 - lv2(개념정리 및 재풀이)
소요시간 - 1시간 초과
분류 - 스택/큐

문제 설명:
- 각의 배포마다 몇 개의 기능 씩 배포되는지 구하기
- 작업의 진도 배열: int[] progresses, 작업의 속도 배열: int[] speeds

풀이 방법:
- idx -> 각 배포별 시작하는 인덱스를 구별하기 위한 정수
- idx가 배열의 크기가 될 때까지 update(배포) 메소드를 통해
    - 정수 cnt -> progresses[i]의 작업이 배포 가능상태면 증가
    - cnt만큼 idx 증가
    - cnt > 0 -> 배포 진행 -> result에 삽입
*/
public class Program_42586 {
    static Queue<Integer> result = new LinkedList<>();

    public int[] solution(int[] progresses, int[] speeds) {

        int idx = 0;
        while (idx < speeds.length) idx += update(progresses, speeds, idx);

        int[] answer = new int[result.size()];
        for (int i = 0; i < answer.length; i++) answer[i] = result.poll();

        return answer;
    }

    private static int update(int[] progresses, int[] speeds, int idx) {
        int cnt = 0;

        for (int i = idx; i < speeds.length; i++) {
            progresses[i] += speeds[i];
            if (i > idx) {
                if (progresses[i] >= 100 && progresses[i - 1] < 0) {
                    cnt++;
                    progresses[i] = -1;
                }
            } else {
                if (progresses[i] >= 100) {
                    cnt++;
                    progresses[i] = -1;
                }
            }
        }
        if (cnt > 0) result.offer(cnt);

        return cnt;
    }

    public static void main(String[] args) {
        Program_42586 pg = new Program_42586();
        int[] progresses1 = {93, 30, 55};
        int[] speeds1 = {1, 30, 5};
        int[] progresses2 = {95, 90, 99, 99, 80, 99};
        int[] speeds2 = {1, 1, 1, 1, 1, 1};
        System.out.println(Arrays.toString(pg.solution(progresses1, speeds1)));
        System.out.println(Arrays.toString(pg.solution(progresses2, speeds2)));
    }
}
