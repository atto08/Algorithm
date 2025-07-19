package programmers.lv1;
/*
붕대감기 - lv1
소요시간 - 39분
문제설명:
- 몬스터의 모든 공격이 끝난 직후의 체력을 구하기

- t초 동안 붕대 감을때 -> 1초마다 x만큼 체력회복
- t초 연속 붕대 감기 성공 -> y만큼 추가 체력회복
- 중간에 몬스터에게 공격 -> 기술 취소

- 몬스터에게 공격당해 기술이 취소당하거나 기술이 끝남 -> 그 즉시 붕대감기 사용 (연속 성공 == 0) 초기화
- 몬스터의 공격에는 정해진 피해량 존재. -> 체력 - 피해량
- 체력 == 0 -> 죽음 == 회복불가
- bandage = {시전시간(t)?, 1초당회복량(x), 추가회복량(y)}
    - t초 연속 성공하면 + (x + y) & 연속 성공 초기화
- health = 최대 체력
- attacks = {{몬스터 공격시간1, 피해량1},{몬스터 공격시간2, 피해량2},...}

풀이설명:
- health는 최대체력으로, hp는 현재체력(증감하는 변수)
- 몬스터의 마지막 공격(lastAttackTime)이 끝나거나 hp가 0이 되는 경우까지 시간 증가하면서 확인
    - 몬스터 공격 여부 체크
        - 공격받을 타이밍 -> hp감소 + 현재 진행중인 공격의 인덱스 증가 + 연속성공 횟수 초기화
        - 공격받지 않는 타이밍 -> hp증가 + 연속성공횟수 증가
            - 연속횟수 == 시전시간(t) -> hp 추가 보상 + 연속성공 횟수 초기화

p.s
- 이제 lv1쪽의 구현문제는 정답률이 50이하 문제여도 꽤 푸는거 같다.
- 이제 lv2를 하나씩 섞어서 도전해봐야지.
- 오늘 푼 두문제 80분 안걸리는거보니 기업들이 제공한 시간이 적절하구나 깨달았다.
- 실전에서 쫄지만 말자 화이팅
*/

public class Program_250137 {
    public static int solution(int[] bandage, int health, int[][] attacks) {
        int hp = health, sec = 0; // 현재 체력, 시간(초)
        int lastAttackTime = attacks[attacks.length - 1][0]; // 몬스터 마지막 공격시간(초)

        int aIdx = 0;   // 몬스터 공격 여부 인덱스
        int cs = 0;     // 연속 성공 갯수

        // hp == 0 or 몬스터의 마지막 공격을 받은 시간이면 종료
        while (sec <= lastAttackTime && hp > 0) {
            // 몬스터 공격 여부 체크
            if (attacks[aIdx][0] == sec) {   // 공격 받는 시간
                hp -= attacks[aIdx][1];
                aIdx++;
                cs = 0;
            } else {    // 공격 받지 않는 시간
                hp += bandage[1];
                cs++;
                if (cs == bandage[0]) { // 연속 성공 초기화 및 보상
                    cs = 0;
                    hp += bandage[2];
                }

                if (hp > health) hp = health;

            }

            sec++; // 시간++
        }

        if (hp <= 0) hp = -1;

        return hp;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{5, 1, 5}, 30, new int[][]{{2, 10}, {9, 15}, {10, 5}, {11, 5}}));
        System.out.println(solution(new int[]{3, 2, 7}, 20, new int[][]{{1, 15}, {5, 16}, {8, 6}}));
        System.out.println(solution(new int[]{4, 2, 7}, 20, new int[][]{{1, 15}, {5, 16}, {8, 6}}));
        System.out.println(solution(new int[]{1, 1, 1}, 5, new int[][]{{1, 2}, {3, 2}}));
    }
}