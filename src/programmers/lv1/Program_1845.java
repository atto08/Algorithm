package programmers.lv1;

import java.util.*;

/*
폰켓몬 - lv1
소요시간 - 13분

N마리 중 N/2마리 선택시
다양한 종류의 포켓몬을 선택할 수 있는 포켓몬 종류 수

[풀이설명]
- 해시맵에 포켓몬(번호)별 개체수를 카운트
- (N/2) < 포켓몬 종류 수(해시맵의 크기)
    - o -> N/2 출력
    - x -> 포켓몬 종류 수 출력
*/
public class Program_1845 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{3, 1, 2, 3}));
        System.out.println(solution(new int[]{3, 3, 3, 2, 2, 4}));
        System.out.println(solution(new int[]{3, 3, 3, 2, 2, 2}));
    }

    public static int solution(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (!map.containsKey(n)) {
                map.put(n, 1);
                continue;
            }

            map.replace(n, map.get(n) + 1);
        }

        int N = nums.length / 2;

        // return Math.min(N, map.size()); -> 대체 가능
        if (N >= map.size()) return map.size();

        return N;
    }
}
