package programmers.lv1;

/*
유연근무제 - lv1
소요시간 - 50분

문제설명:
- 직원 n명이 설정한 출근 희망 시각을 담은 1차원 정수 배열 schedules
- 직원들이 일주일 동안 출근한 시각을 담은 2차원 정수 배열 timelogs
- 이벤트를 시작한 요일을 의미하는 정수 startday

풀이설명:
- startday부터 주말전까지만 확인하기?
- 0 ~ timelogs[i].length까지
- timelogs[startday] ~ < 6

1차시도 - 실패(20점)
- 50분에서 59분인 경우의 출근목표시간이면 +10 더하면
- ex) 10:59 -> 1059 + 10 => 1069가 됨
해결 - 100으로 나눈 나머지가 60이상이면 +40(+100{+1시간} -60{분에서 차감}) 해줘야함

2차시도 - 실패(35점)
- timelogs는 무조건 월~일 순으로 주어진다고 생각함
- 그래서 5,6 인덱스를 계산하지않음.
- 예시를 보면 startday에 따라서 주말은 달라짐.
ex) startday -> 제외
    7 -> 0 6
    1 -> 5 6
    2 -> 4 5
    3 -> 3 4
    4 -> 2 3
    5 -> 1 2
    6 -> 0 1

해결 - startday에 따라서 주말인 부분을 체크하고 평일인 경우만 계산하도록 진행

3차시도 - 성공

p.s
- 예시만 제대로 봤어도 더 빨리 풀 수 있는 구현문제였다.
- 다시 잘해보자. 유형별 학습 다시 정진.
*/
public class Program_388351 {
    public static int solution(int[] schedules, int[][] timelogs, int startday) {

        int result = timelogs.length;
        boolean[] weekend = new boolean[7];
        isWeekend(weekend, startday);   // 주말 여부 판별
        for (int i = 0; i < timelogs.length; i++) {
            boolean isLate = false;
            int goalTime = schedules[i] + 10;
            // ex) 10:59 + 10 -> 11:09 => 1069
            if (goalTime % 100 >= 60) goalTime += 40;

            for (int j = 0; j < 7; j++) {
                if (weekend[j]) continue;
                if (goalTime < timelogs[i][j]) isLate = true;
                if (isLate) {
                    result--;
                    break;
                }
            }
        }
        return result;
    }

    private static void isWeekend(boolean[] weekend, int day) {
        if (day == 1) {
            weekend[5] = true;
            weekend[6] = true;
        } else if (day == 2) {
            weekend[4] = true;
            weekend[5] = true;
        } else if (day == 3) {
            weekend[3] = true;
            weekend[4] = true;
        } else if (day == 4) {
            weekend[2] = true;
            weekend[3] = true;
        } else if (day == 5) {
            weekend[1] = true;
            weekend[2] = true;
        } else if (day == 6) {
            weekend[0] = true;
            weekend[1] = true;
        } else {
            weekend[0] = true;
            weekend[6] = true;
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{700, 800, 1100}, new int[][]{{710, 2359, 1050, 700, 650, 631, 659}, {800, 801, 805, 800, 759, 810, 809}, {1105, 1001, 1002, 600, 1059, 1001, 1100}}, 5));
        System.out.println(solution(new int[]{730, 855, 700, 720}, new int[][]{{710, 700, 650, 735, 700, 931, 912}, {908, 901, 805, 815, 800, 831, 835}, {705, 701, 702, 705, 710, 710, 711}, {707, 731, 859, 913, 934, 931, 905}}, 1));
    }
}