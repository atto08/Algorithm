package programmers.lv1;

/*
크기가 작은 부분 문자열 - lv1
소요시간 - 15분

풀이설명:
- t를 p의 길이만큼 되는 경우를 모두 구하고 그 중에서 p보다 큰 경우를 고르기

p.s
- 런타임 에러 발생
-> p는 최대 18자리 가능 --> int 말고 long 타입
*/
public class Program_147355 {
    public static int solution(String t, String p) {
        int result = 0;

        int L = t.length() - p.length();
        StringBuilder sb;
        long pNum = Long.parseLong(p);
        long tNum;
        for (int i = 0; i <= L; i++) {
            sb = new StringBuilder();
            sb.append(t, i, i + p.length());
            tNum = Long.parseLong(sb.toString());
            if (pNum >= tNum) result++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution("3141592", "271"));
        System.out.println(solution("500220839878", "7"));
        System.out.println(solution("10203", "15"));
    }
}
