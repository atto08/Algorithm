package programmers.lv2;

import java.util.Arrays;

/*
가장 큰 수 - lv2
소요시간 - 1시간 초과
분류 - 정렬

문제 설명:
- 0 또는 양의 정수를 이어 붙여 만들 수 있는 가장 큰 수 구하기
- 0 또는 양의 정수가 담긴 배열 numbers
    - 1 <= numbers.length <= 100,000
    - 0 <= numbers[i] <= 1,000
풀이 방법:
- numbers[i](배열 원소)는 최대 1,000 & numbers 최대 개수는 100,000개
 - 이론상 자릿 수는 40만 자리 가능 ==> 1000(4자리) 10만개 붙이면 4 * 10^6
 - 즉 조합가능한 경우를 전부 볼 수는 X

1차 시도 - 50분 소요(60%)
 - 이중 for문을 사용
    - numbers[i]를 numbers[j]와 비교
    - a = numbers[i] + numbers[j]와 b = numbers[j] + numbers[i]를 비교
    - b > a 라면 numbers[i] = numbers[j]로 교체
    - a > b 라면 변경 x
    - 다음 인덱스 numbers[j+1] 과 비교
 1차 결과
 - 1,2,3,5,6 케이스 -> 시간초과
 - 11 케이스 -> 실패

2차 시도 - 참고
 - 1) 정수형 배열 -> 문자열 배열 전환
 - 2) 문자열 compareTo 메서드 -> 사전순 비교 수행 (숫자 포함 문자 기준)
    - (a,b) -> (b+a).compareTo(a+b); --> 람다식 비교 로직
    - ex) a="3", b="34"
    - 매개변수 a와 b를 기준으로 b+a="343", a+b="334"
        - b+a > a+b ==
            - true -> 양수 반환 -> a와 b의 순서 변환 x
            - false -> 음수 반환 -> a와 b의 순서 변환 o
                - (b가 a보다 앞에 있어야 한다는 뜻 → 즉, “3”이 “30”보다 앞)
    - * 각 숫자 간의 상대적인 순서가 확정되면 정렬 종료!!

 - 3) 졍렬된 배열 연결
 - 4) numbers 전부 "0"만 갖고 있다면(sb.toString().charAt(0) == '0') "0" 반환
    - ex) {0, 0, 0} → "000"이 아니라 "0" 반환
 2차 결과
 - All Pass
 - TimSort 내부 동작과정 이해!!
*/
public class Program_42746 {
    public String solution(int[] numbers) {
        StringBuilder sb = new StringBuilder();

        // 1) 정수형 배열 -> 문자열 배열 변환
        String[] strNumbers = Arrays.stream(numbers)
                .mapToObj(String::valueOf)  // 숫자 -> 문자열 변환
                .toArray(String[]::new);    // 문자열 배열로 변환

        // 2) 정렬 기준 정의
        Arrays.sort(strNumbers, (a, b) -> (b + a).compareTo(a + b)); // 두 숫자 a, b에 대해 (b + a) vs (a + b) 비교

        // 3) 정렬된 문자열을 하나로 합치기
        for (String num : strNumbers) {
            sb.append(num);
        }

        // 4) "0"만 존재하는 경우 처리
        if (sb.charAt(0) == '0') {
            return "0";
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Program_42746 pg = new Program_42746();
        System.out.println(pg.solution(new int[]{3, 30, 34, 5, 9}));
    }
}
