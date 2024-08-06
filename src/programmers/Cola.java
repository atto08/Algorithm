package programmers;

/*
콜라 문제 - level1
소요 시간: 29분
 */
public class Cola {
    public int solution(int a, int b, int n) {
        // 남아 있는 빈병, 받은 콜라
        int bottle = n, coke = 0;

        coke += (n / a) * b; // 새로 받은 콜라
        bottle -= (n / a) * a; // 빈병 반납

        bottle += coke; // 받은 콜라 다 마셔서 빈병 추가

        while (bottle >= a) {

            int cola = (bottle / a) * b;
            bottle -= (bottle / a) * a;

            bottle += cola;
            coke += cola;
        }
        return coke;
    }
}