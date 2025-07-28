package programmers.lv2;

/*
큰 수 만들기 - lv2
소요시간 - 50분

풀이설명:
- 현재 인덱스 수와 뒤쪽 인덱스 수 비교
    - 작으면 현재 인덱스 수 제거O & 인덱스 증가X
    - 크면 현재 인덱스 수 제거X & 인덱스 증가O

1차시도 - tc12번 실패(RuntimeException 발생)
원인: 확인하는 문자열의 인덱스를 넘어가는 경우 발생
    -> 이 경우는 앞에 큰 수가 있어서 k의 갯수가 줄지 않는 경우
해결: 맨뒤가 제일 작다는 의미기 때문에 현재 위치 문자를 제거해주면 됨

p.s
- IDE 없이 풀기 꽤 했다고 생각했는데, 무작정 RuntimeException 발생하니까 답답했다.
*/
public class Program_42883 {
    public static String solution(String number, int k) {
        StringBuilder sb = new StringBuilder().append(number);
        int idx = 0;
        try {
            while (k > 0) {
                if (idx > 0) {
                    if (sb.charAt(idx) < sb.charAt(idx + 1)) {
                        sb.deleteCharAt(idx);
                        k--;
                        idx--;
                        continue;
                    }
                } else {
                    if (sb.charAt(idx) < sb.charAt(idx + 1)) {
                        sb.deleteCharAt(idx);
                        k--;
                        continue;
                    }
                }
                idx++;
            }
        } catch (RuntimeException e) {
            sb.deleteCharAt(idx);
        }
        return sb.toString();
    }

    /*
    큰 수 만들기 - lv2
    소요시간 - 38분
    0 -> N-1 인덱스 순으로 뒷수와 비교하기

    풀이설명:
    예제 3번)
    idx = 0
    4 > 1 유지  4177252841 idx++ (idx = 1)
    1 < 7 1제거 477252841  idx-- (idx = 0)    k--
    4 < 7 4제거 77252841   idx   (idx = 0)    k-- (이미 0이라서)
    7 = 7 유지  77252841   idx++ (idx = 1)
    7 > 2 유지  77252841   idx++ (idx = 2)
    2 < 5 2제거 7752841    idx-- (idx = 1)    k--
    7 > 5 유지  7752841    idx++ (idx = 2)
    5 > 2 유지  7752841    idx++ (idx = 3)
    2 < 8 2제거 775841     idx-- (idx = 2)    k--

    p.s
    - 다시 풀어봤는데, 가장큰수를 만들수 있는 규칙을 찾는데 오래걸렸음.
    - 풀이를 구현하는데는 오래걸리지 않았음.
    - 이렇게 보니까 이전과 똑같이 풀이했다는게 신기하다.
    - 그래도 제한시간내에 두문제 푼것에 박수. 짞짞.(근데, 둘다 풀어본 문제다.)
*/
    public static String solution2(String number, int k) {
        StringBuilder sb = new StringBuilder().append(number);

        int idx = 0;    // 확인하려는 인덱스와 뒷쪽.
        // k == 0 이거나 number의 길이를 넘어가면 정지
        while (k > 0 && idx < sb.length() - 1) {
            if (sb.charAt(idx) < sb.charAt(idx + 1)) {
                sb.deleteCharAt(idx);
                k--;
                if (idx > 0) idx--;
            } else {
                idx++;
            }
        }

        if (k > 0) sb.deleteCharAt(idx);

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(solution("1924", 2));
        System.out.println(solution("1231234", 3));
        System.out.println(solution("4177252841", 4));
        System.out.println(solution("10", 1));
        System.out.println(solution2("1924", 2));
        System.out.println(solution2("1231234", 3));
        System.out.println(solution2("4177252841", 4));
        System.out.println(solution2("10", 1));
    }
}
