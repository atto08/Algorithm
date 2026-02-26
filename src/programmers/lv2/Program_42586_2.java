package programmers.lv2;

import java.util.*;

/*
기능개발 - lv2
소요시간 - 20분

 */
public class Program_42586_2 {
    public static void main(String[] args) {
        int[] progresses1 = {93, 30, 55};
        int[] speeds1 = {1, 30, 5};
        int[] progresses2 = {95, 90, 99, 99, 80, 99};
        int[] speeds2 = {1, 1, 1, 1, 1, 1};
        System.out.println(Arrays.toString(solution(progresses1, speeds1)));
        System.out.println(Arrays.toString(solution(progresses2, speeds2)));
    }

    public static int[] solution(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<>();

        int idx = 0, day = 1;
        int cnt = 0;
        while (idx < progresses.length) {
            if (progresses[idx] + speeds[idx] * day >= 100) {
                idx++;
                cnt++;
                continue;
            }

            if (cnt > 0) list.add(cnt);

            day++;
            cnt = 0;
        }

        list.add(cnt);

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}
