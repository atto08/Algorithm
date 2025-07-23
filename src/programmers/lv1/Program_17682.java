package programmers.lv1;

/*
다트 게임 - lv1
소요시간 - 44분

문제설명:
- 다트의 점수를 배열로 입력받아 점수 계산해주기

풀이설명:
- 다트 점수 문자를 문자별로 아스키 코드로 구분
    1) 숫자면 sb에 추가.
    2) S, D, T이면 sb 숫자타입으로 변환 후 제곱 계산 + sb초기화
    3) * or #은 큐에 담아서 한번에 계산
    4) 모아놓은 큐를 한번에 계산.
*/

import java.util.*;

public class Program_17682 {
    public static int solution(String dartResult) {
        int result = 0;

        StringBuilder sb = new StringBuilder();
        List<Integer> list = new ArrayList<>();
        Queue<Dart> q = new LinkedList<>();
        for (char c : dartResult.toCharArray()) {
            if (c > 47 && c < 58) {
                sb.append(c);
            } else if (c < 43) { // *, #
                q.offer(new Dart(c, list.size() - 1));
            } else {    // S, D, T
                int num = Integer.parseInt(sb.toString());
                if (c == 68) {
                    num = (int) Math.pow(num, 2);
                } else if (c == 84) {
                    num = (int) Math.pow(num, 3);
                }
                list.add(num);
                sb = new StringBuilder();
            }
        }

        int[] numbers = new int[list.size()];
        for (int i = 0; i < list.size(); i++) numbers[i] = list.get(i);

        while (!q.isEmpty()) {
            Dart now = q.poll();
            int idx = now.i;

            if (now.c == '*') {  // *
                if (idx > 0) numbers[idx - 1] *= 2;
                numbers[idx] *= 2;
            } else {    // #
                numbers[idx] *= -1;
            }
        }

        for (int num : numbers) result += num;

        return result;
    }

    static class Dart {
        char c;
        int i;

        public Dart(char c, int i) {
            this.c = c;
            this.i = i;
        }
    }

    public static void main(String[] args) {
        System.out.println(solution("1S2D*3T"));
        System.out.println(solution("1D2S#10S"));
        System.out.println(solution("1D2S0T"));
        System.out.println(solution("1S*2T*3S"));
    }
}