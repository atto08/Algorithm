package programmers.lv2;

/*
점프와 순간이동 - lv2
소요시간 - 1시간 초과

풀이설명:
- 점프: 한번에 k칸 점프 => 건전지 O
- 순간이동: (현재까지 거리) * 2 위치로 순간이동 => 건전지 X
- 건전지 제한 X
- 이동거리 까지 갈 수 있는 최소 건전지 사용량 구하기

1차시도 - 완전탐색(실패)
- N <= 10억이라서 안됨

2차시도 - 풀이법 봄
- n을 0으로 만들면서 점프(건전지 사용) 이동 경우 최소화 방법 규칙찾기

p.s
- 규칙찾기 보다는 자료구조와 알고리즘 종류에 또 신경을 쓴듯하다.
- 규칙찾아서 구현하는게 최고다.
- 이를 위한 방법이 알고리즘 종류이긴 하다만, 너무 신경을 쓰지말라.
*/
public class Program_12980 {
    public static int solution(int n) {
        int result = 0;

        while (n != 0) {
            if (n % 2 == 0) { // 짝수 == 순간이동
                n /= 2;
            } else { // 홀수 == 점프
                n--;
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(5));
        System.out.println(solution(6));
        System.out.println(solution(5000));
    }
}