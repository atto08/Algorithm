package programmers;
/*
예상 대진표 - level2
소요 시간: 28분
 */
public class Program_12985 {
    public int solution(int n, int a, int b) {
        int round = 1;

        int ra = checkOddNum(a);
        int rb = checkOddNum(b);

        while (true) {
            int na = ra / 2;
            int nb = rb / 2;

            if (na == nb) break;

            ra = checkOddNum(na);
            rb = checkOddNum(nb);

            round++;
        }
        return round;
    }

    private int checkOddNum(int num) {
        if (num % 2 != 0) return num + 1;
        return num;
    }
}
