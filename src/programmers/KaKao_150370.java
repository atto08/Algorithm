package programmers;



import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
/*
개인정보 수집 유효기간 - level1
소요 시간: 1시간 4분

1) 40점 - 불통 (tc6,9~19)
원인: 12월을 넘어갔을 때, 더해 질 유효기간이 12달 이하일 거라고 생각함.
해결: 12달을 넘어가는 경우 고려

2) 95점 - 불통 (tc17)
원인: 12월을 넘어갔을 때, 월에 바로 deadline[1] % 12를 하면 0월이 되는 경우가 존재
해결: 조건문으로 구분

3) 통과
 */
public class KaKao_150370 {
    public int[] solution(String today, String[] terms, String[] privacies) {

        int[] todays = stringToInt(today.split("\\.")); // 날짜 변환

        // privacies에서 term 찾기쉽게 만들기 위한 작업
        HashMap<String, Integer> termMap = new HashMap<>();
        for (int i = 0; i < terms.length; i++) {
            String[] term = terms[i].split(" ");
            int month = Integer.parseInt(term[1]);
            termMap.put(term[0], month);
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String[] privacy = privacies[i].split(" ");
            int[] deadline = stringToInt(privacy[0].split("\\."));

            deadline[1] += termMap.get(privacy[1]);

            // 월 계산
            if (deadline[1] > 12) {
                int addYear = deadline[1] / 12;
                int month = deadline[1] % 12;

                deadline[0] += addYear;
                if (month == 0) {
                    deadline[0]--;
                    deadline[1] = 12;
                } else {
                    deadline[1] = month;
                }
            }
            // 일 계산
            if (deadline[2] == 1) {
                deadline[1]--;
                deadline[2] = 28;
            } else {
                deadline[2]--;
            }

            // 오늘 날짜 부로 유효기간 검사
            for (int j = 0; j < 3; j++) {
                if (todays[j] > deadline[j]) {
                    list.add(i + 1);
                    break;
                } else if (todays[j] == deadline[j]) {
                    continue;
                } else {
                    break;
                }
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) result[i] = list.get(i);

        return result;
    }

    // 날짜 문자열 >> 숫자로 변환 메소드
    private int[] stringToInt(String[] arr) {

        int[] todays = new int[3];
        for (int i = 0; i < 3; i++) todays[i] = Integer.parseInt(arr[i]);

        return todays;
    }
}
