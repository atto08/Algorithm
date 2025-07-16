package programmers.lv1;
/*
두개 뽑아서 더하기 - lv1
소요시간 - 12분

문제설명:
- numbers[]에서 서로다른 인덱스에 있는 2개의 수를 뽑아 더해서 만들수 있는 모든 수를 오름차순으로 구하기.

풀이설명:
- numbers[].length <= 100이하
- 우측기준 하나씩만 뽑아서 N^2 시간 복잡도
- 하지만 0 <= numbers[i] <= 100 과 최소 2개 ~ 최대 100개이면 복잡도 충분
*/

import java.util.*;

public class Program_68684 {
    public static int[] solution(int[] numbers) {

        List<Integer> list = new ArrayList<>();
        Arrays.sort(numbers);

        int num;
        boolean[] visited = new boolean[201];
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                num = numbers[i] + numbers[j];
                if (visited[num]) continue;
                visited[num] = true;
                list.add(num);
            }
        }

        Collections.sort(list);
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) result[i] = list.get(i);

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{2, 1, 3, 4, 1})));
        System.out.println(Arrays.toString(solution(new int[]{5, 0, 2, 7})));
    }
}