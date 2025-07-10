package boj.implementation;

/*
셀프 넘버 - 실5
소요시간 - 40분

문제설명:
- 10000이하의 숫자 중 셀프넘버를 오름차순으로 출력하기.
- 셀프넘버는 생성자가 없는 숫자를 의미함
ex) d(1) => 1+1 = 2 -> 2+2 = 4 ..
    d(33) => 33 + 3 + 3 = 39 / d(112) => 112 + 1 + 1 + 2 = 116 같은 규칙

풀이설명:
- 1~10000 숫자동안 방문처리를 시도
- 방문을 시도하면서 d(n)을 시도하면서 방문한 경우는 제외 --> 이미 해당 숫자의 d(n)은 중복임.
- 숫자의 길이 별로 계산하는 메서드를 통해 구현.

p.s
- 10000이하의 자연수라 계산식을 무식하게 구현하여 동작시킬 수 있었다.
- 초반에 100과 1000의 자리를 가진 숫자에 대해서 10의자리, 100의 자리의 숫자를 구하는 계산식에 오류가 있었는데, 바로 알아차렸다.
- 더 빨리 풀었어야했는데 아쉽다.
*/
public class Boj_4673 {
    public static void main(String[] args) {

        boolean[] visited = new boolean[10001];
        for (int i = 1; i < 10001; i++) {
            if (visited[i]) continue;

            System.out.println(i);
            int idx = i;
            while (idx <= 10000) {
                if (visited[idx]) break;

                visited[idx] = true;
                idx = calculate(idx);
            }
        }
    }

    private static int calculate(int num) {
        int result = num;
        if (0 < num && num < 10) {
            result += num;
        } else if (10 <= num && num < 100) {
            result += (num / 10) + (num % 10);
        } else if (100 <= num && num < 1000) {
            result += (num / 100) + ((num % 100) / 10) + ((num % 100) % 10);
        } else if (1000 <= num && num < 10000) {
            result += (num / 1000) + ((num % 1000) / 100) + (((num % 1000) % 100) / 10) + (((num % 1000) % 100) % 10);
        } else {
            result = Integer.MAX_VALUE;
        }
        return result;
    }
}