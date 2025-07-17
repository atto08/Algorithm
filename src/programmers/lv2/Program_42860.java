package programmers.lv2;

/*
조이스틱 - lv2
소요시간 - 1시간 초과

문제설명:
- 문자를 만들기위한 조작횟수 최솟값 구하기
- 상: A->B / Z->A
- 하: A<-B / Z<-A
- 좌: 첫 번째위치-> 마지막문자
- 우: 첫 번째문자 <- 마지막위치

풀이설명:
- 규칙 a 기준으로
    - n은 아래로 위로 접근하던 13번 이동.
    - b ~ m은 위 커서가 빠름(1~12)
    - o ~ z는 아래 커서가 빠름(1~12)
a
b c d e f g h i j k l m
n
o p q r s t u v w x y z
1 -> 10 (9회 카운트)

ex)
J  A  N
9  0 13
1(좌우 이동) + 22(상하 이동) = 23

J  E  R  O  E  N
9  4  9 12  4 13
5(좌우 이동) + 51(상하 이동) = 56

J  E  R  A  E  N
9  4  9  0  4 13
현 위치에서 좌우기준으로만 방문을해야하는지? -> A인지 여부로 따지면될듯? -> 불가 ex) "CCAAACAAACCC"

p.s
- 상하이동 최솟갑 카운트는 빨리 작성했음.
- 좌우이동 최솟값 카운트는 규칙을 찾지 못했음.
- 블로그 참고 풀이방법
    - 상하 이동방법 구현은 완벽히 이해됨 + 이 방법이 깔끔하다(문자형을 정수형으로 어떻게 표현할지 감을 못잡아서 해시맵에 규칙별로 구현했음)
    - 좌우 이동방법 구현에 대해서는 완벽히 이해하지 못함.
        - 하지만, 모든 경우 (0 ~ name.length - 1) 기준으로 moveBack과 moveFront를 계산해서 최솟값이 나오는 경우가 좌우 최소 이동거리.
    - 인덱스 별로 상황을 찍어보니까 이해가 됐다.
    - i(현재 인덱스) 기준으로 A가 아닌 곳까지 찍고 돌아 간후에
    - 뒤에 최대 길이의 A만큼 갈필요 없으니까 (찍고간 구간 + 연속된 A구간)까지 (0~next) == next
    - 그래서 len - next 만큼 더하기!!

- 2시간동안 2문제 풀기 시도했는데, 상대적으로 쉬운 문제에서 57분 사용해서 이문제는 못풀었다.
- 나름 구현단계를 구분하고 규칙을 찾았지만, 좌우 이동 방법의 최솟값을 구하는 방식은 생각해내지 못했다.
- 풀다보면 또 늘겠지.
*/
public class Program_42860 {
    public static int solution(String name) {
        int result = 0;
        int len = name.length();
        int minMove = len - 1; // 초기값: 오른쪽으로만 쭉 가는 경우

        for (int i = 0; i < len; i++) {
            // 1. 상하 이동 (A와 Z 중 더 가까운 쪽 선택)
            char c = name.charAt(i);
            result += Math.min(c - 'A', 'Z' - c + 1);
            System.out.println("c = " + c);
            System.out.println("i = " + i);

            // 2. 좌우 이동 최솟값 계산
            int next = i + 1;
            while (next < len && name.charAt(next) == 'A') {    // 연속된 A 구간 찾기
                next++;
            }
            System.out.println("next = " + next);

            int moveBack = i * 2 + (len - next);    // 우로 이동 >> i까지 갔다가 되돌아오고(i*2) + 끝까지 가는(len-next) 경우
            int moveFront = (len - next) * 2 + i;   // 좌로 이동 << 끝까지 갔다가 돌아와서((len-next)*2) + i까지 가는(i) 경우

            System.out.println("moveBack = " + moveBack);
            System.out.println("moveFront = " + moveFront);
            // 최솟값 갱신
            minMove = Math.min(minMove, Math.min(moveBack, moveFront));
            System.out.println("minMove = " + minMove);
            System.out.println();
        }

        return result + minMove;
    }

    public static void main(String[] args) {
//        System.out.println(solution("JAZ"));
//        System.out.println(solution("JEROEN"));
//        System.out.println(solution("JAN"));
//        System.out.println(solution("CCAAACAAACCC"));
        System.out.println(solution("CCAAAAAAACCC"));
    }
}
