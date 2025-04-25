package programmers.lv1;

import java.util.*;

/*
문자열 내 마음대로 정렬하기 - lv1
소요시간 - 40분

풀이설명:
- 우선순위 큐 활용
- compareTo 메서드 구현

p.s
- String.compareTo & Character.compare 처음 써봄. 차이를 새로 배웠다.
- str1.compareTo(str2)
    - 두 문자열을 사전식으로 비교합니다.
    - 비교는 문자열의 각 문자에 대한 유니코드 값을 기반
    - 이 String 객체가 나타내는 문자 시퀀스는 인수 문자열이 나타내는 문자 시퀀스와 사전식으로 비교됩니다.
    - 이 String 객체가 인수 문자열보다 사전식으로 앞에 있으면 결과는 음의 정수
    - 이 String 객체가 인수 문자열보다 사전식으로 뒤에 있으면 결과는 양의 정수
    - 문자열이 같으면 결과는 0

- char1.compare(char2)
    -> 유니코드 유닛 값 비교 return (int 생략) char1 - char2;
*/
public class Program_12915 {
    public static String[] solution(String[] strings, int n) {
        String[] result = new String[strings.length];
        PriorityQueue<StrSort> pq = new PriorityQueue<>();
        for (String str : strings) pq.offer(new StrSort(str, n));

        for (int i = 0; i < strings.length; i++) result[i] = pq.poll().str;

        return result;
    }

    static class StrSort implements Comparable<StrSort> {
        String str;
        char c;

        public StrSort(String str, int idx) {
            this.str = str;
            this.c = str.charAt(idx);
        }

        @Override
        public int compareTo(StrSort other) {
            if (this.c == other.c) return this.str.compareTo(other.str);
            return Integer.compare(this.c, other.c);
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"sun", "bed", "car"}, 1)));
        System.out.println(Arrays.toString(solution(new String[]{"abce", "abcd", "cdx"}, 2)));
    }
}