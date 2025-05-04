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

    public static void main(String[] args) {
        System.out.println(solution("1924", 2));
        System.out.println(solution("1231234", 3));
        System.out.println(solution("4177252841", 4));
    }
}
